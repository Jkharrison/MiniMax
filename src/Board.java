class Board
{
    public char[][] board = {{'_','_','_'}, {'_', '_', '_'}, {'_', '_', '_'}};
    private boolean xHasWon = false;
    private boolean oHasWon = false;
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
    void resetBoard()
    {

    }
}
