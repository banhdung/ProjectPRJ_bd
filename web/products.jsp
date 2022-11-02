<%@include file="template/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .active{
        background: green;
    }

</style>

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
        <div  class="path">${nameCate}</b></div>
        <div class="content-main"> 
            <c:forEach items="${products}" var="p">
                <c:set var="c" value="${cateID}"/> 
                <c:if test="${p.getCategoryID() == c}">
                    <div class="product">
                        <a href=""><img src="img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>                        <div class="price">${p.getUnitPrice()}</div>
                       <c:if test="${p.getUnitsInStock() !=0}"><div><a href="buy?id=${p.getProductID()}">Buy now</a></div></c:if>
                        <c:if test="${p.getUnitsInStock() ==0}"><div style="color : red">Out Of Stocks</div></c:if>
                    </div>
                </c:if>
            </c:forEach>

            <c:if test="${product!=null}">
                <div class="path">PRODUCT HAS :  ${txtSearchUser}</div>


                <c:forEach items="${product}" var="p">
                    <c:set var="c" value="${cateID}"/> 
                    <div class="product">
                        <a href=""><img src="img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>                        <div class="price">${p.getUnitPrice()}</div>
                        <c:if test="${p.getUnitsInStock() !=0}"><div><a href="buy?id=${p.getProductID()}">Buy now</a></div></c:if>
                        <c:if test="${p.getUnitsInStock() ==0}"><div style="color : red">Out Of Stocks</div></c:if>
                    </div>                
                </c:forEach>


            </c:if>


        </div>
        <c:if test="${numberOfPage > 1}">
            <div id="paging">
                <div class="pagination">

                    <c:forEach var = "i" begin = "1" end = "${numberOfPage}">
                        <c:choose>
                            <c:when test="${i==page}">
                                <button class="active">
                                    <div class="pageNumber" ><a  href="searchuser?txtSearchUser=${txtSearchUser}&page=${i}">${i}</a></div>
                                </button>
                            </c:when>    
                            <c:otherwise>
                                <button>
                                    <div class="pageNumber""><a  href="searchuser?txtSearchUser=${txtSearchUser}&page=${i}">${i}</a></div>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </div>
</div>
<%@include file="template/footer.jsp" %>
