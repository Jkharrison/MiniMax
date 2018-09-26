import java.util.Arrays;
class Board
{
    public char[][] board = {{'_','_','_'}, {'_', '_', '_'}, {'_', '_', '_'}};
    Board()
    {

    }
    public char[][] getBoard()
    {
        return this.board;
    }
    void setBoard(char[][] that)
    {
        this.board = that;
    }
    public boolean draw()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(this.board[i][j] == '_')
                    return false;
            }
        }
        if(!(this.xWon()) && !(this.oWon()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    void makeMove(int x, int y, char c)
    {
        // Checking if move is valid;
        if(this.board[y][x] == '_')
            this.board[y][x] = c;
        else
            System.out.println("Unable to make move, piece already located in that position");
    }
    public boolean xWon()
    {
        char[] rowOfX = {'X', 'X', 'X'};
        if(Arrays.equals(rowOfX, this.board[0]))
            return true;
        else if(Arrays.equals(rowOfX, this.board[1]))
            return true;
        else if(Arrays.equals(rowOfX, this.board[2]))
            return true;
        else if(this.board[0][0] == 'X' && this.board[1][1] == 'X' && this.board[2][2] == 'X')
            return true;
        else if(this.board[2][0] == 'X' && this.board[1][1] == 'X' && this.board[0][2] == 'X')
            return true;
        else if(this.board[0][0] == 'X' && this.board[1][0] == 'X' && this.board[2][0] == 'X')
            return true;
        else if(this.board[0][1] == 'X' && this.board[1][1] == 'X' && this.board[2][1] == 'X')
            return true;
        else if(this.board[0][2] == 'X' && this.board[1][2] == 'X' && this.board[2][2] == 'X')
            return true;
        return false;
    }
    public boolean oWon()
    {
        char[] rowOfO = {'O', 'O', 'O'};
        if(Arrays.equals(rowOfO, this.board[0]))
            return true;
        else if(Arrays.equals(rowOfO, this.board[1]))
            return true;
        else if(Arrays.equals(rowOfO, this.board[2]))
            return true;
        else if(this.board[0][0] == 'O' && this.board[1][1] == 'O' && this.board[2][2] == 'O')
            return true;
        else if(this.board[2][0] == 'O' && this.board[1][1] == 'O' && this.board[0][2] == 'O')
            return true;
        else if(this.board[0][0] == 'O' && this.board[1][0] == 'O' && this.board[2][0] == 'O')
            return true;
        else if(this.board[0][1] == 'O' && this.board[1][1] == 'O' && this.board[2][1] == 'O')
            return true;
        else if(this.board[0][2] == 'O' && this.board[1][2] == 'O' && this.board[2][2] == 'O')
            return true;
        return false;
    }
}
