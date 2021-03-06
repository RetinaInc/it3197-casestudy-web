<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/dataTable/jquery.dataTables_themeroller.css" />
   		<link rel="stylesheet" type="text/css" href="css/dataTable/demo_page.css" />
   		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.custom.css" />
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	  	<script src="js/displayArticle.js"></script>
	  	<script type="text/javascript" language="javascript" src="js/dataTable/dataTables.js"></script>
   		<script type="text/javascript" language="javascript" src="js/dataTable/numberSort.js"></script>
	</jsp:attribute>
	<jsp:attribute name="content">
		
			<h1></h1>
			<div class="panel panel-info" Style="width:705px;margin:0px auto;">
			  <div class="panel-heading">
			    <h3>Send Email</h3>
			  </div>
			
			  
			  <form action="DesktopFeedbackArticle" method="post">
				 &nbsp<input type="submit" class="btn btn-primary btn-sm pull-right" value="Cancel" id="btn" style="margin-top:5px;margin-right:13px;">
			  </form>
			  
			  
			  
			  <form action="ArticleSend" method="post">
			<!--    <div class="panel-body" style="background-image: url(resources/article.jpg); height: 375px; width: 675px; margin:0px auto; border: 1px solid black;">-->
		   <div class="panel-body" style="background-image: url(resources/article.jpg); height: 375px; width: 675px; margin:0px auto; border: 1px solid black;margin-top:20px;">
					   <p></p>
					  
					   <div class="form-group">
					    <div class="input-group">
					      <div class="input-group-addon"><b>To: </b></div>
					    <!--    <input class="form-control" type="email" placeholder="" name = "to" style="width:300px;">-->
							<select class="form-control" style="width:350px;">
							  <option value="National Environment Agency (NEA)">National Environment Agency (NEA)</option>
							  <option value="National Parks Board (NPARKS)">National Parks Board (NPARKS)</option>
							  <option value="Singapore Land Authority (SLA)">Singapore Land Authority (SLA)</option>
							  <option value="PUB, The National Water Agency (PUB)">PUB, The National Water Agency (PUB)</option>
							  <option value="National Council Of Social Service (NCSS)">National Council Of Social Service (NCSS)</option>
							  <option value="Land Transport Authority (LTA)">Land Transport Authority (LTA)</option>
							  <option value="Housing And Development Board (HDB)">Housing And Development Board (HDB)</option>
							</select>  
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="input-group">
					      <div class="input-group-addon"><b>Subject: </b></div>
					      <input class="form-control" type="text" placeholder="" name = "subject" style="width:280px;" value="${articleTitle}">
					    </div>
					  </div>
					  <textarea cols="50" placeholder="Enter your message..." style="width:100%; height:190px;font-family:arial;font-size:16px;" name="message" >${msg}</textarea>
					  <p></p>
					  <button type="submit" class="btn-primary btn-sm" id="btn" style= "background-image: url(resources/articleIcon.png); width:37px;height:35px; margin:0px auto;"></button>
					  
					  <input type = "text" class="form-control" id="idArticle" style="width:100%;display:none;" name="idArticle" value = "${idArticle}"/>
			  </div>
			 
			    
			</div>  
		</form>

	</jsp:attribute>
</t:master>