<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/validation/screen.css" />
		<link rel="stylesheet" type="text/css" href="css/validation/cmxform.css" />
		<style>
			.floatLeftText{
				float:left;
			}
			#currentForm{
				margin-top:3%;
			}
			#location{
				display:inline;
			}
			#container{
				margin-bottom:5%:
			}
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script type="text/javascript" src="https://www.dropbox.com/static/api/1/dropins.js" id="dropboxjs" data-app-key="cqvf3nim3klslqb"></script>
		<script src="js/validation/form-validate.js"></script>
		<script src="js/validation/additional-methods.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				
				$('#uploadPosterButtons').css("margin-top","-50px");
				$('#selected-image').hide();
				$('#poster').hide();
		        var button = Dropbox.createChooseButton({
		            success: function(files) {
		            	$('#uploadPosterButtons').css("margin-top","0px");
		            	$('#selected-image').attr("src",files[0].link).show();
		            	$('#poster').show();
		            },
		            linkType: 'direct',
		            extensions: ['.jpg', '.jpeg', '.png'],
		        });
				$("#suggestLocation").click(function(e) {
					e.preventDefault();
					var eventCategory = $('#eventCategory').val();
					window.open("suggestLocation.jsp?category="+ eventCategory + "", '','width=1000px,height=650px,resizable=no');
					window.focus();
				});
		        $('#container').append(button);
	            $("#submitBtn").click(function(e){
	            	$('#createEventStep1Form').validate();
	            });
		        $('#createEventStep1Form').validate({
		            rules: {
		                eventName: {
		                    required: true
		                },
		                eventDescription: {
		                    required: true,
		                },
		                eventLocation: {
		                    required: true
		                }
		            },
		            highlight: function(element) {
		                $(element).closest('.form-group').addClass('has-error');
		            },
		            unhighlight: function(element) {
		                $(element).closest('.form-group').removeClass('has-error');
		            },
		            errorElement: 'span',
		            errorClass: 'help-block',
		            errorPlacement: function(error, element) {
		                if(element.parent('.input-group').length) {
		                    error.insertAfter(element.parent());
		                } else {
		                    error.insertAfter(element);
		                }
		            }
		        });
		        $("#magicBtn").click(function(){
			        var eventName = "Caregiving Welfare Association Fund Raising Event";
				    var eventDescription = "We are welcoming participants for CWA Fund Raising Event, scheduled on 28 Aug 2014 (Saturday), from 8.30am to 4.30pm. All donations collected will go towards the well-being of our clients. ";
				    $("#eventName").val(eventName);
				    $("#eventDescription").val(eventDescription);
				    $('#eventCategory').val("Family");
				    $("#noOfParticipants").val(99999);
				    $('#help').attr('checked','checked');
			    });
			});
		</script>
	</jsp:attribute>
	<jsp:attribute name="content">
		<button type="button" id="magicBtn" class="btn btn-default"> Magic Button (for presentation purposes)</button>
		<form method="post" id="createEventStep1Form" action="createEventStep2.jsp">
			<div id="currentForm" class="form-horizontal" role="form">
				<div class="panel panel-primary">
				<div class="panel-heading">
					Create Event
					<br/>
					Step 1: Please fill in the event details
				</div>
					<div class="panel-body">
						<div class="form-group">
							<span class="floatLeftText"><label id="poster" class="col-xs-12 control-label">Poster</label></span>
							<br/>
							<br/>
							<input type="image" id="selected-image" name="image_url" width="400" height="200"/>
							<table id="uploadPosterButtons">
								<tr>
									<td><div id="container"></div></td>
								</tr>
								<!--<tr>
									<td style='height:50px;padding-left:20%;'>- Or -</td>
								</tr> -->
								<tr>
									<td><input type="file" name="excelPathName" id="uploadBtnExcel" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/></td>
								</tr>
							</table>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label" for="eventName"> Name</label></span>
							<input class="form-control" type="text" name="eventName" id="eventName" placeholder="Enter event name"/>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Category</label></span>
							<select class="form-control" id="eventCategory" name="eventCategory">
								<option> Arts </option>
								<option> Education</option>
								<option> Family </option>
								<option> Health </option>
							</select>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Description</label></span>
							<textarea class="form-control" id="eventDescription" name="eventDescription" rows="3" placeholder="Enter event description"></textarea>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Location</label></span>
							<br/>
							<div id="location">
								<textarea class="form-control floatLeftText" name="eventLocation" rows="3" placeholder="Enter event location"></textarea>
							</div>
							<br/>
							<br/>
							<button type="submit" class="btn btn-default help-block" id='suggestLocation' formaction="#"  data-bind="jqueryui: 'button'"> Suggest Location </button>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">No of participants</label></span>
							<select class="form-control" id="noOfParticipants" name="noOfParticipants">
								<option value="99"> 0 - 99 </option>
								<option value="499"> 100 - 499 </option>
								<option value="999"> 500 - 999 </option>
								<option value="9999"> 1000 - 9999 </option>
								<option value="99999"> 10000 - 99999 </option>
							</select>
						</div>
						<div class="form-group">
							<div class="checkbox">
							  <label>
							    <input type="checkbox" name="help" id="help"/>
								Need help from hobby groups for the event?
							  </label>
							</div>
							<br/>
						</div>
						<div class="form-group">
							<input type="hidden" name="locationName" id="locationName" />
							<input type="hidden" name="locationHyperLink" id="locationHyperLink" />
							<input type="hidden" name="lat" id="lat" />
							<input type="hidden" name="lng" id="lng" />
							<a href="retrieveAllEvents?web=true"><button type="button" id="cancelBtn" class="btn btn-default"> Cancel </button></a>
							<input type="submit" name="submit" id="submitBtn" value="Next" class="btn btn-default"/>
						</div>
					</div>
				</div>
			</div>
		</form>
	</jsp:attribute>
</t:master>