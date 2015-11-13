package com.TanksOOP;

/**
 * Created by Guyver on 13.11.15.
 */
public class BT77 extends Tank {
    public BT77() {
        super("Green",4,70);
    }
    public BT77(String color,int crew,int maxSpeed) {
       super(color, crew, maxSpeed);
    }
    @Override
    public void move() {
        System.out.println("BT77 is move");
    }
    @Override
    public String toString() {
        return "Tank is BT77. "+super.toString();
    }
}
