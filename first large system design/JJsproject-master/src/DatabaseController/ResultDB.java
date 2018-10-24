/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;

import SupportController.Observer;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Colum
 */
public class ResultDB {
   private final List<Observer> observers;
   private String state;

    public ResultDB() {
        this.observers = new ArrayList<Observer>();
        
    }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
      notifyAllObservers(state);
   }

   public void attach(Observer observer){
      observers.add(observer);		
   }

   public void notifyAllObservers(String Winner){
      for (Observer observer : observers) {
         observer.pay(Winner);
      }
   } 	
}
