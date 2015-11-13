package com.TanksOOP;

/**
 * Created by Guyver on 13.11.15.
 */
public class Tank {
    private String color;
    private int crew;
    private int maxSpeed;

    public Tank() {
        this("Red", 4, 120);
    }

    public Tank(String color) {
        this(color, 0, 0);
    }

    public Tank(String color, int crew, int maxSpeed) {
        this.color = color;
        this.crew = crew;
        setMaxSpeed(maxSpeed);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed > 0) {
            if (maxSpeed > 200)
                this.maxSpeed = 200;
            else
                this.maxSpeed = maxSpeed;
        } else {
            this.maxSpeed = 10; // minimum
        }
    }
    public void printInfo() {
        System.out.println("Tank is BT77");
        System.out.print("Tank color is ");
        System.out.println(this.getColor()+" .");
        System.out.print("Crew "+this.getCrew()+" humans. ");
        System.out.println("Speed "+this.getMaxSpeed()+".");
    }
}
