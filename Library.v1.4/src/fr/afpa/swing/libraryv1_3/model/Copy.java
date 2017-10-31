/**
 * 
 */
package fr.afpa.swing.libraryv1_3.model;

/**
 * @author 34011-79-08
 *
 */
public class Copy {

	Book book;
	int id;
	boolean available;
	boolean underRepair;
	
	public Copy(Book book, 	int id,	boolean available, boolean underRepair) {
		setBook(book);
		setId(id);
		setAvailable(available);
		setUnderRepair(underRepair);
	}
	
	public Copy(Book book, 	int id) {
		setBook(book);
		setId(id);
		setAvailable(true);
		setUnderRepair(false);
	}

	public String toString() {
		return "ex n°" + this.id + " of " + this.getBook();
	}
	/**
	 * @return the ISBN of the selected Copy belong to the Book ISBN
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @return the id of the selected Copy
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the status if available of the Copy
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @return the the status if underRepair of the Copy
	 */
	public boolean isUnderRepair() {
		return underRepair;
	}

	/**
	 * @param book the ISBN of the Copy to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @param id the id of the Copy to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param available the availability of the Copy to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @param underRepair the status underRepair to set
	 */
	public void setUnderRepair(boolean underRepair) {
		this.underRepair = underRepair;
	}
}
