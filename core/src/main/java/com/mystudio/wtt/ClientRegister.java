package com.mystudio.wtt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRegister{
      private Socket clientSocket;
      // public String hostName;
      // public int serverPort;
      // public DataInputStream reader;
      private DataOutputStream writer;
      private static ClientRegister client;

      public ClientRegister() throws IOException{

      }

      public void register(String hostName, int serverPort, int x, int y, int dir) throws IOException{
            // this.hostName = hostName;
            // this.serverPort = serverPort;
            this.clientSocket = new Socket(hostName, serverPort);
            this.writer = new DataOutputStream(clientSocket.getOutputStream());
            this.writer.writeUTF("hello" + x + "," + y + ":" + dir);
      }

      // public void sendToServer(String str){
      //       try{
      //             Socket s = new Socket(this.hostName, this.serverPort);
      //             this.writer = new DataOutputStream(s.getOutputStream());
      //             this.writer.writeUTF(str);
      //       }
      //       catch(IOException e){
      //             e.printStackTrace();
      //       }
      // }

      public static ClientRegister getInstance(){
            try{
                  client = new ClientRegister();
            }
            catch(IOException e){
                  e.printStackTrace();
            }
            return client;
      }
}