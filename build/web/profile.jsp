<%@include file="template/header.jsp" %>

<%
     Account acc = (Account)(request.getSession().getAttribute("AccSession"));
     String id = acc.getCustomerID();
     Customer cus = new CustomerDAO().getCustomerByID(id);
                    
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
            <a href="<%=request.getContextPath()%>/OrderCancelByUser"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">Personal information</b></div>
        <div class="content-main">
            <div id="profile-content">
                <div class="profile-content-col">
                    <div>Company name: <br/><%=cus.getCompanyName()%></div>
                    <div>Contact name: <br/><%=cus.getContactName()%></div>               
                    <div>
                        <c:url value="profile-edit" var="edit">                        
                        </c:url>
                        <a href="<%=request.getContextPath()%>/${edit}">Edit</a>
                    </div>
                </div>
                <div class="profile-content-col">
                    <div>Company title: <br/><%=cus.getContactTitle()%></div>
                    <div>Address: <br/><%=cus.getAddress()%></div>
                </div>
                <div class="profile-content-col">
                    <div>Email: <br/><%=acc.getEmail()%></div>
                </div>
                <div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>