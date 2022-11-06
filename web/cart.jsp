<%@include file="template/header.jsp" %>
<%@page import="java.time.LocalDateTime" %>
<%@page import="java.time.format.DateTimeFormatter" %>

<!--<script>
    function changeQuantityOnBlur(el) {
        let inputs = Array.from(document.getElementsByName("Quantity"));
        let num;
        inputs.forEach(input => {
            if (input.id == el.id) {
                num = input.value;
                return;
            }
        });
        let id = el.id;
        window.location = "addToCart?idC=" + id + "&num=" + num;
    }
</script>-->

<%
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();
    String date = dtf.format(now);
%>
<div id="content">
    <div id="cart">
        <div id="cart-title">
            <h3>SHOPPING CART</h3>
        </div>
        <div id="cart-content">
            <div class="cart-item">
                <div style="color:green ; margin-left: 100px" >${messOrderProduct}</div>
                <c:if test="${sessionScope.AccSession == null}">
                    <c:set value="${sessionScope.cart}" var="c"/>
                    <c:forEach var="p" items="${c.items}">
                        <div id="cart-content">
                            <div class="cart-item">
                                <div class="cart-item-infor">
                                    <div class="cart-item-img">
                                        <img src="img/1.jpg"/>
                                    </div>
                                    <div class="cart-item-name">
                                        <a href="product/detail?ID=${p.getProduct().getProductID()}">${p.getProduct().getProductName()}</a>
                                    </div>
                                    <div class="cart-item-price">
                                        ${p.getProduct().getUnitPrice() * p.quantity}$
                                    </div>
                                    <div class="cart-item-button">
                                        <a href="remove?id=${p.getProduct().getProductID()}">Remove</a>
                                    </div>
                                </div>
                                <div class="cart-item-function">
                                    <a href="amount?num=-1&id=${p.getProduct().getProductID()}">-</a>  
                                    <a href="amount?num=1&id=${p.getProduct().getProductID()}">+</a>
                                    <input id="${p.getProduct().getProductID()}" onblur="changeQuantityOnBlur(this)" type="nu<mber" name="Quantity" value="${p.quantity}"/>

                                </div>
                            </div>

                        </div>
                    </c:forEach>
                </c:if>

                <c:if test="${sessionScope.AccSession != null}">
                    <c:set value="${sessionScope.cart}" var="c"/>
                    <c:forEach var="p" items="${c.items}">
                        <div id="cart-content">
                            <div class="cart-item">

                                <div class="cart-item-infor">
                                    <div class="cart-item-img">
                                        <img src="img/1.jpg"/>
                                    </div>
                                    <div class="cart-item-name">
                                        <a href="detail?id=1">${p.getProduct().getProductName()}</a>
                                    </div>
                                    <div class="cart-item-price">
                                        ${p.getProduct().getUnitPrice() * p.quantity}$
                                    </div>
                                    <div class="cart-item-button">
                                        <!--<form action="amount" method="post">-->
                                        <a href="remove?id=${p.getProduct().getProductID()}">Remove</a>

                                        <!--</form>-->
                                    </div>
                                </div>
                                <div class="cart-item-function">
                                    <a href="amount?num=-1&id=${p.getProduct().getProductID()}">-</a>  
                                    <a href="amount?num=1&id=${p.getProduct().getProductID()}">+</a>
                                    <input id="${p.getProduct().getProductID()}" onblur="changeQuantityOnBlur(this)" type="nu<mber" name="Quantity" value="${p.quantity}"/>
                                </div>
                            </div>

                        </div>
                    </c:forEach>
                </c:if>

                <!--                <div id="cart-summary">
                                    
                                    <div id="cart-summary-content">Total amount: <span style="color:red">${t} $</span></div>
                                </div>-->
                <div id="cart-summary">
                    <div id="cart-summary-content">
                        <c:choose>
                            <c:when test="${t != 0 and sessionScope.size != 0}">
                                Total amount: 
                                <span style="color:red">
                                    <c:out value="${t}"/> $
                                </span>
                            </c:when>    
                            <c:otherwise>
                                <img  src="img/2eacfa305d7715bdcd86bb4956209038--android.jpg" alt="" class="img-fluid"/>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <form method="post" action="order-product">
                    <div id="customer-info">
                        <div id="customer-info-content">
                            <h3>CUSTOMER INFORMATION:</h3>
                            <div id="customer-info-detail">

                                <c:if test="${sessionScope.AccSession != null}">
                                    <%
                                Account acc = (Account)(request.getSession().getAttribute("AccSession"));
                                String id = acc.getCustomerID();
                                Customer cus = new CustomerDAO().getCustomerByID(id);                  
                                    %> 
                                    <div id="customer-info-left">
                                        <input readonly="" type="text" placeholder="Company name *" value="CompanyName : <%=cus.getCompanyName()%>"/></input><br/>
                                        <input readonly="" type="text" placeholder="Contact name *" value="ContactName :  <%=cus.getContactName()%>"/><br/>
                                        Required Date<br/>
                                        <input type="date" name="txtRequiredDate" value="<%=date%>" min="<%=date%>" max="2099-12-30" required />

                                    </div>
                                    <div id="customer-info-right">
                                        <input readonly="" type="text" placeholder="Contact title *" value="ContactTitle : <%=cus.getContactTitle()%>" /><br/>
                                        <input readonly="" type="text" placeholder="Address *" value="Address : <%=cus.getAddress()%>" /><br/>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.AccSession == null}">
                                    <div id="customer-info-left">
                                        <input required="" type="text" placeholder="Company name *" name="txtCompanyName" /></input><br/>
                                        <input required="" type="text" placeholder="Contact name *" name="txtContactName"/><br/>
                                        Required Date<br/>
                                        <input type="date" name="txtRequiredDate" value="<%=date%>" min="<%=date%>" max="2099-12-30" required />

                                    </div>
                                    <div id="customer-info-right">
                                        <input  required="" type="text" placeholder="Contact title *" name="txtContactTitle" /><br/>
                                        <input  required="" type="text" placeholder="Address *" name="txtAddress" a/><br/>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div id="customer-info">
                        <div id="customer-info-content">
                            <h3>PAYMENT METHODS:</h3>
                            <div id="customer-info-payment">
                                <div>
                                    <input type="radio" name="rbPaymentMethod" checked/>
                                    Payment C.O.D - Payment on delivery
                                </div>
                                <div>
                                    <input type="radio" name="rbPaymentMethod" disabled/>
                                    Payment via online payment gateway
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="cart-order">
                        <input style="background-color: red ; color:white ;width: 100px"   type="submit" value="ORDER"/>
                    </div>
                </form>
            </div>
        </div>
        <%@include file="template/footer.jsp" %>