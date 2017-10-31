package fr.afpa.swing.libraryv1_3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.afpa.swing.libraryv1_3.model.Author;
import fr.afpa.swing.libraryv1_3.model.Book;
import fr.afpa.swing.libraryv1_3.model.Catalog;
import fr.afpa.swing.libraryv1_3.model.Copy;
import fr.afpa.swing.libraryv1_3.model.Subscriber;


public class DaoLibraryMySql implements IDaoLibrary {

	private String url = "jdbc:mysql://localhost:3306/library";
	private String login = "root";
	private String passwd = "toor";
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result = null;
	private ResultSet resultInside = null;
	private ResultSet resultIn = null;
	private ResultSet rsInside = null;
	private ResultSet rs = null;


	public DaoLibraryMySql() {
		init();
	}

	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<Catalog> allCatalogs() {
		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM catalog;";

			result = statement.executeQuery(query);

			while (result.next()) {
				int catalogId = result.getInt("idCatalog");
				String catalogName = result.getString("catalogName");

				Catalog dataToAdd = new Catalog(catalogId, catalogName);
				catalogs.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return catalogs;			
	}


	public ArrayList<Book> allBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM book;";

			result = statement.executeQuery(query);

			while (result.next()) {
				long isbn = result.getLong("isbn");
				String title = result.getString("title");
				String subtitle = result.getString("subtitle");
				int authorId = result.getInt("Author_idAuthor");
				int catalogId = result.getInt("Catalog_idCatalog");
				Author author = getAuthor(authorId);
				Catalog catalog = getCatalog(catalogId);

				Book dataToAdd = new Book(isbn, title, subtitle, author, catalog);
				books.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return books;			
	}


	public ArrayList<Subscriber> allSubscribers() {		
		ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();

		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM subscriber;";

			result = statement.executeQuery(query);

			while (result.next()) {
				String subscriberFirstName = result.getString("firstName");
				String subscriberLastName = result.getString("lastName");
				int subscriberId = result.getInt("idSubscriber");

				Subscriber dataToAdd = new Subscriber(subscriberId, subscriberFirstName, subscriberLastName);
				subscribers.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return subscribers;			
	}


	public ArrayList<Author> allAuthors() {
		ArrayList<Author> authors = new ArrayList<Author>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM author;";

			result = statement.executeQuery(query);

			while (result.next()) {
				String authorFirstName = result.getString("firstName");
				String authorLastName = result.getString("lastName");
				int authorId = result.getInt("idAuthor");
				int yearOfBirth = result.getInt("yearOfBirth");
				int yearOfDeath = result.getInt("yearOfDeath");

				Author dataToAdd = new Author(authorId, authorFirstName, authorLastName, yearOfBirth, yearOfDeath);
				authors.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;			
	}


	public ArrayList<Catalog> searchCatalog(String name) {
		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM catalog WHERE catalogName LIKE '%" + name.toLowerCase().replace("'", "\\'") + "%';";

			result = statement.executeQuery(query);

			while (result.next()) {
				int catalogId = result.getInt("idCatalog");
				String catalogName = result.getString("catalogName");

				Catalog dataToAdd = new Catalog(catalogId, catalogName);
				catalogs.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return catalogs;
	}


	public ArrayList<Book> searchBook(String keywords) {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM book WHERE title LIKE '%" + keywords.toLowerCase().replace("'", "\\'")
					+ "%' OR subtitle  LIKE '%" + keywords.toLowerCase().replace("'", "\\'") + "%';";

			result = statement.executeQuery(query);

			while (result.next()) {
				long isbn = result.getLong("isbn");
				String title = result.getString("title");
				String subtitle = result.getString("subtitle");
				int authorId = result.getInt("Author_idAuthor");
				int catalogId = result.getInt("Catalog_idCatalog");
				Author author = getAuthor(authorId);
				Catalog catalog = getCatalog(catalogId);

				Book dataToAdd = new Book(isbn, title, subtitle, author, catalog);
				books.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return books;			
	}

	@Override
	public Copy getCopy(int id) {
		Copy c = null;
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy WHERE idCopy = " + id + ";";
			resultInside = statement.executeQuery(query);
			System.out.println(query);
			while (resultInside.next()) {
				boolean available = false;
				boolean underRepair = false;
				if (resultInside.getInt("available") == 1) {
					available = true;
				}
				if (resultInside.getInt("underRepair") == 1) {
					underRepair = true;
				}
				long isbn = resultInside.getLong("Book_isbn");
				Book book = getBook(isbn);
				c = new Copy(book, id, available, underRepair);
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}


	public ArrayList<Subscriber> searchSubscriber(String name) {
		ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();

		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM subscriber WHERE firstName LIKE '%" + name.toLowerCase().replace("'", "\\'") 
					+"%' OR lastName  LIKE '%" + name.toLowerCase().replace("'", "\\'") + "%';";

			result = statement.executeQuery(query);

			while (result.next()) {
				String subscriberFirstName = result.getString("firstName");
				String subscriberLastName = result.getString("lastName");
				int subscriberId = result.getInt("idSubscriber");

				Subscriber dataToAdd = new Subscriber(subscriberId, subscriberFirstName, subscriberLastName);
				subscribers.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return subscribers;
	}

	@Override
	public ArrayList<Copy> searchBorrow() {
		ArrayList<Copy> copies = new ArrayList<Copy>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy INNER JOIN borrow ON idCopy = Copy_idCopy WHERE current = 1;";

			result = statement.executeQuery(query);

			while (result.next()) {
				long isbnBook = result.getLong("Book_isbn");
				int id = result.getInt("idCopy");
				boolean available = true;
				boolean underRepair = false;
				if (result.getBoolean("underRepair")) {
					underRepair = true;
				}
				if (!result.getBoolean("available")) {
					available = false;					
				}
				System.out.println(isbnBook);
				Book book = getBook(isbnBook);
				Copy copyToAdd = new Copy(book, id, available, underRepair);
				copies.add(copyToAdd);

			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao searchBorrow()");
		}
		return copies;
	}

	public int countBorrowsBySubscriber(int subscriberId) {
		int countBorrow = 0;
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM borrow JOIN subscriber ON idSubscriber = Subscriber_idSubscriber "
					+ " WHERE current = 1 AND Subscriber_idSubscriber = "+ subscriberId +";";
			result = statement.executeQuery(query);

			while (result.next()) {
				countBorrow++;
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao getBorrowedBooksBySubscriber()");
		}
		return countBorrow;
	}

	public ArrayList<Book> getBorrowedBooksBySubscriber(int subscriberId) {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM book inner JOIN copy ON Book_isbn = isbn "
					+ " JOIN borrow ON Copy_idCopy = idCopy"
					+ " JOIN subscriber ON idSubscriber = Subscriber_idSubscriber"
					+ " WHERE current = 1 AND Subscriber_idSubscriber = "+ subscriberId +";";
			result = statement.executeQuery(query);

			while (result.next()) {
				long isbn = result.getLong("isbn");
				String title = result.getString("title");
				String subtitle = result.getString("subtitle");
				int authorId = result.getInt("Author_idAuthor");
				int catalogId = result.getInt("Catalog_idCatalog");
				Author author = getAuthor(authorId);
				Catalog catalog = getCatalog(catalogId);

				Book dataToAdd = new Book(isbn, title, subtitle, author, catalog);
				books.add(dataToAdd);				
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao getBorrowedBooksBySubscriber()");
		}
		return books;
	}


	public Subscriber getBorrower(int idCopy) {
		Subscriber s = null;
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM subscriber JOIN borrow WHERE Subscriber_idSubscriber = "
					+ "idSubscriber and Copy_idCopy = " + idCopy +";";

			result = statement.executeQuery(query);

			while (result.next()) {
				String subscriberFirstName = result.getString("firstName");
				String subscriberLastName = result.getString("lastName");
				int subscriberId = result.getInt("idSubscriber");

				s = new Subscriber(subscriberId, subscriberFirstName, subscriberLastName);

			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return s;

	}

	public int getSelectedIdCopyBorrowedBySubscriberAndBook(long isbn, int subscriberId) {
		int i = -1;
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM borrow inner JOIN copy ON Copy_idCopy = idCopy "
					+ " JOIN book ON Book_isbn = isbn "
					+ " JOIN subscriber ON idSubscriber = Subscriber_idSubscriber "
					+ " WHERE current = 1 AND Subscriber_idSubscriber = "+ subscriberId +" "
					+ "AND isbn = "+ isbn +";";
			result = statement.executeQuery(query);

			while (result.next()) {
				i = result.getInt("Copy_idCopy");				
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao getSelectedIdCopyBorrowedBySubscriberAndBook()");
		}
		return i;
	}


	public ArrayList<Author> searchAuthor(String name) {
		ArrayList<Author> authors = new ArrayList<Author>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM author WHERE firstName LIKE '%" + name.toLowerCase().replace("'", "\\'") 
					+"%' OR lastName  LIKE '%" + name.toLowerCase().replace("'", "\\'") + "%';";

			result = statement.executeQuery(query);

			while (result.next()) {
				String authorFirstName = result.getString("firstName");
				String authorLastName = result.getString("lastName");
				int authorId = result.getInt("idAuthor");
				int yearOfBirth = result.getInt("yearOfBirth");
				int yearOfDeath = result.getInt("yearOfDeath");

				Author dataToAdd = new Author(authorId, authorFirstName, authorLastName, yearOfBirth, yearOfDeath);
				authors.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}


	public void addCatalog(String catalogName) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "INSERT INTO catalog(catalogName) values ('" + catalogName.replace("'", "\\'") + "');";

			statement.executeUpdate(query);

			statement.close();

			connect.close();			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteCatalog(int selectedCatalogId) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String delQuery = "DELETE FROM catalog WHERE idCatalog =" + selectedCatalogId + ";";

			statement.executeUpdate(delQuery);

			statement.close();

			connect.close();			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Catalog getCatalog(int selectedCatalogId) {
		Catalog selectedCatalog = null;
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM catalog WHERE idCatalog =" + selectedCatalogId + ";";

			rsInside = statement.executeQuery(query);

			while (rsInside.next()) {
				int catalogId = rsInside.getInt("idCatalog");
				String catalogName = rsInside.getString("catalogName");

				selectedCatalog = new Catalog(catalogId, catalogName);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return selectedCatalog;
	}


	public void addBook(Book b) {
		long isbn = b.getIsbn();
		String title = b.getTitle().replace("'", "\\'");
		String subtitle = b.getSubtitle().replace("'", "\\'");
		int authorId = b.getAuthor().getAuthorId();
		int catalogId = b.getCatalog().getId();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "INSERT INTO book VALUES (" + isbn + ", \""+ title + "\", \"" + subtitle + "\", " + authorId + ", " + catalogId + ");";
			statement.executeUpdate(query);

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void deleteBook(long isbn) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String delQuery = "DELETE FROM book WHERE isbn =" + isbn + ";";

			statement.executeUpdate(delQuery);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Book getBook(long selectedIsbn) {
		Book selectedBook = null;
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM book WHERE isbn =" + selectedIsbn + ";";

			resultIn = statement.executeQuery(query);

			while (resultIn.next()) {
				long isbn = resultIn.getLong("isbn");
				String title = resultIn.getString("title");
				String subtitle = resultIn.getString("subtitle");
				int authorId = resultIn.getInt("Author_idAuthor");
				int catalogId = resultIn.getInt("Catalog_idCatalog");
				Author author = getAuthor(authorId);
				Catalog catalog = getCatalog(catalogId);
				selectedBook =  new Book(isbn, title, subtitle, author, catalog);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return selectedBook;
	}


	public void addCopy(long isbn) {

		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "INSERT INTO copy (Book_isbn, available, underRepair) VALUES (" + isbn + ", "+ 1 + ", " + 0 + ");";

			statement.executeUpdate(query);

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}



	public void addSubscriber(Subscriber s) {
		String subscriberFirstName = s.getSubscriberFirstName().toUpperCase().replace("'", "\\'");
		String subscriberLastName = s.getSubscriberLastName().replace("'", "\\'");		

		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "INSERT INTO subscriber(firstName, lastName) values ('" + subscriberFirstName + "', '" + subscriberLastName + "');";

			statement.executeUpdate(query);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteSubscriber(int id) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String delQuery = "DELETE FROM subscriber WHERE idSubscriber =" + id + ";";

			statement.executeUpdate(delQuery);

			statement.close();

			connect.close();

		}  
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Subscriber getSubscriber(int id) {
		Subscriber selectedSubscriber = null;
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String sel = "SELECT * FROM subscriber WHERE idSubscriber = " + id +";";

			result = statement.executeQuery(sel);

			while (result.next()) {
				String sFirstName = result.getString("firstName");
				String sLastName = result.getString("lastName");
				int sId = result.getInt("idSubscriber");

				selectedSubscriber = new Subscriber(sId, sFirstName, sLastName);
			}

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return selectedSubscriber;
	}


	public void addAuthor(Author a) {
		String authorFirstName = a.getAuthorFirstName().toUpperCase().replace("'", "\\'");
		String authorLastName = a.getAuthorLastName().replace("'", "\\'");
		int yearOfBirth = a.getYearOfBirth();
		int yearOfDeath = a.getYearOfDeath();

		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "INSERT INTO author(firstName, lastName, yearOfBirth, yearOfDeath) values "
					+ "('" + authorFirstName + "', '" + authorLastName  + "', '" + yearOfBirth + "', '" + yearOfDeath + "');";

			statement.executeUpdate(query);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteAuthor(int id) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String delQuery = "DELETE FROM author WHERE idAuthor =" + id + ";";

			statement.executeUpdate(delQuery);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Author getAuthor(int id) {
		Author selectedAuthor = null;
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String sel = "SELECT * FROM author WHERE idAuthor = " + id +";";

			rsInside = statement.executeQuery(sel);

			while (rsInside.next()) {
				String authorFirstName = rsInside.getString("firstName");
				String authorLastName = rsInside.getString("lastName");
				int authorId = rsInside.getInt("idAuthor");
				int yearOfBirth = rsInside.getInt("yearOfBirth");
				int yearOfDeath = rsInside.getInt("yearOfDeath");

				selectedAuthor = new Author(authorId, authorFirstName, authorLastName, yearOfBirth, yearOfDeath);
			}

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return selectedAuthor;
	}


	public void updateBook(long isbn, String title, String subtitle, int authorId, int catalogId) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();
			String query;
			if (subtitle == null) {
				query = "UPDATE book SET title ='" + title.replace("'", "\\'") + "', Author_idAuthor ='" + authorId + 
						"', Catalog_idCatalog = '" + catalogId + "' WHERE isbn = '"+ isbn +"';";
			}
			else query = "UPDATE book SET title ='" + title.replace("'", "\\'") + "', subtitle = '" + subtitle.replace("'", "\\'") + "', Author_idAuthor ='" + authorId + 
					"', Catalog_idCatalog = '" + catalogId + "' WHERE isbn = '"+ isbn +"';";

			statement.executeUpdate(query);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void updateSubscriber(int id, String firstName,String lastName) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "UPDATE subscriber SET firstName ='" + firstName.toUpperCase().replace("'", "\\'") + "', "
					+ "lastName = '" + lastName.replace("'", "\\'") + "' WHERE idSubscriber = "+ id +";";

			statement.executeUpdate(query);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void updateAuthor(int id, String firstName, String lastName, int birth, int death) {

		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "UPDATE author SET firstName ='" + firstName.toUpperCase().replace("'", "\\'") + "',"
					+ " lastName = '" + lastName.replace("'", "\\'") + "',  yearOfBirth = '" + birth +"', "
					+ "yearOfDeath = '" + death + "' WHERE idAuthor = "+ id +";";

			statement.executeUpdate(query);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<Book> getBooksFromCatalog(int selectedCatalogId) {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM book WHERE Catalog_idCatalog = " + selectedCatalogId +";";

			result = statement.executeQuery(query);

			while (result.next()) {
				long isbn = result.getLong("isbn");
				String title = result.getString("title");
				String subtitle = result.getString("subtitle");
				int authorId = result.getInt("Author_idAuthor");
				int catalogId = result.getInt("Catalog_idCatalog");
				Author author = getAuthor(authorId);
				Catalog catalog = getCatalog(catalogId);

				Book dataToAdd = new Book(isbn, title, subtitle, author, catalog);
				books.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return books;			
	}

	public ArrayList<Book> getBookListByAuthor(int selectedAuthorId) {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM book WHERE Author_idAuthor = " + selectedAuthorId +";";

			result = statement.executeQuery(query);

			while (result.next()) {
				long isbn = result.getLong("isbn");
				String title = result.getString("title");
				String subtitle = result.getString("subtitle");
				int authorId = result.getInt("Author_idAuthor");
				int catalogId = result.getInt("Catalog_idCatalog");
				Author author = getAuthor(authorId);
				Catalog catalog = getCatalog(catalogId);

				Book dataToAdd = new Book(isbn, title, subtitle, author, catalog);
				books.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return books;	
	}


	public void addBorrow(int idSubscriber, long isbn, int idCopy) {
		if (getBorrowedBooksBySubscriber(idSubscriber).size() < 5) {
			try {
				//Get connection informations
				connect = DriverManager.getConnection(url, login, passwd);
				//Create statement
				statement = connect.createStatement();

				String query = "INSERT INTO borrow VALUES "
						+ "(" + idSubscriber + ", " + idCopy  + ", " + 1 + ", " + isbn + ");";

				String preQuery = "SELECT COUNT(*) FROM borrow WHERE Copy_idCopy = "+ idCopy + " AND "  
						+ " Subscriber_idSubscriber = " + idSubscriber + " AND Copy_Book_isbn =" + isbn  + ";";

				result = statement.executeQuery(preQuery);
				while (result.next()) {
					System.out.println(result.getInt(1));
					if (result.getInt(1) > 0) {
						query = "UPDATE borrow SET current = 1 WHERE Copy_idCopy = " + idCopy + " AND "
								+ " Subscriber_idSubscriber = " + idSubscriber + " AND Copy_Book_isbn = " + isbn + ";";
					}
				}



				statement.executeUpdate(query);

				statement.close();

				connect.close();

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void setCopyAvailable(long isbn, int idCopy) {
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "UPDATE copy SET underRepair = 0, available = 1 WHERE idCopy = '" + idCopy + "' ; ";
			System.out.println(query);
			statement.executeUpdate(query);

			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao setCopyAvailable()");
		}
	}


	public void setCopyUnavailable(long isbn, int idCopy) {
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "UPDATE copy SET available = 0  WHERE Book_isbn = '" + isbn + "' AND idCopy = '" + idCopy + "' ; ";

			statement.executeUpdate(query);

			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao setCopyUnavailable()");
		}
	}


	public ArrayList<Copy> getCopies(Long isbn) {
		ArrayList<Copy> copies = new ArrayList<Copy>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy WHERE Book_isbn = " + isbn + ";";
			System.out.println(query);
			result = statement.executeQuery(query);

			while (result.next()) {
				Book book = getBook(isbn);
				System.out.println(book);
				int id = result.getInt("idCopy");
				boolean available = true;
				boolean underRepair = false;
				if (result.getBoolean("underRepair")) {
					underRepair = true;
				}
				if (!result.getBoolean("available")) {
					available = false;					
				}
				Copy copyToAdd = new Copy(book, id, available, underRepair);
				copies.add(copyToAdd);

			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao getCopies()");
		}
		return copies;
	}

	public boolean isBookAvailable(long isbn) {
		boolean bookStatus = false;
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy WHERE Book_isbn = " + isbn + ";";

			result = statement.executeQuery(query);

			while (result.next()) {
				int id = result.getInt("idCopy");

				if (isCopyAvailable(id)) {
					bookStatus = true;
				}
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return bookStatus;
	}

	public boolean isCopyAvailable(int id) {
		boolean copyStatus = false;
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy WHERE idCopy = " + id + ";";
			resultInside = statement.executeQuery(query);
			System.out.println(query);
			while (resultInside.next()) {
				if (resultInside.getInt("available") == 1) {
					copyStatus = true;
				}
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return copyStatus;
	}

	public boolean isCopyUnderRepair(int id) {
		boolean copyRepairStatus = false;
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy WHERE idCopy = " + id + ";";
			resultInside = statement.executeQuery(query);
			System.out.println(query);
			while (resultInside.next()) {
				if (resultInside.getInt("underRepair") == 1) {
					copyRepairStatus = true;
				}
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao isCopyUnderRepair()");;
		}
		return copyRepairStatus;
	}


	public void setCopyToStateUnderRepair(long isbn, int idCopy) {
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "UPDATE copy SET underRepair = 1  WHERE Book_isbn = '" + isbn + "' AND idCopy = '" + idCopy + "' ; ";

			statement.executeUpdate(query);

			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao setCopyToStateUnderRepair()");
		}
	}


	public void returnBorrow(int idSubscriber, long isbn, int idCopy) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "UPDATE borrow SET current = 0 WHERE Copy_idCopy = "+ idCopy +" AND "
					+ " Subscriber_idSubscriber = " + idSubscriber + " AND Copy_Book_isbn =" + isbn  + ";";
			System.out.println(query);
			statement.executeUpdate(query);

			statement.close();

			connect.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao returnBorrow()");
		}
	}

	@Override
	public ArrayList<Copy> searchedBorrowsBySubscriber(String name) {
		ArrayList<Copy> copies = new ArrayList<Copy>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy INNER JOIN borrow ON idCopy = Copy_idCopy "
					+ " JOIN subscriber ON Subscriber_idSubscriber = idSubscriber "
					+ " WHERE current = 1 AND (firstName LIKE '%" + name.toLowerCase().replace("'", "\\'") +"%' "  
					+" OR lastName  LIKE '%" + name.toLowerCase().replace("'", "\\'") + "%');";

			result = statement.executeQuery(query);

			while (result.next()) {
				long isbnBook = result.getLong("Book_isbn");
				Book book = getBook(isbnBook);
				int id = result.getInt("idCopy");
				boolean available = true;
				boolean underRepair = false;
				if (result.getBoolean("underRepair")) {
					underRepair = true;
				}
				if (!result.getBoolean("available")) {
					available = false;					
				}
				Copy copyToAdd = new Copy(book, id, available, underRepair);
				copies.add(copyToAdd);

			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao searchedBorrowsBySubscriber()");
		}
		return copies;
	}


	public ArrayList<Copy> searchedBorrowsByTitle(String title) {
		ArrayList<Copy> copies = new ArrayList<Copy>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy INNER JOIN borrow ON idCopy = Copy_idCopy "
					+ " JOIN book ON Book_isbn = isbn "
					+ " WHERE current = 1 AND (title LIKE '%" + title.toLowerCase().replace("'", "\\'")  
					+ "%' OR subtitle  LIKE '%" + title.toLowerCase().replace("'", "\\'") + "%');";

			result = statement.executeQuery(query);

			while (result.next()) {
				long isbnBook = result.getLong("Book_isbn");
				Book book = getBook(isbnBook);
				int id = result.getInt("idCopy");
				boolean available = true;
				boolean underRepair = false;
				if (result.getBoolean("underRepair")) {
					underRepair = true;
				}
				if (!result.getBoolean("available")) {
					available = false;					
				}
				Copy copyToAdd = new Copy(book, id, available, underRepair);
				copies.add(copyToAdd);

			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao searchedBorrowsByTitle()");
		}
		return copies;
	}


	public ArrayList<Copy> searchedBorrowsByTitleAndSubscriber(String title, String name) {
		ArrayList<Copy> copies = new ArrayList<Copy>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy INNER JOIN borrow ON idCopy = Copy_idCopy "
					+ " JOIN book ON Book_isbn = isbn JOIN subscriber ON Subscriber_idSubscriber = idSubscriber "
					+ " WHERE current = 1 AND ((title LIKE '%" + title.toLowerCase().replace("'", "\\'")  
					+ "%' OR subtitle  LIKE '%" + title.toLowerCase().replace("'", "\\'") + "%') OR ("
					+ "firstName LIKE '%" + name.toLowerCase().replace("'", "\\'") +"%' "
					+ "OR lastName  LIKE '%" + name.toLowerCase().replace("'", "\\'") + "%'));";
			System.out.println(query);
			result = statement.executeQuery(query);

			while (result.next()) {
				long isbnBook = result.getLong("Book_isbn");
				int id = result.getInt("idCopy");
				boolean available = true;
				boolean underRepair = false;
				if (result.getBoolean("underRepair")) {
					underRepair = true;
				}
				if (!result.getBoolean("available")) {
					available = false;					
				}
				Book book = getBook(isbnBook);
				Copy copyToAdd = new Copy(book, id, available, underRepair);
				copies.add(copyToAdd);

			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao searchedBorrowsByTitleAndSubscriber()");
		}
		return copies;
	}


	public ArrayList<Subscriber> subscriberAlReadyExists(String firstName, String lastName) {
		ArrayList<Subscriber> existingSubscribers = new ArrayList<Subscriber>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM subscriber WHERE firstName LIKE '%" + firstName.toLowerCase().replace("'", "\\'") 
					+"%' AND lastName  LIKE '%" + lastName.toLowerCase().replace("'", "\\'") + "%';";

			result = statement.executeQuery(query);

			while (result.next()) {
				String subscriberFirstName = result.getString("firstName");
				String subscriberLastName = result.getString("lastName");
				int subscriberId = result.getInt("idSubscriber");

				Subscriber dataToAdd = new Subscriber(subscriberId, subscriberFirstName, subscriberLastName);
				existingSubscribers.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return existingSubscribers;
	}




	public ArrayList<Author> authorAlReadyExists(String firstName, String lastName) {
		ArrayList<Author> existingAuthors = new ArrayList<Author>();
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String query = "SELECT * FROM author WHERE firstName LIKE '%" + firstName.toLowerCase().replace("'", "\\'") 
					+"%' AND lastName  LIKE '%" + lastName.toLowerCase().replace("'", "\\'") + "%';";

			result = statement.executeQuery(query);

			while (result.next()) {
				String authorFirstName = result.getString("firstName");
				String authorLastName = result.getString("lastName");
				int authorId = result.getInt("idSubscriber");

				Author dataToAdd = new Author(authorId, authorFirstName, authorLastName);
				existingAuthors.add(dataToAdd);
			}

			statement.close();

			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return existingAuthors;
	}


	public ArrayList<Copy> getCopyToReturn(int idSubscriber, long isbn) {
		ArrayList<Copy> copyToReturn = new ArrayList<Copy>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy inner JOIN borrow ON Copy_idCopy = idCopy "
					+ " JOIN book ON Book_isbn = isbn "
					+ " JOIN subscriber ON idSubscriber = Subscriber_idSubscriber "
					+ " WHERE current = 1 AND Subscriber_idSubscriber = "+ idSubscriber +" "
					+ "AND isbn = "+ isbn +";";
			result = statement.executeQuery(query);
			while (result.next()) {
				int id = result.getInt("Copy_idCopy");
				Copy c = getCopy(id);
				copyToReturn.add(c);
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao getCopyToReturn()");
		}
		return copyToReturn;
	}

	@Override
	public ArrayList<Copy> getCopiesToBorrow(long isbn) {
		ArrayList<Copy> copiesAvalaibleToBorrow = new ArrayList<Copy>();
		try {
			connect = DriverManager.getConnection(url, login, passwd);
			statement = connect.createStatement();

			String query = "SELECT * FROM copy "
					+ " JOIN book ON Book_isbn = isbn "
					+ " WHERE available = 1 "
					+ " AND isbn = "+ isbn +";";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("idCopy");
				Copy c = getCopy(id);
				copiesAvalaibleToBorrow.add(c);
			}
			statement.close();
			connect.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error SQL on Dao getCopiesToBorrow()");
		}
		return copiesAvalaibleToBorrow;
	}

	@Override
	public void deleteCopy(long isbn, int copyId) {
		try {
			//Get connection informations
			connect = DriverManager.getConnection(url, login, passwd);
			//Create statement
			statement = connect.createStatement();

			String delQuery = "DELETE FROM copy WHERE idCopy =" + copyId + ";";

			statement.executeUpdate(delQuery);

			statement.close();

			connect.close();			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
