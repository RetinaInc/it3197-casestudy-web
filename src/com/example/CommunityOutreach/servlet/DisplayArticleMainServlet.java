package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DisplayArticleMainServlet
 */
@WebServlet("/DisplayArticleMainServlet")
public class DisplayArticleMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayArticleMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		
		
        
        
       // double currentLat = Double.parseDouble(request.getParameter("currentLat"));
		//double currentLon = Double.parseDouble(request.getParameter("currentLon"));
		//int distSelected = Integer.parseInt(request.getParameter("distSelected"));
		
		//System.out.println(currentLat);
		//System.out.println(currentLon);
		//System.out.println(distSelected);
        
        
        
		
		
		ArticleManager am = new ArticleManager();
		List<Article> artList = am.retrieveAllApprovedArticlesWithinDistance(0, 0, 0);
	/*	for(int i=0; i<artList.size();i++){
			artList.get(i).getTitle();
			System.out.println();
			System.out.println(artList.get(i).getTitle());
		}*/
	//	System.out.println(artList.size());
		request.setAttribute("artList", artList);
		request.setAttribute("selectedDist", 0);
		
		
	/*	if((artList.size() == 0) || (artList == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message", "Unable to retrieve articles.");
            out.println(myObj.toString());
        }
        else {
            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
        	JsonElement eventObj;
        	JsonArray eventArray = new JsonArray();
            for(int i=0;i<artList.size();i++){
            	eventObj = gson.toJsonTree(artList.get(i));
            	eventArray.add(eventObj);
            	myObj.add("artList", eventArray);
            }
            out.println(myObj.toString());
        }
        out.close();*/
		
        RequestDispatcher rd = request.getRequestDispatcher("articleDisplayMainMobileView.jsp");
		rd.forward(request,response);
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*double dist = Math.sqrt(
				
				
			//	1.3871626, 103.89699930000006
			//	1.36991135967923, 103.85165950555734
				
				
	            (1.3871626 - 1.36991135967923) *  (1.3871626 - 1.36991135967923) + 
	            (103.89699930000006 - 103.85165950555734) *  (103.89699930000006 - 103.85165950555734)
	        );
		System.out.print(dist);*/
		
	//	HttpSession session = request.getSession(true);
	//	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
	//	ArrayList<Article> articlesArray=new ArticleManager().retrieveAllArticles();
	//	session.setAttribute("articlesArray", gson.toJson(articlesArray));
		//System.out.println("HIIII" + articlesArray.size());
		//RequestDispatcher rd = request.getRequestDispatcher("DisplayPacking.jsp");
		//rd.forward(request,response);
		
	/*	PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        ArticleManager articleManager = new ArticleManager();
        ArrayList<Article> articleArrList = articleManager.retrieveAllArticles();
        
        System.out.println(articleArrList.size());
 
        if((articleArrList.size() == 0) || (articleArrList == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message", "Unable to retrieve users.");
            out.println(myObj.toString());
        }
        else {
            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
        //    myObj.addProperty("success", true);
        	JsonElement articlesObj;
        	JsonArray articlesArray = new JsonArray();
            for(int i=0;i<articleArrList.size();i++){
            	articlesObj = gson.toJsonTree(articleArrList.get(i));
            	articlesArray.add(articlesObj);
            	myObj.add("articlesInfo", articlesArray);
            }
            out.println(myObj.toString());
       //     System.out.println((articlesArray.size()));
            session.setAttribute("articlesArray", articlesArray);
        }
        
        
        out.close();*/
        
     //   RequestDispatcher rd = request.getRequestDispatcher("articleDisplayMain.jsp");
	//	rd.forward(request,response);
	}

}

