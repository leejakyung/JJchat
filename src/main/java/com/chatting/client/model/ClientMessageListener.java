package com.chatting.client.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientMessageListener extends Thread {
	
	private static final Logger logger = LogManager.getLogger(ClientMessageListener.class);

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
                logger.info(msg);

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
