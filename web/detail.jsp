<%@include file="template/header.jsp" %>
<script type="text/javascript">
    function buynow(id) {
        if (confirm("Cofirm buy this product ?")) {
            window.location = "<%=request.getContextPath()%>/buy?id=" + id;
        }
    }
</script>
<div id="content">
    <div id="content-detail">
        <div id="content-title">
            <a href="<%=request.getContextPath()%>/home">Home</a> >
            <a href="<%=request.getContextPath()%>/products?cateID=${ca.getCategoryID()}">${ca.getCategoryName()}</a> >
            Model: SP${p.getProductID()}
        </div>
        <div id="product">
            <div id="product-name">
                <h2>${p.getProductName()}</h2>
                <div id="product-detail">
                    <div id="product-detail-left">
                        <div id="product-img">
                            <img src="<%=request.getContextPath()%>/img/1.jpg"/>
                        </div>
                        <div id="product-img-items">
                            <div><a href="#"><img src="<%=request.getContextPath()%>/img/1.jpg"/></a></div>
                            <div><a href="#"><img src="<%=request.getContextPath()%>/img/1.jpg"/></a></div>
                            <div><a href="#"><img src="<%=request.getContextPath()%>/img/1.jpg"/></a></div>
                            <div><a href="#"><img src="<%=request.getContextPath()%>/img/1.jpg"/></a></div>
                        </div>
                    </div>
                    <c:set var="stockstatus" value = "${p.getUnitsInStock()}"/>
                    <div id="product-detail-right">
                        <div id="product-detail-right-content">
                            <div id="product-price">${p.getUnitPrice()}$</div>
                            <c:if test="${stockstatus>0}"><div id="product-status">In stock</div></c:if>      
                            <c:if test="${stockstatus==0}"><div style="color : purple;" id="product-status">Out of stock</div></c:if>
                                <c:if test="${p.getUnitsInStock() !=0}">
                                <div id="product-detail-buttons">
                                    <div style="display: flex ; margin-left: 20px ; " id="product-detail-button">
                                        <form action="<%=request.getContextPath()%>/buy?id=${p.getProductID()}" method="post">
                                        <input type="submit" value="ADD TO CART"  style="background-color: orange; color:white;border: 1px solid gray;">
                                    </form>
                                    <input style="margin-left: 20px ; background-color: red; color:white" type="submit" value="BUY NOW" onclick="buynow('${p.getProductID()}')">
                                </div>
                            </div>
                                </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="info-detail">
            <div id="info-detail-title">
                <h2>Information deltail</h2>
                <div style="margin:10px auto;">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Illum, debitis. Asperiores soluta eveniet eos accusantium doloremque cum suscipit ducimus enim at sapiente mollitia consequuntur dicta quaerat, sunt voluptates autem. Quam!
                    Lorem ipsum dolor, sit amet consectetur adipisicing elit. Rem illum autem veritatis maxime corporis quod quibusdam nostrum eaque laborum numquam quos unde eveniet aut, exercitationem voluptatum veniam fugiat, debitis esse?
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio eligendi ratione vitae nobis numquam dolorum assumenda saepe enim cumque blanditiis, deleniti neque voluptate vel ducimus in omnis harum aut nisi.
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>