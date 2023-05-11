package com.haarmk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
    private String[] recipients;
    private String msgBody;
    private String subject;
    private String attachment;
    private Boolean isHtml;
}
