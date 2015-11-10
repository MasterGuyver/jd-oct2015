package hw5;

import java.util.Arrays;

public class Library {
	private Book[] bank;

	public Library() {
		bank = null;
	}

	public void addBook(String name, String author, Genre genre) {
		if (bank == null) {
			bank = new Book[1];
			bank[0] = new Book(name,author,genre);
		} else {
			Book[] temp = new Book[bank.length + 1];
			int i = 0;
			for (; i < bank.length; i++)
				temp[i] = bank[i];
			temp[i] = new Book(name,author,genre);
			bank = temp;
		}
	}

	public void deleteBook(int index) {
		if (bank != null && bank.length > 0 && index >= 0
				&& index < bank.length) {
			if (bank.length == 1 && index == 0)
				bank = null;
			else {
				Book[] temp = new Book[bank.length - 1];
				for (int i = 0; i < bank.length; i++)
					if (i != index)
						temp[i] = bank[i];
				bank = temp;
			}
		}
	}
	
   public Book findBookByName(String name) {
	   sort(1);
	   Book tmp=null;
	   for(int i=0; i<bank.length; i++) {
		   if(bank[i].getName().equals(name)) {
			   tmp = bank[i];
			   break;
		   }
	   }
	   return tmp;
   }

   public Book findBookByAuthor(String at) {
	   sort(2);
	   Book tmp=null;
	   for(int i=0; i<bank.length; i++) {
		   if(bank[i].getAuthor().equals(at)) {
			   tmp = bank[i];
			   break;
		   }
	   }
	   return tmp;
   }
   
   public Book[] findBookByGenre(Genre gn) {
	   sort(3);
	   Book[] tmp=new Book[5];
	   int k = 0;
	   for(int i=0; i<bank.length; i++) {
		   if(bank[i].getGenre().getId() == gn.getId() && k<5 ) {
			   tmp[k++] = bank[i];
			   if(k==5) break;
			   continue;
		   }
	   }
	   return tmp;
   }
   
   
   private void sort(int selector) {
		if (bank == null && selector < 1 && selector > 3)
			return;
		String[] strName = new String[bank.length];
		String[] strAuthor = new String[bank.length];
		int[] msi = new int[bank.length];
		for (int i = 0; i < bank.length; i++) {
			strName[i] = bank[i].getName();
			strAuthor[i] = bank[i].getAuthor();
			msi[i] = bank[i].getGenre().getId();
		}
		if (selector == 1) {
			Arrays.sort(strName);
			proName(strName);
		}
		if (selector == 2) {
			Arrays.sort(strAuthor);
			proAuthor(strAuthor);
		}
		if (selector == 3) {
			Arrays.sort(msi);
			proGenre(msi);
		}
	}

	private void proName(String[] words) {
		for (int i = 0; i < words.length; i++)
			for (int j = i; j < words.length; j++) {
				if(words[i].equals(bank[j].getName())) {
					Book tmp = new Book();
					tmp = bank[i];
					bank[i] = bank[j];
					bank[j] = tmp;
				}
			}
	}
	private void proAuthor(String[] words) {
		for (int i = 0; i < words.length; i++)
			for (int j = i; j < words.length; j++) {
				if(words[i].equals(bank[j].getAuthor())) {
					Book tmp = new Book();
					tmp = bank[i];
					bank[i] = bank[j];
					bank[j] = tmp;
				}
			}
	}
	private void proGenre(int[] gnr) {
		for (int i = 0; i < bank.length; i++)
			for (int j = i; j < bank.length; j++) {
				if(gnr[i]==bank[j].getGenre().getId()) {
					Book tmp = new Book();
					tmp = bank[i];
					bank[i] = bank[j];
					bank[j] = tmp;
				}
			}
	}
}