package com.haarmk.dto;

import lombok.Data;

@Data
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
