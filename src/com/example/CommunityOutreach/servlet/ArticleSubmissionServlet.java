package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ArticleSubmissionServlet
 */
@WebServlet("/ArticleSubmissionServlet")
public class ArticleSubmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleSubmissionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("titletitle", "");
		request.setAttribute("contentcontent", "");
		//request.setAttribute("catcat", "Feedback");
		request.setAttribute("articleErrorTitle", "");
		request.setAttribute("articleErrorMessage", "");
		request.setAttribute("articleclose", "");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String category = (request.getParameter("category"));
		String content = request.getParameter("content");
		DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy HH:mm a");
		Calendar cal = Calendar.getInstance();
		String now = dateFormat.format(cal.getTime());
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy HH:mm a");

            Date currentTime = null;
			try {
				currentTime = simpleDateFormat.parse(now);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		String location=request.getParameter("address");
		
		String userNRIC = "S9512233X";
		int active = 1;
		String approved= "Pending";
		double dbLat= Double.parseDouble(request.getParameter("storingLat"));
		double dbLon = Double.parseDouble(request.getParameter("storingLon"));
		
		
		
		if(title.equals("")||content.equals("")){
		
			request.setAttribute("titletitle", title);
			request.setAttribute("contentcontent", content);
			//request.setAttribute("catcat", category);
			request.setAttribute("articleErrorTitle", "Unable to submit");
			request.setAttribute("articleErrorMessage", "Check if you have entered all required fields");
			request.setAttribute("articleclose", "X Close");
			RequestDispatcher rd = request.getRequestDispatcher("articleSubmission.jsp");
	        rd.forward(request,response);
	        
	        
	        
		}
		
		
		else{
		
		
			ArticleManager am = new ArticleManager();
			Article a = new Article(0, title, content, currentTime, category, location, userNRIC, active,approved,dbLat, dbLon);
			
			 boolean articleCreatedCheck = false;
		        try{
		        	articleCreatedCheck = am.createArticle(a);
		        	if(!articleCreatedCheck){
		        		JsonObject myObj = new JsonObject();
		                myObj.addProperty("success", false);
		                myObj.addProperty("message","Unable to create article successfully.");
		                out.println(myObj.toString());
		        	}
		        	else{
		                JsonObject myObj = new JsonObject();
		                myObj.addProperty("success", true);
		                myObj.addProperty("message","Article created successfully.");
		                out.println(myObj.toString());
		        	}
		        }
		        catch(Exception ex){
		        	ex.printStackTrace();
		    		JsonObject myObj = new JsonObject();
		            myObj.addProperty("success", false);
		            myObj.addProperty("message","Unable to create article successfully.");
		            out.println(myObj.toString());
		        }
			    
		        /****STILL USING****/
		        RequestDispatcher rd = request.getRequestDispatcher("DisplayArticleMainServlet");
		        rd.forward(request,response);
		}
	}

}
