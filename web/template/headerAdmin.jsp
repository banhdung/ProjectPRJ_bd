<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.role != 1}">
           <c:redirect url="errorPage.jsp"></c:redirect>
        </c:if>
        <div id="container">
            <div style="height:100px" id="header">
                <div style="padding-top: 20px" id="logo-admin">
                    Ecommerce Admin
                </div>
                <div id="banner-admin">
                    <ul>
                        <li><a style="text-decoration: none " href="<%=request.getContextPath()%>/account/signin?siginout=true">SignOut</a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="content-left">
                    <ul>
                        <a href="dashboard.jsp"><li>Dashboard</li></a>
                        <a href="OrderManager"><li>Orders</li></a>
                        <a href="product-list"><li>Products</li></a>
                        <a href="#"><li>Customers</li></a>
                    </ul>
                </div>
     