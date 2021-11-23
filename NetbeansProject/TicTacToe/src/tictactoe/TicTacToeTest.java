
package tictactoe;

// Class TicTacToeTest
// to play TicTacToe between two players

import java.util.Scanner;
import java.util.Random;

public class TicTacToeTest {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        TicTacToe myTicTacToe = new TicTacToe();
        Status pointer = Status.CONTINUE;

        int player1 = 0;
        int player2 = 0;
        boolean legal = true; // variable to toggle through player1 and player2

        System.out.println("WELCOME TO THE TIC-TAC-TOE\n"
                + "Choose a number from 1-9 to select a cell"); //introductory message

        while (pointer == Status.CONTINUE)// loops through game while there's no win or draw
        {
            if (legal == true) {
                System.out.print("First player make your move: ");
                player1 = input.nextInt();
                legal = myTicTacToe.moveFirstPlayer(player1);
                myTicTacToe.displayBoard();
                pointer = TicTacToe.checkWinDraw();
            } else if (legal == false) {
                System.out.print("Second player make your move: ");
                player2 = input.nextInt();
                legal = myTicTacToe.moveSecondPlayer(player2);
                myTicTacToe.displayBoard();
                pointer = myTicTacToe.checkWinDraw();
            }
        }

        if (legal == false && pointer == Status.WIN) {
            System.out.println("PLAYER1 WINS!!!");
        } else if (legal == true && pointer == Status.WIN) {
            System.out.println("PLAYER2 WINS!!!");
        } else if (pointer == Status.DRAW) {
            System.out.println("IT'S A DRAW!");
        }
    }

}

