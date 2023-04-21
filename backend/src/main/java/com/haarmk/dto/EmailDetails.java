package com.haarmk.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDetails {
    private String[] recipients;
    private String msgBody;
    private String subject;
    private String attachment;
    private Boolean isHtml;
}
