package com.mystudio.wtt.screen;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;


public class MainMenu extends Screen {
      
      public final static int ID = 1;
      private Visibility v = Visibility.VISIBLE ;
      private Visibility h = Visibility.HIDDEN ;

      @Override
      public void initialise(GameContainer gc){
            this.newname(gc);
            TextButton newGameButton = new TextButton(0, 0, 400, 100); 
            newGameButton.setText("Start");
            uiContainer.add(newGameButton);
            newGameButton.setVisibility(v);
            newGameButton.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        screenToLoad = Lobby.ID;
                        newGameButton.setVisibility(h);
                  }
            });
      }

      public int getId(){
            return ID;
      }
}