package model.exception;

public class ObjectAlreadyInListException extends Exception {
	
	public ObjectAlreadyInListException(String objectClass){
		super("The Object of class " + objectClass + " is already in the list");
	}

}
