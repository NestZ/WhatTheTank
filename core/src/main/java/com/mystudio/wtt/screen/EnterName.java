package com.mystudio.wtt.screen;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextBox;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import org.mini2Dx.ui.listener.TextInputListener;

public class EnterName extends Screen{
      public static final int ID = 1;

      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            TextBox nameBox = new TextBox(0, 0, 400, 50);
            TextButton ok = new TextButton(0, 50, 400, 50);
            nameBox.setVisibility(Visibility.VISIBLE);
            nameBox.addTextInputListener(new TextInputListener(){
                  @Override
                  public boolean textReceived(char c){
                        if(nameBox.getValue().length() < 6)return true;
                        return false;
                  }
            });
            ok.setText("Ok");
            ok.setVisibility(Visibility.VISIBLE);
            this.uiContainer.add(nameBox);
            this.uiContainer.add(ok);
            ok.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        Lobby.myName = nameBox.getValue();
                        screenToLoad = SelectRole.ID;
                  }
            });
      }

      @Override
      public int getId(){
            return ID;
      }
}