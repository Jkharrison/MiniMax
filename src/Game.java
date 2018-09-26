import java.util.Scanner;
import java.util.Random;
public class Game
{
    public static int miniMax(Board board, char Player)
    {
        return -1; // PlaceHolder for now.
    }
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String temp;
        Board boardGame = new Board();
        System.out.println("What would you like to do?");
        System.out.println("To start a new game, enter 1");
        System.out.println("To quit, enter 2");

        byte firstChoice = scan.nextByte();
        temp = scan.nextLine();
        switch(firstChoice)
        {
            case 1:
                int i = 0;
                while(true)
                {
                    byte x, y;
                    if(!(boardGame.xWon()) && !(boardGame.oWon()))
                    {
                        if(i % 2 == 0)
                        {
                            System.out.println("Player one, please enter move (x,y) coordinate, separate number by new line");
                            x = scan.nextByte();
                            temp = scan.nextLine();
                            y = scan.nextByte();
                            temp = scan.nextLine();
                            // TODO: Switch conditional below to while loop for error checking.
                            if(boardGame.board[y][x] == '_')
                                boardGame.board[y][x] = 'X'; // Row, Col format which is y, x in cartesian.
                            else
                                throw new RuntimeException("Unable to move there because spot has been played already");
                            System.out.println("Board Status");
                            for(int j = 0; j < 3; j++)
                            {
                                for(int k = 0; k < 3; k++)
                                {
                                    System.out.print((char)boardGame.board[j][k]);
                                    System.out.print(' ');
                                }
                                System.out.println();
                            }
                        }
                        else
                        {
                            System.out.println("Player two, please enter move (x, y) coordinate, separte number by new line");
                            x = scan.nextByte();
                            temp = scan.nextLine();
                            y = scan.nextByte();
                            temp = scan.nextLine();
                            // TODO: Switch conditional to while loop for error checking.
                            if(boardGame.board[y][x] == '_')
                                boardGame.board[y][x] = 'O'; // Row, Col format which is y, x in cartesian.
                            else
                                throw new RuntimeException("Unable to move there because spot has been played already");
                            System.out.println("Board Status");
                            for(int j = 0; j < 3; j++)
                            {
                                for(int k = 0; k < 3; k++)
                                {
                                    System.out.print((char)boardGame.board[j][k]);
                                    System.out.print(' ');
                                }
                                System.out.println();
                            }
                        }
                    }
                    else
                    {
                        // Winning Condition
                        if(boardGame.xWon())
                        {
                            System.out.println("Player one has won the game, now exiting");
                            scan.close();
                            System.exit(0);
                        }
                        else if(boardGame.oWon())
                        {
                            System.out.println("Player two has one the game, now exiting");
                            scan.close();
                            System.exit(0);
                        }
                    }
                    i++;
                }
            case 2:
                System.out.println("Thank you for playing TicTacToe");
                scan.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input, please restart program and try again");
                scan.close();
                System.exit(1);
                break;
        }
    }
}
