package com.valeriygulin.quiz.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valeriygulin.quiz.model.ResponseResult;
import com.valeriygulin.quiz.model.Result;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {

    private ResponseResult responseResult;
    private ObjectMapper objectMapper = new ObjectMapper();

    public FileRepository() {
    }

    public FileRepository(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream())) {
            this.responseResult = objectMapper.readValue(bufferedInputStream, ResponseResult.class);
            for (Result result : this.responseResult.getResults()) {
                String question = result.getQuestion();
                String decode = StringEscapeUtils.unescapeHtml4(question);
                result.setQuestion(decode);
                String decode1 = StringEscapeUtils.unescapeHtml4(result.getCorrectAnswer());
                result.setCorrectAnswer(decode1);
                List<String> strings = new ArrayList<>();
                for (String incorrectAnswer : result.getIncorrectAnswers()) {
                    String decode2 = StringEscapeUtils.unescapeHtml4(incorrectAnswer);
                    strings.add(decode2);
                }
                result.setIncorrectAnswers(strings);
            }
        }
    }

    public FileRepository(File file) throws IOException {
        this.responseResult = objectMapper.readValue(file, ResponseResult.class);
        for (Result result : this.responseResult.getResults()) {
            String cipher = this.cipherDecryption(result.getCorrectAnswer());
            result.setCorrectAnswer(cipher);
            List<String> strings = new ArrayList<>();
            for (String incorrectAnswer : result.getIncorrectAnswers()) {
                String cipher1 = this.cipherDecryption(incorrectAnswer);
                strings.add(cipher1);
            }
            result.setIncorrectAnswers(strings);
        }
    }

    public ResponseResult getModels() {
        return responseResult;
    }

    public void save(File file) throws IOException {
        for (Result result : this.responseResult.getResults()) {
            String cipher = this.cipherEncryption(result.getCorrectAnswer());
            result.setCorrectAnswer(cipher);
            List<String> strings = new ArrayList<>();
            for (String incorrectAnswer : result.getIncorrectAnswers()) {
                String cipher1 = this.cipherEncryption(incorrectAnswer);
                strings.add(cipher1);
            }
            result.setIncorrectAnswers(strings);
        }
        objectMapper.writeValue(file, this.responseResult);
    }

    private String cipherEncryption(String str) {
        String res = "";
        int len = str.length();
        for (int x = 0; x < len; x++) {
            char ch = (char) (str.charAt(x) + 1);
            if (ch > 'z')
                res += (char) (str.charAt(x) - (26 - 1));
            else
                res += (char) (str.charAt(x) + 1);
        }
        return res;
    }

    private String cipherDecryption(String str) {
        String res = "";
        int len = str.length();
        for (int x = 0; x < len; x++) {
            char ch = (char) (str.charAt(x) - 1);
            if (ch > 'z')
                res += (char) (str.charAt(x) + (26 + 1));
            else
                res += (char) (str.charAt(x) - 1);
        }
        return res;
    }


    @Override
    public String toString() {
        return "FileRepository{" +
                "model=" + responseResult + "}";
    }

}
