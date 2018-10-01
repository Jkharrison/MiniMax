import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
public class Game
{
    static final char player = 'O';
    static final char opponent = 'X';
    public static int miniMax(Board board, int depth, boolean isMax)
    {
        int score = evaluate(board);
        if(score == 10)
            return score;
        if(score == -10)
            return score;
        if(!(moveLeft(board)))
            return 0; // Draw case
        if(isMax)
        {
            int best = Integer.MIN_VALUE;

            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    if(board.board[i][j] == '_')
                    {
                        board.board[i][j] = player; // Make the move.
                        int value = miniMax(board, depth+1, !isMax); // Recursively simulate the game, and chose max value.
                        best = Math.max(best, value);
                        board.board[i][j] = '_'; // Undo the move.
                    }
                }
            }
            return best;
        }
        else
        {
            int best = Integer.MAX_VALUE;
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    if(board.board[i][j] == '_')
                    {
                        board.board[i][j] = opponent;
                        int value = miniMax(board, depth+1, !isMax);
                        best = Math.min(best, value);
                        board.board[i][j] = '_';
                    }
                }
            }
            return best;
        }
        // Start with calculating possible (valid) moves for each player a given moment.
        // Next Minimize maximum loss for the AI Agent, while calculating the player's best move.
        // Recursively call this method and figure out which move is the best bet for the user to take at a givent moment.
        // Keep calling this methid until a victory or draw is reached.
    }
    public static int[] findBestMove(Board board)
    {
        int bestVal = -1000;
        int[] bestMove = new int[2];
        bestMove[0] = -1;
        bestMove[1] = -1;

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(board.board[i][j] == '_')
                {
                    board.board[i][j] = player;
                    int moveVal = miniMax(board, 0, false);
                    board.board[i][j] = '_';
                    if(moveVal > bestVal)
                    {
                        bestMove[0] = i; // The row
                        bestMove[1] = j; // The col
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
    public static boolean moveLeft(Board game)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(game.board[i][j] == '_')
                    return true;
            }
        }
        return false;
    }
    public static int evaluate(Board b)
    {
        if(b.oWon())
            return 10;
        else if(b.xWon())
            return -10;
        // for(int row = 0; row < 3; row++)
        // {
        //     if(b.board[row][0] == b.board[row][1] && b.board[row][1] == b.board[row][2])
        //     {
        //         if(b.board[row][0] == player)
        //             return 10;
        //         else if(b.board[row][0] == opponent)
        //             return -10;
        //     }
        // }
        // for(int col = 0; col < 3; col++)
        // {
        //     if(b.board[0][col] == b.board[1][col] && b.board[1][col] == b.board[col][2])
        //     {
        //         if(b.board[0][col] == player)
        //             return 10;
        //         else if(b.board[0][col] == opponent)
        //             return -10;
        //     }
        // }
        // if(b.board[0][0] == b.board[1][1] && b.board[1][1] == b.board[2][2])
        // {
        //     if(b.board[0][0] == player)
        //         return 10;
        //     else if(b.board[0][0] == opponent)
        //         return -10;
        // }
        // if(b.board[2][0] == b.board[1][1] && b.board[1][1] == b.board[0][2])
        // {
        //     if(b.board[2][0] == player)
        //         return 10;
        //     else if(b.board[2][0] == opponent)
        //         return -10;
        // }
        return 0;
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
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print((char)boardGame.board[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        switch(firstChoice)
        {
            case 1:
                int i = 0;
                while(true)
                {
                    byte x, y;
                    if(!(boardGame.xWon()) && !(boardGame.oWon()) && !(boardGame.draw()))
                    {
                        if(i % 2 == 0)
                        {
                            System.out.println("Player one, please enter move (row,col) coordinate, separate number by new line");
                            y = scan.nextByte();
                            temp = scan.nextLine();
                            x = scan.nextByte();
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
                            int[] result = findBestMove(boardGame);
                            x = (byte)result[1];
                            // temp = scan.nextLine();
                            y = (byte)result[0];
                            // temp = scan.nextLine();
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
                        else if(boardGame.draw())
                        {
                            System.out.println("The game is a draw");
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
