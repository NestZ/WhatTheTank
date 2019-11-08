package com.mystudio.wtt.server;

import java.net.SocketException;

public class ServerStarter{
      private ServerThread server;

      public ServerStarter(){
            try{
                  server = new ServerThread();
            }
            catch(SocketException e){
                  e.printStackTrace();
            }
            this.server.start();
      }
}