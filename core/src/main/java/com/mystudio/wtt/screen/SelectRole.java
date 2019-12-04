package com.mystudio.wtt.screen;

import java.io.IOException;
import java.net.SocketException;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.Label;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;

import com.badlogic.gdx.graphics.Color;
import com.mystudio.wtt.client.ClientStarter;
import com.mystudio.wtt.server.ServerThread;

public class SelectRole extends Screen{
      public final static int ID = 2;
      
      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            TextButton selHost = new TextButton(650, 660, 150, 50);
            TextButton selClient = new TextButton(1100,660, 150, 50);
            Label selrow = new Label(875, 620, 150, 50);
            
            selrow.setText("SELECT YOUR ROW");
            selrow.setVisibility(Visibility.VISIBLE);
            selrow.setColor(Color.GOLDENROD);
            selHost.setText("Host");
            selHost.setVisibility(Visibility.VISIBLE);
            selClient.setText("Client");
            selClient.setVisibility(Visibility.VISIBLE);
            this.uiContainer.add(selrow);
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
                                    Lobby.isHost = true;
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
                        Lobby.isHost = false;
                        screenToLoad = Connector.ID;
                  }
            });
      }

      @Override
      public int getId(){
            return ID;
      }
}