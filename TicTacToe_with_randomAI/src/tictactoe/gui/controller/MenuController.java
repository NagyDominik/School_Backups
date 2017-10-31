/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class MenuController implements Initializable
{

    @FXML
    private Button single;
    @FXML
    private Button multi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }

    @FXML
    private void SinglePlayer(ActionEvent event) throws IOException
    {
        
       FXMLLoader Loader = new FXMLLoader(getClass().getResource("/tictactoe/gui/views/TicTacView.fxml"));
	Parent root = Loader.load(); 
        
        //Fetches controller from view
        TicTacViewController controller = Loader.getController();
        controller.setController(this);
        controller.setSingle();
        
        Scene scene = new Scene(root);
        Stage stage  =(Stage) single.getScene().getWindow();
        stage.setScene(scene);

    }

    @FXML
    private void MultiPlayer(ActionEvent event) throws IOException
    {
        
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/tictactoe/gui/views/TicTacView.fxml"));
	Parent root = Loader.load();
        
        //Fetches controller from view
        TicTacViewController controller = Loader.getController();
        controller.setController(this);
        controller.setMulti();
        
        Scene scene = new Scene(root);
        Stage stage  =(Stage) single.getScene().getWindow();
        stage.setScene(scene);
        
    }

}
