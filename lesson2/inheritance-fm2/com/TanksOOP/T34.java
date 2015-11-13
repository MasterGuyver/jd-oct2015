package com.TanksOOP;

/**
 * Created by Guyver on 13.11.15.
 */
public class T34 extends Tank {
    public T34() {
        super("Dark",8,35);
    }
    public T34(String color,int crew,int maxSpeed) {
        super(color,crew,maxSpeed);
    }
    public void printInformation() {
        super.printInfo();
    }
}
