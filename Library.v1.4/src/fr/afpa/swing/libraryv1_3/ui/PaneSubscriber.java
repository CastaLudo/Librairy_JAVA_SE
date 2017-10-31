package fr.afpa.swing.libraryv1_3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import fr.afpa.swing.libraryv1_3.model.Book;
import fr.afpa.swing.libraryv1_3.model.Subscriber;
import fr.afpa.swing.libraryv1_3.service.IServiceLibrary;

public class PaneSubscriber extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrameLibrary myLibrary;
	private IServiceLibrary service;
	
	private JTextField txtSearchSubscriber;
	private JTextField txtIdSubscriber;
	private JTextField txtFirstNameSubscriber;
	private JTextField txtLastNameSubscriber;
	private JTextField txtCountBorrows;
	private JList<Subscriber> lstSearchSubscriber;
	private DefaultListModel<Subscriber> modelLstSearchSubscriber;
	private JList<Book> lstBorrowedBooksBySubscriber;
	private DefaultListModel<Book> modelLstBorrowedBooksBySubscriber;
	private JButton btnNewSubscriber;
	private JButton btnUpdateSubscriber;
	private JButton btnDeleteSubscriber;
	private JButton btnApplyInSubscribers;
	private JButton btnCancelInSubscribers;
	private JButton btnReturnABorrowInSubscribers;
	private JButton btnCreateBorrowInSubscribers;	
	private boolean boolNewSubscriber = false;
	
	public PaneSubscriber(IServiceLibrary serviceSubscriber, FrameLibrary frame) {
		this.service = serviceSubscriber;
		this.myLibrary = frame;
		
		initSubscriber();
	}
	
	
	public void initSubscriber() {
		
		this.setLayout(null);
		
		//Left Area
				JButton btnAllSubscribers = new JButton("All Subscribers");
				btnAllSubscribers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						displayAllSubscribers();
					}
				});
				btnAllSubscribers.setBounds(30, 40, 142, 23);
				this.add(btnAllSubscribers);
				
				txtSearchSubscriber = new JTextField();
				txtSearchSubscriber.setBorder(new LineBorder(new Color(30, 144, 255)));
				txtSearchSubscriber.setColumns(10);
				txtSearchSubscriber.setBounds(30, 95, 206, 20);
				this.add(txtSearchSubscriber);
				
				JButton btnSearchSubscriber = new JButton("Search");
				btnSearchSubscriber.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						displaySelectedSubscribers();
					}
				});
				btnSearchSubscriber.setBounds(246, 94, 89, 23);
				this.add(btnSearchSubscriber);
				
				JFormattedTextField fmtdtxtFTitleSubscribersList = new JFormattedTextField();
				fmtdtxtFTitleSubscribersList.setFont(new Font("Tahoma", Font.BOLD, 11));
				fmtdtxtFTitleSubscribersList.setEditable(false);
				fmtdtxtFTitleSubscribersList.setText("All Authors / Search result");
				fmtdtxtFTitleSubscribersList.setHorizontalAlignment(SwingConstants.CENTER);
				fmtdtxtFTitleSubscribersList.setBorder(new LineBorder(new Color(30, 144, 255)));
				fmtdtxtFTitleSubscribersList.setBackground(new Color(210, 105, 30));
				fmtdtxtFTitleSubscribersList.setBounds(30, 133, 344, 35);
				this.add(fmtdtxtFTitleSubscribersList);
				
				lstSearchSubscriber = new JList<Subscriber>();
				lstSearchSubscriber.setBorder(new LineBorder(new Color(30, 144, 255)));
				lstSearchSubscriber.setBounds(30, 168, 344, 360);
				modelLstSearchSubscriber = new DefaultListModel<>();
				lstSearchSubscriber.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						 isSubscriberSelected();
						 displayBorrowedBooks();
					}
				});
				lstSearchSubscriber.setModel(modelLstSearchSubscriber);
				this.add(lstSearchSubscriber);
				
				btnCreateBorrowInSubscribers = new JButton("Create a Borrow");
				btnCreateBorrowInSubscribers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnCreateBorrowInSubscribers.setEnabled(false);
				btnCreateBorrowInSubscribers.setBounds(388, 505, 134, 23);
				this.add(btnCreateBorrowInSubscribers);
				btnCreateBorrowInSubscribers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getSelectedSubscriberToBorrow();
					}
				});
				
				
				//Right Area
				btnNewSubscriber = new JButton("New Subscriber");
				btnNewSubscriber.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						newSubscriber();
					}
				});
				btnNewSubscriber.setBounds(598, 99, 129, 23);
				this.add(btnNewSubscriber);
				
				btnUpdateSubscriber = new JButton("Update");
				btnUpdateSubscriber.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateSubscriber();
					}
				});
				btnUpdateSubscriber.setEnabled(false);
				btnUpdateSubscriber.setBounds(745, 99, 89, 23);
				this.add(btnUpdateSubscriber);
				
				btnDeleteSubscriber = new JButton("Delete");
				btnDeleteSubscriber.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteSubscriber();
					}
				});
				btnDeleteSubscriber.setEnabled(false);
				btnDeleteSubscriber.setBounds(844, 99, 89, 23);
				this.add(btnDeleteSubscriber);
				
				JLabel lblIdSubscriber = new JLabel("ID");
				lblIdSubscriber.setBounds(598, 152, 46, 14);
				this.add(lblIdSubscriber);
				
				txtIdSubscriber = new JTextField();
				txtIdSubscriber.setEnabled(false);
				txtIdSubscriber.setColumns(10);
				txtIdSubscriber.setCaretColor(Color.BLACK);
				txtIdSubscriber.setBorder(new LineBorder(new Color(30, 144, 255)));
				txtIdSubscriber.setBounds(721, 149, 212, 20);
				this.add(txtIdSubscriber);
				
				JLabel lblFisrtNameSubscriber = new JLabel("First Name");
				lblFisrtNameSubscriber.setBounds(598, 189, 66, 14);
				this.add(lblFisrtNameSubscriber);
				
				txtFirstNameSubscriber = new JTextField();
				txtFirstNameSubscriber.setEnabled(false);
				txtFirstNameSubscriber.setColumns(10);
				txtFirstNameSubscriber.setBorder(new LineBorder(new Color(30, 144, 255)));
				txtFirstNameSubscriber.setBounds(721, 186, 212, 20);
				this.add(txtFirstNameSubscriber);
				
				JLabel lblLastNameSubscriber = new JLabel("Last Name");
				lblLastNameSubscriber.setBounds(598, 224, 66, 14);
				this.add(lblLastNameSubscriber);
				
				txtLastNameSubscriber = new JTextField();
				txtLastNameSubscriber.setEnabled(false);
				txtLastNameSubscriber.setColumns(10);
				txtLastNameSubscriber.setBorder(new LineBorder(new Color(30, 144, 255)));
				txtLastNameSubscriber.setBounds(721, 221, 212, 20);
				this.add(txtLastNameSubscriber);
				
				JLabel lblCountBorrows = new JLabel("Borrowing in progress :");
				lblCountBorrows.setBounds(598, 270, 129, 20);
				this.add(lblCountBorrows);
				
				txtCountBorrows = new JTextField();
				txtCountBorrows.setBounds(748, 271, 40, 20);
				this.add(txtCountBorrows);
				txtCountBorrows.setColumns(10);
				
				btnApplyInSubscribers = new JButton("Apply");
				btnApplyInSubscribers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (boolNewSubscriber)
						addSubscriber();
						else applyUpdateSubscriber();							
					}
				});
				btnApplyInSubscribers.setEnabled(false);
				btnApplyInSubscribers.setBounds(885, 270, 89, 23);
				this.add(btnApplyInSubscribers);
				
				btnCancelInSubscribers = new JButton("Cancel");
				btnCancelInSubscribers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelApplyInSubscriber();
					}
				});
				btnCancelInSubscribers.setEnabled(false);
				btnCancelInSubscribers.setBounds(1001, 270, 89, 23);
				this.add(btnCancelInSubscribers);
				
				JFormattedTextField frmtdtxtfldBorrowerBooks = new JFormattedTextField();
				frmtdtxtfldBorrowerBooks.setEditable(false);
				frmtdtxtfldBorrowerBooks.setFont(new Font("Tahoma", Font.BOLD, 11));
				frmtdtxtfldBorrowerBooks.setHorizontalAlignment(SwingConstants.CENTER);
				frmtdtxtfldBorrowerBooks.setText("Borrower Books");
				frmtdtxtfldBorrowerBooks.setBorder(new LineBorder(new Color(30, 144, 255)));
				frmtdtxtfldBorrowerBooks.setBackground(new Color(210, 105, 30));
				frmtdtxtfldBorrowerBooks.setBounds(598, 343, 397, 35);
				this.add(frmtdtxtfldBorrowerBooks);
				
				lstBorrowedBooksBySubscriber = new JList<Book>();
				lstBorrowedBooksBySubscriber.setBorder(new LineBorder(new Color(30, 144, 255)));
				lstBorrowedBooksBySubscriber.setBounds(598, 378, 397, 150);
				modelLstBorrowedBooksBySubscriber = new DefaultListModel<>();
				lstBorrowedBooksBySubscriber.setModel(modelLstBorrowedBooksBySubscriber);
				this.add(lstBorrowedBooksBySubscriber);
				lstBorrowedBooksBySubscriber.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						 isBookSelected();
					}
				});
				
				btnReturnABorrowInSubscribers = new JButton("Return a Borrow");
				btnReturnABorrowInSubscribers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						returnABobbowBySubscriber();
					}
				});
				btnReturnABorrowInSubscribers.setEnabled(false);
				btnReturnABorrowInSubscribers.setBounds(1047, 505, 129, 23);
				this.add(btnReturnABorrowInSubscribers);
	
	}
	
	private void returnABobbowBySubscriber() {
		Book b = modelLstBorrowedBooksBySubscriber.getElementAt(lstBorrowedBooksBySubscriber.getSelectedIndex());
		myLibrary.paneBorrow.getTxtIsbnInBorrow().setText(String.valueOf(b.getIsbn()).toString());
		myLibrary.paneBorrow.getBookValuesInBorrow();
		getSelectedSubscriberToBorrow();
		myLibrary.paneBorrow.getBorrowedCopyToReturn();
	}
	
	/*
	private int getSelectedIdCopyBorrowedBySubscriberAndBook(long book, int subscriberId) {
		return service.getSelectedIdCopyBorrowedBySubscriberAndBook(book, subscriberId);
	}
	*/

	private void countBorrowsForSubscriber() {
		int subscriberId = Integer.valueOf(txtIdSubscriber.getText()).intValue();
		txtCountBorrows.setText(String.valueOf(service.countBorrowsBySubscriber(subscriberId)).toString());
	}

	private void isBookSelected() {
		if (!modelLstBorrowedBooksBySubscriber.isEmpty() && !lstBorrowedBooksBySubscriber.isSelectionEmpty())
			btnReturnABorrowInSubscribers.setEnabled(true);
	}


	private void displayBorrowedBooks() {
		modelLstBorrowedBooksBySubscriber.clear();
		if (!lstSearchSubscriber.isSelectionEmpty()) {
			Subscriber s = modelLstSearchSubscriber.getElementAt(lstSearchSubscriber.getSelectedIndex());
			int id = s.getSubscriberId();
			ArrayList<Book> borrowedBooks = getBorrowedBooksBySubscriber(id);
			for (int i = 0; i < borrowedBooks.size(); i++) {
				modelLstBorrowedBooksBySubscriber.addElement(borrowedBooks.get(i));
			}
		}
	}
	
	private ArrayList<Book> getBorrowedBooksBySubscriber(int id) {
		return service.getBorrowedBooksBySubscriber(id);
	}


	protected void getSelectedSubscriberToBorrow() {
		int id = modelLstSearchSubscriber.getElementAt(lstSearchSubscriber.getSelectedIndex()).getSubscriberId();
		myLibrary.tabbedPane.setSelectedIndex(3);
		myLibrary.paneBorrow.getTxtSubscriberIdInBorrow().setText(String.valueOf(id));
		myLibrary.paneBorrow.getSubscriberValuesInBorrow();
		
	}


	protected void displaySelectedSubscribers() {
		modelLstSearchSubscriber.clear();
		ArrayList<Subscriber> mySelectedSubescribers = searchSubscriber();
		for (int i = 0; i < mySelectedSubescribers.size(); i++) {
			modelLstSearchSubscriber.addElement(mySelectedSubescribers.get(i));
		}
	}
	
	protected ArrayList<Subscriber> searchSubscriber() {
		String name = txtSearchSubscriber.getText();
		return service.searchSubscriber(name);
	}

	protected Subscriber getSubscriber(int id) {
		return service.getSubscriber(id);
	}
	
	protected void isSubscriberSelected() {
		if (!modelLstSearchSubscriber.isEmpty() && !lstSearchSubscriber.isSelectionEmpty()) {
			btnUpdateSubscriber.setEnabled(true);
			Subscriber s = modelLstSearchSubscriber.getElementAt(lstSearchSubscriber.getSelectedIndex());
			int id = s.getSubscriberId();
			txtIdSubscriber.setText(String.valueOf(getSubscriber(id).getSubscriberId()));
			txtFirstNameSubscriber.setText(getSubscriber(id).getSubscriberFirstName());
			txtLastNameSubscriber.setText(getSubscriber(id).getSubscriberLastName());
			txtFirstNameSubscriber.setEnabled(true);
			txtFirstNameSubscriber.setEditable(false);
			txtLastNameSubscriber.setEnabled(true);
			txtLastNameSubscriber.setEditable(false);
			boolNewSubscriber = false;
			btnCreateBorrowInSubscribers.setEnabled(true);
			countBorrowsForSubscriber();
		}		
	}	
	
	protected void newSubscriber() {
		boolNewSubscriber = true;
		txtIdSubscriber.setText("");
		txtFirstNameSubscriber.setEditable(true);
		txtFirstNameSubscriber.setEnabled(true);
		txtFirstNameSubscriber.setText("");
		txtLastNameSubscriber.setEditable(true);
		txtLastNameSubscriber.setEnabled(true);
		txtLastNameSubscriber.setText("");
		btnApplyInSubscribers.setEnabled(true);
		btnCancelInSubscribers.setEnabled(true);
		btnNewSubscriber.setEnabled(false);
		btnUpdateSubscriber.setEnabled(false);
		btnDeleteSubscriber.setEnabled(false);
	}

	protected void updateSubscriber() {
		boolNewSubscriber = false;
		txtFirstNameSubscriber.setEditable(true);
		txtFirstNameSubscriber.setEnabled(true);		
		txtLastNameSubscriber.setEditable(true);
		txtLastNameSubscriber.setEnabled(true);
		btnNewSubscriber.setEnabled(false);
		btnUpdateSubscriber.setEnabled(false);
		btnDeleteSubscriber.setEnabled(true);
		btnApplyInSubscribers.setEnabled(true);
		btnCancelInSubscribers.setEnabled(true);		
	}
	
	protected void applyUpdateSubscriber() {
		int id = Integer.valueOf(txtIdSubscriber.getText()).intValue();
		String firstName = txtFirstNameSubscriber.getText();
		String lastName = txtLastNameSubscriber.getText();
		service.updateSubscriber(id, firstName, lastName);
		btnApplyInSubscribers.setEnabled(false);
		btnCancelInSubscribers.setEnabled(false);
		btnNewSubscriber.setEnabled(true);
		txtFirstNameSubscriber.setEditable(false);
		txtFirstNameSubscriber.setEnabled(false);		
		txtLastNameSubscriber.setEditable(false);
		txtLastNameSubscriber.setEnabled(false);
		displayAllSubscribers();
	}

	protected void addSubscriber() {
		if (txtIdSubscriber.getText().isEmpty()) {
			Pattern pWords = Pattern.compile("[a-zA-Z]{1,}[- ']?[a-zA-Z]*?");
			String firstName = txtFirstNameSubscriber.getText();
			String lastName = txtLastNameSubscriber.getText();
			Matcher first = pWords.matcher(firstName);
			Matcher last = pWords.matcher(lastName);
			if (first.matches() && last.matches()) {
				if (subscriberAlReadyExists(firstName, lastName).size() == 0) {
					Subscriber s = new Subscriber(firstName, lastName);
					service.addSubscriber(s);
				}
				else {
					Object[] options = { "OK", "CANCEL" };
					int selectedValueOption = JOptionPane.showOptionDialog(null, "Un abonné avec ces informations existe déjà \n"
							+ "Confirmez-vous la création de ce nouvel abonné ?", "Library Manager 1.2 : ATTENTION",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					if (selectedValueOption==JOptionPane.OK_OPTION) {
						Subscriber s = new Subscriber(firstName, lastName);
						service.addSubscriber(s);
					}
				}
			}
			defaultEnabledFields();
		}
	}
	
	
	private void defaultEnabledFields() {
		btnApplyInSubscribers.setEnabled(false);
		btnCancelInSubscribers.setEnabled(false);
		btnNewSubscriber.setEnabled(true);
		txtIdSubscriber.setText("");
		txtFirstNameSubscriber.setText("");
		txtLastNameSubscriber.setText("");
		txtFirstNameSubscriber.setEditable(false);
		txtLastNameSubscriber.setEditable(false);
		displayAllSubscribers();
		boolNewSubscriber = false;
	}
	
	
	private ArrayList<Subscriber> subscriberAlReadyExists(String firstName, String lastName) {
		return service.subscriberAlReadyExists(firstName, lastName);
	}


	protected void cancelApplyInSubscriber() {
		btnApplyInSubscribers.setEnabled(false);
		btnCancelInSubscribers.setEnabled(false);
		btnNewSubscriber.setEnabled(true);
		btnUpdateSubscriber.setEnabled(false);
		btnDeleteSubscriber.setEnabled(false);
		txtIdSubscriber.setText("");
		txtFirstNameSubscriber.setText("");
		txtLastNameSubscriber.setText("");
	}
	
	
	private void displayAllSubscribers() {
		modelLstSearchSubscriber.clear();
		ArrayList<Subscriber> mySubscribers = allSubscribers();
		for (int i = 0; i < mySubscribers.size(); i++) {
			modelLstSearchSubscriber.addElement(mySubscribers.get(i));
		}
	}
	
	
	protected void deleteSubscriber() {
		int id = Integer.valueOf(txtIdSubscriber.getText()).intValue();
		System.out.println(service.countBorrowsBySubscriber(id));
		if (service.countBorrowsBySubscriber(id) == 0) {
			Object[] options = { "OK", "CANCEL" };
			int selectedValueOption = JOptionPane.showOptionDialog(null, "Veuillez confirmer la suppression de cet(te) abonné(e)", "Library Manager 1.2 : ATTENTION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
			if (selectedValueOption==JOptionPane.OK_OPTION) {
				service.deleteSubscriber(id);
			}
		}
		else JOptionPane.showMessageDialog(myLibrary,
			    "Veuillez retourner tous les emprunts avant suppression !", "Library Manager 1.2 : INFORMATION", JOptionPane.PLAIN_MESSAGE);
		cancelApplyInSubscriber();
		displayAllSubscribers();
	}
	

	
	protected ArrayList<Subscriber> allSubscribers() {
		return service.allSubscribers();
	}
}
