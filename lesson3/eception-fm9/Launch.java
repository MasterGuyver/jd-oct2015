
public class Launch {

	public static void main(String[] args) {
		Exept a = new Exept(); 
		try{
			a.evaluate();
		}
		catch(MyPersonalException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Error message!");
		}
	}

}
