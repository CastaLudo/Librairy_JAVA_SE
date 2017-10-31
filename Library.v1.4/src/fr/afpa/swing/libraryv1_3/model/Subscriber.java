/**
 * 
 */
package fr.afpa.swing.libraryv1_3.model;

/**
 * @author 34011-79-08
 *
 */
public class Subscriber {
	
	private String subscriberFirstName;
	private String subscriberLastName;
	private int subscriberId;
	
	
	public Subscriber(String fisrtName, String lastName) {
		setSubscriberFirstName(fisrtName);
		setSubscriberLastName(lastName);		
	}
	
	public Subscriber(int id, String fisrtName, String lastName) {
		setSubscriberId(id);
		setSubscriberFirstName(fisrtName);
		setSubscriberLastName(lastName);
		
	}
	
	/**
	 * @return the subscriberFirstName
	 */
	public String getSubscriberFirstName() {
		return subscriberFirstName;
	}
	/**
	 * @return the subscriberLastName
	 */
	public String getSubscriberLastName() {
		return subscriberLastName;
	}
	/**
	 * @return the subscriberId
	 */
	public int getSubscriberId() {
		return subscriberId;
	}
	/**
	 * @param subscriberFirstName the subscriberFirstName to set
	 */
	public void setSubscriberFirstName(String subscriberFirstName) {
		this.subscriberFirstName = subscriberFirstName;
	}
	/**
	 * @param subscriberLastName the subscriberLastName to set
	 */
	public void setSubscriberLastName(String subscriberLastName) {
		this.subscriberLastName = subscriberLastName;
	}
	public void setSubscriberId(int id) {
		this.subscriberId = id;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return subscriberFirstName + " " + subscriberLastName
				+ ", Subscription n°" + subscriberId;
	}
	
	
	

}
