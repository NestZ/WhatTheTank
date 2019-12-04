package com.mystudio.wtt.screen;

import com.badlogic.gdx.graphics.Color;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.Label;
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
            TextBox nameBox = new TextBox(765, 645, 350, 50);
            TextButton ok = new TextButton(861, 700, 150, 50);
            Label entername = new Label(875, 620, 150, 50);
            entername.setText("Enter your name");
            entername.setColor(Color.GOLDENROD);
            entername.setVisibility(Visibility.VISIBLE);
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
            this.uiContainer.add(entername);
            ok.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        if(nameBox.getValue().length() > 0){
                              Lobby.myName = nameBox.getValue();
                              screenToLoad = SelectRole.ID;
                        }
                        else System.out.println("Name too short!!");
                  }
            });
      }

      @Override
      public int getId(){
            return ID;
      }
}