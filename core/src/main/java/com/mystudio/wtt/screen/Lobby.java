package com.mystudio.wtt.screen;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import com.mystudio.wtt.WhatTheTank;
import com.mystudio.wtt.client.ClientStarter;
import com.mystudio.wtt.entity.Map;
import com.mystudio.wtt.entity.Map.Point;
import com.mystudio.wtt.entity.tank.Tank;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;

public class Lobby extends Screen{
      private Map map;
      public static final int ID = 4;
      public static boolean isHost;
      public static String myName;
      private static HashSet<Client> blueTeam = new HashSet<>();
      private static HashSet<Client> redTeam = new HashSet<>();

      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            this.map = new Map();
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
                        if(Lobby.isHost){
                              HashMap<Integer, Tank> tanks = new HashMap<>();
                              Iterator<Client> it = blueTeam.iterator();
                              this.mapToTank(it, tanks);
                              it = redTeam.iterator();
                              this.mapToTank(it, tanks);
                              WhatTheTank.tanks = tanks;
                              WhatTheTank.initField();
                              screenToLoad = WhatTheTank.ID;
                        }
                        else{
                              System.out.println("You are client");
                        }
                  }

                  private void mapToTank(Iterator<Client> it, HashMap<Integer, Tank> tanks){
                        while(it.hasNext()){
                              Client c = it.next();
                              Point initPos = map.getPos(c.team);
                              try{
                                    tanks.put(c.ID, new Tank(initPos.getX(), initPos.getY(), c.team, ClientStarter.clientID(), c.name));
                              }
                              catch(IOException e){
                                    e.printStackTrace();
                              }
                        }
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

      public static class Client{
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