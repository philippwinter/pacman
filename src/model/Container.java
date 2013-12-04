package model;

import java.util.ArrayList;

import model.exception.ObjectAlreadyInListException;

public interface Container<E> {
	
	public E get(int i);
	
	public void add(E el) throws ObjectAlreadyInListException;
	
	public ArrayList<E> getAll();

}
