package com.msci.eshopping.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.msci.eshopping.api.Product;

public class ProductServiceImpl {
	
	public Connection getConnection() {
		
		Connection con = null;
		try {
			Class.forName("java.sql.DriverManager");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");
			con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}

	public ArrayList<Product> readAll() {

		String sql= "select * from product";

		Connection con = getConnection();
		ArrayList<Product> al = new ArrayList<>(); 
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setQuantity(rs.getString("quantity"));
				al.add(product);
			} 
		} catch (Exception e) {
		}
		return al;
	}
}
