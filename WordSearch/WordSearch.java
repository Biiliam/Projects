   import java.util.*;
import java.io.*;

public class WordSearch {

	private String[][] grid;
	private ArrayList<String> wordBank;
	private String[][] used;
	private String[] wordLetters;
	private String[] words;
	private int sum11, xr, ogx, ogy,aD,dx,t1;


    public WordSearch() throws IOException
    {
    	used=new String[12][12];
    	grid=new String[12][12];
    	xr=100;
    	int aD=0; //so there is 1 diagonal
    	int dx=0;
    	int t1=0;
    	wordBank= new ArrayList<String>();
    	Scanner fileRead = new Scanner(new File("WordBank.txt"));
		// many code, very doge.
    	for(int x=0; x<100; x++)
    	{
    		wordBank.add(fileRead.nextLine());
    	}
    }

    //current will be length, sum11 must =2 for ifPossible methods.
    public void setGrid()//super setGrid uses all adding methods
    {
    	ArrayList<String> wL = new ArrayList<String>();
    	// make word something random from wordbank.
    	words= new String[10];
		int i=0;
    	while(i<10)
    	{
    		t1=0;
    		sum11=2;
    		int wordChose=(int)(Math.random()*(xr-0+0));
			String word= wordBank.get(wordChose);
			wordBank.remove(word);
			xr--;
			//^picks word.
			wordLetters= new String[word.length()];
			int[] spot=pickSpot();
			int methodPick;
			if(aD==0)
				methodPick=2;
					else{
						methodPick=(int)(Math.random()*(2-0+1)); //will have to change max to 2 when doing diagnol.
					}

			if(i==8 && dx==0){
				String L1=word.charAt(0)+"";
				System.out.print(""); //never getting in here.
				for(int j=0;j<12;j++){
					for(int q=0;q<12;q++){
						if(grid[j][q].compareTo(L1)==0){
							spot[0]=j;
							spot[1]=q;
							j=12;
							q=12;
							t1=1;
							//System.out.println("Word::"+word+" methodPick::"+methodPick+" dx::"+dx);
						}
					}
				}
			}

			if(methodPick==0)//horizontal
			{
				sum11=ifPossibleH(spot[0],spot[1],word.length(),true,2);
				for(int x=0;x<word.length();x++)
						wordLetters[x]=word.substring(x,x+1);
				boolean canOverlap;
				if (sum11>-1)
					canOverlap= isUsedH(spot[0],spot[1],0,true,sum11);
					else
						canOverlap=false;


				if(sum11>-1 && canOverlap==true)
				{
					setHorizontal(word,spot[0],spot[1],0,sum11);
					words[i]=word; // will tell me which words i've used.
					i++;
					if(t1==1)
						dx++;

				}
			}
				else if(methodPick==1) //vertical
				{
					sum11=ifPossibleV(spot[0],spot[1],word.length(),true,2);
					for(int x=0;x<word.length();x++)
						wordLetters[x]=word.substring(x,x+1);
					boolean canOverlap;
					if(sum11>-1)
						canOverlap= isUsedV(spot[0],spot[1],0,true,sum11);
						else
							canOverlap=false;

					if(sum11>-1 && canOverlap==true)
					{
						setVertical(word,spot[0],spot[1],0,sum11);
						words[i]=word;
						i++;
						if(t1==1)
						dx++;

					}
				}
				else if(methodPick==2)
				{
					sum11=ifPossibleD(spot[0],spot[1],word.length(),true,2,0);
					for(int x=0;x<word.length();x++)
						wordLetters[x]=word.substring(x,x+1);
					boolean canOverlap;
						if(sum11>-1)
							canOverlap=isUsedD(spot[0],spot[1],0,true,sum11);
							else
								canOverlap=true;

					if(sum11>-1 && canOverlap==true)
					{
						int ogx=spot[0];
						int ogy=spot[1];

						setDiagonal(word,spot[0],spot[1],0,sum11);
						words[i]=word;
						i++;
						aD++;
						if(t1==1)
						dx++;

					}
				}

    	}





    }
    public int[] pickSpot() //will gib me random spot.
    {
    	int x= (int)(Math.random()*(11-0+1)+0);
    		int y=(int)(Math.random()*(11-0+1)+0);
    	int[]xy= new int[2];
    	xy[0]=x;
    	xy[1]=y;
    	return xy;
    }
    public void fillUsedGrid()
	{
		for(int i=0; i<12;i++)
		{
			for(int j=0;j<12;j++)
			{
				used[i][j]="0";
			}
		}


	}
    public boolean isUsedH(int x,int y, int current, boolean canIt, int sum11) //will determine if spots are used so we can overlap.
    {
    	if(used[x][y].compareTo(wordLetters[current])!=0 && used[x][y].compareTo("0")!=0)
    		return false;
    		else if (current==wordLetters.length-1 && (used[x][y].compareTo("0")==0 || used[x][y].compareTo(wordLetters[current])==0))
    			return canIt;
    	else{
    	if(used[x][y].compareTo("0")==0)
    	{
    		canIt=true;
    		if(sum11==0)
    			return isUsedH(x,y-1,current+1,canIt,sum11);
    			else if(sum11==3)
    				return isUsedH(x,y+1,current+1,canIt,sum11);

    	}
    	else if(used[x][y].compareTo(wordLetters[current])==0)
    	{

    		canIt=true;
    		if(sum11==0)
    			return isUsedH(x,y-1,current+1,canIt,sum11);
    			else if(sum11==3)
    				return isUsedH(x,y+1,current+1,canIt,sum11);

    	}
    	else
    		return false;
    	}
		return false;
    }
    public boolean isUsedV(int x,int y, int current, boolean canIt, int sum11) //will determine if spots are used so we can overlap.
    {
    	if(used[x][y].compareTo(wordLetters[current])!=0 && used[x][y].compareTo("0")!=0)
    		return false;
    		else if (current==wordLetters.length-1 && (used[x][y].compareTo("0")==0 || used[x][y].compareTo(wordLetters[current])==0))
    			return canIt;
    	else{
    	if(used[x][y].compareTo("0")==0)
    	{
    		canIt=true;
    		if(sum11==0)
    			return isUsedV(x-1,y,current+1,canIt,sum11);
    			else if(sum11==3)
    				return isUsedV(x+1,y,current+1,canIt,sum11);

    	}
    	else if(used[x][y].compareTo(wordLetters[current])==0)
    	{
    		//System.out.println("is it here");
    		canIt=true;
    		if(sum11==0)
    			return isUsedV(x-1,y,current+1,canIt,sum11);
    			else if(sum11==3)
    				return isUsedV(x+1,y,current+1,canIt,sum11);

    	}
    	else{
    		canIt=false;
    		return false;
    	}

    	}
		return canIt;
    }
    public boolean isUsedD(int x,int y, int current, boolean canIt, int sum11)
    {
    	// -1 can't,  0 down left, 2 down right, 4 up left,  5 up right
    	if(used[x][y].compareTo(wordLetters[current])!=0 && used[x][y].compareTo("0")!=0)
    		return false;
    		else if (current==wordLetters.length-1 && (used[x][y].compareTo("0")==0 || used[x][y].compareTo(wordLetters[current])==0))
    			return canIt;
    	else{
    	if(used[x][y].compareTo("0")==0)
    	{
    		canIt=true;
    		if(sum11==0)
    			return isUsedD(x-1,y-1,current+1,canIt,sum11);
    			else if(sum11==2)
    				return isUsedD(x+1,y-1,current+1,canIt,sum11);
    				else if(sum11==3)
    					return isUsedD(x+1,y+1,current+1,canIt,sum11);
    					else if(sum11==5)
    						return isUsedD(x-11,y+1,current+1,canIt,sum11);
    	}

    	else if(used[x][y].compareTo(wordLetters[current])==0)
    	{

    		canIt=true;
    		if(sum11==0)
    			return isUsedD(x-1,y-1,current+1,canIt,sum11);
    			else if(sum11==2)
    				return isUsedD(x+1,y-1,current+1,canIt,sum11);
    				else if(sum11==3)
    					return isUsedD(x+1,y+1,current+1,canIt,sum11);
    					else if(sum11==5)
    						return isUsedD(x-1,y+1,current+1,canIt,sum11);
    	}
    	else{
    		canIt=false;
    		return false;
    	}
    	}
		return canIt;
    }
    public int ifPossibleH(int x, int y, int current, boolean canIt, int sum11) //horizontal
    {
		//sum11 = 2 by default.
		// -1 if it can't, 0 left, 3 right.
		if(current==1)
    			return sum11;
    	if((x>=12 || y>=12) || (x<0 || y<0) || y==-1)
    	{
    		canIt=false;// get rid of dis shit man
    		sum11=-1;
    		return sum11;

    	}
    	else
    	{
				//check if can left down then right
				if(y-1==1)//holy shit this fixes it
					return -1;
				if ( (y-1)>=0 && sum11<=2) //left
				{
					sum11=0; //makes it pick L or
					return ifPossibleH(x,y-1,current-1,canIt,sum11); //fixed
				}
				else if( (y+1)<12 && sum11>=2) //right
				{
					sum11=3;
					return ifPossibleH(x,y+1,current-1,canIt,sum11);
				}
				else //nope
				{
					sum11=-1;
					return sum11;
				}

    	}



    }
    public int ifPossibleV(int x, int y, int current, boolean canIt, int sum11) //vertical
    {
    	//-1 can't, 0 up, 3 down
    	if(current==0)//the one to fix it all boys.
    			return sum11;
    	if((x>=12 || y>=12) || (x<0 || y<0) || x==-1)
    	{
    		canIt=false;
    		sum11=-1;
    		return -1;
    	}
    	else
    	{
    			if((x-1)==-1 || x==-1 || sum11==-1)
    			{
    				sum11=-1;
    				return -1;
    			}
				else if( (x-1)>=0 && sum11<=2) //up
				{
					//System.out.println("X-1: "+(x-1));
					sum11=0; //makes it pick a up or down
					return ifPossibleV(x-1,y,current-1,canIt,0);
				}
				else if( (x+1)<12 && sum11>=2) //down
				{
					sum11=3;
					return ifPossibleV(x+1,y,current-1,canIt,3);
				}
				else //nope
				{
					sum11=-1;
					return -1;
				}
    	}
    }
    public int ifPossibleD(int x, int y, int current, boolean canIt, int sum11, int rL) //diagnoal
    {
    	// sum11 must =2.
    	// -1 can't,  0 down left, 2 down right, 3 up left,  5 up right
    	// rL: right=0, left=2

    	if(x==-1 || y==-1 || x==12 || y==12) //random checks from here***
    	{
    		canIt=false;
    		sum11=-1;
    		return -1;
    	}
    	if(current==0 && (x!=12 && x!=-1)) //move to front?
    			return sum11;
    	else{
    	if(x==12 || y==12)
    	{
    		canIt=false;
    		return -1;
    	}//to here***
    	else
    	{
				if ( y-1>=0 && sum11<=2 ) //down
				{

					if((x-1)>=0 && sum11<=1) //left
					{
						sum11=0;
						return ifPossibleD(x-1,y-1,current-1,canIt,sum11,rL);
					}
					else if((x+1)<12 && sum11>=1 && y-1!=-1) //right
					{
						//System.out.println("Getting to here?::"+wordLetters.length+":C:"+current+":x:"+x+":y:"+y);
						sum11=2;
						return ifPossibleD(x+1,y-1,current-1,canIt,sum11,rL);
					}
					else //switches to check up
					{
						sum11=4;
						return ifPossibleD(ogx,ogy,wordLetters.length,canIt,sum11,rL); //had to fix this to make sure it is starting fresh.
					}
				} //do i need to implement things to constantly reset sum11. ****POSSIBLE SOLUTION****
				else if( ogy+1<12 && sum11==4) //up
				{
					if((x-1)>=0 && sum11<=4 && y+1<12) //left
					{
						sum11=3;
						return ifPossibleD(x-1,y+1,current-1,canIt,sum11,rL);
					}
					else if(x+1<12 && sum11>=4 && y+1<12) //right
					{
						sum11=5;
						return ifPossibleD(x+1,y+1,current-1,canIt,sum11,rL);
					}
					else
					{
						canIt=false;
						return -1;
					}
				}
				else //nope
				{
					canIt=false;
					return -1;
				}
    	}
    	}

    }
    //sum11 will be what ifPossible returns and what setGrid uses to determine what to use.
	public int setVertical(String word,int x, int y, int current, int sum11)
	{

		// current will equal 0, will go until it = wordlength.
		if(current==word.length())
				return 0;
			// should check sum11. If 0 it goes down, if 3 it goes up.
		else{
			if(sum11==-1)
			{
				return 0;
			}
			if(sum11==0)
			{
				//System.out.println("X:Y "+x+" "+y); //debugging V
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setVertical(word, x-1, y, current+1, sum11);
			}
			if(sum11==3)
			{
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setVertical(word, x+1, y, current+1, sum11);
			}
			}

		return 0;

	}
	public int setHorizontal(String word, int x, int y, int current, int sum11)
	{
		//should check sum11. If 0 it goes left, if 3 it goes right.


			if(current==word.length())
				return 0;
			else{
			if(sum11==0)
			{
				//System.out.println("X:Y "+x+" "+y); //debugging H
				//System.out.println("Word "+word+"\nX:Y "+x+":"+y+"\nCurrent: "+current+"\nSum11: "+sum11); //debugging thing
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setHorizontal(word, x, y-1, current+1, sum11);
			}
			if(sum11==3)
			{
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setHorizontal(word, x, y+1, current+1, sum11);
			}
			}

		return 0;

	}
	public int setDiagonal(String word, int x, int y, int current, int sum11)
	{
		// -1 can't,  0 up right, 2 up left, 3 down right,  5 down left
			//System.out.println("Word::"+word+" Current::"+current+" Sum11::"+sum11);
			if(current==word.length())
				return 0;
			if(sum11==-1)
			{
				return 0;
			}
			if(sum11==0)
			{
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setDiagonal(word, x-1, y-1, current+1, sum11);
			}
			if(sum11==2)
			{
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setDiagonal(word, x+1, y-1, current+1, sum11);
			}
			if(sum11==3)
			{
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setDiagonal(word, x+1, y+1, current+1, sum11);
			}
			if(sum11==5)
			{
				grid[x][y]=wordLetters[current];
				used[x][y]=wordLetters[current];
				return setDiagonal(word, x+1, y+1, current+1, sum11);
			}
			return 0;

	}
	public void fillGridNonsense()
	{
		for(int i=0; i<12;i++)
		{
			for(int j=0;j<12;j++)
			{
				grid[i][j]="#";
			}
		}


	}
	public void fillGridSpaces()
	{
		for(int i=0; i<12;i++)
		{
			for(int j=0;j<12;j++)
			{
				if(grid[i][j].compareTo("")==0)
					grid[i][j]="#";
			}
		}


	}
	public void fillGridLetters(){
		for(int i=0; i<12;i++)
		{
			for(int j=0;j<12;j++){
					int q=(int)(Math.random()*(122-97+1)+97);
					char x= (char)q;
					grid[i][j]=x+"";
			}
		}
	}
	public String toString()
	{
		String output=" ";
		for(int x=0; x<grid.length; x++)
		{
			for(int y=0; y<grid[x].length; y++)
			{
				output+=grid[x][y]+" ";
			}
			output+="\n ";
		}
		output+="\n\nWords:";
		for(int x=0;x<words.length;x++)
		{
			if(words[x]==null){

			}
			else{
				output+=" "+words[x];
			}
		}
		return output;
	}
	public void fileWriting() throws IOException{
		FileWriter writ= new FileWriter("F:\\CS2\\JAVA LABS\\Recursion\\WordSearch\\word_search.txt");
		PrintWriter out = new PrintWriter(writ);
		String output=" ";
		for(int x=0; x<grid.length; x++)
		{
			for(int y=0; y<grid[x].length; y++)
			{
				out.print(grid[x][y]+" ");
			}
			out.println();
		}
		out.println();
		out.print("Words:");
		for(int x=0;x<words.length;x++)
		{
			if(words[x]==null){

			}
			else{
				output+=" "+words[x];
			}
		}
		out.print(output);
		out.println("");
		out.println("by hunter&&ben");
		out.close();
		writ.close();
	}

    /*
     *Fixes:
     *current=0 for ifPossibleV fixes it.
     *Changed wordLetters to get rid of first space
     *
     *Tasks:
     *get rid of canIt.
     *get rid of rL in D.
     *
     *Important notes:
     *Switched horizontal and vertical method names.
     *set current=0 for setmethods.
     *Will go until it has 10words. no duplicates.
     *had to change the second set of conditions in ifPD
     *
     *
     *v2.0
     */


}
