package com.project1.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

//url: jdbc:postgresql://mydb1.ctqn1wf3ao57.us-east-1.rds.amazonaws.com:5432/project1?currentSchema=project1schema
//username: jdbc_user
//password: wasspord

public class EnvironmentConnectionUtil {
	//final static Logger log = Logger.getLogger("EnvironmentConnectionUtil"); //currentSchema is optional,, default is public
	//url for jdbc -> jdbc:postgresql://endpoint:port/dbName?currentSchema=schema
//	private final String url=System.getenv("url");
//	private final String username =System.getenv("username");
//	private final String password = System.getenv("password");
	private final String url="jdbc:postgresql://mydb1.ctqn1wf3ao57.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=project1schema";
	private final String username ="jdbc_user";
	private final String password = "wasspord";
	private static EnvironmentConnectionUtil instance;
	private EnvironmentConnectionUtil() {
		
		super();
		
	}
	
	//makes this a singleton
	public static EnvironmentConnectionUtil getInstance() {
		if(instance == null) {
			instance = new EnvironmentConnectionUtil();
		}
		return instance;
	}
	
	//to create a connection to the db
	public Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(url,username,password);
	}
	
	
	
}//end of class
