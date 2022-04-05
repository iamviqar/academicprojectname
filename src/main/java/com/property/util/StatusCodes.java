package com.property.util;

public enum StatusCodes {
		DATA_RETRIEVE_SUCCESS("S1", "Data retrieved successfully"),
	    DATA_INSERT_SUCCESS("S2", "Data saved successfully"),
		DATA_UPDATE_SUCCESS("S3", "Data updated successfully"),
		DATA_RETRIEVE_FAIL("E1", "Data retrieval error"),
	    DATA_INSERT_FAIL("E2", "Data save error"),
		DATA_UPDATE_FAIL("E3", "Data update error"),
		RENT_ALREADY_MARKED("RE1", "Rent Already Paid For This Month"),
		RENT_PREVIOUS_UNPAID("RE2", "Previous Month Rent Not Paid"),
		TENANT_UNIQUE_CODE("TE1", "Tenant Code Should be Unique"),
		PROPERTY_UNIQUE_CODE("PE1", "Property Code Should be Unique"),
		PARTY_UNIQUE_CODE("PRE1", "Party Code Should be Unique"),
		AGREEMENT_UNIQUE_CERTIFICATE("AE1", "Agreement Certifate Should be Unique"),
		USERNAME_UNIQUE("UE1", "Username Should be Unique"),
		AUTH_SUCCESS("S4", "Authentication Successfull"),
		AUTH_FAIL("E4", "Invalid Credential"),;
	
		private final String code;
		private final String description;
		
		StatusCodes(String code,String description){
			this.code = code;
		    this.description = description;
		}
		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}
}
