package com.mystudio.wtt.screen;

import java.util.HashSet;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;

public class Lobby extends Screen{
      public static final int ID = 4;
      public static String myName;
      public static HashSet<Client> blueTeam = new HashSet<>();
      public static HashSet<Client> redTeam = new HashSet<>();

      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            TextButton see = new TextButton(0, 0, 400, 50);
            TextButton start = new TextButton(0, 50, 400, 50);
            see.setText("See");
            see.setVisibility(Visibility.VISIBLE);
            start.setText("Start");
            start.setVisibility(Visibility.VISIBLE);
            this.uiContainer.add(see);
            this.uiContainer.add(start);
            see.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        System.out.println("blue : " + Lobby.blueTeam.size());
                        System.out.println("red : " + Lobby.redTeam.size());
                  }
            });
            start.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){

                  }
            });
      }

      public static void addMember(String name, int team, int ID){
            if(team == 1)blueTeam.add(new Lobby.Client(name, team, ID));
            else if(team == 2)redTeam.add(new Lobby.Client(name, team, ID));
      }

      @Override
      public int getId(){
            return ID;
      }

      private static class Client{
            private String name;
            private int team;
            private int ID;

            private Client(String name, int team, int ID){
                  this.name = name;
                  this.team = team;
                  this.ID = ID;
            }
      }
}