package fr.afpa.swing.libraryv1_3.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.afpa.swing.libraryv1_3.service.IServiceLibrary;

import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Dimension;


public class FrameLibrary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IServiceLibrary service;
	private JPanel contentPane;
	protected JTabbedPane tabbedPane;
	
	protected PaneCatalog paneCatalog;
	protected PaneBook paneBook;
	protected PaneSubscriber paneSubscriber;
	protected PaneBorrow paneBorrow;
	protected PaneAuthor paneAuthor;


	/**
	 * Create the frame.
	 */
	public FrameLibrary(IServiceLibrary service) {
		this.service = service;
		
		initFrame();
		initContent();		
	}
	
	public void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setMinimumSize(new Dimension(200, 150));
		tabbedPane.setFont(new Font("Arabic Typesetting", Font.ITALIC, 40));
		tabbedPane.setBounds(0, 11, 1234, 651);
		contentPane.add(tabbedPane);
		
	}
	
	public void initContent() {
		
	//Pane Catalogs
		paneCatalog = new PaneCatalog(service, this);		
		tabbedPane.addTab("  Catalogs  ", null, paneCatalog, null);
		
	//Pane Books
		paneBook = new PaneBook(service, this);
		tabbedPane.addTab("  Books  ", null, paneBook, null);
		
	//Pane Subscribers
		paneSubscriber = new PaneSubscriber(service, this);
		tabbedPane.addTab("  Subscribers  ", null, paneSubscriber, null);
				
	//Pane Borrows
		paneBorrow = new PaneBorrow(service, this);
		tabbedPane.addTab("  Borrows  ", null, paneBorrow, null);
				
	//Pane Author
		paneAuthor = new PaneAuthor(service, this);
		tabbedPane.addTab("  Authors  ", null, paneAuthor, null);
	}
	
	/**
	 * @return the paneBorrow
	 */
	public JPanel getPaneBorrow() {
		return paneBorrow;
	}

	
}
