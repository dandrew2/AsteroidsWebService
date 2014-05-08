package edu.ycp.cs496.asteroids.model;

public class User {
	private String name; 
	private int score;
	
	public User(String name){
		this.name = name; 
		score = 0; 
	}
	
	public User(){
		name = null; 
		score = 0; 
	}
	
	public User(String name, int score){
		this.name = name; 
		this.score = score; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	} 
	
}
