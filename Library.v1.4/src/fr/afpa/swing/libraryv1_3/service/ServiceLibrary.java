/**
 * 
 */
package fr.afpa.swing.libraryv1_3.service;

import java.util.ArrayList;

import fr.afpa.swing.libraryv1_3.dao.IDaoLibrary;
import fr.afpa.swing.libraryv1_3.model.Author;
import fr.afpa.swing.libraryv1_3.model.Book;
import fr.afpa.swing.libraryv1_3.model.Catalog;
import fr.afpa.swing.libraryv1_3.model.Copy;
import fr.afpa.swing.libraryv1_3.model.Subscriber;


/**
 * @author 34011-79-08
 *
 */
public class ServiceLibrary implements IServiceLibrary {
	
	private IDaoLibrary daoLibrary;
	
	public ServiceLibrary(IDaoLibrary dao) {
		this.daoLibrary = dao;
	}

	public ArrayList<Catalog> allCatalogs() {
		return daoLibrary.allCatalogs();
	}

	public ArrayList<Book> allBooks() {
		return daoLibrary.allBooks();
	}

	public ArrayList<Subscriber> allSubscribers() {
		return daoLibrary.allSubscribers();
	}

	public ArrayList<Author> allAuthors() {
		return daoLibrary.allAuthors();
	}

	public ArrayList<Catalog> searchCatalog(String name) {
		return daoLibrary.searchCatalog(name);
	}

	public ArrayList<Book> searchBook(String keywords) {
		return daoLibrary.searchBook(keywords);
	}

	public Copy getCopy(int id) {
		return daoLibrary.getCopy(id);
	}

	public ArrayList<Subscriber> searchSubscriber(String keywords) {
		return daoLibrary.searchSubscriber(keywords);
	}

	
	public ArrayList<Copy> searchBorrow() {
		return daoLibrary.searchBorrow();
	}

	public ArrayList<Author> searchAuthor(String name) {
		return daoLibrary.searchAuthor(name);
	}

	public void addCatalog(String catalogName) {
		daoLibrary.addCatalog(catalogName);
	}

	public void deleteCatalog(int catalogId) {
		daoLibrary.deleteCatalog(catalogId);
	}
	
	public Catalog getCatalog(int catalogId) {
		return daoLibrary.getCatalog(catalogId);
	}

	public void addBook(Book b) {
		daoLibrary.addBook(b);
	}

	@Override
	public void deleteBook(long isbn) {
		// TODO Auto-generated method stub
		
	}
	
	public Book getBook(long isbn) {
		return daoLibrary.getBook(isbn);
	}

	public void addCopy(long isbn) {
		daoLibrary.addCopy(isbn);		
	}

	
	public void deleteCopy(long isbn, int copyId) {
		daoLibrary.deleteCopy(isbn, copyId);
	}
	
	public void addBorrow(int idSubscriber, long isbn, int idCopy) {
		daoLibrary.addBorrow(idSubscriber, isbn, idCopy);
	}
	
	public void setCopyUnavailable(long isbn, int idCopy) {
		daoLibrary.setCopyUnavailable(isbn, idCopy);
	}

	public void addSubscriber(Subscriber s) {
		daoLibrary.addSubscriber(s);
		
	}

	public void deleteSubscriber(int id) {
		daoLibrary.deleteSubscriber(id);		
	}
	
	public Subscriber getSubscriber(int subscriberId) {
		return daoLibrary.getSubscriber(subscriberId);
	}

	public void addAuthor(Author a) {
		daoLibrary.addAuthor(a);
		
	}

	
	public void deleteAuthor(int id) {
		daoLibrary.deleteAuthor(id);	
	}
	
	public Author getAuthor(int authorId) {
		return daoLibrary.getAuthor(authorId);
	}

	
	public void updateBook(long isbn, String title, String subtitle, int authorId, int catalogId) {
		daoLibrary.updateBook(isbn, title, subtitle, authorId, catalogId);
	}


	public void updateSubscriber(int id, String firstName,String lastName) {
		daoLibrary.updateSubscriber(id, firstName, lastName);		
	}

	public void updateAuthor(int id, String firstName, String lastName, int birth, int death) {
		daoLibrary.updateAuthor(id, firstName, lastName, birth, death);
	}

	public ArrayList<Book> getBooksFromCatalog(int catalogId) {
		return daoLibrary.getBooksFromCatalog(catalogId);
		
	}
	
	public ArrayList<Book> getBookListByAuthor(int authorId) {
		return daoLibrary.getBookListByAuthor(authorId);
	}

	
	public ArrayList<Copy> getCopies(Long isbn) {
		return daoLibrary.getCopies(isbn);
	}

	
	public void setCopyAvailable(long isbn, int idCopy) {
		daoLibrary.setCopyAvailable(isbn, idCopy);
		
	}

	
	public boolean isCopyAvailable(int idCopy) {
		return daoLibrary.isCopyAvailable(idCopy);
	}

	
	public boolean isBookAvailable(long isbn) {
		return daoLibrary.isBookAvailable(isbn);
	}

	
	public boolean isCopyUnderRepair(int idCopy) {
		return daoLibrary.isCopyUnderRepair(idCopy);
	}

	
	public void setCopyToStateUnderRepair(long isbn, int idCopy) {
		daoLibrary.setCopyToStateUnderRepair(isbn, idCopy);
	}

	
	public void returnBorrow(int idSubscriber, long isbn, int idCopy) {
		daoLibrary.returnBorrow(idSubscriber, isbn, idCopy);
	}

	
	public Subscriber getBorrower(int idCopy) {
		return daoLibrary.getBorrower(idCopy);
		
	}

	
	public ArrayList<Book> getBorrowedBooksBySubscriber(int id) {
		return daoLibrary.getBorrowedBooksBySubscriber(id);
	}

	
	public int getSelectedIdCopyBorrowedBySubscriberAndBook(long isbn, int subscriberId) {
		return daoLibrary.getSelectedIdCopyBorrowedBySubscriberAndBook(isbn, subscriberId);
	}

	
	public ArrayList<Copy> searchedBorrowsBySubscriber(String name) {
		return daoLibrary.searchedBorrowsBySubscriber(name);
	}

	
	public ArrayList<Copy> searchedBorrowsByTitle(String title) {
		return daoLibrary.searchedBorrowsByTitle(title);
	}

	
	public ArrayList<Copy> searchedBorrowsByTitleAndSubscriber(String title, String name) {
		return daoLibrary.searchedBorrowsByTitleAndSubscriber(title, name);
	}

	
	public int countBorrowsBySubscriber(int subscriberId) {
		return daoLibrary.countBorrowsBySubscriber(subscriberId);
	}

	
	public ArrayList<Subscriber> subscriberAlReadyExists(String firstName, String lastName) {
		return daoLibrary.subscriberAlReadyExists(firstName, lastName);
	}

	@Override
	public ArrayList<Author> authorAlReadyExists(String firstName, String lastName) {
		return daoLibrary.authorAlReadyExists(firstName, lastName);
	}

	@Override
	public ArrayList<Copy> getCopyToReturn(int idSubscriber, long isbn) {
		return daoLibrary.getCopyToReturn(idSubscriber, isbn);
	}

	@Override
	public ArrayList<Copy> getCopiesToBorrow(long isbn) {
		return daoLibrary.getCopiesToBorrow(isbn);
	}
}
