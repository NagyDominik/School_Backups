/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questioner;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *
 * @author Dominik
 */
public class MainWindowController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button btnmain;
    @FXML
    private TextField nameField;
    @FXML
    private ListView<String> scoreBoard;
    
    private QuestionWindowController controller;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("QuestionWindow.fxml"));
        // Fetches primary stage and gets loader and loads FXML file to Parent
	FXMLLoader Loader = new FXMLLoader(getClass().getResource("QuestionWindow.fxml"));
	Parent root = Loader.load();
        
        //Fetches controller from view
        QuestionWindowController controller = Loader.getController();
        controller.setController(this);
        
        Scene scene = new Scene(root);
        Stage stage  =(Stage) label.getScene().getWindow();
        stage.setScene(scene);
        
        controller.setName(nameField.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setController(QuestionWindowController controller)
    {
	this.controller = controller;
    }
    
    public void setScoreBoard(List<String> entry){
        scoreBoard.getItems().addAll(entry);
    }
    
}
