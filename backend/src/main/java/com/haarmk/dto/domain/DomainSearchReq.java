package com.haarmk.dto.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DomainSearchReq {

	int timeout;
	String keyword;
	String[] tldFilter;
}
