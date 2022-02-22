package com.demo.assignment.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorDetails {
	private Date timestamp;
    private String message;
    private String details;

}
