module com.valeriygulin.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsonschema2pojo.core;
    requires codemodel;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.prefs;
    requires java.desktop;
    requires org.apache.commons.lang3;


    opens com.valeriygulin.quiz to javafx.fxml;
    exports com.valeriygulin.quiz;
    exports com.valeriygulin.quiz.controllers;
    opens com.valeriygulin.quiz.controllers to javafx.fxml;
    exports com.valeriygulin.quiz.model to com.fasterxml.jackson.databind;
    opens com.valeriygulin.quiz.model to javafx.base;

}