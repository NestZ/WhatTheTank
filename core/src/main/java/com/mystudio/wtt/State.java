package com.mystudio.wtt;

import java.util.Stack;

public class State{
      public enum state{
            MENU,
            OPTION
      }
      private Stack<state> stack;

      public void push(state s){
            stack.push(s);
      }

      public void pop(){
            stack.pop();
      }

      public state top(){
            return stack.lastElement();
      }
}