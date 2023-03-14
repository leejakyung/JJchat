package com.chatting.client.model;

public class ClientMessageListener extends Thread {

    private Client client;

    public ClientMessageListener(Client client){
      this.client = client;
    }

    @Override
    public void run() {

        boolean isStop = false;
        while (client.isAlive()) {
            try {
                String msg = client.getOis().readObject().toString();
                System.out.println(msg);

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
