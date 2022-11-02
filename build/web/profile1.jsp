<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<script>
    function show(o) {
    console.log(o)
    if (o.nextElementSibling.style.display === "flex") {
        o.nextElementSibling.style.display = "none";
    } else {
        o.nextElementSibling.style.display = "flex";
    }
}
</script>

<%
     Account acc = (Account)(request.getSession().getAttribute("AccSession"));
     String id = acc.getCustomerID();
     Customer cus = new CustomerDAO().getCustomerByID(id);
     ProductDAO p = new ProductDAO()  ;              
%> 
<div id="content">
    <div id="content-left">
        <h3 style="font-weight: normal;">Welcome <%=cus.getContactName()%></h3>
        <h3>Account Management</h3>
        <ul>
            <a href="<%=request.getContextPath()%>/account/profile"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="<%=request.getContextPath()%>/allorder"><li>All orders</li></a>
            <a href="OrderCancelByUser"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">LIST ORDERS</b></div>
        <div class="content-main">
            <div id="profile-content-order">
                <div>
                    <c:forEach items="${listOrder}" var="o">
                            <c:if test="${o.getRequiredDate() != null}">
                        <div class="profile-order-title">              
                            <div class="profile-order-title-left">                            
                                <div>Order creation date: ${o.getOrderDate()}</div>                          
                                <div>Order: <a style="text-decoration: none" href="#">#${o.getOrderID()}</a></div>
                            </div>
                            <div class="profile-order-title-right">
                                  
                                    <c:choose>
                                    <c:when test="${o.getShippedDate() != null}">
                                        <span style="color: green;">
                                            Completed
                                        </span>
                                    </c:when>
                                    
                                    <c:when test="${o.getShippedDate() == null}">
                                        <span style="color: blue ">
                                            Pending | <a href="CancelByAdmin?oid=${o.getOrderID()}">Cancel</a>                                    
                                        </span>
                                    </c:when>
                                </c:choose>
                          

                            </div>
                              
                        </div>
                        <c:forEach items="${listOrderDetail}" var="od">
                            <c:if test="${o.getOrderID() == od.getOrderID()}" >
                                <div class="profile-order-content">
                                    <div class="profile-order-content-col1">
                                        <a href="#"><img src="<%=request.getContextPath()%>/img/1.jpg" width="100%"/></a>
                                    </div>   
                                    <div class="profile-order-content-col2">${od.getProductName()}</div>
                                    <div class="profile-order-content-col3">Quantity : ${od.getQuantity()}</div>
                                    <div style="color:red" class="profile-order-content-col4">${od.getUnitPrice()}$</div>
                                </div>
                            </c:if>
                        </c:forEach>
                             </c:if>
                    </c:forEach>
               
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">footer</div>
</div>
</body>
</html>