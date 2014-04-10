import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Maze
{
   private int[][] maze;
   private boolean hasExit;
   private int count=0;
   private int win=100000;
   private boolean found=false;


	public Maze()
	{
		//

	}
	public Maze(int size, String line)
	{
		maze = new int[size][size];
		setMaze(size,line);
	}
	public void setMaze(int size, String line){

		String [] spots = line.split(" ");
		int counter = 0;
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				maze[i][j]= Integer.parseInt(spots[counter]);
				counter++;
			}
		}
	}
	public void hasExitPath(int r, int c)
	{

		if(inbounds(r,c)&& maze[r][c]==1){

			if(c==maze.length-1){
				hasExit= true;
				if(win>count){
					maze[r][c]=8;
					count++;
					win=count;
					found=true;
				}

			}
			else{
					maze[r][c] = 0;   //marking
					count++;
					int cur= count;
					if(!found){
						hasExitPath(r,c+1);
						count=cur;
					}
					if(!found){
	   					hasExitPath(r-1,c);
	   					count=cur;
	   				}
					if(!found){
	   					hasExitPath(r+1,c);
	   					count=cur;
	   				}
					if(!found){
	   					hasExitPath(r,c-1);
	   					count=cur;
	   				}

	   				maze[r][c]=1;
	   				if(found)
	   					maze[r][c]=8;


			}

		}
		else if(!(hasExit)){
			hasExit=false;
		}


	}

	public boolean inbounds(int r, int c){
		if(r >=0 && c>=0 && c<maze.length && r <maze.length)
			return true;
			else
				return false;
	}
	public String toString()
	{
		String output="";
		for(int i = 0; i<maze.length; i++){
			for(int j = 0; j<maze.length; j++){
				output+=" "+maze[i][j];
			}
			output+="\n";
		}
		if(hasExit)
			output+="exit found- "+win+" STEPS";//fix

		else
			output+="exit not found";
		return output;
	}
	/*TO DO:
	 *highlight path
	 *output shortest distance*/

}
