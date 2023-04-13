package com.valeriygulin.quiz.controllers;

import com.valeriygulin.quiz.App;
import com.valeriygulin.quiz.model.Result;
import com.valeriygulin.quiz.repository.FileRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.prefs.Preferences;

public class GameFormController implements ControllerData<FileRepository> {
    @FXML
    public TabPane tabPaneMain;
    private HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public void initData(FileRepository value) {
        int count = 1;
        for (Result result : value.getModels().getResults()) {
            Tab tab = new Tab();
            VBox vBox = new VBox();
            ToggleGroup group = new ToggleGroup();
            tab.setText("Q" + count);
            String question = result.getQuestion();
            Label label = new Label(question);
            label.setTranslateX(75);
            label.setTranslateY(75);
            vBox.getChildren().add(label);
            tab.setContent(label);
            ArrayList<String> answers = new ArrayList<>();
            answers.add(result.getCorrectAnswer());
            answers.addAll(result.getIncorrectAnswers());
            Collections.shuffle(answers);
            int y = 100;
            for (String answer : answers) {
                RadioButton radioButton1 = new RadioButton(answer);
                radioButton1.setTranslateX(75);
                radioButton1.setTranslateY(y);
                radioButton1.setToggleGroup(group);
                vBox.getChildren().add(radioButton1);
                y += 25;
            }
            tab.setContent(vBox);
            this.tabPaneMain.getTabs().add(tab);
            count++;
            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                    RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle();
                    hashMap.put(tab.getText(), chk.getText());
                }
            });
        }
        Tab tab = new Tab();
        tab.setText("Check");
        Button button = new Button("Check");
        button.setTranslateX(75);
        button.setTranslateY(25);
        button.setPrefSize(227, 26);
        tab.setContent(button);
        button.setOnAction(actionEvent -> {
            if (this.hashMap.size() != (this.tabPaneMain.getTabs().size() - 1)) {
                App.showAlert("Error!", "Please,answer all the questions", Alert.AlertType.ERROR);
                return;
            }
            int y = 35;
            VBox vBox = new VBox();
            vBox.getChildren().add(button);
            int count2 = 0;
            for (int i = 0; i < this.hashMap.size(); i++) {
                String text = this.tabPaneMain.getTabs().get(i).getText();
                Label label = null;
                Result result1 = value.getModels().getResults().get(i);
                String correctAnswer = result1.getCorrectAnswer();
                Preferences prefs = Preferences.userRoot().node(App.class.getName());
                String cbAction = prefs.get("cbAction", "");
                boolean cb = Boolean.parseBoolean(cbAction);
                if (this.hashMap.getOrDefault(text, null).equals(correctAnswer)) {
                    if (cb) {
                        label = new Label(text + ":" + " +" + correctAnswer);
                        count2++;
                    } else {
                        label = new Label(text + ":" + " +");
                        count2++;
                    }
                } else {
                    if (cb) {
                        label = new Label(text + ":" + " +" + correctAnswer);

                    } else {
                        label = new Label(text + ":" + " -");
                    }
                }
                label.setTranslateX(75);
                label.setTranslateY(y);
                y += 10;
                vBox.getChildren().add(label);
            }
            Label label1 = new Label("Correct/Incorrect:" + count2 + "/" + (this.tabPaneMain.getTabs().size() - 1));
            label1.setTranslateX(75);
            label1.setTranslateY(y);
            double percent = ((double) count2 / (this.tabPaneMain.getTabs().size() - 1)) * 100;
            Label label2 = new Label("Correct Answer Rate:" + (int) percent + "%");
            label2.setTranslateX(75);
            label2.setTranslateY(y + 10);
            vBox.getChildren().addAll(label1, label2);
            tab.setContent(vBox);
        });
        this.tabPaneMain.getTabs().add(tab);
    }
}
