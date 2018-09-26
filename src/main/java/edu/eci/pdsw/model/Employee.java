package edu.eci.pdsw.model;

/**
 * Employee class
 */
public class Employee {
	/** The person identifier. */
	private int personId;
	/** The employee salary. */
	private long salary;
	/** The employee social security type. */
	private SocialSecurityType socialSecurityType;

	/**
	 * Default class constructor
	 */
	public Employee() {
		super();
	}

	/**
	 * Constructors using fields,
	 *
	 * @param personId the person id
	 * @param salary the salary
	 * @param socialSecurityType the social security type
	 */
	public Employee(int personId, long salary, SocialSecurityType socialSecurityType) {
		this.personId = personId;
		this.salary = salary;
		this.socialSecurityType = socialSecurityType;
	}

	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @return the salary
	 */
	public long getSalary() {
		return salary;
	}

	/**
	 * @return the socialSecurityType
	 */
	public SocialSecurityType getSocialSecurityType() {
		return socialSecurityType;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(long salary) {
		this.salary = salary;
	}

	/**
	 * @param socialSecurityType the socialSecurityType to set
	 */
	public void setSocialSecurityType(SocialSecurityType socialSecurityType) {
		this.socialSecurityType = socialSecurityType;
	}

}