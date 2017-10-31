package fr.afpa.swing.libraryv1_3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import fr.afpa.swing.libraryv1_3.model.Copy;
import fr.afpa.swing.libraryv1_3.model.Subscriber;
import fr.afpa.swing.libraryv1_3.service.IServiceLibrary;

public class PaneBorrow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrameLibrary myLibrary;
	private IServiceLibrary service;

	private JTextField txtSearchBorrowBySubscriber;	
	private JTextField txtSubscriberIdInBorrow;
	private JTextField txtSubscriberInformationInBorrow;
	private JTextField txtIsbnInBorrow;
	private JTextField txtCopyIdInBorrow;
	private JTextField txtBookValuesInBorrow;
	private JTextField txtSearchBorrowByTitle;	
	private JList<Copy> lstSearchBorrows;
	private DefaultListModel<Copy> modelLstSearchBorrows;
	private JButton btnReturnBorrowInBorrows;
	private JButton btnConfirmBorrowInBorrow;
	private JButton btnConfirmReturnInBorrow;
	private JComboBox<Copy> cboCopiesToBorrow;
	private DefaultComboBoxModel<Copy> mdlCboCopiesToBorrow;
	private JComboBox<Copy> cboCopiesToReturn;
	private DefaultComboBoxModel<Copy> mdlCboCopiesToReturn;
	
	
	public PaneBorrow(IServiceLibrary serviceBorrow, FrameLibrary frame) {
		this.service = serviceBorrow;
		this.myLibrary = frame;

		initBorrow();
	}


	public void initBorrow() {

		this.setLayout(null);

		//Left Area
		JButton btnAllBorrows = new JButton("Current Borrows");
		btnAllBorrows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaySearchBorrows();
			}
		});
		btnAllBorrows.setBounds(30, 40, 142, 23);
		this.add(btnAllBorrows);

		txtSearchBorrowByTitle = new JTextField();
		txtSearchBorrowByTitle.setText("by title...");
		txtSearchBorrowByTitle.setColumns(10);
		txtSearchBorrowByTitle.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtSearchBorrowByTitle.setBounds(30, 74, 206, 20);
		txtSearchBorrowByTitle.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e){
				txtSearchBorrowByTitle.setText("");
			}
			public void focusLost(FocusEvent e) {
				if (txtSearchBorrowByTitle.getText().isEmpty())
				txtSearchBorrowByTitle.setText("by title...");
			}
		});
		this.add(txtSearchBorrowByTitle);

		txtSearchBorrowBySubscriber = new JTextField();
		txtSearchBorrowBySubscriber.setText("by subscriber...");
		txtSearchBorrowBySubscriber.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtSearchBorrowBySubscriber.setColumns(10);
		txtSearchBorrowBySubscriber.setBounds(30, 102, 206, 20);
		this.add(txtSearchBorrowBySubscriber);
		txtSearchBorrowBySubscriber.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e){
				txtSearchBorrowBySubscriber.setText("");
			}
			public void focusLost(FocusEvent e) {
				if (txtSearchBorrowBySubscriber.getText().isEmpty())
				txtSearchBorrowBySubscriber.setText("by subscriber...");
			}
		});

		JButton btnSearchBorrow = new JButton("Search");
		btnSearchBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaySearchBorrows();
			}
		});
		btnSearchBorrow.setBounds(246, 94, 89, 23);
		this.add(btnSearchBorrow);

		JFormattedTextField fmtdtxtFTitleBorrowsList = new JFormattedTextField();
		fmtdtxtFTitleBorrowsList.setEditable(false);
		fmtdtxtFTitleBorrowsList.setFont(new Font("Tahoma", Font.BOLD, 11));
		fmtdtxtFTitleBorrowsList.setText("Borrows List / Search result");
		fmtdtxtFTitleBorrowsList.setHorizontalAlignment(SwingConstants.CENTER);
		fmtdtxtFTitleBorrowsList.setBorder(new LineBorder(new Color(30, 144, 255)));
		fmtdtxtFTitleBorrowsList.setBackground(new Color(210, 105, 30));
		fmtdtxtFTitleBorrowsList.setBounds(30, 133, 344, 35);
		this.add(fmtdtxtFTitleBorrowsList);

		lstSearchBorrows = new JList<Copy>();
		lstSearchBorrows.setBorder(new LineBorder(new Color(30, 144, 255)));
		lstSearchBorrows.setBounds(30, 168, 344, 360);
		modelLstSearchBorrows = new DefaultListModel<>();
		lstSearchBorrows.setModel(modelLstSearchBorrows);
		lstSearchBorrows.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				isCurrentBorrowSelected();
			}
		});
		this.add(lstSearchBorrows);

		btnReturnBorrowInBorrows = new JButton("Return the Borrow");
		btnReturnBorrowInBorrows.setEnabled(false);
		btnReturnBorrowInBorrows.setBounds(388, 505, 134, 23);
		this.add(btnReturnBorrowInBorrows);

		//Right Area		

		JLabel label = new JLabel("Borrow's Informations");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(606, 44, 142, 14);
		this.add(label);

		JLabel lblBorrowerIdInBorrow = new JLabel("Borrower's Id");
		lblBorrowerIdInBorrow.setBounds(606, 105, 107, 14);
		this.add(lblBorrowerIdInBorrow);

		txtSubscriberIdInBorrow = new JTextField();
		txtSubscriberIdInBorrow.setColumns(10);
		txtSubscriberIdInBorrow.setCaretColor(Color.BLACK);
		txtSubscriberIdInBorrow.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtSubscriberIdInBorrow.setBounds(729, 102, 212, 20);
		this.add(txtSubscriberIdInBorrow);

		txtSubscriberInformationInBorrow = new JTextField();
		txtSubscriberInformationInBorrow.setEnabled(true);
		txtSubscriberInformationInBorrow.setEditable(false);
		txtSubscriberInformationInBorrow.setColumns(10);
		txtSubscriberInformationInBorrow.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtSubscriberInformationInBorrow.setBounds(729, 133, 390, 57);
		this.add(txtSubscriberInformationInBorrow);

		JLabel lblIsbnInBorrow = new JLabel("Book ISBN");
		lblIsbnInBorrow.setBounds(606, 222, 81, 14);
		this.add(lblIsbnInBorrow);

		txtIsbnInBorrow = new JTextField();
		txtIsbnInBorrow.setColumns(10);
		txtIsbnInBorrow.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtIsbnInBorrow.setBounds(729, 219, 212, 20);
		this.add(txtIsbnInBorrow);

		txtBookValuesInBorrow = new JTextField();
		txtBookValuesInBorrow.setEnabled(true);
		txtBookValuesInBorrow.setEditable(false);
		txtBookValuesInBorrow.setColumns(10);
		txtBookValuesInBorrow.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtBookValuesInBorrow.setBounds(729, 250, 390, 69);
		this.add(txtBookValuesInBorrow);

		JLabel lblCopyIdInBorrow = new JLabel("Copy to borrow");
		lblCopyIdInBorrow.setBounds(606, 349, 90, 14);
		this.add(lblCopyIdInBorrow);

		cboCopiesToBorrow = new JComboBox<Copy>();
		cboCopiesToBorrow.setBounds(729, 346, 129, 20);
		this.add(cboCopiesToBorrow);
		mdlCboCopiesToBorrow = new DefaultComboBoxModel<Copy>();
		cboCopiesToBorrow.setModel(mdlCboCopiesToBorrow);

		/*
				txtCopyIdInBorrow = new JTextField();
				txtCopyIdInBorrow.setColumns(10);
				txtCopyIdInBorrow.setBorder(new LineBorder(new Color(30, 144, 255)));
				txtCopyIdInBorrow.setBounds(729, 346, 129, 20);
				this.add(txtCopyIdInBorrow);
		 */

		JButton btnValidateBorrowerIdInBorrow = new JButton("OK");
		btnValidateBorrowerIdInBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSubscriberValuesInBorrow();
				if (txtIsbnInBorrow.getText().length() > 0) {
					System.out.println("try getBorrowedCopyToReturn()");
					getBorrowedCopyToReturn();
				}
			}
		});
		btnValidateBorrowerIdInBorrow.setBounds(952, 101, 89, 23);
		this.add(btnValidateBorrowerIdInBorrow);

		JButton btnValidateIsbnInBorrow = new JButton("OK");
		btnValidateIsbnInBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBookValuesInBorrow();
				if (txtSubscriberIdInBorrow.getText().length() > 0) {
					getCopiesToBorrow();
				}
			}
		});
		btnValidateIsbnInBorrow.setBounds(952, 218, 89, 23);
		this.add(btnValidateIsbnInBorrow);

		btnConfirmBorrowInBorrow = new JButton("Confirm the Borrow");
		btnConfirmBorrowInBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSubscriberIdInBorrow.getText().length() != 0 && txtIsbnInBorrow.getText().length() != 0) {
					addBorrow();
				}
			}
		});
		btnConfirmBorrowInBorrow.setEnabled(true);
		btnConfirmBorrowInBorrow.setBounds(965, 345, 154, 23);
		this.add(btnConfirmBorrowInBorrow);

		JLabel lblCopyIdToReturn = new JLabel("Copy to return");
		lblCopyIdToReturn.setBounds(606, 399, 90, 20);
		this.add(lblCopyIdToReturn);

		cboCopiesToReturn = new JComboBox<Copy>();
		cboCopiesToReturn.setBounds(729, 396, 129, 20);
		this.add(cboCopiesToReturn);
		mdlCboCopiesToReturn = new DefaultComboBoxModel<Copy>();
		cboCopiesToReturn.setModel(mdlCboCopiesToReturn);

		btnConfirmReturnInBorrow = new JButton("Confirm the Return");
		btnConfirmReturnInBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnBorrow();
			}
		});
		btnConfirmReturnInBorrow.setEnabled(true);
		btnConfirmReturnInBorrow.setBounds(965, 395, 154, 23);
		this.add(btnConfirmReturnInBorrow);
	}

	protected void displaySearchBorrows() {
		ArrayList<Copy> searchResult = null;
		if (!txtSearchBorrowByTitle.getText().equals("by title...")) {
			if (!txtSearchBorrowBySubscriber.getText().equals("by subscriber...")) {
				searchResult = searchedBorrowsByTitleAndSubscriber();
			}
			else searchResult = searchedBorrowsByTitle();
		}
		else if (!txtSearchBorrowBySubscriber.getText().equals("by subscriber...")) {
			searchResult = searchedBorrowsBySubscriber();
		}
		else searchResult = service.searchBorrow();
		displayBorrows(searchResult);

	}


	private void displayBorrows(ArrayList<Copy> resultList) {
		modelLstSearchBorrows.clear();
		ArrayList<Copy> borrowedCopies = resultList;
		for (int i = 0; i < borrowedCopies.size(); i++) {
			modelLstSearchBorrows.addElement(borrowedCopies.get(i));
		}
	}


	private ArrayList<Copy> searchedBorrowsByTitleAndSubscriber() {
		String title = txtSearchBorrowByTitle.getText();
		String name = txtSearchBorrowBySubscriber.getText();
		return service.searchedBorrowsByTitleAndSubscriber(title, name);
	}


	private ArrayList<Copy> searchedBorrowsByTitle() {
		String title = txtSearchBorrowByTitle.getText();
		return service.searchedBorrowsByTitle(title);		
	}


	private ArrayList<Copy> searchedBorrowsBySubscriber() {
		String name = txtSearchBorrowBySubscriber.getText();
		return service.searchedBorrowsBySubscriber(name);		
	}


	private void isCurrentBorrowSelected() {
		if (!modelLstSearchBorrows.isEmpty() && !lstSearchBorrows.isSelectionEmpty()) {
			Copy c = modelLstSearchBorrows.getElementAt(lstSearchBorrows.getSelectedIndex());
			System.out.println(" index sélectionné : " + lstSearchBorrows.getSelectedIndex());
			System.out.println("Copie sélectionnée : " +  modelLstSearchBorrows.getElementAt(lstSearchBorrows.getSelectedIndex()));
			Book b = c.getBook();
			System.out.println("Livre sélectionné : " + b);
			txtSubscriberIdInBorrow.setText(String.valueOf(getBorrower(c.getId()).getSubscriberId()).toString());
			getSubscriberValuesInBorrow();
			System.out.println(c.getBook());
			txtIsbnInBorrow.setText(String.valueOf(b.getIsbn()).toString());
			getBookValuesInBorrow();
			getBorrowedCopyToReturn();
			getCopiesToBorrow();
			//btnConfirmBorrowInBorrow.setEnabled(false);
			btnConfirmReturnInBorrow.setEnabled(true);
			//txtCopyIdInBorrow.setText(String.valueOf(c.getId()).toString());
		}
	}

	private Subscriber getBorrower(int idCopy) {
		return service.getBorrower(idCopy);
	}

	private void displayCurrentBorrows() {
		modelLstSearchBorrows.clear();
		ArrayList<Copy> borrowedCopies = service.searchBorrow();
		for (int i = 0; i < borrowedCopies.size(); i++) {
			modelLstSearchBorrows.addElement(borrowedCopies.get(i));
		}
	}

	private void returnBorrow() {
		if (mdlCboCopiesToReturn.getSize() != 0 && mdlCboCopiesToReturn.getSelectedItem() != null) {
			if (txtSubscriberIdInBorrow.getText().length()>0 && txtIsbnInBorrow.getText().length()>0) {
				int idSubscriber = Integer.valueOf(txtSubscriberIdInBorrow.getText()).intValue();
				long isbn = Long.valueOf(txtIsbnInBorrow.getText());
				int idCopy = mdlCboCopiesToReturn.getElementAt(cboCopiesToReturn.getSelectedIndex()).getId();
				System.out.println("copy à retourner : "+ idCopy);
				if (isACurrentBorrow(isbn, idSubscriber, idCopy)) {
					System.out.println("try Return the Borrow");
					service.returnBorrow(idSubscriber, isbn, idCopy);
					System.out.println("Copy will be available hereafter!");
					service.setCopyAvailable(isbn, idCopy);
				}
			}
		}
		displayCurrentBorrows();
	}

	protected void getCopiesToBorrow() {
		if (txtIsbnInBorrow.getText().length() > 0) {
			long isbn = Long.valueOf(txtIsbnInBorrow.getText()).longValue();
			mdlCboCopiesToBorrow.removeAllElements();
			ArrayList<Copy> copiesToBorrow = service.getCopiesToBorrow(isbn);
			System.out.println(copiesToBorrow);
			for (int i = 0; i < copiesToBorrow.size(); i++) {
				mdlCboCopiesToBorrow.addElement(copiesToBorrow.get(i));
			}
			cboCopiesToBorrow.setModel(mdlCboCopiesToBorrow);
		}
	}

	protected void getBorrowedCopyToReturn() {
		if (txtSubscriberIdInBorrow.getText().length() > 0 && txtIsbnInBorrow.getText().length() > 0) {
			int idSubscriber = Integer.valueOf(txtSubscriberIdInBorrow.getText()).intValue();
			long isbn = Long.valueOf(txtIsbnInBorrow.getText());
			mdlCboCopiesToReturn.removeAllElements();
			ArrayList<Copy> copyToReturn = service.getCopyToReturn(idSubscriber, isbn);
			if (!copyToReturn.isEmpty()) {
				for (int i = 0; i < copyToReturn.size(); i++) {
					mdlCboCopiesToReturn.addElement(copyToReturn.get(i));
				}
				cboCopiesToReturn.setModel(mdlCboCopiesToReturn);
				System.out.println(mdlCboCopiesToReturn.getSize());
				cboCopiesToReturn.setSelectedIndex(0);
			}
			else mdlCboCopiesToBorrow.removeAllElements();
		}
	}

	private boolean isACurrentBorrow(long isbn, int idSubscriber, int idCopy) {
		if (service.getSelectedIdCopyBorrowedBySubscriberAndBook(isbn, idSubscriber) == idCopy)
			return true;
		else return false;
	}


	protected void addBorrow() {
		if (mdlCboCopiesToBorrow.getSize() != 0 && mdlCboCopiesToBorrow.getSelectedItem() != null) {
			if (txtSubscriberIdInBorrow.getText().length()>0 && txtIsbnInBorrow.getText().length()>0) {
				int idSubscriber = Integer.valueOf(txtSubscriberIdInBorrow.getText()).intValue();
				long isbn = Long.valueOf(txtIsbnInBorrow.getText());
				if (service.getSelectedIdCopyBorrowedBySubscriberAndBook(isbn, idSubscriber) == -1) {
					int idCopy = mdlCboCopiesToBorrow.getElementAt(cboCopiesToBorrow.getSelectedIndex()).getId();
					if (service.getBorrowedBooksBySubscriber(idSubscriber).size() < 5) {
						if (myLibrary.paneBook.isCopyAvailable(idCopy)) {
							service.addBorrow(idSubscriber, isbn, idCopy);
							service.setCopyUnavailable(isbn, idCopy);
							displayCurrentBorrows();
						}
						else {
							JOptionPane.showMessageDialog(myLibrary,
									"Exemplaire Indisponible !", "Library Manager 1.2 : ACTION IMPOSSIBLE", JOptionPane.PLAIN_MESSAGE);
							displayCurrentBorrows();
						}
					}
					else {
						JOptionPane.showMessageDialog(myLibrary,
								"Nombre d'emprunts maximal atteint !\n ", "Library Manager 1.2 : ACTION IMPOSSIBLE", JOptionPane.PLAIN_MESSAGE);
						displayCurrentBorrows();
					}
				}
				else {
					JOptionPane.showMessageDialog(myLibrary,
							"Cet abonné emprunte déjà un exemplaire de ce livre !\n ", "Library Manager 1.2 : ACTION IMPOSSIBLE", JOptionPane.PLAIN_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(myLibrary,
						"Veuillez sélectionner un exemplaire !\n ", "Library Manager 1.2 : INFORMATION", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}


	protected void getSubscriberValuesInBorrow() {
		int id = Integer.valueOf(txtSubscriberIdInBorrow.getText()).intValue();
		txtSubscriberInformationInBorrow.setText(service.getSubscriber(id).toString());

	}

	protected void getBookValuesInBorrow() {
		long id = Long.valueOf(txtIsbnInBorrow.getText());
		txtBookValuesInBorrow.setText(service.getBook(id).toString());

	}


	/**
	 * @return the txtIsbnInBorrow
	 */
	public JTextField getTxtIsbnInBorrow() {
		return txtIsbnInBorrow;
	}


	/**
	 * @param txtIsbnInBorrow the txtIsbnInBorrow to set
	 */
	public void setTxtIsbnInBorrow(JTextField txtIsbnInBorrow) {
		this.txtIsbnInBorrow = txtIsbnInBorrow;
	}


	/**
	 * @return the txtSubscriberIdInBorrow
	 */
	public JTextField getTxtSubscriberIdInBorrow() {
		return txtSubscriberIdInBorrow;
	}


	/**
	 * @param txtSubscriberIdInBorrow the txtSubscriberIdInBorrow to set
	 */
	public void setTxtSubscriberIdInBorrow(JTextField txtSubscriberIdInBorrow) {
		this.txtSubscriberIdInBorrow = txtSubscriberIdInBorrow;
	}


	/**
	 * @return the txtCopyIdInBorrow
	 */
	public JTextField getTxtCopyIdInBorrow() {
		return txtCopyIdInBorrow;
	}


	/**
	 * @param txtCopyIdInBorrow the txtCopyIdInBorrow to set
	 */
	public void setTxtCopyIdInBorrow(JTextField txtCopyIdInBorrow) {
		this.txtCopyIdInBorrow = txtCopyIdInBorrow;
	}

}
