package hw5;

public class Start {

	public static void main(String[] args) {
		Library lib = new Library();
		lib.addBook("Cleopatra", "Abbott, Jacob", Genre.History);
		lib.addBook("Glinda of Oz", "Baum, L. Frank", Genre.Fantasy);
		lib.addBook("Love And Friendship And Other Early Works",
				"Austen, Jane", Genre.Fiction);
		lib.addBook("A New Interpretation of Information Rate", "J.L. Kelly",
				Genre.Computers);
		lib.addBook("The Art of Dreaming", "Karlos, Kaskaneda", Genre.Fiction);
		lib.addBook("Heart of the West", "Henry, O.", Genre.History);
		lib.addBook("Roads of Destiny", "Henry, O.", Genre.History);
		lib.addBook("The Cost of Kindness", "Jerome K. Jerome", Genre.Fiction);
		lib.addBook("The Game", "London, Jack", Genre.History);
		lib.addBook("South Sea Tales", "London, Jack", Genre.History);
		lib.addBook("The Call of the Wild", "London, Jack", Genre.History);
		System.out.println("Output by Name");
		System.out.println();
		System.out.println(lib.findBookByName("The Game").getName() + " "
				+ lib.findBookByName("The Game").getAuthor());
		System.out.println();
		System.out.println("Output by Author");
		System.out.println();
		System.out.println(lib.findBookByAuthor("Henry, O.").getName() + " "
				+ lib.findBookByAuthor("Henry, O.").getAuthor());
		System.out.println();
		Book[] str = new Book[5];
		str = lib.findBookByGenre(Genre.History);
		System.out.println("Output by Genre 5 books");
		System.out.println();
		for (int i = 0; i < 5; i++) {
			System.out.println(str[i].getName()
					+ " " + str[i].getAuthor()+" ");
		}
		System.out.println();
	}

}
