package com.company;

/**
 * Created by Guyver on 09.11.15.
 */
public class Tank {
    String color;
    int crew;
    int maxSpeed;
    Tank(String color, int crew, int maxSpeed) {
		this.color = color;
		this.crew = crew;
		this.maxSpeed = maxSpeed;
	}

    void printTankInfo() {
        System.out.println("Tank Characteristics.");
        System.out.println("Color: "+color);
        System.out.println("Color: "+crew);
        System.out.println("Color: "+maxSpeed);
    }
}
