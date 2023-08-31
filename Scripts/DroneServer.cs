// using is import in Java
using System;
using System.Collections;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using UnityEngine;


// : is extends in Java
public class DroneServer : MonoBehaviour
{
    // const is final in JAVA
    private const string HOST_NAME = "127.0.0.1";
    private const int PORT = 10666;
    private const int BUFFER_SIZE = 1024;
    private const string LISTENER_INFO = "Server wartet auf Client-Anfrage...";
    private const string REQUEST_INFO = "Folgende Nachricht vom Client empfangen: ";
    private const string STANDARD_DATA = "Ping vom Server";
    private string clientMessage1;
    private double lidarSensor;
    //private float xKoordinate;
    //private float yKoordinate;
    //private float zKoordinate;
    private Vector3 position;
     private Vector3 velocity;
    private RaycastHit raycast;

    // A SerializeField can be seen in Unity-Editor.
    [SerializeField] private GameObject drone;

    // TCPListener to listen for incomming TCP connection requests.
    // https://learn.microsoft.com/de-de/dotnet/api/system.net.sockets.tcplistener?view=net-7.0	
    private TcpListener tcpListener;

    // Background thread for server workload.
    // https://learn.microsoft.com/de-de/dotnet/api/system.threading.thread?view=net-7.0	
    private Thread tcpListenerThread;

    // Handle to connected TCP client. 	
    // https://learn.microsoft.com/de-de/dotnet/api/system.net.sockets.tcpclient?view=net-7.0
    private TcpClient connectedTcpClient;
    


    // Start is called on the frame when a script is enabled just before any of the Update methods are called the first time.
    // Since Start is only called once, it should be used to initialize things that need to persist
    // throughout the life of the script but should only need to be setup immediately before use.
    void Start()
    {
        // Start background thread 	        	
        this.tcpListenerThread = new Thread(new ThreadStart(ListenForIncommingRequests));
        this.tcpListenerThread.IsBackground = true;
        this.tcpListenerThread.Start();
    }


    // Update is called every frame, if the MonoBehaviour is enabled.
    void Update()
    {
        //yKoordinate = drone.transform.position.y; // Besser in FixedUpdate() --> Framerateunabh�ngig, aufgerufen durch Physik-Engine

        //Debug.Log(yKoordinate);
        // TODO handle shutdown via Escape key
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            ; 
        }

        if (clientMessage1.Contains("W"))
        {
            drone.GetComponent<DroneMovement>().W = true;
        }
        if (clientMessage1.Contains("A"))
        {
            drone.GetComponent<DroneMovement>().A = true;
        }
        if (clientMessage1.Contains("S"))
        {
            drone.GetComponent<DroneMovement>().S = true;
        }
        if (clientMessage1.Contains("X"))
        {
            drone.GetComponent<DroneMovement>().D = true;
        }
        if (clientMessage1.Contains("Up"))
        {
            drone.GetComponent<DroneMovement>().I = true;
        }
        if (clientMessage1.Contains("Down"))
        {
            drone.GetComponent<DroneMovement>().K = true;
        }
        if (clientMessage1.Contains("TurnLeft"))
        {
            drone.GetComponent<DroneMovement>().J = true;
        }
        if (clientMessage1.Contains("TurnRight"))
        {
            drone.GetComponent<DroneMovement>().L = true;
        }

    }

    void FixedUpdate()
    {
        //yKoordinate = drone.transform.position.y;
        //xKoordinate = drone.transform.position.x;
        //zKoordinate = drone.transform.position.z;
        position = drone.transform.position;
        velocity = drone.GetComponent<Rigidbody>().velocity;
        if (Physics.Raycast(drone.transform.position, -Vector3.up, out this.raycast))
                {
                    // Calculate the distance from the drone to the ground
                    this.lidarSensor = raycast.distance -1;
                }
    }


    // https://learn.microsoft.com/de-de/dotnet/api/system.net.sockets.tcplistener?view=net-7.0
    // https://learn.microsoft.com/de-de/dotnet/api/system.net.sockets.tcpclient?view=net-7.0
    private void ListenForIncommingRequests()
    {
        try
        {
            this.tcpListener = new TcpListener(IPAddress.Parse(HOST_NAME),PORT);
            this.tcpListener.Start();
            Debug.Log(LISTENER_INFO);

            Byte[] bytes = new Byte[BUFFER_SIZE];
            while (true)
            {
                using (this.connectedTcpClient = tcpListener.AcceptTcpClient())
                {
                    using (NetworkStream stream = this.connectedTcpClient.GetStream())
                    {
                        int length;
                        while ((length = stream.Read(bytes, 0, bytes.Length)) != 0)
                        {
                            var incommingData = new byte[length];
                            Array.Copy(bytes, 0, incommingData, 0, length);
                            string clientMessage = Encoding.ASCII.GetString(incommingData);
                            Debug.Log(REQUEST_INFO + clientMessage);
                            clientMessage1 = clientMessage;
                            SendMessage();
                        }
                    }
                }
            }
        }
        catch (SocketException socketException)
        {
            Debug.Log(socketException.ToString());
        }
    }


    // https://learn.microsoft.com/de-de/dotnet/api/system.net.sockets.tcpclient?view=net-7.0	
    private void SendMessage()
    {
        if (this.connectedTcpClient != null)
        {
            try
            {
                NetworkStream stream = this.connectedTcpClient.GetStream();
                if (stream.CanWrite)
                {
                    byte[] serverMessageAsByteArray = Encoding.ASCII.GetBytes(GetData());
                    stream.Write(serverMessageAsByteArray, 0, serverMessageAsByteArray.Length);
                    Debug.Log(GetData());
                }
            }
            catch (SocketException socketException)
            {
                Debug.Log(socketException);
            }
        }

        
    }


    private string GetData ()
    {
        // TODO how to simulate data from sensors, actors etc.?
        // https://docs.unity3d.com/ScriptReference/GameObject.html
        // https://docs.unity3d.com/ScriptReference/Vector3.Distance.html
        // https://learn.unity.com/tutorial/let-s-try-shooting-with-raycasts#

        // Drohne bewegen
        // M�glichkeit 1: Kraft auf die Drohne aus�ben: drone.GetComponent<Rigidbody>().AddForce(Vector3.forward * 30f);
        // M�glichkeit 2: Tastendruck simulieren:       drone.GetComponent<DroneMovement>().W = true;
        // (Funktioniert hier beides nicht, kann nur aus dem Main-Thread aufgerufen werden)

        // Aktuelle Flugh�he an Client senden
        //return xKoordinate.ToString() + ";" + yKoordinate.ToString() + ";" + zKoordinate.ToString() + "\n"; // \n is needed for Java line read
        DroneData droneData = new DroneData();
        droneData.position = position;
        droneData.velocity = velocity;
        droneData.lidarSensor = this.lidarSensor;

        
        string jsonString = JsonUtility.ToJson(droneData);
        Debug.Log(jsonString);
        return jsonString + "\n";
    }

    
}

public class DroneData
    {
        public Vector3 position;
        public Vector3 velocity;
        public double lidarSensor;
    }
