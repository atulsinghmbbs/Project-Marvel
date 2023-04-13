package com.haarmk.dto.domain;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckAvailabilityResponse {
    @JsonProperty("results")
    private DomainCheckData[] results;


}





