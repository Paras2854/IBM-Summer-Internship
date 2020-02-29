<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="dss.dto.Options"%>
<%@page import="dss.service.locator.ServiceLocator"%>
<%@page import="dss.service.DssService"%>
<%@page import="dss.dto.Question"%>
<%@page import="java.util.List"%>
<%@page import="dss.dao.QuestionDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Questions</title>
<link rel="stylesheet" href="styles/style.css">
</head>

<body>
<div id="header">
Engineering Decision Support System
</div>
<div class="questionForm">
<form action="result" method="post">
<%
DssService dssService = ServiceLocator.getDssService();
List<Question> questionList = dssService.loadAllQuestions();

for(int i=0;i < questionList.size();i++){
	
	Question question = questionList.get(i);
	List<Options>listOptions = question.getOptionsList();
	Collections.sort(listOptions,new Comparator<Options>(){
		
		public int compare(Options o1,Options o2){
			return o1.getOptionId().compareTo(o2.getOptionId());
		}
	});
	Options option1 = listOptions.get(0);
	Options option2 = listOptions.get(1);
	Options option3 = listOptions.get(2);
%>
<div class="question"> 
<%= i+1 %>. <%=question.getQuestionName() %>  
<div class="option">
<div><input type="radio" required name="<%=question.getQuestionId()%>" value="<%=option1.getOptionId()+"_"+question.getQuestionId()+"_"+question.getCategoryId() %>"><%=option1.getOptionName() %></div>
<div><input type="radio" name="<%=question.getQuestionId()%>" value="<%=option2.getOptionId()+"_"+question.getQuestionId()+"_"+question.getCategoryId() %>"><%=option2.getOptionName() %></div>
<div><input type="radio" name="<%=question.getQuestionId()%>" value="<%=option3.getOptionId()+"_"+question.getQuestionId()+"_"+question.getCategoryId() %>"><%=option3.getOptionName() %></div>
</div>
</div>
<% } %>
<div class="btnWrapper">
<input class="btn" type="submit" value="Submit">
</div>
</form>
</div>
</body>
</html>
