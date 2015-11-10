package hw4;

public class Car {

private String name;
private String number;
private int doorCount;
private Color color;
private int maxSpeed;
private boolean isMoving;
private boolean isLights;

public Car() {
	name = "Lada";
	number = "KF 2203 A0";
	doorCount = 4;
	color = Color.BLACK;
	maxSpeed = 200;
	isMoving = false;
	isLights = false;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

public Color getColor() {
	return color;
}

public void setColor(Color color) {
	this.color = color;
}

public int getMaxSpeed() {
	return maxSpeed;
}

public void setMaxSpeed(int maxSpeed) {
	this.maxSpeed = maxSpeed;
}
public void startEngine() {
	isMoving = true;
}
public void stopEngine() {
	isMoving = false;
}
public void turnOnLights() {
	isLights = true;
}
public void turnOffLights() {
	isLights = false;
}
}
