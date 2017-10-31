package fr.afpa.swing.libraryv1_3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.afpa.swing.libraryv1_3.model.Book;
import fr.afpa.swing.libraryv1_3.model.Catalog;
import fr.afpa.swing.libraryv1_3.service.IServiceLibrary;

public class PaneCatalog extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IServiceLibrary service;
	private JTextField txtSearchCatalog;
	private JTextField txtNewCatalog;
	
	private JList<Catalog> lstSearchCatalogs;
	private DefaultListModel<Catalog> modelLstSearchCatalogs;
	private JList<Book> lstBooksByCatalog;
	private DefaultListModel<Book> modelLstBooksByCatalog;
	
	private JButton btnAllCatalogs;
	
	private JButton btnAddBookInCatalogs;
	private JButton btnNewCatalog;
	private JButton btnAddCatalog;
	
	private JButton btnDeleteCatalog;
	private JButton btnCreateABorrowInCatalogs;
	private FrameLibrary myLibrary;
	
	
	public PaneCatalog(IServiceLibrary serviceCatalog, FrameLibrary frame) {
		this.service = serviceCatalog;
		this.myLibrary = frame;
		
		initPane();
	}
		
	public void initPane() {
			
			this.setLayout(null);
			
			//Left Area
			btnAllCatalogs = new JButton("All Catalogs");
			btnAllCatalogs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					displayAllCatalogs();
				}
			});
			btnAllCatalogs.setBounds(30, 40, 142, 23);
			this.add(btnAllCatalogs);
			
			txtSearchCatalog = new JTextField();
			txtSearchCatalog.setBorder(new LineBorder(new Color(30, 144, 255)));
			txtSearchCatalog.setBounds(30, 95, 206, 20);
			this.add(txtSearchCatalog);
			txtSearchCatalog.setColumns(10);
			
			JButton btnSearchInCatalogs = new JButton("Search");
			btnSearchInCatalogs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					displaySelectedCatalogs();
				}
			});
			btnSearchInCatalogs.setBounds(246, 94, 89, 23);
			this.add(btnSearchInCatalogs);
			
			lstSearchCatalogs = new JList<Catalog>();
			lstSearchCatalogs.setBorder(new LineBorder(new Color(30, 144, 255)));
			lstSearchCatalogs.setBounds(30, 168, 344, 362);
			this.add(lstSearchCatalogs);
			modelLstSearchCatalogs = new DefaultListModel<Catalog>();
			lstSearchCatalogs.setModel(modelLstSearchCatalogs);
			lstSearchCatalogs.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					 isCatalogSelected();
				}
			});
			
			
			//Right Area
			btnNewCatalog = new JButton("New Catalog");
			btnNewCatalog.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					newCatalog();
				}
			});
			btnNewCatalog.setBounds(546, 94, 129, 23);
			this.add(btnNewCatalog);
			
			txtNewCatalog = new JTextField();
			txtNewCatalog.setBorder(new LineBorder(new Color(30, 144, 255)));
			txtNewCatalog.setText("");
			txtNewCatalog.setEnabled(false);
			txtNewCatalog.setBounds(685, 95, 244, 20);
			this.add(txtNewCatalog);
			txtNewCatalog.setColumns(10);
			
			btnAddCatalog = new JButton("Create");
			btnAddCatalog.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCatalog();
				}
			});
			btnAddCatalog.setEnabled(false);
			btnAddCatalog.setBounds(970, 94, 89, 23);
			this.add(btnAddCatalog);
			
			lstBooksByCatalog = new JList<Book>();
			lstBooksByCatalog.setBorder(new LineBorder(new Color(30, 144, 255)));
			lstBooksByCatalog.setBounds(546, 230, 475, 298);
			this.add(lstBooksByCatalog);
			modelLstBooksByCatalog = new DefaultListModel<Book>();
			lstBooksByCatalog.setModel(modelLstBooksByCatalog);
			lstBooksByCatalog.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					 isBookSelected();
				}
			});
			
			
			btnDeleteCatalog = new JButton("Delete");
			btnDeleteCatalog.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteCatalog();
				}
			});
			btnDeleteCatalog.setBounds(388, 505, 89, 23);
			this.add(btnDeleteCatalog);
			btnDeleteCatalog.setEnabled(false);
			
			btnCreateABorrowInCatalogs = new JButton("Create  a Borrow");
			btnCreateABorrowInCatalogs.setEnabled(false);
			btnCreateABorrowInCatalogs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSelectedBookToBorrow();
				}
			});
			btnCreateABorrowInCatalogs.setBounds(1054, 505, 142, 23);
			this.add(btnCreateABorrowInCatalogs);
			
			btnAddBookInCatalogs = new JButton("Add a Book");
			btnAddBookInCatalogs.setEnabled(false);
			btnAddBookInCatalogs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSelectedCatalogToAddBook();
				}
			});
			btnAddBookInCatalogs.setBounds(1054, 228, 109, 23);
			this.add(btnAddBookInCatalogs);
			
			JFormattedTextField fmtdtxtFTitleCatalogsList = new JFormattedTextField();
			fmtdtxtFTitleCatalogsList.setFont(new Font("Tahoma", Font.BOLD, 11));
			fmtdtxtFTitleCatalogsList.setEditable(false);
			fmtdtxtFTitleCatalogsList.setHorizontalAlignment(SwingConstants.CENTER);
			fmtdtxtFTitleCatalogsList.setBorder(new LineBorder(new Color(30, 144, 255)));
			fmtdtxtFTitleCatalogsList.setBackground(new Color(210, 105, 30));
			fmtdtxtFTitleCatalogsList.setText("All Catalogs / Search result");
			fmtdtxtFTitleCatalogsList.setBounds(30, 133, 344, 35);
			this.add(fmtdtxtFTitleCatalogsList);
			
			JFormattedTextField fmtdtxtFTitleBooksInCatalog = new JFormattedTextField();
			fmtdtxtFTitleBooksInCatalog.setFont(new Font("Tahoma", Font.BOLD, 11));
			fmtdtxtFTitleBooksInCatalog.setEditable(false);
			fmtdtxtFTitleBooksInCatalog.setText("Books in the selected Catalog");
			fmtdtxtFTitleBooksInCatalog.setHorizontalAlignment(SwingConstants.CENTER);
			fmtdtxtFTitleBooksInCatalog.setBorder(new LineBorder(new Color(30, 144, 255)));
			fmtdtxtFTitleBooksInCatalog.setBackground(new Color(210, 105, 30));
			fmtdtxtFTitleBooksInCatalog.setBounds(546, 195, 475, 35);
			this.add(fmtdtxtFTitleBooksInCatalog);
			
		}		
	
	protected Catalog getCatalog(int idCatalog ) {
		return service.getCatalog(idCatalog);
	}
	
	protected void getSelectedCatalogToAddBook() {
		int catalogId = modelLstSearchCatalogs.getElementAt(lstSearchCatalogs.getSelectedIndex()).getId();
		myLibrary.tabbedPane.setSelectedIndex(1);
		myLibrary.paneBook.blankFields();
		myLibrary.paneBook.addCatalogsToCbo();
		boolean catalogFound = false;
		int i = 0;
		Catalog c = null;
		while (i < myLibrary.paneBook.cboCatalogInBook.getModel().getSize() || !catalogFound) {
			if (myLibrary.paneBook.cboCatalogInBook.getItemAt(i).getId() == catalogId) {
				catalogFound = true;
				c = myLibrary.paneBook.cboCatalogInBook.getItemAt(i);
			}
			i++;
		};
		myLibrary.paneBook.cboCatalogInBook.setSelectedItem(c);
	}

	//METHODS HERE AFTER
	
	protected void getSelectedBookToBorrow() {
		long id = modelLstBooksByCatalog.getElementAt(lstBooksByCatalog.getSelectedIndex()).getIsbn();
		if (id != 0) {
			myLibrary.tabbedPane.setSelectedIndex(3);
			myLibrary.paneBorrow.getTxtIsbnInBorrow().setText(String.valueOf(id));
			myLibrary.paneBorrow.getBookValuesInBorrow();
		}
	}

	protected void isBookSelected() {
		if (!modelLstBooksByCatalog.isEmpty() && !lstBooksByCatalog.isSelectionEmpty()) {
			btnCreateABorrowInCatalogs.setEnabled(true);
		}
		else btnCreateABorrowInCatalogs.setEnabled(false);
	}

	protected void isCatalogSelected() {
		if (!modelLstSearchCatalogs.isEmpty() && !lstSearchCatalogs.isSelectionEmpty()) {
			btnDeleteCatalog.setEnabled(true);
			btnAddBookInCatalogs.setEnabled(true);
			Catalog c = modelLstSearchCatalogs.getElementAt(lstSearchCatalogs.getSelectedIndex());
			getBookList(c);
		}
		else {
			btnDeleteCatalog.setEnabled(false);
			btnAddBookInCatalogs.setEnabled(false);
		}
		
	}
	

	protected void getBookList(Catalog c) {
		modelLstBooksByCatalog.clear();
		ArrayList<Book> myBooks = getBooksFromCatalog(c.getId());
		if (getBooksFromCatalog(c.getId()).size() == 0)
			modelLstBooksByCatalog.addElement(new Book("Aucun livre dans cette liste"));
		else {
			for (int i = 0; i < myBooks.size(); i++) {
				modelLstBooksByCatalog.addElement(myBooks.get(i));
			}
		}
	}

	private ArrayList<Book> getBooksFromCatalog(int catalogId) {
		return service.getBooksFromCatalog(catalogId);
	}

	
	private void displayAllCatalogs() {
		modelLstSearchCatalogs.clear();
		ArrayList<Catalog> myCatalogs = allCatalogs();
		for (int i = 0; i < myCatalogs.size(); i++) {
			modelLstSearchCatalogs.addElement(myCatalogs.get(i));
		}
		
	}
	protected ArrayList<Catalog> allCatalogs() {
		return service.allCatalogs();		
	}
	
	private void displaySelectedCatalogs() {
		modelLstSearchCatalogs.clear();
		ArrayList<Catalog> myCatalogs = searchCatalog();
		for (int i = 0; i < myCatalogs.size(); i++) {
			modelLstSearchCatalogs.addElement(myCatalogs.get(i));
		}
	}

	protected void addCatalog() {
		String name = txtNewCatalog.getText();
		service.addCatalog(name);
		displayAllCatalogs();
		btnAddCatalog.setEnabled(false);
		txtNewCatalog.setText("");
		txtNewCatalog.setEnabled(false);
		myLibrary.paneBook.addCatalogsToCbo();
	}

	protected void newCatalog() {
		txtNewCatalog.setEnabled(true);
		btnAddCatalog.setEnabled(true);
		
	}
	
	protected void deleteCatalog() {
		Catalog c = modelLstSearchCatalogs.getElementAt(lstSearchCatalogs.getSelectedIndex());
		if (getBooksFromCatalog(c.getId()).size() == 0) {
			service.deleteCatalog(c.getId());
			displayAllCatalogs();
			myLibrary.paneBook.addCatalogsToCbo();
		}
		else {
			JOptionPane.showMessageDialog(myLibrary,
				    "Impossible de supprimer le Catalogue " + c.getCatalogName() + " \r\n" +
			"Retirez tous les livres de ce catalogue pour le supprimer !",
				    "Library Manager 1.2 : ERROR",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	protected ArrayList<Catalog> searchCatalog() {
		String name = txtSearchCatalog.getText();
		return service.searchCatalog(name);		
	}
	

}
