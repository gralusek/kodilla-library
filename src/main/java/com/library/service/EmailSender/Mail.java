package com.library.service.EmailSender;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCC;
}