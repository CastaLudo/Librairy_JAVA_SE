/**
 * 
 */
package fr.afpa.swing.libraryv1_3.app.copy;

import java.awt.EventQueue;

import fr.afpa.swing.libraryv1_3.dao.DaoLibraryMySql;
import fr.afpa.swing.libraryv1_3.service.ServiceLibrary;
import fr.afpa.swing.libraryv1_3.ui.FrameLibrary;

/**
 * @author 34011-79-08
 *
 */
public class LibraryApplication {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DaoLibraryMySql dao = new DaoLibraryMySql();
					
					ServiceLibrary service = new ServiceLibrary(dao);
					
					FrameLibrary frame = new FrameLibrary(service);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
