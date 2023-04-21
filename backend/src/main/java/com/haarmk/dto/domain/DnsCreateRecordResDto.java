package com.haarmk.dto.domain;

import lombok.Data;

@Data
public class DnsCreateRecordResDto {
	 private Integer id;
	 private String  domainName;
	 private String  host;
	 private String  fqdn;
	 private DnsRecordType type;
	 private String answer;
	 private Integer ttl;
}
