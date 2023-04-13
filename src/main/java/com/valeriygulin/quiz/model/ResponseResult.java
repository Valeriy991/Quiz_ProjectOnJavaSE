
package com.valeriygulin.quiz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "response_code",
        "results"
})
public class ResponseResult {

    @JsonProperty("response_code")
    private int responseCode;
    @JsonProperty("results")
    private List<Result> results = new ArrayList<Result>();

    /**
     * No args constructor for use in serialization
     */
    public ResponseResult() {
    }

    /**
     * @param results
     * @param responseCode
     */
    public ResponseResult(int responseCode, List<Result> results) {
        super();
        this.responseCode = responseCode;
        this.results = results;
    }

    @JsonProperty("response_code")
    public int getResponseCode() {
        return responseCode;
    }

    @JsonProperty("response_code")
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseResult responseResult = (ResponseResult) o;
        return responseCode == responseResult.responseCode && Objects.equals(results, responseResult.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, results);
    }

    @Override
    public String
    toString() {
        return "Model{" +
                "responseCode=" + responseCode +
                ", results=" + results +
                '}';
    }
}
