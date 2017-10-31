package fr.afpa.swing.libraryv1_3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.afpa.swing.libraryv1_3.model.Author;
import fr.afpa.swing.libraryv1_3.model.Book;
import fr.afpa.swing.libraryv1_3.model.Catalog;
import fr.afpa.swing.libraryv1_3.model.Copy;
import fr.afpa.swing.libraryv1_3.service.IServiceLibrary;

public class PaneBook extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrameLibrary myLibrary;
	private IServiceLibrary service;
	
	private JTextField txtIsbnInBook;
	private JTextField txtTitleInBook;
	private JTextField txtSubtitleInBook;
	//private JTextField txtAuthorInBook;
	protected JComboBox<Author> cboAuthorInBook;
	private DefaultComboBoxModel<Author> modelCboAuthorInBook;
	//private JTextField txtCatalogInBook;
	protected JComboBox<Catalog> cboCatalogInBook;
	private DefaultComboBoxModel<Catalog> modelCboCatalogInBook;
	private JTextField txtNumberOfCopiesInBook;
	private JTextField txtSearchBook;
	private JList<Book> lstSearchBooks;
	private DefaultListModel<Book> modelLstSearchBooks;
	private JList<Copy> lstCopies;
	private DefaultListModel<Copy> modelLstCopies;
	private ButtonGroup isAvailable;
	private JRadioButton rdbtnNotAvailable;
	private JRadioButton rdbtnYesAvailable;
	private JRadioButton rdbtnUnderRepair;
	private JRadioButton rdbtnAvailableCopy;	
	private JButton btnUpdateBook;
	private JButton btnDeleteBook;
	private JButton btnApplyInBooks;
	private JButton btnCancelInBooks;
	private JButton btnCreateBorrowInBooks;
	private JButton btnAddACopy;
	private JButton btnNewBook;
	private JButton btnDeleteCopy;
	private JButton btnApplyState;
	private boolean boolNewBook = false;
	
	
	public PaneBook(IServiceLibrary serviceBook, FrameLibrary frame) {
		this.service = serviceBook;
		this.myLibrary = frame;
		
		initBook();
	}
	
	
	public void initBook() {
		
		this.setLayout(null);
		
		//Left Area
		
		JButton btnAllBooks = new JButton("All Books");
		btnAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayAllBooks();
			}
		});
		btnAllBooks.setBounds(30, 40, 142, 23);
		this.add(btnAllBooks);
		
		JFormattedTextField fmtdtxtFTitleBooksList = new JFormattedTextField();
		fmtdtxtFTitleBooksList.setFont(new Font("Tahoma", Font.BOLD, 11));
		fmtdtxtFTitleBooksList.setEditable(false);
		fmtdtxtFTitleBooksList.setText("All Books / Search result");
		fmtdtxtFTitleBooksList.setHorizontalAlignment(SwingConstants.CENTER);
		fmtdtxtFTitleBooksList.setBorder(new LineBorder(new Color(30, 144, 255)));
		fmtdtxtFTitleBooksList.setBackground(new Color(210, 105, 30));
		fmtdtxtFTitleBooksList.setBounds(30, 133, 344, 35);
		this.add(fmtdtxtFTitleBooksList);
		
		
		lstSearchBooks = new JList<Book>();
		modelLstSearchBooks = new DefaultListModel<>();
		lstSearchBooks.setModel(modelLstSearchBooks);
		lstSearchBooks.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				 isBookSelected();
			}
		});
		JScrollPane scrolLstSearchBooks = new JScrollPane(lstSearchBooks);
		scrolLstSearchBooks.setBounds(30, 168, 344, 360);
		scrolLstSearchBooks.setBorder(new LineBorder(new Color(30, 144, 255)));
		scrolLstSearchBooks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//lstSearchBooks.setBounds(30, 168, 344, 360);
		this.add(scrolLstSearchBooks);
		
		JButton btnSearchBook = new JButton("Search");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaySelectedBooks();
			}
		});
		btnSearchBook.setBounds(246, 94, 89, 23);
		this.add(btnSearchBook);
		
		txtSearchBook = new JTextField();
		txtSearchBook.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtSearchBook.setColumns(10);
		txtSearchBook.setBounds(30, 95, 206, 20);
		this.add(txtSearchBook);
		txtSearchBook.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	displaySelectedBooks();
            }
		});
		
		btnCreateBorrowInBooks = new JButton("Create a Borrow");
		btnCreateBorrowInBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getSelectedCopyToBorrow();
			}
		});
		btnCreateBorrowInBooks.setBounds(388, 505, 130, 23);
		this.add(btnCreateBorrowInBooks);
		btnCreateBorrowInBooks.setEnabled(false);
		
		//Center Area
		btnNewBook = new JButton("New Book");
		btnNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newBook();
			}
		});
		btnNewBook.setBounds(525, 94, 111, 23);
		this.add(btnNewBook);
		
		btnUpdateBook = new JButton("Update");
		btnUpdateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBook();
			}
		});
		btnUpdateBook.setEnabled(false);
		btnUpdateBook.setBounds(660, 94, 89, 23);
		this.add(btnUpdateBook);
		
		btnDeleteBook = new JButton("Delete");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteBook.setEnabled(false);
		btnDeleteBook.setBounds(771, 94, 89, 23);
		this.add(btnDeleteBook);
		
		JLabel lblBook = new JLabel("Book");
		lblBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBook.setBounds(525, 44, 46, 14);
		this.add(lblBook);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(525, 147, 46, 14);
		this.add(lblIsbn);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(525, 184, 46, 14);
		this.add(lblTitle);
		
		JLabel lblSubtitle = new JLabel("SubTitle");
		lblSubtitle.setBounds(525, 219, 46, 14);
		this.add(lblSubtitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(525, 262, 46, 14);
		this.add(lblAuthor);
		
		JLabel lblCatalog = new JLabel("Catalog");
		lblCatalog.setBounds(525, 307, 46, 14);
		this.add(lblCatalog);
		
		JLabel lblNumberOfCopies = new JLabel("Number of copies");
		lblNumberOfCopies.setBounds(525, 370, 111, 14);
		this.add(lblNumberOfCopies);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setBounds(525, 415, 70, 14);
		this.add(lblAvailable);
		
		isAvailable = new ButtonGroup();
		rdbtnYesAvailable = new JRadioButton("Yes");
		rdbtnYesAvailable.setEnabled(false);
		rdbtnYesAvailable.setBorder(new CompoundBorder(new LineBorder(new Color(30, 144, 255)), UIManager.getBorder("CheckBoxMenuItem.border")));
		rdbtnYesAvailable.setBounds(643, 411, 70, 23);
		this.add(rdbtnYesAvailable);
		
		rdbtnNotAvailable = new JRadioButton("No");
		rdbtnNotAvailable.setEnabled(false);
		rdbtnNotAvailable.setBounds(715, 411, 70, 23);
		this.add(rdbtnNotAvailable);
		
		isAvailable.add(rdbtnYesAvailable);
		isAvailable.add(rdbtnNotAvailable);
		isAvailable.clearSelection();
		
		
		btnApplyInBooks = new JButton("Apply");
		btnApplyInBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!boolNewBook) 
					applyUpdateBook();
				else addBook();
			}
		});
		btnApplyInBooks.setEnabled(false);
		btnApplyInBooks.setBounds(648, 469, 89, 23);
		this.add(btnApplyInBooks);
		
		btnCancelInBooks = new JButton("Cancel");
		btnCancelInBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelInBook();
			}
		});
		btnCancelInBooks.setEnabled(false);
		btnCancelInBooks.setBounds(771, 469, 89, 23);
		this.add(btnCancelInBooks);
		
		txtIsbnInBook = new JTextField();
		txtIsbnInBook.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtIsbnInBook.setCaretColor(Color.BLACK);
		txtIsbnInBook.setEditable(false);
		txtIsbnInBook.setBounds(648, 144, 212, 20);
		this.add(txtIsbnInBook);
		txtIsbnInBook.setColumns(10);
		
		txtTitleInBook = new JTextField();
		txtTitleInBook.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtTitleInBook.setEditable(false);
		txtTitleInBook.setBounds(648, 181, 212, 20);
		this.add(txtTitleInBook);
		txtTitleInBook.setColumns(10);
		
		txtSubtitleInBook = new JTextField();
		txtSubtitleInBook.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtSubtitleInBook.setEditable(false);
		txtSubtitleInBook.setBounds(648, 216, 212, 20);
		this.add(txtSubtitleInBook);
		txtSubtitleInBook.setColumns(10);
		
		cboAuthorInBook = new JComboBox<Author>();
		cboAuthorInBook.setBounds(648, 259, 212, 20);
		DefaultListCellRenderer cboACR = new DefaultListCellRenderer();
		cboACR.setHorizontalAlignment(JLabel.CENTER);
		cboAuthorInBook.setRenderer(cboACR);
		modelCboAuthorInBook = new DefaultComboBoxModel<Author>();
		cboAuthorInBook.setModel(modelCboAuthorInBook);
		this.add(cboAuthorInBook);
		addAuthorsToCbo();
		cboAuthorInBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (cboAuthorInBook.getSelectedIndex() != -1)
				getIdForSelectedAuthor();
			}
		});
		
		cboCatalogInBook = new JComboBox<Catalog>();
		cboCatalogInBook.setBounds(648, 304, 212, 20);
		modelCboCatalogInBook = new DefaultComboBoxModel<>();
		cboCatalogInBook.setModel(modelCboCatalogInBook);
		this.add(cboCatalogInBook);
		addCatalogsToCbo();
		cboCatalogInBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (modelCboCatalogInBook.getSelectedItem() !=null)
				getIdForSelectedCatalog();
			}
		});		
		
		txtNumberOfCopiesInBook = new JTextField();
		txtNumberOfCopiesInBook.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtNumberOfCopiesInBook.setEnabled(true);
		txtNumberOfCopiesInBook.setEditable(false);
		txtNumberOfCopiesInBook.setBounds(648, 364, 86, 20);
		this.add(txtNumberOfCopiesInBook);
		txtNumberOfCopiesInBook.setColumns(10);
		
		//Right Area
		JLabel lblCopies = new JLabel("Copies");
		lblCopies.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCopies.setBounds(977, 44, 46, 14);
		this.add(lblCopies);
		
		JFormattedTextField fmtdtxtFTitleCopiesList = new JFormattedTextField();
		fmtdtxtFTitleCopiesList.setEditable(false);
		fmtdtxtFTitleCopiesList.setFont(new Font("Tahoma", Font.BOLD, 11));
		fmtdtxtFTitleCopiesList.setText("Copies list");
		fmtdtxtFTitleCopiesList.setHorizontalAlignment(SwingConstants.CENTER);
		fmtdtxtFTitleCopiesList.setBorder(new LineBorder(new Color(30, 144, 255)));
		fmtdtxtFTitleCopiesList.setBackground(new Color(210, 105, 30));
		fmtdtxtFTitleCopiesList.setBounds(977, 95, 192, 35);
		this.add(fmtdtxtFTitleCopiesList);
		
		
		lstCopies = new JList<Copy>();
		lstCopies.setBorder(new LineBorder(new Color(30, 144, 255)));
		lstCopies.setBounds(977, 130, 192, 254);
		this.add(lstCopies);
		modelLstCopies = new DefaultListModel<Copy>();
		lstCopies.setModel(modelLstCopies);
		lstCopies.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				 isCopySelected();
			}
		});
		
		JLabel lblStateCopy = new JLabel("Change state for the selected copy");
		lblStateCopy.setBounds(977, 447, 206, 14);
		this.add(lblStateCopy);
		

		rdbtnUnderRepair = new JRadioButton("Under Repair");
		rdbtnUnderRepair.setBounds(1008, 469, 109, 23);
		this.add(rdbtnUnderRepair);
		
		rdbtnAvailableCopy = new JRadioButton("Available");
		rdbtnAvailableCopy.setBounds(1008, 490, 109, 23);
		this.add(rdbtnAvailableCopy);
		rdbtnAvailableCopy.setEnabled(false);
		
		btnAddACopy = new JButton("Add a Copy");
		btnAddACopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCopy();
			}
		});
		btnAddACopy.setEnabled(false);
		btnAddACopy.setBounds(1058, 389, 111, 23);
		this.add(btnAddACopy);
		
		btnDeleteCopy = new JButton("Delete Copy");
		btnDeleteCopy.setEnabled(false);
		btnDeleteCopy.setBounds(1058, 413, 111, 23);
		this.add(btnDeleteCopy);
		btnDeleteCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCopy();
			}
		});
		
		btnApplyState = new JButton("Apply State");
		btnApplyState.setBounds(1058, 528, 111, 23);
		this.add(btnApplyState);
		btnApplyState.setEnabled(false);
		btnApplyState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validRepairingState();
			}
		});
	}
	
	protected boolean isCopyAvailable(int idCopy) {
		return service.isCopyAvailable(idCopy);
	}
	
	protected void getSelectedCopyToBorrow() {
		if (lstCopies.getSelectedIndex() != -1) {
			long isbn = Long.valueOf(modelLstCopies.getElementAt(lstCopies.getSelectedIndex()).getBook().getIsbn()).longValue();
			int idCopy = Integer.valueOf(modelLstCopies.getElementAt(lstCopies.getSelectedIndex()).getId()).intValue();
			if (isCopyAvailable(idCopy)) {
				myLibrary.tabbedPane.setSelectedIndex(3);
				myLibrary.paneBorrow.getTxtIsbnInBorrow().setText(String.valueOf(isbn));
				//myLibrary.paneBorrow.getTxtCopyIdInBorrow().setText(String.valueOf(idCopy).toString());
				myLibrary.paneBorrow.getBookValuesInBorrow();
				myLibrary.paneBorrow.getCopiesToBorrow();
			}
			else JOptionPane.showMessageDialog(myLibrary,
				    "Exemplaire emprunté : sélectionnez un exemplaire disponible !", "Library Manager 1.2 : ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		else JOptionPane.showMessageDialog(myLibrary,
			    "Veuillez selectionner un exemplaire 'Disponible' !", "Library Manager 1.2 : INFORMATION", JOptionPane.PLAIN_MESSAGE);
	}


	protected void displaySelectedBooks() {
		modelLstSearchBooks.clear();
		ArrayList<Book> myBooks = selectedBooks();
		for (int i = 0; i <myBooks.size(); i++) {
			modelLstSearchBooks.addElement(myBooks.get(i));
		}
	}
	

	private ArrayList<Book> selectedBooks() {
		String wordsSearch = txtSearchBook.getText();
		return service.searchBook(wordsSearch);
	}


	protected void validRepairingState() {
		if (!modelLstCopies.isEmpty() && !lstCopies.isSelectionEmpty()) {
			long isbn = Long.valueOf(txtIsbnInBook.getText());
			int idCopy = modelLstCopies.getElementAt(lstCopies.getSelectedIndex()).getId();
			if (rdbtnUnderRepair.isSelected()) {
				rdbtnAvailableCopy.setSelected(false);
				service.setCopyUnavailable(isbn, idCopy);
				service.setCopyToStateUnderRepair(isbn, idCopy);
			}
			else service.setCopyAvailable(isbn, idCopy);
			displayCopiesFromBook();
		}		
	}


	protected void isCopySelected() {
		
		if (!modelLstCopies.isEmpty() && !lstCopies.isSelectionEmpty()) {
			btnDeleteCopy.setEnabled(true);
			Copy c = modelLstCopies.getElementAt(lstCopies.getSelectedIndex());
			btnApplyState.setEnabled(true);
			if (service.isCopyAvailable(c.getId())) {
				rdbtnUnderRepair.setSelected(false);
				rdbtnAvailableCopy.setSelected(true);
			}
			else {
				rdbtnAvailableCopy.setSelected(false);
				if (service.isCopyUnderRepair(c.getId()))
					rdbtnUnderRepair.setSelected(true);
				else rdbtnUnderRepair.setSelected(false);
			}
		}
		
	}


	protected void displayCopiesFromBook() {
		modelLstCopies.clear();
		ArrayList<Copy> copiesFromBook = service.getCopies(Long.valueOf(txtIsbnInBook.getText()));
		txtNumberOfCopiesInBook.setText(String.valueOf(copiesFromBook.size()).toString());
		for(int i = 0; i < copiesFromBook.size(); i++) {
			modelLstCopies.addElement(copiesFromBook.get(i));
			if (copiesFromBook.get(i).isAvailable()) 
				rdbtnYesAvailable.setSelected(true);
		}
	}
	
	protected void updateBook() {
		txtIsbnInBook.setEditable(false);
		txtTitleInBook.setEditable(true);
		txtSubtitleInBook.setEditable(true);
		boolNewBook = false;
		btnApplyInBooks.setEnabled(true);
		btnCancelInBooks.setEnabled(true);
	}


	protected void isBookSelected() {
		rdbtnAvailableCopy.setSelected(false);
		rdbtnUnderRepair.setSelected(false);
		if (!modelLstSearchBooks.isEmpty() && !lstSearchBooks.isSelectionEmpty()) {
			btnUpdateBook.setEnabled(true);
			Book b = modelLstSearchBooks.getElementAt(lstSearchBooks.getSelectedIndex());
			Book selectedBook = service.getBook(b.getIsbn());
			txtIsbnInBook.setText(String.valueOf(selectedBook.getIsbn()));
			txtTitleInBook.setText(selectedBook.getTitle());
			txtSubtitleInBook.setText(selectedBook.getSubtitle());
			
			cboAuthorInBook.isEnabled();
			modelCboAuthorInBook.setSelectedItem(selectedBook.getAuthor().getAuthorId());
			//recherche de l'index sélectionné et sélection dans les CBO
			boolean foundA = false;
			int i = 0;
			while (!foundA || i<modelCboAuthorInBook.getSize()) {
				if (cboAuthorInBook.getItemAt(i).getAuthorId() == selectedBook.getAuthor().getAuthorId()) {
					cboAuthorInBook.setSelectedIndex(i);
					foundA = true;
				}
				i++;
			}
			cboCatalogInBook.isEnabled();
			System.out.println(selectedBook.getCatalog().getId());
			modelCboCatalogInBook.setSelectedItem(selectedBook.getCatalog().getId());
			boolean foundC = false;
			int j = 0;
			while (!foundC || j<modelCboCatalogInBook.getSize()) {
				if (cboCatalogInBook.getItemAt(j).getId() == selectedBook.getCatalog().getId()) {
					cboCatalogInBook.setSelectedIndex(j);
					foundC = true;
				}
				j++;
			}
			btnAddACopy.setEnabled(true);
			displayCopiesFromBook();
			if (service.isBookAvailable(b.getIsbn())) {
				btnCreateBorrowInBooks.setEnabled(true);
				rdbtnYesAvailable.setSelected(true);
			}
			else {
				btnCreateBorrowInBooks.setEnabled(false);
				rdbtnNotAvailable.setSelected(true);
			}
		}
		else {
			defaultEnabledFields();
			blankFields();
		}
		
	}

	protected void addCopy() {
		long isbn = Long.valueOf(txtIsbnInBook.getText());
		service.addCopy(isbn);
		isBookSelected();
	}

	protected void cancelInBook() {
		blankFields();
		defaultEnabledFields();
	}
		
	protected int getIdForSelectedAuthor() {
		int idA = -1;
		if (cboAuthorInBook.getSelectedIndex() != -1) {
			Author a = modelCboAuthorInBook.getElementAt(cboAuthorInBook.getSelectedIndex());
			idA = a.getAuthorId();
		}
		return idA;
	}


	protected int getIdForSelectedCatalog() {
		int idC = -1;
		if (cboCatalogInBook.getSelectedIndex() != -1) {
			Catalog c = modelCboCatalogInBook.getElementAt(cboCatalogInBook.getSelectedIndex());
			idC = c.getId();
		}
		return idC;		
	}


	protected void addAuthorsToCbo() {
		ArrayList<Author> authors = service.allAuthors();
		modelCboAuthorInBook.removeAllElements();
		for (int i = 0; i <authors.size(); i++) {
			modelCboAuthorInBook.addElement(authors.get(i));
		}
		cboAuthorInBook.setModel(modelCboAuthorInBook);
	}


	protected void addCatalogsToCbo() {
		ArrayList<Catalog> catalogs = service.allCatalogs();
		modelCboCatalogInBook.removeAllElements();
		for (int i = 0; i <catalogs.size(); i++) {
			modelCboCatalogInBook.addElement(catalogs.get(i));
		}
		cboCatalogInBook.setModel(modelCboCatalogInBook);
	}


	protected void applyUpdateBook() {
		long isbn = Long.valueOf(txtIsbnInBook.getText());
		String title = txtTitleInBook.getText();
		String subtitle = txtSubtitleInBook.getText();
		int idA = getIdForSelectedAuthor();
		int idC = getIdForSelectedCatalog();
		System.out.println(idA + ", " + idC);
		if (idA == -1) {
			if (service.searchAuthor("A renseigner") == null)	
			service.addAuthor(new Author("A renseigner"));
			else idA = service.searchAuthor("A renseigner").get(0).getAuthorId();
		}
		if (idC == -1) {
			if (service.searchCatalog("A renseigner") == null)
				service.addCatalog("A renseigner");
			else idC = service.searchCatalog("A renseigner").get(0).getId();
		}
		if (subtitle !="")
			service.updateBook(isbn, title, subtitle, idA, idC);
		else service.updateBook(isbn, title, null, idA, idC);
		defaultEnabledFields();
		btnUpdateBook.setEnabled(true);
		displayAllBooks();
	}



	protected void addBook() {
		long isbn = Long.parseLong(txtIsbnInBook.getText());
		Author a = null;
		Catalog c = null;
		if (service.getBook(isbn) == null) {
			String title = txtTitleInBook.getText();
			String subtitle = txtSubtitleInBook.getText();
			
			int idA = getIdForSelectedAuthor();
			int idC = getIdForSelectedCatalog();
			if (idA == -1) {
				if (service.searchAuthor("A renseigner").isEmpty())	
				service.addAuthor(new Author("A renseigner"));
				else idA = service.searchAuthor("A renseigner").get(0).getAuthorId();
				a = myLibrary.paneAuthor.getAuthor(idA);
			}
			if (idC == -1) {
				if (service.searchCatalog("A renseigner").isEmpty())
					service.addCatalog("A renseigner");
				else idC = service.searchCatalog("A renseigner").get(0).getId();
				c = myLibrary.paneCatalog.getCatalog(idC);
			}
				service.addBook(new Book(isbn, title, subtitle, a, c));
			displayAllBooks();
			blankFields();
			defaultEnabledFields();
		}
		else  {
			JOptionPane.showMessageDialog(myLibrary, "ERREUR : un livre avec cet ISBN existe déjà !",
					"Library Manager 1.2 : ERROR", JOptionPane.WARNING_MESSAGE);
		}
	}


	protected ArrayList<Book> allBooks() {
		return service.allBooks();
	}
	
	protected void newBook() {
		boolNewBook = true;
		txtIsbnInBook.setEditable(true);
		txtTitleInBook.setEditable(true);
		txtSubtitleInBook.setEditable(true);
		btnApplyInBooks.setEnabled(true);
		btnCancelInBooks.setEnabled(true);
		blankFields();
	}
	
	private void displayAllBooks() {
		modelLstSearchBooks.clear();
		ArrayList<Book> myBooks = allBooks();
		for (int i = 0; i <myBooks.size(); i++) {
			modelLstSearchBooks.addElement(myBooks.get(i));
		}
	}

	protected void deleteCopy() {
		Copy c = modelLstCopies.getElementAt(lstCopies.getSelectedIndex());
		if (isCopyAvailable(c.getId())||c.isUnderRepair()) {
			Object[] options = { "OK", "CANCEL" };
			int selectedValueOption = JOptionPane.showOptionDialog(myLibrary, "Veuillez confirmer la suppression de cet exemplaire", "Library Manager 1.2 : ATTENTION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
			if (selectedValueOption==JOptionPane.OK_OPTION) {
				service.deleteCopy(c.getBook().getIsbn(), c.getId());
			}
		}
		else {
			JOptionPane.showMessageDialog(myLibrary, "IMPOSSIBLE : cette copie est empruntée. \n Elle ne peut être supprimée !", "Library Manager 1.2 : ERREUR", JOptionPane.WARNING_MESSAGE);
		}
	}	
	
	protected void defaultEnabledFields() {
		boolNewBook = false;
		btnCreateBorrowInBooks.setEnabled(false);
		btnNewBook.setEnabled(true);
		btnUpdateBook.setEnabled(false);
		btnDeleteBook.setEnabled(false);
		btnApplyInBooks.setEnabled(false);
		btnCancelInBooks.setEnabled(false);
		txtIsbnInBook.setEditable(false);
		txtTitleInBook.setEditable(false);
		txtSubtitleInBook.setEditable(false);
		btnApplyInBooks.setEnabled(false);
		btnCancelInBooks.setEnabled(false);
		btnApplyState.setEnabled(false);
	}
	
	protected void blankFields() {
		txtIsbnInBook.setText("");
		txtTitleInBook.setText("");
		txtSubtitleInBook.setText("");
		txtNumberOfCopiesInBook.setText("");
		cboAuthorInBook.setSelectedIndex(-1);
		cboCatalogInBook.setSelectedIndex(-1);
		modelLstCopies.clear();
		isAvailable.clearSelection();
		rdbtnAvailableCopy.setSelected(false);
		rdbtnUnderRepair.setSelected(false);
		isAvailable.clearSelection();
	}
}
