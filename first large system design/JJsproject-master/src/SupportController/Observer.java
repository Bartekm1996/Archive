/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SupportController;

import DatabaseController.ResultDB;

public abstract class Observer {
   protected ResultDB house;
   public abstract void pay(String winner);
}