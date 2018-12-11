package com.foo.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.foo.beans.City;

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
	
	public ArrayList<City> getAllCities() {
		var cities = new ArrayList<City>();
		try {
			PreparedStatement preparedStatement = this.dbConnection
				    .prepareStatement("SELECT * FROM ville");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id_ville");
				String name = resultSet.getString("nom_ville");
				
				var city = new City(id, name);
				
				cities.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cities;
	}
	
	public City getCityById(int id) {
		City city = null;
		try {
			PreparedStatement preparedStatement = this.dbConnection
				    .prepareStatement("SELECT * FROM ville WHERE id_ville=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("nom_ville");
				
				city = new City(id, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}
}
