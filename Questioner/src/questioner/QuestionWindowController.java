/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questioner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class QuestionWindowController implements Initializable {
    
    @FXML
    private ToggleGroup qt1;
    @FXML
    private ToggleGroup qt2;
    @FXML
    private ToggleGroup qt7;
    @FXML
    private ToggleGroup qt6;
    @FXML
    private ToggleGroup qt5;
    @FXML
    private ToggleGroup qt4;
    @FXML
    private ToggleGroup qt3;
    @FXML
    private ToggleGroup qt8;
    @FXML
    private ToggleGroup qt9;
    @FXML
    private Label nameOfParticipant;
    @FXML
    private Button btncalculate;
    @FXML
    private Label scorefield;
    @FXML
    private Button btnSaveScore;
    @FXML
    private GridPane GridPane;
    @FXML
    private AnchorPane AnchorPane;
    
    private int score;
    private List<ToggleGroup> lofasz = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private List<String> scoreentry = new ArrayList<>();
    private int lastentry = 0;
    private boolean added = false;
    private String nameStorage;
    private int dis = 0;
    private int agr = 0;
    
    private MainWindowController controller;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void calculateScore(ActionEvent event) {
        score = 0;
        if (!added) {
            lofasz.clear();
            answers.clear();
            lofasz.addAll(Arrays.asList(qt1,qt2,qt3,qt4,qt5,qt6,qt7,qt8,qt9));
            for (ToggleGroup group : lofasz) {
                if (group.getSelectedToggle() != null) {
                    answers.add(group.getSelectedToggle().toString());
                }
                else{
                    answers.add(group.getSelectedToggle().toString());
                }
            }
        }
        added = true; 
        
        for (String selected : answers) {
            if (selected.contains("Disagree")) {
                score -= 1;
            }
            if (selected.contains("Agree")) {
                score += 1;
            }
        }
        scorefield.setText("Score:        " + score);
        added = false;
    }
    
    public void setName(String name){
        nameOfParticipant.setText("Name of participant: " + name);
        nameStorage = name;
    }
    
    public void setController(MainWindowController controller)
    {
	this.controller = controller;
    }

    @FXML
    private void saveScore(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
	Parent root = Loader.load();
        
        //Fetches controller from view
        MainWindowController controller = Loader.getController();
        controller.setController(this);
        
        Scene scene = new Scene(root);
        Stage stage  =(Stage) GridPane.getScene().getWindow();
        stage.setScene(scene);
        
        EntryStorage storage = new EntryStorage();
        storage.addToList(nameStorage, score);
        
        controller.setScoreBoard(storage.getEntries());
    }
    
}