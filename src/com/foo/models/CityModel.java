package com.foo.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class CityModel {
	private Connection dbConnection;
	
	public CityModel() {
		this.dbConnection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			this.dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/erwan_g_wcs_reims", "root", "jecode4wcs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.dbConnection;
	}
}
