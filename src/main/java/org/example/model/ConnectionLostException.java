package org.example.model;

public class ConnectionLostException extends Exception
{
    public ConnectionLostException(String s)
    {
        System.out.println(s);
        System.exit(1);
    }
}
