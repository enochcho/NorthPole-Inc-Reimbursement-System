package com.project1.repo;

import java.util.List;

/**
 * 
 * @author Revature
 *
 * @param <T> Type of model
 * @param <I> Primary key for model
 * 
 * This will create a contract for all dao classes to
 * follow.
 */
public interface DaoContract <T,I> {
	
	
	
	/**
	 * 
	 * @return A list of all instances in the db
	 */
	List<T> findAll();
	
	
	/**
	 * 
	 * @param i primary key of the instance
	 * @return the instance with the same primary key
	 */
	T findById(I i);
	
	
	/**
	 * 
	 * @param t the instance to update
	 * @return the affected number of rows
	 */
	int update(T t);
	
	
	/**
	 * 
	 * @param t the instance to create
	 * @return the number of rows affected
	 */
	int create(T t);
	
	
	/**
	 * 
	 * @param i the primary key of the instance to be removed
	 * @return how many were removed [0 if failure, 1 if success]
	 */
	int delete(I i);

}