package fr.afpa.swing.libraryv1_3.model;

public class Book {
	
	private long isbn;
	
	private String title;
	
	private String subtitle;
	
	private Author author;
	
	private Catalog catalog;
	

	public Book(long isbn, String title, Author author, Catalog catalog) {
		setIsbn(isbn);
		setTitle(title);
		setAuthor(author);
		setCatalog(catalog);
	}
	
	public Book(long isbn, String title, String subtitle, Author author, Catalog catalog) {
		setIsbn(isbn);
		setTitle(title);
		setSubtitle(subtitle);
		setAuthor(author);
		setCatalog(catalog);
	}
	
	public Book(long isbn, String title, Author author) {
		setIsbn(isbn);
		setTitle(title);
		setAuthor(author);
		
	}
	
	public Book(String title) {
		setTitle(title);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (subtitle != "null" && subtitle != null)
		return "ISBN : " + isbn + ", " + title + " - " + subtitle + " by " + author ;
		else return "ISBN : " + isbn + ", " + title  + " by " + author ;
	}

	/**
	 * @return the book
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @return the authorId
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @return the catalog
	 */
	public Catalog getCatalog() {
		return catalog;
	}

	/**
	 * @param book the book to set
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(Catalog i) {
		this.catalog = i;
	}
	
	

}
