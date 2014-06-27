package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Article;

/**
 * This is the data access manager for Article
 * @author Lee Zhuo Xun
 *
 */
public class ArticleManager {
	private static DBController dbController = new DBController();
	
	/**
	 * This method is to create article into database
	 * 
	 * @param article
	 * @return boolean
	 */
	public boolean createArticle(Article article) {
		String sql = "INSERT INTO articles ";
		sql += "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, article.getArticleID());
			ps.setString(2, article.getTitle());
			ps.setString(3, article.getContent());
			Timestamp timestamp = new Timestamp(article.getDateTime().getTime());
			ps.setTimestamp(4, timestamp);
			ps.setString(5, article.getCategory());
			ps.setString(6, article.getLocation());
			ps.setString(7, article.getUserNRIC());
			ps.setInt(8, article.getActive());
			ps.setInt(9, article.getApproved());

			System.out.println(ps);
			ps.executeUpdate();

			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is to retrieve all articles from the database.
	 * 
	 * @return ArrayList<Article>
	 */
	public ArrayList<Article> retrieveAllArticles() {
		String sql = "SELECT * FROM articles";
		ArrayList<Article> articlesArrList = new ArrayList<Article>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setArticleID(rs.getInt("articleID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setDateTime(rs.getTimestamp("dateTime"));
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getInt("approved"));
				articlesArrList.add(article);
			}
			conn.close();
			return articlesArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to retrieve a article based on articleID
	 * @param articleID
	 * @return Article
	 */
	public Article retrieveArticle(int articleID) {
		String sql = "SELECT * FROM articles WHERE articleID = " + articleID ;
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			Article article = new Article();
			if (rs.next()) {
				article.setArticleID(rs.getInt("articleID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setDateTime(rs.getTimestamp("dateTime"));
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getInt("approved"));
			} else {
				return null;
			}
			conn.close();
			return article;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to edit article into the database
	 * @param article
	 * @return boolean
	 */
	public boolean editArticle(Article article) {
		String sql = "UPDATE articles ";
		sql += "SET title = ? , content = ? , dateTime = ? , category = ? , location = ? ," +
				" userNRIC = ? , active = ? , approved = ? WHERE articleID = ? ";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, article.getTitle());
			ps.setString(2, article.getContent());
			Timestamp timestamp = new Timestamp(article.getDateTime().getTime());
			ps.setTimestamp(3, timestamp);
			ps.setString(4, article.getCategory());
			ps.setString(5, article.getLocation());
			ps.setString(6, article.getUserNRIC());
			ps.setInt(7, article.getActive());
			ps.setInt(8, article.getApproved());
			ps.setInt(9, article.getArticleID());
			
			System.out.println(ps);
			ps.executeUpdate();
			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is to obsolete article
	 * @param articleID
	 * @return boolean
	 */
	public boolean obsoleteArticle(int articleID) {
		String sql = "UPDATE articles SET active = 0 WHERE articleID = " + articleID;
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			System.out.println(ps1);
			ps1.executeUpdate();
			
			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String args[]){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,30);
		calendar.set(Calendar.MONTH,6);
		calendar.set(Calendar.YEAR,2014);
		calendar.set(Calendar.HOUR_OF_DAY,9);
		calendar.set(Calendar.MINUTE,20);
		calendar.set(Calendar.SECOND, 00);
		//Article article = new Article(1,"Xy","Xy",calendar.getTime(),"X","X","S9523803C",1,0);
		/*createArticle(article);*/
		//System.out.println(editArticle(article));
		//obsoleteArticle(1);
	}
}
