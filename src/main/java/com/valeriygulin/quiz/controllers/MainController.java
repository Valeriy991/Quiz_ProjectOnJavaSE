package com.valeriygulin.quiz.controllers;

import com.valeriygulin.quiz.App;
import com.valeriygulin.quiz.repository.FileRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class MainController {
    @FXML
    public CheckBox checkboxShowCorrectAnswer = new CheckBox();

    @FXML
    public void initialize() throws IOException {
        Preferences prefs = Preferences.userRoot().node(App.class.getName());
        //Generate Schema from JSON file
        /*Json2PojoGenerator generator = new Json2PojoGenerator("source.json",
                "src/main/java/");
        generator.generate("Model", "com.valeriygulin.quiz.model");*/
        this.checkboxShowCorrectAnswer.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                prefs.put("cbAction", String.valueOf(new_val));
            }
        });
    }

    @FXML
    public void ButtonFromInternet(ActionEvent actionEvent) throws IOException {
        App.openWindowAndWait("loadingForm.fxml", "Loading Form", null);
    }

    @FXML
    public void buttonFromFile(ActionEvent actionEvent) {
        Preferences prefs = Preferences.userRoot().node(App.class.getName());
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
            try {
                FileRepository fileRepository = new FileRepository(file);
                App.openWindowAndWait("gameForm.fxml", "Game Form", fileRepository);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
