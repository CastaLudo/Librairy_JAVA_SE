/**
 * 
 */
package fr.afpa.swing.libraryv1_3.service;

import java.util.ArrayList;

import fr.afpa.swing.libraryv1_3.model.Author;
import fr.afpa.swing.libraryv1_3.model.Book;
import fr.afpa.swing.libraryv1_3.model.Catalog;
import fr.afpa.swing.libraryv1_3.model.Copy;
import fr.afpa.swing.libraryv1_3.model.Subscriber;

/**
 * @author 34011-79-08
 *
 */
public interface IServiceLibrary {
	
	public ArrayList<Catalog> allCatalogs();
	
	public ArrayList<Book> allBooks();
	
	public ArrayList<Subscriber> allSubscribers();
	
	public ArrayList<Author> allAuthors();
	
	public ArrayList<Catalog> searchCatalog(String name);
	
	public ArrayList<Book> searchBook(String keywords);
	
	public Copy getCopy(int id);
	
	public ArrayList<Subscriber> searchSubscriber(String keywords);
	
	public ArrayList<Copy> searchBorrow();
	
	public ArrayList<Author> searchAuthor(String name);
	
	public void addCatalog(String catalogName);
	
	public void deleteCatalog(int catalogId);
	
	public Catalog getCatalog(int catalogId);
	
	public void addBook(Book b);
	
	public Book getBook(long bookId);
	
	public void deleteBook(long isbn);
	
	public void addCopy(long isbn);
	
	public void deleteCopy(long isbn, int copyId);
	
	public void addBorrow(int idSubscriber, long isbn, int idCopy);
	
	public void addSubscriber(Subscriber s);
	
	public void deleteSubscriber(int id);
	
	public Subscriber getSubscriber(int subscriberId);
	
	public void addAuthor(Author a);
	
	public void deleteAuthor(int id);
	
	public Author getAuthor(int authorId);
	
	public void updateBook(long isbn, String title, String subtitle, int authorId, int catalogId); 
	
	public void updateSubscriber(int id, String firstName,String lastName); 
	
	public void updateAuthor(int id, String fistName, String lastName, int birth, int death); 
	
	public ArrayList<Book> getBooksFromCatalog(int catalogId);
	
	public ArrayList<Book> getBookListByAuthor(int authorId);

	public ArrayList<Copy> getCopies(Long isbn);

	public void setCopyUnavailable(long isbn, int idCopy);

	public void setCopyAvailable(long isbn, int idCopy);

	public boolean isCopyAvailable(int idCopy);	
	
	public boolean isBookAvailable(long isbn);

	public boolean isCopyUnderRepair(int idCopy);

	public void setCopyToStateUnderRepair(long isbn, int idCopy);

	public void returnBorrow(int idSubscriber, long isbn, int idCopy);

	public Subscriber getBorrower(int idCopy);

	public ArrayList<Book> getBorrowedBooksBySubscriber(int id);

	public int getSelectedIdCopyBorrowedBySubscriberAndBook(long isbn, int subscriberId);

	public ArrayList<Copy> searchedBorrowsBySubscriber(String name);

	public ArrayList<Copy> searchedBorrowsByTitle(String title);

	public ArrayList<Copy> searchedBorrowsByTitleAndSubscriber(String title, String name);

	public int countBorrowsBySubscriber(int subscriberId);

	public ArrayList<Subscriber> subscriberAlReadyExists(String firstName, String lastName);

	public ArrayList<Author> authorAlReadyExists(String firstName, String lastName);

	public ArrayList<Copy> getCopyToReturn(int idSubscriber, long isbn);

	public ArrayList<Copy> getCopiesToBorrow(long isbn);
}
