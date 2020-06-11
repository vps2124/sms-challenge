package com.vipul.demo.response;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("MISSING REQUIRED FIELD"), RECORD_ALREADY_EXIST("RECORD EXISTS"),
	INTERNAL_SERVER_ERROR("INTERNAL SERVER ERROR"), NO_RECORD_FOUND("NO RECORD FOUND");

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
