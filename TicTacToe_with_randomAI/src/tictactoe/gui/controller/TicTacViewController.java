/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoe.bll.AIGameBoard;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable
{

    @FXML
    private Label lblPlayer;
    @FXML
    private Button btnNewGame;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;

    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;
    private int player;
    private String xOrO = "O";
    private boolean gameRunning = true;
    public int gamemode;
    private MenuController controller;
    private List<Button> allTheButtons = new ArrayList<Button>();
    private Random rnd = new Random();

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;

            if (game.play(c, r) && gameRunning)
            {
                if (gamemode == 1)
                {
                    if (game.isGameOver() || game.getWinner() != -1)
                    {
                        Button btn = (Button) event.getSource();
                        xOrO = player == 1 ? "X" : "O";
                        btn.setText(xOrO);
                        endOfGame();
                    } else
                    {
                        Button btn = (Button) event.getSource();
                        xOrO = player == 1 ? "X" : "O";
                        btn.setText(xOrO);
                        setPlayer();
                    }
                } else
                {
                    if (game.isGameOver() || game.getWinner() != -1)
                    {
                        Button btn = (Button) event.getSource();
                        xOrO = player == 1 ? "X" : "O";
                        btn.setText(xOrO);
                        endOfGame();
                    } else
                    {
                        Button btn = (Button) event.getSource();
                        xOrO = "X";
                        btn.setText(xOrO);
                        player = game.getNextPlayer();
                        xOrO = "O";
                        /*int x = game.AIcol();
                        int y = game.AIrow();       //Preserved for possibble later use.
                        game.play(x, y);
                        allTheButtons.get(x * 3 + y).setText(xOrO);
                        player = game.getNextPlayer();*/
                        checkAI();
                        player = game.getNextPlayer();
                        if (game.isGameOver() || game.getWinner() != -1)
                        {
                            endOfGame();
                        }
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {

        if (gamemode == 0)
        {
            clearBoard();
            gameRunning = true;
            game.newGame();
            xOrO = "O";
            checkAI();
            setPlayer();
            lblPlayer.setText("Player: " + player);
        } else
        {
            gameRunning = true;
            player = 1;
            game.newGame();
            setPlayer();
            lblPlayer.setText("Player: " + player);
            clearBoard();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        allTheButtons.addAll(Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9));
        game = new AIGameBoard();
        player = game.getNextPlayer();
        setPlayer();
    }

    private void setPlayer()
    {
        player = game.getNextPlayer();
        lblPlayer.setText(TXT_PLAYER + player);
    }

    private void displayWinner(int winner)
    {
        String message = "";
        if (gamemode == 1)
        {
            switch (winner)
            {
                case -1:
                    message = "It's a draw :-(";
                    break;
                default:
                    message = "Player " + winner + " wins!!!";
                    break;
            }
            lblPlayer.setText(message);
        } else
        {
            switch (winner)
            {
                case -1:
                    message = "It's a draw :-(";
                    break;
                case 2:
                    message = "Computer wins!";
                    break;
                default:
                    message = "Player wins!!!";
                    break;
            }
            lblPlayer.setText(message);
        }
    }

    private void clearBoard()
    {
        for (Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

    public void setController(MenuController controller)
    {
        this.controller = controller;
    }

    public void setSingle()
    {
        game = new AIGameBoard();
        gamemode = 0;
        game.getNextPlayer();
        checkAI();
        game.getNextPlayer();
    }

    public void setMulti()
    {
        game = new GameBoard();
        gamemode = 1;
    }

    private void endOfGame()
    {
        int winner = game.getWinner();
        displayWinner(winner);
        gameRunning = false;
    }

    public void checkAI()
    {
        int x = rnd.nextInt(6) % 3;
        int y = rnd.nextInt(6) % 3;
        if (game.play(x, y))
        {
            allTheButtons.get(x * 3 + y).setText(xOrO);

        } else
        {
            checkAI();
        }
    }

}
