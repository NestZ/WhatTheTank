package com.mystudio.wtt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
      private boolean isRunning = true;
      private BufferedReader reader;
      
      public ClientThread(Socket clientSocket) throws SocketException{
            try{
                  this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }
            catch(IOException e){
                  e.printStackTrace();
            }
            System.out.println("Client Created");
      }

      public void run(){
            System.out.println("Client Started");
            while(this.isRunning){
                  String command = "";
                  try{
                        command = reader.readLine();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
                  if(command != "")System.out.println(command);
            }
      }
}