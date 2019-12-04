package com.mystudio.wtt.screen;



import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;

public class MainMenu extends Screen {
      public final static int ID = 0;
 

      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            
            TextButton startButton = new TextButton(810, 640, 300, 50);
            TextButton quitButton = new TextButton(860, 695, 200, 50);

            startButton.setText("Start");
            startButton.setVisibility(Visibility.VISIBLE);
            quitButton.setText("Quit");
            quitButton.setVisibility(Visibility.VISIBLE);
            this.uiContainer.add(startButton);
            this.uiContainer.add(quitButton);
            
            startButton.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        screenToLoad = EnterName.ID;
                  }
            });
            quitButton.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        System.exit(0);
                  }
            });
      }

      public int getId(){
            return ID;
      }
}