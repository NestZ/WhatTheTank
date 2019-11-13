package com.mystudio.wtt.screen;

import java.io.IOException;
import java.net.SocketException;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import com.mystudio.wtt.client.ClientStarter;
import com.mystudio.wtt.server.ServerThread;

public class SelectRole extends Screen{
      public final static int ID = 2;
      
      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            TextButton selHost = new TextButton(0, 0, 400, 50);
            TextButton selClient = new TextButton(0, 50, 400, 50);
            selHost.setText("Host");
            selHost.setVisibility(Visibility.VISIBLE);
            selClient.setText("Client");
            selClient.setVisibility(Visibility.VISIBLE);
            this.uiContainer.add(selHost);
            this.uiContainer.add(selClient);
            selHost.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        try{
                              new ServerThread().start();
                              try{
                                    new ClientStarter("127.0.0.1").start();
                                    screenToLoad = Lobby.ID;
                              }
                              catch(IOException e){
                                    e.printStackTrace();
                              }
                        }
                        catch(SocketException e){
                              e.printStackTrace();
                        }
                  }
            });
            selClient.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        screenToLoad = Connector.ID;
                  }
            });
      }

      @Override
      public int getId(){
            return ID;
      }
}