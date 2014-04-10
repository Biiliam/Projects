import java.util.*;
import java.io.*;

public class main {

    public static void main(String [] args) throws IOException
    {
      WordSearch roon= new WordSearch();
		  roon.fillGridNonsense();
		  roon.fillUsedGrid();
		  roon.setGrid();
		  roon.fillGridSpaces();
		  roon.fileWriting();
		
	  	System.out.println(roon);
    }


}
