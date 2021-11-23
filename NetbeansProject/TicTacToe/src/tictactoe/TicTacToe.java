
package tictactoe;


// CLASS TicTacToe
// simulating a game of TicTacToe between two human players
import java.util.Scanner;


enum Status {WIN, DRAW, CONTINUE};//public enum constants to indicate status of the game

public class TicTacToe
{
    private static boolean legal = true;
    private static Scanner input = new Scanner(System.in);
    
    private enum Cell {X, O, EMPTY};// enum econstants to indicate value of each cell
    
    
    private static Status pointer;
    private static final Cell[][] tico = new Cell[3][3];
    
    // constructor initializing all cells to EMPTY
    public TicTacToe()
    {
        for (int row = 0; row < tico.length; row++)
        {
            for (int column = 0; column < tico[row].length; column++)
               tico[row][column] = Cell.EMPTY;
        }
    }
      
    //method to indicate move of first player
    //checks if move is legal and if so, places an X in the position
    public static boolean moveFirstPlayer(int move)
    {
        int count = 1;
        boolean legal = false;
        
        if (move < 1 || move > 9)// checks if move is within board range
            legal = true;
        
        for (int row = 0; row < tico.length; row++)
        {
            for (int column = 0; column < tico[row].length; column++)
            {
                if(count == move)
                {
                    if(tico[row][column] == Cell.X || tico[row][column] == Cell.O)// checks if cell is taken already
                        legal = true;
                    else if(tico[row][column] == Cell.EMPTY)
                        tico[row][column] = Cell.X;   
                }
                count++;
            }
        }
        
        if(legal == true)
            System.out.println("Move not legal! Replay move");
        
        return legal;
    }
    
    //method to indicate move of second player
    //checks if move is legal and if so, places an O in the position
    public static boolean moveSecondPlayer(int move)
    {
        int count = 1;
        boolean legal = true;
        
        if (move < 1 || move > 9)// checks if move is within board range
            legal = false;
        
        for (int row = 0; row < tico.length; row++)
        {
            for (int column = 0; column < tico[row].length; column++)
            {
                if(count == move)
                {
                    if(tico[row][column] == Cell.X || tico[row][column] == Cell.O)// checks if cell is taken already
                        legal = false;
                    else if(tico[row][column] == Cell.EMPTY)
                        tico[row][column] = Cell.O;   
                }
                
                count++;
            }
        }
        
        if(legal == false)
            System.out.println("Move not legal! Replay move");
        
        return legal;
    }
    
    // method to display Tic-Tac-Toe board
    // reiterates through rows and columns and prints appropriate element
    public void displayBoard()
    {
        for (int star = 0; star < 20; star++)
            System.out.print("*");
        System.out.println();
        
        for (int row = 0; row < tico.length; row++)
        {
            for (int column = 0; column < tico[row].length; column++)
            {
                if(tico[row][column] == Cell.X)
                    System.out.print("X ");
                else if(tico[row][column] == Cell.O)
                    System.out.print("O ");
                else if(tico[row][column] == Cell.EMPTY)
                    System.out.print("  ");
                
                if (column < 2)
                    System.out.print("! ");       
            }  
            System.out.println();
            
            if (row < 2)
                System.out.println("----------");
        }

        for (int star = 0; star < 20; star++)
            System.out.print("*");
        System.out.println();
    }
    
    // method to check if there is a win or draw in the game (kinda long sha)
    public static Status checkWinDraw()
    {
        int col = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        pointer = Status.CONTINUE;
        
        // reiterates through each each row to check for horizontal wins
        // (0,0) to (0,1) to (0,2)....
        // also checks if there's a draw (count3 and stuff...)
        for (int row = 0; row < tico.length; row++)
        {
            count1 = 0;
            count2 = 0;
            
            for (int column = 0; column < tico[row].length; column++)
            {
                switch(tico[row][column])
                {
                    case X:
                        count1++;
                        break;
                        
                    case O:
                        count2++;
                        break;
                        
                    case EMPTY:
                        count3++;
                        break;
                }
            }
            
            if (count1 == 3)
                pointer = Status.WIN;
            else if (count2 == 3)
                pointer = Status.WIN;
        }
        
        // reiterates through each column to check for vertical wins
        // (0,0) to (1,0) to (2,0)....
        for (int column = 0; column < tico.length; column++)
        {
            count1 = 0;
            count2 = 0;
            
            for (int row = 0; row < tico.length; row++)
            {
                switch(tico[row][column])
                {
                    case X:
                        count1++;
                        break;
                        
                    case O:
                        count2++;
                        break;
                }
            }
            
            if (count1 == 3)
                pointer = Status.WIN;
            else if (count2 == 3)
                pointer = Status.WIN;
        }
        
        // reiterates through rows and columns to check for left-right diagonal wins
        // (0,0) to (1,1) to (2,2)
        count1 = 0;
        count2 = 0;        
        col = 0;
        for (int row = 0; row < tico.length; row++)
        {
            switch(tico[row][col])
            {
                case X:
                    count1++;
                    break;

                case O:
                    count2++;
                    break;
            }
            
            col++;
            
            if (count1 == 3)
                pointer = Status.WIN;
            else if (count2 == 3)
                pointer = Status.WIN;
        }
        
        //reiterates through rows and columns to check for right-left diagonal wins
        // (0,2) to (1,1) to (2,0)
        count1 = 0;
        count2 = 0;        
        col = 2;
        for (int row = 0; row < tico.length; row++)
        { 
            switch(tico[row][col])
            {
                case X:
                    count1++;
                    break;

                case O:
                    count2++;
                    break;
            }
            
            col--;
            if (count1 == 3)
                pointer = Status.WIN;
            else if (count2 == 3)
                pointer = Status.WIN;
        }
        
        if(pointer == Status.CONTINUE && count3 == 0)
            pointer = Status.DRAW;
        
        return pointer;
    }
}
