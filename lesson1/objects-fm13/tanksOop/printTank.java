package tanksOop;

public class printTank {
	public static void main(String[] args) {
	Tank[] h = new Tank[5];
	for(int i = 0; i<h.length; i++ )
		h[i] = new Tank();
	h[0].setColor("Red");
	h[0].setCrew(5);
	h[0].setMaxSpeed(70);
	h[1].setColor("Green");
	h[1].setCrew(10);
	h[1].setMaxSpeed(300);
	h[2].setColor("Blue");
	h[2].setCrew(8);
	h[2].setMaxSpeed(150);
	h[3].setColor("Yellow");
	h[3].setCrew(6);
	h[3].setMaxSpeed(200);
	h[4].setColor("Magenta");
	h[4].setCrew(3);
	h[4].setMaxSpeed(100);
	printTankInfo(h);
	}
static void printTankInfo(Tank[] hp) {
	if(hp != null && hp.length != 0)
		for(int i=0; i<hp.length; i++) {
	System.out.print("Tank "+i+" color: " +hp[i].getColor());
	System.out.print(" crew: "+hp[i].getCrew());
	System.out.println(" maxim speed: "+hp[i].getMaxSpeed());
		}
}
}
