import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Lab21g
{
	public static void main( String args[] ) throws IOException
	{
		Scanner kb = new Scanner(new File("lab21g.dat"));

		do{
			int size =  kb.nextInt();
			kb.nextLine();

			Maze doug = new Maze(size,kb.nextLine());
			doug.hasExitPath(0,0);
			System.out.println(doug);
			System.out.println("\n\n\n---------------------------------");

		}while(kb.hasNextLine());
		//17 first solve.



	}
}
