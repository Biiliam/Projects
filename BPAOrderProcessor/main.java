
import java.util.*;
import java.io.*;

public class main {
	 
	private static int orderId, partNum, quanity;
	private static double price;
    public static void main(String [] args) throws IOException{
    	
    	System.out.println("Start processing orders.");
    	//strings from input file into numbers
    	read();
    	//test case
    	OrderProcessor test = new OrderProcessor(orderId,partNum,quanity,price);
    	test.OrdersProcessed();
    	
    	System.out.println("Finished processing orders");
    }
    public static void read() throws IOException{
    	//will read input into usable variables
    	Scanner fileIn= new Scanner(new File("Orders.txt"));
    	String line = fileIn.nextLine();
    	String [] variables= new String[4];
    	int count=0; // will be used to keep track of spot in variables.
    	
    	//I dont know how the inpurt file will actually look, so just incase.
    	if(line.compareTo("ORDER_ID|PART_NUM|PRICE|QUANTITY"))
    		line=fileIn.nextLine();
    		
    	for(int i=0; i<line.length();i++){
    		//will take apart string and input only the numbers into 'variables'
    		int loc = line.indexOf("|",i);
    		if(loc==-1)
    			loc=line.length();
    		variables[count]= line.substring(i,loc);
    		count++;
    		i=loc;
    	}
    	fileIn.close();
    	
    	//variables -to-> numbers
    	orderId=Integer.parseInt(variables[0]);
    	partNum=Integer.parseInt(variables[1]);
    	quanity=Integer.parseInt(variables[3]);
    	price=Double.parseDouble(variables[2]);
    }
    
}
