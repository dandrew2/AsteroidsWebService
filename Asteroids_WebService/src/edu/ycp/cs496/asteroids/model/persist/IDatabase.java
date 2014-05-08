package edu.ycp.cs496.asteroids.model.persist;

import java.util.List;

import edu.ycp.cs496.asteroids.model.User;



/**
 * Persistence operations for fruit web service.
 */
public interface IDatabase {
	/**
	 * Get a single {@link Item} for the given item name.
	 * 
	 * @param itemName the item name
	 * @return the {@link Item}, or null if there is no such item
	 */
	public User getUser(String user);
	
	/**
	 * Get the current inventory (list of {@link Item}s.
	 * 
	 * @return the current inventory (list of {@link Item}s
	 */
	
	public List<User> getLeaderboard(); 
	
	public void addUser(User u); 
	
	public void deleteUser(User u); 
	
	public void cleanLeaderboard();
	
}