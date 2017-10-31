package fr.afpa.swing.libraryv1_3.model;

public class Author {
	
	private String authorFirstName;
	private String authorLastName;
	private int authorId;
	private int yearOfBirth;
	private int yearOfDeath;
	
	
	public Author(int id, String firstName, String lastName, int birth, int death) {
		setAuthorId(id);
		setAuthorFirstName(firstName);
		setAuthorLastName(lastName);
		setYearOfBirth(birth);
		setYearOfDeath(death);
	}
	
	public Author(int id, String firstName, String lastName) {
		setAuthorId(id);
		setAuthorFirstName(firstName);
		setAuthorLastName(lastName);
	}
	
	public Author(String firstName, String lastName, int birth, int death) {
		setAuthorFirstName(firstName);
		setAuthorLastName(lastName);
		setYearOfBirth(birth);
		setYearOfDeath(death);
	}
	
	public Author(String firstName, String lastName, int birth) {
		setAuthorFirstName(firstName);
		setAuthorLastName(lastName);
		setYearOfBirth(birth);
	}
	
	public Author(String firstName, String lastName) {
		setAuthorFirstName(firstName);
		setAuthorLastName(lastName);
	}
	
	public Author(String firstName) {
		setAuthorFirstName(firstName);
	}


	/**
	 * @return the authorFirstName
	 */
	public String getAuthorFirstName() {
		return authorFirstName;
	}


	/**
	 * @return the authorLastName
	 */
	public String getAuthorLastName() {
		return authorLastName;
	}


	/**
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}


	/**
	 * @return the yearOfBirth
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}


	/**
	 * @return the yearOfDeath
	 */
	public int getYearOfDeath() {
		return yearOfDeath;
	}


	/**
	 * @param authorFirstName the authorFirstName to set
	 */
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}


	/**
	 * @param authorLastName the authorLastName to set
	 */
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}


	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	/**
	 * @param yearOfBirth the yearOfBirth to set
	 */
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}


	/**
	 * @param yearOfDeath the yearOfDeath to set
	 */
	public void setYearOfDeath(int yearOfDeath) {
		this.yearOfDeath = yearOfDeath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return authorFirstName + " " + authorLastName + "(" + yearOfBirth + ", " + yearOfDeath +")";
	}

}
