package com.domoment.leaves.service;

/**
 * public service with save update delete query
 * @author Narule
 *
 * @param <T>
 */
public interface BaseService<T> {
	
	public Object save(T data);
	
	public Object delete(T data);
	
	public Object update(T data);
	
	public Object query(T data);
}
