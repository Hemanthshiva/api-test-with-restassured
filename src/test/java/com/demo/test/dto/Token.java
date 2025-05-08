package com.demo.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "token"
})
public class Token {
    @JsonProperty("token")
    private String token;

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

}
