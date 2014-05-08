package edu.ycp.cs496.asteroids.servlets;


import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import edu.ycp.cs496.asteroids.controllers.ScoreController;
import edu.ycp.cs496.asteroids.model.User;
import edu.ycp.cs496.asteroids.model.json.JSON;



public class Leaderboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Handle get request
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			

			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {
				// Set status code and content type
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("application/json");

				//Get entire inventory and print it out
				ScoreController cont = new ScoreController();
				List<User> userList = cont.getLeaderboard(); 
				User[] userListAsArray = userList.toArray(new User[userList.size()]);
				Writer writer = resp.getWriter(); 
				
				JSON.getObjectMapper().writeValue(writer, userListAsArray); 
				return;
			}

			// Get the item name
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);
			}

			// Use a GetItemByName controller to find the item in the database
			ScoreController controller = new ScoreController();
			User user = controller.getUser(pathInfo);

			if (user == null) {
				// No such item, so return a NOT FOUND response
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				resp.setContentType("text/plain");
				resp.getWriter().println("No such item: " + pathInfo);
				return;
			}

			// Set status code and content type
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");

			// Return the item in JSON format
			JSON.getObjectMapper().writeValue(resp.getWriter(), user);
			
		}

		//Handle post request
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {

				// Use a GetItemByName controller to find the item in the database
				ScoreController cont = new ScoreController(); 
				User user = JSON.getObjectMapper().readValue(req.getReader(),  User.class); 
				cont.addScore(user); 

				// Set status code and content type
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("application/json");
				resp.getWriter().println("Item " + user.getName() + " added to leaderboard");
			}

			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);	
		}


		//Handle delete request
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			ScoreController cont = new ScoreController(); 
			String pathInfo = req.getPathInfo();

			if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {
				
				cont.deleteLeaderboard(); 
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("application/json");
			}

			else{
				// Get the item name
				if (pathInfo.startsWith("/")) {
					pathInfo = pathInfo.substring(1);
				}
				
				System.out.println(pathInfo); 
				// Use a GetItemByName controller to find the item in the database
				
				User user = cont.getUser(pathInfo);
				
				
				if (user == null) {
					// No such item, so return a NOT FOUND response
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					resp.setContentType("text/plain");
					resp.getWriter().println("No such item: " + pathInfo);
					return;
				}

				cont.deleteUser(user); 

				// Set status code and content type
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("application/json");
				
			}
		}
}
