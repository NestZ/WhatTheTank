package com.mystudio.wtt.screen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mystudio.wtt.WhatTheTank;
import com.mystudio.wtt.client.ClientThread;
import com.mystudio.wtt.client.Protocol;
import com.mystudio.wtt.entity.Map;
import com.mystudio.wtt.utils.Point;
import com.mystudio.wtt.entity.tank.Tank;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.element.Label;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import org.mini2Dx.ui.style.UiTheme;

public class Lobby extends Screen{
      private float currClock = 5f;
      private Map map = new Map();
      public static final int ID = 4;
      public static boolean isHost;
      public static boolean isStart = false;
      public static String myName;
      private static HashSet<Client> blueTeam = new HashSet<>();
      private static HashSet<Client> redTeam = new HashSet<>();
      private Label teamA;
      private Label teamB;

      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
            TextButton see = new TextButton(0, 0, 400, 50);
            TextButton start = new TextButton(855, 620, 200, 50);
            teamA = new Label(400, 150, 200, 200);
            teamB = new Label(1400, 150, 200, 200);
            teamA.setColor(Color.GOLDENROD);
            teamB.setColor(Color.GOLDENROD);
            teamA.setVisibility(Visibility.VISIBLE);
            teamB.setVisibility(Visibility.VISIBLE);
            see.setText("See");
            see.setVisibility(Visibility.HIDDEN);
            start.setText("Start");
            start.setVisibility(Visibility.VISIBLE);
            this.uiContainer.add(see);
            this.uiContainer.add(start);
            this.uiContainer.add(teamA);
            this.uiContainer.add(teamB);
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
                              ClientThread.sendToServer(Protocol.startPackage());
                        }
                        else{
                              System.out.println("You are client");
                        }
                  }
            });
            InetAddress ip;
            String hostname;
            try{
                ip = InetAddress.getLocalHost();
                hostname = ip.getHostName();
                System.out.println("Your current IP address : " + ip);
                System.out.println("Your current Hostname : " + hostname);
     
            }catch(UnknownHostException e){
                e.printStackTrace();
            }
      }

      @Override
      public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta){
            Iterator<Client> it = blueTeam.iterator();
            this.teamA.setText("Team A\n\n");
            this.teamB.setText("Team B\n\n");
            while(it.hasNext()){
                  this.teamA.setText(this.teamA.getText() + it.next().name + "\n");
            }
            it = redTeam.iterator();
            while(it.hasNext()){
                  this.teamB.setText(this.teamB.getText() + it.next().name + "\n");
            }
            if(!this.assetManager.update()){
                  return;
            }
            if(!UiContainer.isThemeApplied()){
                  UiContainer.setTheme(this.assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
            }
            this.uiContainer.update(delta);
            if (this.screenToLoad != 0){
                  screenManager.enterGameScreen(this.screenToLoad, new NullTransition(), new NullTransition());
                  this.screenToLoad = 0;
            }
            Gdx.input.setInputProcessor(this.uiContainer);
            if(Lobby.isStart){
                  if(this.currClock > 0f){
                        this.currClock -= delta;
                        System.out.println((int) this.currClock);
                  }
                  else{
                        this.startGame();
                        Lobby.isStart = false;
                  }
            }
      }

      @Override
      public int getId(){
            return ID;
      }

      private void startGame(){
            HashMap<Integer, Tank> tanks = new HashMap<>();
            Iterator<Client> it = blueTeam.iterator();
            this.mapToTank(it, tanks);
            it = redTeam.iterator();
            this.mapToTank(it, tanks);
            WhatTheTank.tanks = tanks;
            WhatTheTank.initField(this.map);
            screenToLoad = WhatTheTank.ID;
      }

      private void mapToTank(Iterator<Client> it, HashMap<Integer, Tank> tanks){
            while(it.hasNext()){
                  Client c = it.next();
                  Point<Float> initPos = this.map.getPos(c.team);
                  tanks.put(c.ID, new Tank(initPos.getX(), initPos.getY(), c.team, c.ID));
            }
      }

      public static void addMember(String name, int team, int ID){
            if(team == 1)blueTeam.add(new Lobby.Client(name, team, ID));
            else if(team == 2)redTeam.add(new Lobby.Client(name, team, ID));
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