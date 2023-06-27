package com.chatting.client.core;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ClientReceiver receiver;

    public Client(String ip, int port) {
        try{
            this.socket = new Socket(ip, port);
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
            receiver = new ClientReceiver(this);
            receiver.start();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void initialize() {

    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public boolean isAlive(){
        return !socket.isInputShutdown();
    }
}
