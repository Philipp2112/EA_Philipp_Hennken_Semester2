package org.example.client;


public class Main
{
    public static void main(String[] args)
    {
        Thread myClient = new Thread(new Client());
        myClient.start();
    }
}
