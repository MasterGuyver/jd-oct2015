package com.TanksOOP;

/**
 * Created by Guyver on 13.11.15.
 */
public class Tiger extends Tank {
    private int armor = 1;
    public Tiger() {
        super("Orange",4,70);
    }
    public Tiger(String color,int crew,int maxSpeed,int armor) {
        super(color,crew,maxSpeed);
        if(armor > 0) {
            this.armor = armor;
        }
            }
    public void setArmor(int armor)  {
        this.armor = armor;
    }
    public int getArmor() {
        return this.armor;
    }
    public void printInformation() {
        super.printInfo();
        System.out.println("Armor: "+getArmor());
    }
}
