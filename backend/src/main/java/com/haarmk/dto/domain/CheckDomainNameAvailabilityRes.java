package com.haarmk.dto.domain;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CheckDomainNameAvailabilityRes {
	private DomainCheckData result;
	@SerializedName("results")
    private List<DomainCheckData> suggestions;
}





