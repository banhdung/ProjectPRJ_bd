<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Edit</title>

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

    </head>
    <body>
        <h1>Edit product by ID</h1>

        <c:set value="${product}" var="p"></c:set>

            <form action="product-edit" method="post">
                <label>ProductID:</label> 
                <input type="text" name="ProductID" value="${p.getProductID()}" readonly><br/>
            <label>ProductName:</label> 
            <input type="text" name="ProductName" value="${p.getProductName()}" placeholder="Enter new name" required><br/>
            <label>CategoryID:</label> 
            <select name="ddlCategoryID">
                <c:forEach items="${category}" var="c">
                    <c:if test="${c.getCategoryID()==p.getCategoryID()}">
                        <option value="${c.getCategoryID()}" selected><c:out value = "${c.getCategoryName()}"/></option>
                    </c:if>
                    <c:if test="${c.getCategoryID()!=p.getCategoryID()}">
                        <option value="${c.getCategoryID()}"><c:out value = "${c.getCategoryName()}"/></option>
                    </c:if>
                </c:forEach>
            </select><br/> 
            <label>QuantityPerUnit:</label> 
            <input type="text" name="QuantityPerUnit"value="${p.getQuantityPerUnit()}"><br/>
            <label>UnitPrice:</label> 
            <input type="text" name="UnitPrice"value="${p.getUnitPrice()}"><br/>
            <label>UnitsInStock:</label> 
            <input type="text" name="UnitsInStock"value="${p.getUnitsInStock()}"><br/>
            <label>UnitsOnOrder:</label> 
            <input type="text" name="UnitsOnOrder"value="${p.getUnitsOnOrder()}"><br/>
            <label>ReorderLevel:</label> 
            <input type="text" name="ReorderLevel"value="${p.getReorderLevel()}"><br/>
            <label>Discontinued:</label> 
            Yes <input type="radio" name="Discontinued" value="true" checked required>
            No <input type="radio" name="Discontinued" value="false" required><br/>
            <label></label>
            <input type="submit" name="submit" value="Update">
        </form>
    </body>
</html>
