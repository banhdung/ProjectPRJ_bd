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
            <a href="#"><li>All orders</li></a>
            <a href="#"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">Personal information</b></div>
        <div class="content-main">
            <form style="width: 98%" action="profile-edit" method="post">
                <div style="width: 100%" id="profile-content">
                    <div style="width: 33.3%" class="profile-content-col">
                        <div>Company name:<br> <input name="txtCompanyName" value="<%=cus.getCompanyName()%>" /></div>
                        <div>Contact name:<br> <input name="txtContactName" value="<%=cus.getContactName()%>" /></div>
                        <div>
                            <input style="width: 50px" type="submit" value="Save"/>
                        </div>
                    </div>
                    <div style="width: 33.3%" class="profile-content-col">
                        <div>Contact Title:<br> <input name="txtContactTitle" value="<%=cus.getContactTitle()%>" /></div>
                        <div>Address :<br> <input name="txtAddress" value="<%=cus.getAddress()%>" /></div>
                    </div>
                    <div style="width: 33.3%" class="profile-content-col">
                        <div>Email:<br> <input name="txtEmail" value="<%=acc.getEmail()%>" disabled="" /></div>
                    </div>
                        <input type="hidden" name="CusID" value="<%=cus.getCustomerID()%>">


                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>