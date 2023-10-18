package com.mcs.userservice.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String id;

    public ResourceNotFoundException(String resourceName, String id){
        super("Resource not founde with: "+ id);
    }
}
/*	String resourceName;
	String fieldName;
	long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}*/