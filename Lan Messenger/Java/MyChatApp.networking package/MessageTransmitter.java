package mychatapp.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageTransmitter extends Thread{
    String message, hostname;
    int port;
    WriteableGUI gui;
    public MessageTransmitter(){
        
    }
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
            gui.write("->"+message);
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
