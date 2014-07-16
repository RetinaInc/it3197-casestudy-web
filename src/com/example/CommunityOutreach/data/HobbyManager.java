package com.example.CommunityOutreach.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Hobby;

public class HobbyManager {
	private DBController dbController = new DBController();
	
	public boolean createHobby(Hobby hobby){
		int active = 1;
		boolean result = false;
		String sql = "INSERT INTO hobbies_group ";
		sql += "VALUES(?,?,?,?,?,?,?,?)";
		try {
			Connection conn = dbController.getConnection();
			hobby.setGrpID(0);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			/*
			try {
				File file = new File("/storage/emulated/0/WhatsApp/Media/WhatsApp/Images/IMG-20140704-WA0001.jpg");
				FileInputStream fs = new FileInputStream(file);
				ps.setBinaryStream(2,fs,fs.available());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			ps.setInt(1, hobby.getGrpID());
			ps.setString(2, hobby.getGrpName());
			ps.setString(3, hobby.getCategory());
			ps.setDouble(4, hobby.getLat());
			ps.setDouble(5, hobby.getLng());
			ps.setString(6, hobby.getGrpDesc());
			ps.setInt(7, active);
			ps.setString(8, "");
			
			ps.executeUpdate();
			result=true;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Hobby> retrieveAllHobby(){
		ArrayList<Hobby> hobbyList = new ArrayList<Hobby>();
		String sql = "SELECT * FROM hobbies_group WHERE active = 1";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Hobby hobby = new Hobby();
				hobby.setGrpID(rs.getInt("groupID"));
				hobby.setGrpName(rs.getString("groupName"));
				hobby.setCategory(rs.getString("category"));
				hobby.setGrpDesc(rs.getString("description"));
				hobby.setLat(rs.getDouble("Lat"));
				hobby.setLng(rs.getDouble("Lng"));
				hobbyList.add(hobby);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catchAl block
			e.printStackTrace();
		}
		return hobbyList;
	}
	
	
	public Hobby retrieveHobby(int id){
		String sql = "SELECT * FROM hobbies_group WHERE groupID = " + id;
		
		Hobby hobby = new Hobby();
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				hobby.setGrpID(rs.getInt("groupID"));
				hobby.setGrpName(rs.getString("groupName"));
				hobby.setCategory(rs.getString("category"));
				hobby.setGrpDesc(rs.getString("description"));
				hobby.setLocation(rs.getString("location"));
				hobby.setLat(rs.getDouble("Lat"));
				hobby.setLng(rs.getDouble("Lng"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hobby;
	}
	
}
