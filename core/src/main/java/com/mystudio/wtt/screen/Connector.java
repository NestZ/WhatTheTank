package com.mystudio.wtt.screen;

import java.io.IOException;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextBox;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import com.mystudio.wtt.client.ClientStarter;

public class Connector extends Screen{
      public final static int ID = 3;

      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            TextBox ip = new TextBox(0, 0, 400, 50);
            TextButton joinButton = new TextButton(0, 50, 400, 50);
            ip.setVisibility(Visibility.VISIBLE);
            joinButton.setText("Join");
            joinButton.setVisibility(Visibility.VISIBLE);
            this.uiContainer.add(ip);
            this.uiContainer.add(joinButton);
            joinButton.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        try{
                              new ClientStarter(ip.getValue());
                        }
                        catch(IOException e){
                              e.printStackTrace();
                        }
                  }
            });
      }

      public int getId(){
            return ID;
      }
}