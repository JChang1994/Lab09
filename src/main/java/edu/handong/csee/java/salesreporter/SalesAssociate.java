package edu.handong.csee.java.salesreporter;
/**
 * The SalesAssociate class is used to create an object that represents one of our
 * sales associate.
 * It stores the information of the sales associate including their name and sales
 */
public class SalesAssociate {
	//This is the information that we want SalesAssociate to store
	String name;
	double sales;
	/**
	 * Gets the sales associates name
	 * @return returns the name of the associate
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the sales associates name
	 * @param name The name that we want set into the sales associates name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the sales associates sales
	 * @return returns the sales of the associate
	 */
	public double getSales() {
		return sales;
	}
	/**
	 * Sets the sales associates sales
	 * @param sales The sales that we want set into the sales associates sales
	 */
	public void setSales(double sales) {
		this.sales = sales;
	}
}
