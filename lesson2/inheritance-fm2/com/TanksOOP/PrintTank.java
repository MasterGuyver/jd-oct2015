package com.TanksOOP;

/**
 * Created by Guyver on 13.11.15.
 */
public class PrintTank {
    public static void main(String[] args) {
        BT77 bt = new BT77("Grey",4,80);
        T34 tank = new T34();
        Tiger agressor = new Tiger("Black",8,20,3);
        System.out.println("Tank information!");
        bt.printInformation();
        tank.printInformation();
        agressor.printInformation();
    }
}
