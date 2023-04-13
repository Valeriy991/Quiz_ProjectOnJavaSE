
package com.valeriygulin.quiz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "category",
        "type",
        "difficulty",
        "question",
        "correct_answer",
        "incorrect_answers"
})
public class Result {

    @JsonProperty("category")
    private String category;
    @JsonProperty("type")
    private String type;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("question")
    private String question;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param difficulty
     * @param incorrectAnswers
     * @param question
     * @param category
     * @param type
     * @param correctAnswer
     */
    public Result(String category, String type, String difficulty, String question, String correctAnswer, List<String> incorrectAnswers) {
        super();
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("difficulty")
    public String getDifficulty() {
        return difficulty;
    }

    @JsonProperty("difficulty")
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonProperty("correct_answer")
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @JsonProperty("correct_answer")
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @JsonProperty("incorrect_answers")
    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    @JsonProperty("incorrect_answers")
    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(category, result.category) && Objects.equals(type, result.type) && Objects.equals(difficulty, result.difficulty) && Objects.equals(question, result.question) && Objects.equals(correctAnswer, result.correctAnswer) && Objects.equals(incorrectAnswers, result.incorrectAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, type, difficulty, question, correctAnswer, incorrectAnswers);
    }

    @Override
    public String
    toString() {
        return "Result{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                '}';
    }
}
