import java.io.*;
import java.util.*;

public class OrderProcessor {
	private int orderId, partNum, quanity;
	private double price, tax, shipping, total; 
		
	//no generic constructor is needed, as it will not be used.
	
    public OrderProcessor(int order1, int partNum1, int quanity1, double price1) {
    	//Will hold all variables
    	orderId=order1;
    	partNum=partNum1;
    	quanity=quanity1;
    	price=price1;
    	//parts to be calculated
    	tax=calcTax();
    	shipping=calcShipping();
    	total=addTotal();
    }
    
    public double calcTax(){
    	//find tax, 2% rate
    	return (price*quanity)*.02;
    }
    
    public double calcShipping(){
    	//find shipping, calcuated before taxes, 5% rate
    	return (price*quanity)*.05;
    }
    
    public double addTotal(){
    	//add all applicable variables together
    	return (price*quanity)+tax+shipping;
    }
    
    public void OrdersProcessed()throws IOException{
    	//Will create the output file
    	FileWriter fileOut = new FileWriter("D:\\CS2\\JavaBPA\\OrderProcessor_HunterBrown\\OrdersProcessed.txt");
    	PrintWriter out= new PrintWriter(fileOut);
    	
    	out.println("Order Id: "+orderId);
    	out.println("Part Num: "+partNum);
    	out.println("Price: "+price);
    	out.println("Quanity: "+quanity);
    	out.println("Tax: "+tax);
    	out.println("Shipping: "+shipping);
    	out.println("Total: "+total);
    	out.close();
    	
    }
    		/*Example Ouput
    		Order Id: 1 
			Part Num: 111 
			Price: 4.99 
			Quantity: 3 
			Tax: 0.2994 
			Shipping: 0.7485 
			Total: 16.0179 */
    
    
}
