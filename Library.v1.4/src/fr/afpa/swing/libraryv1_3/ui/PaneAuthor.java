package fr.afpa.swing.libraryv1_3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.afpa.swing.libraryv1_3.model.Author;
import fr.afpa.swing.libraryv1_3.model.Book;
import fr.afpa.swing.libraryv1_3.service.IServiceLibrary;

public class PaneAuthor extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrameLibrary myLibrary;
	private IServiceLibrary service;

	private JTextField txtSearchAuthor;
	private JTextField txtFirstNameInAuthor;
	private JTextField txtLastNameInAuthor;
	private JTextField txtYearOfBirthInAuthor;
	private JTextField txtYearOfDeathInAuthor;	
	private JList<Author> lstSearchAuthors;
	private DefaultListModel<Author> modelLstSearchAuthors;
	private JList<Book> lstBooksByAuthor;
	private DefaultListModel<Book> modelLstBooksByAuthor;
	private JButton btnAddABookInAuthors;
	private JButton btnCancelInAuthor;
	private JButton btnApplyInAuthor;
	private JButton btnDeleteAuthor;
	private JButton btnUpdateAuthor;
	private JButton btnNewAuthor;
	private JButton btnSearchAuthor;
	private boolean boolNewAuthor = false;
	private PaneBook paneBook;


	public PaneAuthor(IServiceLibrary serviceAuthor, FrameLibrary frame) {
		this.service = serviceAuthor;
		this.myLibrary = frame;

		initAuthor();
		paneBook = myLibrary.paneBook;
	}


	public void initAuthor() {


		this.setLayout(null);

		//Left Area
		JButton btnAllAuthors = new JButton("All Authors");
		btnAllAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllAuthors();
			}
		});
		btnAllAuthors.setBounds(30, 40, 142, 23);
		this.add(btnAllAuthors);

		txtSearchAuthor = new JTextField();
		txtSearchAuthor.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtSearchAuthor.setColumns(10);
		txtSearchAuthor.setBounds(30, 95, 206, 20);
		this.add(txtSearchAuthor);
		txtSearchAuthor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				displaySelectedAuthors();
			}
		});

		btnSearchAuthor = new JButton("Search");
		btnSearchAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaySelectedAuthors();
			}
		});
		btnSearchAuthor.setBounds(246, 94, 89, 23);
		this.add(btnSearchAuthor);		

		JFormattedTextField fmtdtxtFTitleAuthorsList = new JFormattedTextField();
		fmtdtxtFTitleAuthorsList.setEditable(false);
		fmtdtxtFTitleAuthorsList.setFont(new Font("Tahoma", Font.BOLD, 11));
		fmtdtxtFTitleAuthorsList.setText("All Authors / Search result");
		fmtdtxtFTitleAuthorsList.setHorizontalAlignment(SwingConstants.CENTER);
		fmtdtxtFTitleAuthorsList.setBorder(new LineBorder(new Color(30, 144, 255)));
		fmtdtxtFTitleAuthorsList.setBackground(new Color(210, 105, 30));
		fmtdtxtFTitleAuthorsList.setBounds(30, 133, 344, 35);
		this.add(fmtdtxtFTitleAuthorsList);

		lstSearchAuthors = new JList<Author>();
		lstSearchAuthors.setBorder(new LineBorder(new Color(30, 144, 255)));
		lstSearchAuthors.setBounds(30, 168, 344, 360);
		modelLstSearchAuthors = new DefaultListModel<>();
		lstSearchAuthors.setModel(modelLstSearchAuthors);
		lstSearchAuthors.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				isAuthorSelected();
			}
		});
		this.add(lstSearchAuthors);

		//Right Area
		btnNewAuthor = new JButton("New Author");
		btnNewAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAuthor();
			}
		});
		btnNewAuthor.setBounds(667, 95, 113, 23);
		this.add(btnNewAuthor);

		btnUpdateAuthor = new JButton("Update");
		btnUpdateAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAuthor();
			}
		});
		btnUpdateAuthor.setEnabled(false);
		btnUpdateAuthor.setBounds(803, 94, 89, 23);
		this.add(btnUpdateAuthor);

		btnDeleteAuthor = new JButton("Delete");
		btnDeleteAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAuthor();
			}
		});
		btnDeleteAuthor.setEnabled(false);
		btnDeleteAuthor.setBounds(913, 94, 89, 23);
		this.add(btnDeleteAuthor);

		JLabel lblFirstNameAuthor = new JLabel("First Name");
		lblFirstNameAuthor.setBounds(667, 148, 71, 14);
		this.add(lblFirstNameAuthor);

		txtFirstNameInAuthor = new JTextField();
		txtFirstNameInAuthor.setEnabled(false);
		txtFirstNameInAuthor.setColumns(10);
		txtFirstNameInAuthor.setCaretColor(Color.BLACK);
		txtFirstNameInAuthor.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtFirstNameInAuthor.setBounds(790, 145, 212, 20);
		this.add(txtFirstNameInAuthor);

		JLabel lblLastNameAuthor = new JLabel("Last Name");
		lblLastNameAuthor.setBounds(667, 185, 71, 14);
		this.add(lblLastNameAuthor);

		txtLastNameInAuthor = new JTextField();
		txtLastNameInAuthor.setEnabled(false);
		txtLastNameInAuthor.setColumns(10);
		txtLastNameInAuthor.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtLastNameInAuthor.setBounds(790, 182, 212, 20);
		this.add(txtLastNameInAuthor);

		JLabel lblYearBirthAuthor = new JLabel("Born in ");
		lblYearBirthAuthor.setBounds(667, 220, 46, 14);
		this.add(lblYearBirthAuthor);

		txtYearOfBirthInAuthor = new JTextField();
		txtYearOfBirthInAuthor.setEnabled(false);
		txtYearOfBirthInAuthor.setColumns(10);
		txtYearOfBirthInAuthor.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtYearOfBirthInAuthor.setBounds(790, 217, 212, 20);
		this.add(txtYearOfBirthInAuthor);

		JLabel lblYearDeathAuthor = new JLabel("Died in");
		lblYearDeathAuthor.setBounds(667, 251, 46, 14);
		this.add(lblYearDeathAuthor);

		txtYearOfDeathInAuthor = new JTextField();
		txtYearOfDeathInAuthor.setEnabled(false);
		txtYearOfDeathInAuthor.setColumns(10);
		txtYearOfDeathInAuthor.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtYearOfDeathInAuthor.setBounds(790, 248, 212, 20);
		this.add(txtYearOfDeathInAuthor);

		btnApplyInAuthor = new JButton("Apply");
		btnApplyInAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!boolNewAuthor)
					applyUpdateAuthor();
				else addAuthor();
			}
		});
		btnApplyInAuthor.setEnabled(false);
		btnApplyInAuthor.setBounds(790, 289, 89, 23);
		this.add(btnApplyInAuthor);

		btnCancelInAuthor = new JButton("Cancel");
		btnCancelInAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blanckFields();
				defaultEnabledFields();
			}
		});
		btnCancelInAuthor.setEnabled(false);
		btnCancelInAuthor.setBounds(913, 289, 89, 23);
		this.add(btnCancelInAuthor);

		JFormattedTextField fmtdtxtFTitleBooksByAuthorList = new JFormattedTextField();
		fmtdtxtFTitleBooksByAuthorList.setEditable(false);
		fmtdtxtFTitleBooksByAuthorList.setFont(new Font("Tahoma", Font.BOLD, 11));
		fmtdtxtFTitleBooksByAuthorList.setHorizontalAlignment(SwingConstants.CENTER);
		fmtdtxtFTitleBooksByAuthorList.setBorder(new LineBorder(new Color(30, 144, 255)));
		fmtdtxtFTitleBooksByAuthorList.setBackground(new Color(210, 105, 30));
		fmtdtxtFTitleBooksByAuthorList.setText("Books availables");
		fmtdtxtFTitleBooksByAuthorList.setBounds(667, 343, 397, 35);
		this.add(fmtdtxtFTitleBooksByAuthorList);

		lstBooksByAuthor = new JList<Book>();
		lstBooksByAuthor.setBorder(new LineBorder(new Color(30, 144, 255)));
		lstBooksByAuthor.setBounds(667, 378, 397, 150);
		modelLstBooksByAuthor = new DefaultListModel<>();
		lstBooksByAuthor.setModel(modelLstBooksByAuthor);
		this.add(lstBooksByAuthor);

		btnAddABookInAuthors = new JButton("Add a Book");
		btnAddABookInAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSelectedAuthorToAddBook();
			}
		});
		btnAddABookInAuthors.setEnabled(false);
		btnAddABookInAuthors.setBounds(1100, 349, 107, 23);
		this.add(btnAddABookInAuthors);

	}

	//METHODS

	private void defaultEnabledFields() {
		txtFirstNameInAuthor.setEnabled(false);
		txtLastNameInAuthor.setEnabled(false);
		txtYearOfBirthInAuthor.setEnabled(false);
		txtYearOfDeathInAuthor.setEnabled(false);
		btnApplyInAuthor.setEnabled(false);
		btnUpdateAuthor.setEnabled(false);
		btnCancelInAuthor.setEnabled(false);
		btnDeleteAuthor.setEnabled(false);
		btnAddABookInAuthors.setEnabled(false);
		btnNewAuthor.setEnabled(true);
		boolNewAuthor = false;
	}

	protected void getSelectedAuthorToAddBook() {
		int authorId = modelLstSearchAuthors.getElementAt(lstSearchAuthors.getSelectedIndex()).getAuthorId();
		paneBook.blankFields();
		paneBook.addAuthorsToCbo();
		boolean authorFound = false;
		int i = 0;
		Author a = null;
		while (i < paneBook.cboAuthorInBook.getModel().getSize() || !authorFound) {
			if (paneBook.cboAuthorInBook.getItemAt(i).getAuthorId() == authorId) {
				authorFound = true;
				a = paneBook.cboAuthorInBook.getItemAt(i);
			}
			i++;
		};
		paneBook.cboAuthorInBook.setSelectedItem(a);
		myLibrary.tabbedPane.setSelectedIndex(1);
		defaultEnabledFields();
	}


	protected void getBookList() {
		modelLstBooksByAuthor.clear();
		int authorId = modelLstSearchAuthors.getElementAt(lstSearchAuthors.getSelectedIndex()).getAuthorId();
		ArrayList<Book> bookList = service.getBookListByAuthor(authorId);
		if (bookList.size() > 0 ) {
			for (int i = 0; i < bookList.size(); i++) {
				modelLstBooksByAuthor.addElement(bookList.get(i));
			}
		}
	}

	protected void displaySelectedAuthors() {
		modelLstSearchAuthors.clear();
		ArrayList<Author> mySelectedAuthors = searchAuthor();// TODO Auto-generated method stub
		for (int i = 0; i < mySelectedAuthors.size(); i++) {
			modelLstSearchAuthors.addElement(mySelectedAuthors.get(i));
		}
		blanckFields();
		defaultEnabledFields();
	}



	protected void applyUpdateAuthor() {
		int id = modelLstSearchAuthors.getElementAt(lstSearchAuthors.getSelectedIndex()).getAuthorId();
		String firstName = txtFirstNameInAuthor.getText();
		String lastName = txtLastNameInAuthor.getText();
		int birth = Integer.valueOf(txtYearOfBirthInAuthor.getText()).intValue();
		int death = Integer.valueOf(txtYearOfDeathInAuthor.getText()).intValue();
		service.updateAuthor(id, firstName, lastName, birth, death);
		isAuthorSelected();
		displayAllAuthors();
		defaultEnabledFields();
		btnUpdateAuthor.setEnabled(true);
		btnAddABookInAuthors.setEnabled(true);
	}

	protected void updateAuthor() {
		txtFirstNameInAuthor.setEditable(true);
		txtFirstNameInAuthor.setEnabled(true);
		txtLastNameInAuthor.setEditable(true);
		txtLastNameInAuthor.setEnabled(true);
		txtYearOfBirthInAuthor.setEditable(true);
		txtYearOfBirthInAuthor.setEnabled(true);
		txtYearOfDeathInAuthor.setEditable(true);
		txtYearOfDeathInAuthor.setEnabled(true);
		boolNewAuthor = false;
		btnDeleteAuthor.setEnabled(true);
		btnApplyInAuthor.setEnabled(true);
		btnCancelInAuthor.setEnabled(true);
	}

	private void blanckFields() {
		txtFirstNameInAuthor.setText("");
		txtLastNameInAuthor.setText("");
		txtYearOfBirthInAuthor.setText("");
		txtYearOfDeathInAuthor.setText("");
	}


	protected void newAuthor() {
		blanckFields();
		txtFirstNameInAuthor.setEditable(true);
		txtFirstNameInAuthor.setEnabled(true);
		txtLastNameInAuthor.setEditable(true);
		txtLastNameInAuthor.setEnabled(true);
		txtYearOfBirthInAuthor.setEditable(true);
		txtYearOfBirthInAuthor.setEnabled(true);
		txtYearOfDeathInAuthor.setEditable(true);
		txtYearOfDeathInAuthor.setEnabled(true);
		btnApplyInAuthor.setEnabled(true);
		btnUpdateAuthor.setEnabled(false);
		btnNewAuthor.setEnabled(false);
		btnCancelInAuthor.setEnabled(true);
		boolNewAuthor = true;
	}

	protected Author getAuthor(int id) {
		return service.getAuthor(id);
	}

	protected void isAuthorSelected() {
		if (!modelLstSearchAuthors.isEmpty() && !lstSearchAuthors.isSelectionEmpty()) {
			btnUpdateAuthor.setEnabled(true);
			Author a = modelLstSearchAuthors.getElementAt(lstSearchAuthors.getSelectedIndex());
			int id = a.getAuthorId();
			txtFirstNameInAuthor.setText(getAuthor(id).getAuthorFirstName());
			txtLastNameInAuthor.setText(getAuthor(id).getAuthorLastName());
			txtYearOfBirthInAuthor.setText(String.valueOf(getAuthor(id).getYearOfBirth()));
			txtYearOfDeathInAuthor.setText(String.valueOf(getAuthor(id).getYearOfDeath()));
			txtFirstNameInAuthor.setEnabled(true);
			txtFirstNameInAuthor.setEditable(false);
			txtLastNameInAuthor.setEnabled(true);
			txtLastNameInAuthor.setEditable(false);
			txtYearOfBirthInAuthor.setEnabled(true);
			txtYearOfBirthInAuthor.setEditable(false);
			txtYearOfDeathInAuthor.setEnabled(true);
			txtYearOfDeathInAuthor.setEditable(false);
			boolNewAuthor = false;
			btnAddABookInAuthors.setEnabled(true);
			getBookList();
		}
	}

	protected void addAuthor() {
		String firstName = txtFirstNameInAuthor.getText();
		String lastName = txtLastNameInAuthor.getText();
		int birth = 0, death = 0;
		Author a = null;
		if (service.authorAlReadyExists(firstName, lastName).size() == 0) {
			if (!txtYearOfBirthInAuthor.getText().isEmpty()) {
				birth = Integer.valueOf(txtYearOfBirthInAuthor.getText()).intValue();

				if (!txtYearOfDeathInAuthor.getText().isEmpty()) {
					death = Integer.valueOf(txtYearOfDeathInAuthor.getText()).intValue();
					a = new Author(firstName, lastName, birth, death);
				}
				else a = new Author(firstName, lastName, birth);
			}
			else a = new Author(firstName, lastName);

			service.addAuthor(a);
			defaultEnabledFields();
			displayAllAuthors();
			myLibrary.paneBook.addAuthorsToCbo();
		}
		else {
			Object[] options = { "OK", "CANCEL" };
			int selectedValueOption = JOptionPane.showOptionDialog(null, "Un auteur avec ces informations existe déjà \n"
					+ "Confirmez-vous la création de cet auteur ?", "Library Manager 1.2 : ATTENTION",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			if (selectedValueOption==JOptionPane.OK_OPTION) {
				if (!txtYearOfBirthInAuthor.getText().isEmpty()) {
					birth = Integer.valueOf(txtYearOfBirthInAuthor.getText()).intValue();

					if (!txtYearOfDeathInAuthor.getText().isEmpty()) {
						death = Integer.valueOf(txtYearOfDeathInAuthor.getText()).intValue();
						a = new Author(firstName, lastName, birth, death);
					}
					else a = new Author(firstName, lastName, birth);
				}
				else a = new Author(firstName, lastName);

				service.addAuthor(a);
				defaultEnabledFields();
				displayAllAuthors();
				myLibrary.paneBook.addAuthorsToCbo();
			}
		}
	}

	private void displayAllAuthors() {
		modelLstSearchAuthors.clear();
		ArrayList<Author> myAuthors = allAuthors();
		for (int i = 0; i < myAuthors.size(); i++) {
			modelLstSearchAuthors.addElement(myAuthors.get(i));
		}
		blanckFields();
		defaultEnabledFields();
	}

	protected ArrayList<Author> searchAuthor() {
		String name = txtSearchAuthor.getText();
		return service.searchAuthor(name);
	}

	protected void deleteAuthor() {
		if (modelLstBooksByAuthor.isEmpty()) {
			int id = modelLstSearchAuthors.getElementAt(lstSearchAuthors.getSelectedIndex()).getAuthorId();
			service.deleteAuthor(id);
			displayAllAuthors();
			blanckFields();
			defaultEnabledFields();
			myLibrary.paneBook.addAuthorsToCbo();
		}
		else JOptionPane.showMessageDialog(myLibrary, "Impossible ! \n"
				+ "Des livres sont associés à cet auteur \n"
				+ "Vous ne pouvez pas le supprimer \n", "Library Manager 1.2 : ERREUR", JOptionPane.WARNING_MESSAGE);
	}

	protected ArrayList<Author> allAuthors() {
		return service.allAuthors();
	}

}
