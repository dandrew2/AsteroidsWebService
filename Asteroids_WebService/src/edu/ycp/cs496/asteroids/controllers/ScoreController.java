package edu.ycp.cs496.asteroids.controllers;


import java.util.List;

import edu.ycp.cs496.asteroids.model.User;
import edu.ycp.cs496.asteroids.model.persist.Database;
import edu.ycp.cs496.asteroids.model.persist.IDatabase;


public class ScoreController {
	
	public void addScore(User u){
		IDatabase db = Database.getInstance();
		List<User> leaderboard = db.getLeaderboard(); 
		
		int lbSize = leaderboard.size(); 
		
		if(lbSize < 10){
			db.addUser(u); 	
		}
	}
	
	public List<User> getLeaderboard(){
		IDatabase db = Database.getInstance(); 
		return db.getLeaderboard(); 
	}
	
	public User getUser(String userName) {
		IDatabase db = Database.getInstance();
		return db.getUser(userName);
	}
	
	public void deleteUser(User u) {
		IDatabase db = Database.getInstance();

		for(User user : db.getLeaderboard()){
			if(user.getName().equals(u.getName())){
				db.deleteUser(user); 
			}
		}
		
	}
	
	public void deleteLeaderboard(){
		IDatabase db = Database.getInstance(); 
		
		db.cleanLeaderboard(); 
	}

}
