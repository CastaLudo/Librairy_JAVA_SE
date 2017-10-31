/**
 * 
 */
package fr.afpa.swing.libraryv1_3.model;

/**
 * @author 34011-79-08
 *
 */
public class Catalog {
	
	private String catalogName;
	private int id;
	
	public Catalog(int id, String name) {
		setCatalogName(name);
		setId(id);
	}
	
	public Catalog(String name) {
		setCatalogName(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (" Catalog n°" + this.getId() +" - "+ catalogName);
	}

	/**
	 * @return the name
	 */
	public String getCatalogName() {
		return catalogName;
	}

	/**
	 * @param name the name to set
	 */
	public void setCatalogName(String name) {
		this.catalogName = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
