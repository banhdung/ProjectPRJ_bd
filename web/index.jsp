<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp" %>

<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>
        <ul>
            <c:forEach items="${category}" var="c">
                <a href="products?cateID=${c.getCategoryID()}"><li>${c.getCategoryName()}</li></a>
                    </c:forEach>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">Hot</b></div>
        <div class="content-main">

            <c:forEach items="${productsHot}" var = "p">
                <div class="product">
                    <a href=""><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                    <div class="price">${p.getUnitPrice()}</div>
                    <c:if test="${p.getUnitsInStock() !=0}"><div><a href="buy?id=${p.getProductID()}">Buy now</a></div></c:if>
                     <c:if test="${p.getUnitsInStock() ==0}"><div style="color : red">Out Of Stocks</div></c:if>
                </div>
            </c:forEach>
            


        </div>
        <div class="path">Best Sale</b></div>
        <div class="content-main">
            <c:forEach items="${productsBest}" var = "p">
                <div class="product">
                    <a href=""><img src="img/1.jpg" width="100%"/></a>
                            <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                    <div class="price">${p.getUnitPrice()}</div>
                    <c:if test="${p.getUnitsInStock() !=0}"><div><a href="buy?id=${p.getProductID()}">Buy now</a></div></c:if>
                     <c:if test="${p.getUnitsInStock() ==0}"><div style="color : red">Out Of Stocks</div></c:if>
                </div>
            </c:forEach>
        </div>

        <div class="path">New Product</b></div>
        <div class="content-main">
            <c:forEach items="${productsNew}" var = "p">
                <div class="product">
                    <a href=""><img src="img/1.jpg" width="100%"/></a>
                           <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                    <div class="price">${p.getUnitPrice()}</div>
                  <c:if test="${p.getUnitsInStock() !=0}"><div><a href="buy?id=${p.getProductID()}">Buy now</a></div></c:if>
                   <c:if test="${p.getUnitsInStock() ==0}"><div style="color : red">Out Of Stocks</div></c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
