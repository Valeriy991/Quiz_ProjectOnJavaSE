package com.valeriygulin.quiz.controllers;

import com.valeriygulin.quiz.App;
import com.valeriygulin.quiz.repository.FileRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class LoadingFormController {
    @FXML
    public ComboBox<String> comboBoxSelectCategory;
    @FXML
    public ComboBox<String> comboBoxSelectDifficulty;
    @FXML
    public TextField textFieldNumberOfQuestions;
    private Map<String, Integer> maps = new HashMap<>();

    @FXML
    public void initialize() {
        ArrayList<String> difficulties = new ArrayList<>();
        difficulties.add("Easy");
        difficulties.add("Medium");
        difficulties.add("Hard");
        this.comboBoxSelectDifficulty.setItems(FXCollections.observableList(difficulties));
        Map<String, Integer> map = new HashMap<>();
        map.put("General Knowledge", 9);
        map.put("Sports", 21);
        map.put("History", 23);
        map.put("Politics", 24);
        this.maps = map;
        this.comboBoxSelectCategory.setItems(FXCollections.observableList(new ArrayList<>(map.keySet())));
    }

    @FXML
    public void ButtonSave(ActionEvent actionEvent) {
        String text = this.textFieldNumberOfQuestions.getText();
        if (text.isEmpty()) {
            App.showAlert("Error!", "Enter a value from 1 to 10!", Alert.AlertType.ERROR);
            return;
        }
        int num = Integer.parseInt(text);
        if (num > 10 || num < 1) {
            App.showAlert("Error!", "Enter a value from 1 to 10!", Alert.AlertType.ERROR);
            return;
        }
        String selectedCategory = this.comboBoxSelectCategory.getSelectionModel().getSelectedItem();
        if (selectedCategory == null) {
            App.showAlert("Error!", "Select category", Alert.AlertType.ERROR);
            return;
        }
        String selectedDifficulty = this.comboBoxSelectDifficulty.getSelectionModel().getSelectedItem();
        if (selectedDifficulty == null) {
            App.showAlert("Error!", "Select difficulty", Alert.AlertType.ERROR);
            return;
        }
        Integer category = this.maps.getOrDefault(selectedCategory, 0);
        String url = "https://opentdb.com/api.php?amount=" + text + "&category=" + category + "&difficulty="
                + selectedDifficulty.toLowerCase() + "";
        try {
            Preferences prefs = Preferences.userRoot().node(App.class.getName());
            FileRepository fileRepository = new FileRepository(url);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            String dirPath = prefs.get("dirPath", "");
            if (!dirPath.isEmpty()) {
                fileChooser.setInitialDirectory(new File(dirPath));
            }
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                prefs.put("dirPath", file.getParent());
                fileRepository.save(file);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void ButtonStart(ActionEvent actionEvent) throws IOException {
        String text = this.textFieldNumberOfQuestions.getText();
        if (text.isEmpty()) {
            App.showAlert("Error!", "Enter a value from 1 to 10!", Alert.AlertType.ERROR);
            return;
        }
        int num = Integer.parseInt(text);
        if (num > 10 || num < 1) {
            App.showAlert("Error!", "Enter a value from 1 to 10!", Alert.AlertType.ERROR);
            return;
        }
        String selectedCategory = this.comboBoxSelectCategory.getSelectionModel().getSelectedItem();
        if (selectedCategory == null) {
            App.showAlert("Error!", "Select category", Alert.AlertType.ERROR);
            return;
        }
        String selectedDifficulty = this.comboBoxSelectDifficulty.getSelectionModel().getSelectedItem();
        if (selectedDifficulty == null) {
            App.showAlert("Error!", "Select difficulty", Alert.AlertType.ERROR);
            return;
        }
        Integer category = this.maps.getOrDefault(selectedCategory, 0);
        String url = "https://opentdb.com/api.php?amount=" + text + "&category=" + category + "&difficulty="
                + selectedDifficulty.toLowerCase();

        try {
            FileRepository fileRepository = new FileRepository(url);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            App.openWindowAndWait("gameForm.fxml", "Game Form", fileRepository);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
