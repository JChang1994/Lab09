package edu.handong.csee.java.salesreporter;
//We use the Scanner utility to get the user's input data
import java.util.Scanner;
import java.util.ArrayList;
/**
 * The SalesReporter class is used to generate a sales report. 
 * The class is able get the users data, compute the data, and display the computed data
 */
public class SalesReporter {
	//Initialize all the necessary variables.
	private double highestSales = 0.0;
	private double averageSales = 0.0;
	private ArrayList<SalesAssociate> team = new ArrayList<SalesAssociate>();
	
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
		//We instantiate a new Scanner so that we can collect users input
		Scanner keyboard = new Scanner(System.in);
		
		boolean addAnother = true;
		int count = 0;
		boolean isValid = false;
		
		//This while loop will keep repeating until the user enters no more associates
		//by replying no to the question
		while(addAnother) {
			String anotherAssociate;
			//We instantiate a new SalesAssociate so that we can save their information
			SalesAssociate member = new SalesAssociate();
			//We ask the user to enter in the data for this associate
			System.out.println("Enter data for associate number " + (count + 1));
			//We collect the users input of the name of the sales associate and
			//set it in the member object using .setName
			System.out.print("Enter name of sales associate: ");
			String name = keyboard.nextLine();
			member.setName(name);
			//We do the same with the associate's sales we collect user input
			//then set it in the member object using setSales
			System.out.print("Enter associate's sales: $");
			double sales = keyboard.nextDouble();
			member.setSales(sales);
			//We then add the member object to the team ArrayList
			team.add(member);
			keyboard.nextLine();
			
			//We use this while loop to keep checking whether the user inputs yes/no
			//If they do not input yes or no then we ask the question again after
			//telling them that the input they put in was invalid.
			while(isValid == false) {
				System.out.print("Would you like to add another associate? Enter yes/no ");
				anotherAssociate = keyboard.nextLine();
				//These if checks if what the user's input was and if it was a valid input
				//Then respond accordingly. If no then we stop recording more associates, and
				//if yes then we continue to record more associates.
				if(anotherAssociate.equalsIgnoreCase("no")) {
					addAnother = false;
					break;
				}
				else if(anotherAssociate.equalsIgnoreCase("yes")) {
					break;
				}
				else {
					System.out.println("Not a valid entry");
					isValid = false;
				}
			}
			//We add one to count so that we can just keep a visual counter of how many
			//associates were recorded to show to the user.
			count++;
		}
	}
	
	/**
	 * The computeStats function is used to calculate the average sales and highest sales
	 * It also sorts the users data based on the number of sales.
	 */
	public void computeStats() {
		//We use this for loop to find the total amount of sales as well as the highest
		//Sales and then we put the SalesAssociate with the highest sales into
		//the 0 position of the ArrayList to signify that it has the highest sales
		int teamListSize = team.size();
		for(int i = 0; i < teamListSize; i++){
			double associateSales = team.get(i).getSales();
			averageSales += associateSales;
			if(associateSales > highestSales) {
				highestSales = associateSales;
				SalesAssociate temp = team.get(i);
				team.set(i, team.get(0));
				team.set(0, temp);
			}
		}
		//We compute the average by taking the total amount that we got earlier
		//and divide it by the number of associates.
		averageSales = averageSales / (float) teamListSize;
		
		//We use these for loops to go through each entry and determine 
		//If the next entry is greater than the current one and if it is then
		//switch places with the next entry so that we get the highest entries
		//at the top of the array starting from 0.
		for(int i = 0; i < teamListSize - 1; i++) {
			for(int j = 0;j<(teamListSize - 1); j++) {
				if(team.get(i).getSales() > team.get(j).getSales()) {
					SalesAssociate xtemp = team.get(j);
					team.set(j, team.get(i));
					team.set(i, xtemp);
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
		//because we organized the ArrayList to have the greatest sales at 0
		System.out.println("The following had the highest sales:");
		System.out.println("Name: " + team.get(0).getName());
		System.out.println("Sales: $" + team.get(0).getSales());
		System.out.println("$" + (team.get(0).getSales() - averageSales) + " above the average.");
			
		//We use this for loop to go through each SalesAssociate after the highest
		//Sales SalesAssociate and print their information.
		int teamListSize = team.size();
		System.out.println("The rest performed as follows:");
		for(int i = 1; i < teamListSize; i++){
			System.out.println("Name: " + team.get(i).getName());
			System.out.println("Sales: $" + team.get(i).getSales());
			//We need to check if they associate had more sales than the average
			//or less so that we can print the information accurately.
			if(team.get(i).getSales() > averageSales)
				System.out.println("$" + (team.get(i).getSales() - averageSales) + " above the average.");
			else
				System.out.println("$" + (averageSales - team.get(i).getSales()) + " below the average.");
		}
	} 
	
}
