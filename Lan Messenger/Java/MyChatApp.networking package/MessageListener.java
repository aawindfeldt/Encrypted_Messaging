package mychatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *<h1>MessageListener</h1>
 * <p>
 * Class creates an object to listen for an 
 * incoming message from specified port
 * @author Andrew Windfeldt
 * @version 1.0
 * @since 2017-11-24
 */
public class MessageListener extends Thread{
    ServerSocket server;
    int port = 8877;
    WriteableGUI gui;
    /**
     * Constructor of the MessageLstener class that listens to specified port
     * @param gui GUI to print to 
     * @param port number ID of port being created
     */
    public MessageListener(WriteableGUI gui, int port){
        this.port = port;
        this.gui = gui;
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Overloaded constructor that uses default value for port
     */
    public MessageListener(){
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        Socket clientSocket;
        try {
            while((clientSocket = server.accept()) != null){
                InputStream is = clientSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                if(line != null){
                    gui.write("Friend: "+line);
                }
            }
                } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

