package edu.ycp.cs496.asteroids.model.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs496.asteroids.model.User;




/**
 * Implementation of the {@link IDatabase} interface that stores
 * objects using in-memory data structures.  It doesn't
 * provide actual persistence, but is useful as a proof
 * of concept.
 */
public class FakeDatabase implements IDatabase{
	private List<User> leaderboard;
	
	public FakeDatabase() {
		leaderboard = new ArrayList<User>();
		
		User testUser = new User(); 
		testUser.setName("DSA"); 
		testUser.setScore(1005); 
		
		leaderboard.add(testUser); 
	}
	
	@Override
	public User getUser(String name) {
		for (User user : leaderboard) {
			if (user.getName().equals(name)) {
				// return a copy
				return new User(user.getName(), user.getScore());
			}
		}
		
		
		
		// no such item
		return null;
	}

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		leaderboard.add(u); 
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		leaderboard.remove(u); 
	}

	
	@Override
	public void cleanLeaderboard() {
		// TODO Auto-generated method stub
		for(User u : leaderboard){
			leaderboard.remove(u); 
		}
	}

	@Override
	public List<User> getLeaderboard() {
		// TODO Auto-generated method stub
		return new ArrayList<User>(leaderboard); 
	}


	
	
}