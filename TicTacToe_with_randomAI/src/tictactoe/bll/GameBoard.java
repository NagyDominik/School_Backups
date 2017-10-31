/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{

    private int currentPlayer = 1;
    private Integer[][] board = new Integer[3][3];

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        if (currentPlayer == 2)
        {
            currentPlayer = 1;
            return 1;
        } else
        {
            currentPlayer = 2;
            return 2;
        }
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    public boolean play(int col, int row)
    {
        if (board[col][row] != null)
        {
            return false;
        } else
        {
            if (currentPlayer == 1)
            {
                board[col][row] = 1;
            } else
            {
                board[col][row] = 2;
            }
            return true;
        }
    }

    public boolean isGameOver()
    {
        boolean fullBoard = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == null)
                {
                    fullBoard = false;
                }
            }
        }
        return fullBoard;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        int winner = -1;
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] != null && board[i][1] == board[i][0] && board[i][2] == board[i][0])
            {
                winner = board[i][0];
            }
            if (board[0][i] != null && board[1][i] == board[0][i] && board[2][i] == board[0][i])
            {
                winner = board[0][i];
            }
            if (board[1][1] != null && board[0][0] == board[1][1] && board[2][2] == board[1][1])
            {
                winner = board[1][1];
            }
            if (board[1][1] != null && board[0][2] == board[1][1] && board[2][0] == board[1][1])
            {
                winner = board[1][1];
            }
        }
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = null;
            }
        }
        currentPlayer = 2;
    }

    public int AIcol()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int AIrow()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
