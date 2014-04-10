import java.util.*;


public class connectFour {

	//General
	String [][] board = new String [6][7];
	private Scanner kbs = new Scanner(System.in);
	int turn = (int)(Math.random()*(2-1+1)+1);
	int turns=1;

	//Variables tied to the Win checking
	String [][] counted= new String [6][7];
	int lastX;
	int lastY;
	int wturns=0;
	boolean play = true;



    public connectFour() {
		fillSpaces();

    }

    public void fillSpaces(){
    	//Blank the board
    	for(int i=0; i<6;i++){

    		for(int j=0; j<7;j++){
    			board[i][j]=" ";
    		}
    	}
    }

    public void blankCounted(){
    	//reset counted grid and winTurns Counter.
    	wturns=0;
    	for(int i=0; i<6;i++){

    		for(int j=0; j<7;j++){
    			counted[i][j]=" ";
    		}
    	}
    	//blank after every win check
    }

    public boolean inbounds(int x, int y){

    	if(x>=0 && x<6 && y>=0 && y<7){
    		return true;
    	}
    	else
    		return false;
    }

    public void playGame2(){
    	//2player Game Here

    	while(play){
    		boardPrint();
    		if(turn==1){
    			System.out.println("Player One's turn.");
    			System.out.println("\n\n\n\n\n\n\n\n\n\n");
    			move();
    			counted[lastX][lastY]="1";
    			if(win(1,lastX,lastY,0, "X")){
    				boardPrint();
    				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------\nCONGRATS PLAYER ONE!!! You won in: "+turns+" turns!!\n----------------------------");
    				play=false;

    			}
    			blankCounted();
    			turn++;
    			turns++;
    		}
    		else if(turn==2){
    			System.out.println("Player Two's turn.");
    			System.out.println("\n\n\n\n\n\n\n\n\n\n");
    			move();
    			counted[lastX][lastY]="1";
    			if(win(1,lastX,lastY,0, "O")){
    				boardPrint();
    				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------\nCONGRATS PLAYER TWO!!! YOU WIN!\n----------------------------");
    				play=false;
    			}

    			blankCounted();
    			turn--;
    			turns++;
    		}

    	}


    }

    public void move(){
    	// where the move will occur
		int col;
		do{
			System.out.println("Which Column?");
			col= kbs.nextInt()-1;

		}while(col<0 || col>7);
		if(board[0][col].compareTo(" ")>0){
			System.out.println("Pick a different Column.");
			move();
		}
		else{
			int x=5;
			//potential problem, -1 thing when
			while(board[x][col].compareTo(" ")>0 && x>=0){
				x--;
			}
				lastX=x;
				lastY=col;
				if(turn==1)
					board[x][col]="X";
				else if(turn==2)
					board[x][col]="O";

		}

    }

    public boolean win(int count, int x, int y, int type, String player){
		//Method Usage
    	//Using it: win(1,lastX,lastY,0, "X" or "O")
		// WILL CHECK IN THIS ORDER: HORIZONTAL (Left/Right) , VERTICAL (Up/Down) , DIAGONAL (Left/Right)(Up/Down)
		//Type: 0=N/A,  1=Left/ 2=Right,   3=Up/4=Down,   5=Left-Up Diag/ 6= Right-Down Diag,   7 Right-Up Diag 8 Left-Down Diag

		//System.out.println("X:"+x+" Y:"+y+" Type:"+type+" Count:"+count);//remove

		if(wturns>=2){
			//Checks for infinite looping back and forth
			blankCounted();
			return false;
		}
		if(turns<6){
			blankCounted();
			return false;
		}

		else if(count==4){
			return true;
		}
		else{

			//left
			if(type==0 || type==1){

				if(inbounds(x,y-1) ){
					if(board[x][y-1].equals(player)){

						if(counted[x][y-1].compareTo("1")!=0){
							counted[x][y-1]="1";
							return win(count+1,x,y-1,1,player);
						}
						else{
							wturns++;
							return win(count,x,y-1,2,player);
						}

						}

					}
					else{
						//blankCounted();//potential
						//counted[lastX][lastY]="1";
						return win(count, lastX, lastY,2,player);
						//REMEMBER RETURN THE GOD DAMN COUNT FOR CORRECT PAIRINGS SO IT DOESNT RESET TO 1.
					}

				}
			}
			//right
			if(type==0 || type ==2){
				if(inbounds(x,y+1)){

					if(board[x][y+1].equals(player)){

						if(counted[x][y+1].compareTo("1")!=0){
								counted[x][y+1]="1";
						return win(count+1,x,y+1,1,player);
						}
						else{
							wturns++;
							return win(1, lastX, lastY,3,player); //return win(count,x,y+1,1,player);
						}
					}
				}
				else{

					blankCounted();
					counted[lastX][lastY]="1";
					return win(1, lastX, lastY,3,player);
				}
			}
			//up
			if(type==0 || type==3){

				if(inbounds(x+1,y)){

					if(board[x+1][y].equals(player)){
						if(counted[x+1][y].compareTo("1")!=0){
							counted[x+1][y]="1";
							return win(count+1,x+1,y,3,player);
						}
						else{
							wturns++;
							return win(count,x+1,y,4,player);
						}

					}

				}

			}
			//down
			if(type==0 || type==4){
				if(inbounds(x-1,y)){

					if(board[x-1][y].equals(player)){

						if(counted[x-1][y].compareTo("1")!=0){
							counted[x-1][y]="1";
							return win(count+1,x-1,y,4,player);
					}
					else{
						wturns++;
						return win(1, lastX, lastY,5,player); // return win(count,x-1,y,4,player);
					}

					}
				}
				else
					return win(1, lastX, lastY,5,player); //Make it go down the list check for the FOURRSS
			}

			//diagonals...
			//Left-Up
			if(type==0 || type ==5){
				if(inbounds(x-1,y-1)){

					if(board[x-1][y-1].equals(player)){

						if(counted[x-1][y-1].compareTo("1")!=0){
								counted[x-1][y-1]="1";
						return win(count+1,x-1,y-1,1,player);
						}
						else{
							wturns++;
							return win(count,x-1,y-1,6,player);
						}
					}
				}
				else{

					blankCounted();
					counted[lastX][lastY]="1";
					return win(1, lastX, lastY,6,player);
				}
			}
			//Right-down
			if(type==0 || type ==6){
				if(inbounds(x+1,y+1)){

					if(board[x+1][y+1].equals(player)){

						if(counted[x+1][y+1].compareTo("1")!=0){
								counted[x+1][y+1]="1";
						return win(count+1,x+1,y+1,1,player);
						}
						else{
							wturns++;
							return win(1, lastX, lastY,7,player); //return win(count,x+1,y+1,6,player);
						}
					}
				}
				else{

					blankCounted();
					counted[lastX][lastY]="1";
					return win(1, lastX, lastY,7,player);
				}
			}
			//Right-Up
			if(type==0 || type ==7){
				if(inbounds(x-1,y+1)){

					if(board[x-1][y+1].equals(player)){

						if(counted[x-1][y+1].compareTo("1")!=0){
								counted[x-1][y+1]="1";
								return win(count+1,x-1,y+1,7,player);
						}
						else{
							wturns++;
							return win(count,x-1,y+1,8,player);
						}
					}
				}
				else{

					blankCounted();
					counted[lastX][lastY]="1";
					return win(1, lastX, lastY,8,player);
				}
			}
			//Left-Down
			if(type==0 || type ==8){
				if(inbounds(x+1,y-1)){

					if(board[x+1][y-1].equals(player)){

						if(counted[x+1][y-1].compareTo("1")!=0){
								counted[x+1][y-1]="1";
								return win(count+1,x+1,y-1,8,player);
						}
						else{
							wturns++;
							return false;//return win(count,x,y,8,player);
						}
					}
				}
				else{//final failure

					blankCounted();
					return false;
				}
			}

			return false;



		}

    public void boardPrint(){
    	System.out.println("\n\n\n----------------------------");
    	System.out.println(" 1    2     3    4    5    6    7");
    	for(int i=0; i<6;i++){

    		for(int j=0; j<7;j++){
    			System.out.print("[ "+board[i][j]+" ]");
    		}
    		System.out.println();
    	}

    }




}
