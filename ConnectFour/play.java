import java.util.*;
public class play {
	


        /*To do:
         *When the board is full with no winner, declare a draw
         *A bad AI
         *Then the Master AI*/


    public static void main(String[] args) {

    	Scanner kb = new Scanner(System.in);

        System.out.println("----------------------------\nWelcome to Hunter's Connect 4!\n----------------------------");
        System.out.println("Options!\n----------------------------\n(1)Two-Player (2)Single-Player \n----------------------------");

		  connectFour g = new connectFour();
		

        //Actual like setting up game, uneeded for testing.
        int i=kb.nextInt();
        if(i==1){
        	g.playGame2();
        }
        else if(i==2){
        	System.out.println("Not done yet!");
        	//g.playGame1(easy/medium/hard);
        }
        else{
        	System.exit(0);
        }

    }
}
