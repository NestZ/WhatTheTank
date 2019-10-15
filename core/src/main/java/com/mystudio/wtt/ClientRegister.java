package com.mystudio.wtt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRegister{
      private Socket clientSocket;
      private String hostName;
      private int serverPort;
      private DataInputStream reader;
      private DataOutputStream writer;
      private static ClientRegister client;

      public ClientRegister() throws IOException{
            
      }

      public void register(String hostName, int serverPort, int x, int y, int dir) throws IOException{
            this.hostName = hostName;
            this.serverPort = serverPort;
            this.clientSocket = new Socket("127.0.0.1", 1234);
            this.writer = new DataOutputStream(clientSocket.getOutputStream());
            this.writer.writeUTF("hello" + x + "," + y + ":" + dir);
      }

      public void sendToServer(String command){
            try{
                  Socket s = new Socket("127.0.0.1", 1234);
                  this.writer = new DataOutputStream(s.getOutputStream());
                  this.writer.writeUTF(command);
            }
            catch(IOException e){
                  System.out.println("can not send to server");
            }
      }

      public static ClientRegister getInstance(){
            if(client == null){
                  try{
                        client = new ClientRegister();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
            return client;
      }
}