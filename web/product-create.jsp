<%@page import="models.*" %>
<%@page import="controllers.*" %>
<%@page import="dal.*" %>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>Create a new Product!</title>

<style>
    form {
        margin: 12px auto;
        width: 50%;
    }

    h1 {
        text-align: center;
    }

    label {
        display: inline-block;
        width: 20%;
        margin-bottom: 15px;
    }

    select {
        width: 20%;
        margin-bottom: 15px;
    }
</style>

<h1>Create a new product</h1>

<form action="product-create" method="post">
    <label>ProductName:</label> 
    <input type="text" name="txtName"><br/>
    <label>Category:</label> 
    <select name="txtCategory">
        <c:forEach items="${category}" var="c">
            <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
        </c:forEach>
    </select><br/> 
    <label>QuantityPerUnit:</label> 
    <input type="text" name="txtQuantity"><br/>
    <label>UnitPrice:</label> 
    <input type="text" name="txtUnitPrice"><br/>
    <label>UnitsInStock:</label> 
    <input type="text" name="txtUnitsInStock"><br/>
    <label>UnitsOnOrder:</label> 
    <input type="text" name="txtUnitsOnOrder"><br/>
    <label>ReorderLevel:</label> 
    <input type="text" name="txtReorderLevel"><br/>
    <label>Discontinued:</label> 
    Yes <input type="radio" name="txtDiscontinued" value="true" checked>
    No <input type="radio" name="txtDiscontinued" value="false"><br/>
    <input type="submit" name="submit" value="Create">
</form>



