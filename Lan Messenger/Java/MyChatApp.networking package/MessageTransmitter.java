package mychatapp.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>MessageTransmitter</h1>
 * <p>
 * Creates an object to send a message to a specified port
 * @author Andrew Windfeldt
 * @version 1.0
 * @since 2017-11-24
 */
public class MessageTransmitter extends Thread{
    String message, hostname;
    int port;
    WriteableGUI gui;
    public MessageTransmitter(){
        
    }
    /**
     * Constructor of class
     * @param message line of text to be sent
     * @param hostname name of IP address to send the message
     * @param port number of the port to be sent to
     * @param gui 
     */
    public MessageTransmitter(String message, String hostname, int port, WriteableGUI gui){
        this.message = message;
        this.hostname = hostname;
        this.port = port;
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(hostname, port);
            s.getOutputStream().write(message.getBytes());
            s.close();
            gui.write("You: "+message);
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

