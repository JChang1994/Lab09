package edu.handong.csee.java.salesreporter;
//We use the Scanner utility to get the user's input data
import java.util.Scanner;
/**
 * The SalesReporter class is used to generate a sales report. 
 * The class is able get the users data, compute the data, and display the computed data
 */
public class SalesReporter {
	//Initialize all the necessary variables.
	private double highestSales = 0.0;
	private double averageSales = 0.0;
	private SalesAssociate[] team;
	private int numberOfAssociates = 0;
	
	/**
	 * The main function is used to start the program
	 * @param args
	 */
	public static void main(String[] args) {
		//We create a SalesReport object so that we can interact with it and
		//get all the users data, compute it, and display it.
		SalesReporter report = new SalesReporter();
		
		report.getData();
		report.computeStats();
		report.displayResults();
	}
	/**
	 * The getData function is used to get the users data
	 */
	public void getData() {
		Scanner keyboard = new Scanner(System.in);
		//We prompt the user to input the number of sales Associates
		System.out.println("Enter number of sales associates: ");
		numberOfAssociates = keyboard.nextInt();
		keyboard.nextLine();
		
		//Using the number of associates we create an array that has
		//the same amount of SalesAssociate objects as there is sales associates
		//by using a for loop
		team = new SalesAssociate[numberOfAssociates];
		for(int i = 0; i < numberOfAssociates; i++) {
			team[i] = new SalesAssociate();
		}
		
		//We use a for loop to enter in each SalesAssociate objects data
		for(int i = 0; i < numberOfAssociates; i++){
			System.out.println("Enter data for associate number " + (i + 1));
			
			System.out.print("Enter name of sales associate: ");
			String name = keyboard.nextLine();
			team[i].setName(name);
			
			System.out.print("Enter associate's sales: $");
			double sales = keyboard.nextDouble();
			team[i].setSales(sales);
			keyboard.nextLine();
		}
	}
	
	/**
	 * The computeStats function is used to calculate the average sales and highest sales
	 * It also sorts the users data based on the number of sales.
	 */
	public void computeStats() {
		//We use this for loop to find the total amount of sales as well as the highest
		//Sales and then we put the SalesAssociate with the highest sales into
		//the 0 position of the array to signify that it has the highest sales
		for(int i = 0; i < numberOfAssociates; i++){
			double associateSales = team[i].getSales();
			averageSales += associateSales;
			if(associateSales > highestSales) {
				highestSales = associateSales;
				SalesAssociate temp = team[i];
				team[i] = team[0];
				team[0] = temp;
			}
		}
		//We compute the average by taking the total amount that we got earlier
		//and divide it by the number of associates.
		averageSales = averageSales / (float) numberOfAssociates;
		
		//We use these for loops to go through each entry and determine 
		//If the next entry is greater than the current one and if it is then
		//switch places with the next entry so that we get the highest entries
		//at the top of the array starting from 0.
		for(int i = 0; i < numberOfAssociates - 1; i++) {
			for(int j = 0;j<(numberOfAssociates - 1); j++) {
				if(team[i].getSales() > team[j].getSales()) {
					SalesAssociate xtemp = team[j];
					team[j] = team[i];
					team[i] = xtemp;
				}
			}
		}
	}
	/**
	 * The displayResults function displays the results of the computed stats to the user
	 */
	public void displayResults() {
		//Print out all the information that we computed
		System.out.println("Average sales per associate is $" + averageSales);
		System.out.println("The highest sales figure is $" + highestSales);
		
		//We print out the highest sales here and we can print it like this
		//because we organized the array to have the greatest sales at 0
		System.out.println("The following had the highest sales:");
		System.out.println("Name: " + team[0].getName());
		System.out.println("Sales: $" + team[0].getSales());
		System.out.println("$" + (team[0].getSales() - averageSales) + " above the average.");
			
		//We use this for loop to go through each SalesAssociate after the highest
		//Sales SalesAssociate and print their information.
		System.out.println("The rest performed as follows:");
		for(int i = 1; i < numberOfAssociates; i++){
			System.out.println("Name: " + team[i].getName());
			System.out.println("Sales: $" + team[i].getSales());
			//We need to check if they associate had more sales than the average
			//or less so that we can print the information accurately.
			if(team[i].getSales() > averageSales)
				System.out.println("$" + (team[i].getSales() - averageSales) + " above the average.");
			else
				System.out.println("$" + (averageSales - team[i].getSales()) + " below the average.");
		}
	} 
	
}
