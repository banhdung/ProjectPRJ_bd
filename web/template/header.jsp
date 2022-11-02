<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nike Global</title>
        <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/img/logo.png"> 
        <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet"/>
    </head>    
    

    <body lang="vi">
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%=request.getContextPath()%>/home"><img src="<%=request.getContextPath()%>/img/logo.png"/></a>
                </div>
               
                  
                <c:if test="${category!=null}">
                    <div id="product-title-2" style="width: 100%;display: inline-block;padding-left: 30px;padding-top: 75px">
                        <form action="searchuser" method="get">
                            <input type="text" name="txtSearchUser"  placeholder="Enter product name to search"/>
                            <input type="submit" value="Search">
                        </form>
                    </div>   
                </c:if>
                
                <div id="banner">
                    <ul>
                        <li><a  style="text-decoration: none" href="<%=request.getContextPath()%>/cart.jsp">Cart: ${size}</a></li>
                            <%
                                if(session.getAttribute("AccSession")==null){
                            %>
                        <li><a style="text-decoration: none" href="<%=request.getContextPath()%>/account/signin">SignIn</a></li>
                        <li><a style="text-decoration: none" href="<%=request.getContextPath()%>/account/signup">SignUp</a></li>
                            <% }else{
                           
                            %>
                           <li><a style="text-decoration: none" href="<%=request.getContextPath()%>/account/profile">Profile: <%=session.getAttribute("uname")%></a></li>
                           <li><a style="text-decoration: none" href="<%=request.getContextPath()%>/account/signin?siginout=true">SignOut</a></li>
                            <%
                                 }
                            %>
                    </ul>
                </div>
            </div>