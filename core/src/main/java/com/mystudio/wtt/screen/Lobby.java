package com.mystudio.wtt.screen;


import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextBox;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;


public class Lobby extends Screen{
      public final static int ID = 2;
      private Visibility v = Visibility.VISIBLE ;
      private Visibility h = Visibility.HIDDEN ;
      
      @Override
      public void initialise(GameContainer gc){
            this.newname(gc);
            TextBox a = new TextBox(0,0,400,100);
            TextBox b = new TextBox(0,200,400,100);
            uiContainer.add(a);
            uiContainer.add(b);
            a.setVisibility(v);
            b.setVisibility(v);
            a.addActionListener(new ActionListener(){
                  @Override
                  public void onActionEnd(ActionEvent event){
                        System.out.println(a.getValue());  
                  }
                  
                  @Override
                  public void onActionBegin(ActionEvent event){
                        
                  }
            });
            b.addActionListener(new ActionListener(){
                  @Override
                  public void onActionEnd(ActionEvent event){
                        System.out.println(b.getValue());  
                        screenToLoad = Lobby1.ID;
                        b.setVisibility(h);
                        a.setVisibility(h);
                  }
                  
                  @Override
                  public void onActionBegin(ActionEvent event){
                        
                  }
            });
      }

      @Override
      public int getId(){
            return ID;
      }
}