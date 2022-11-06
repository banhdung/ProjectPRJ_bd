<%@include file="template/headerAdmin.jsp" %>
                <div id="content-right">
                    <div class="path-admin">PRODUCTS LIST</b></div>
                    <div class="content-main">
                        <div id="content-main-dashboard">
                            <div id="product-title-header">
                                <div id="product-title-1" style="width: 25%;">
                                    <b>Filter by Catetory:</b>
                                    <form action="filter" method="post">
                                        <input type="hidden" name="txtSearch" value="${txtSearch}"/>
                                        <select name="cateID">
                                            <c:forEach items="${category}" var="c">
                                                <option value="${c.getCategoryID()}" ${id eq c.getCategoryID()?"selected":"" }>${c.getCategoryName()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="submit" value="Filter">
                                    </form>
                                </div>
                                <div id="product-title-2" style="width: 55%;">
                                    <form action="product-search" method="get">
                                        <input type="text" name="txtSearch" placeholder="Enter product name to search" value="${txtSearch}"/>
                                        <input type="submit" value="Search">
                                    </form>
                                </div>
                                <div id="product-title-3" style="width: 20%;">
                                    <a href="product-create">Create a new Product</a>
                                    <form action="">
                                        <label for="upload-file">Import .xls or .xlsx file</label>
                                        <input type="file" name="file" id="upload-file" />
                                    </form>
                                </div>
                            </div>
                            <div id="order-table-admin">
                                <table id="orders">
                                    <tr>
                                        <th>ProductID</th>
                                        <th>ProductName</th>
                                        <th>UnitPrice</th>
                                        <th>Unit</th>
                                        <th>UnitsInStock</th>
                                        <th>Category</th>
                                        <th>Discontinued</th>
                                        <th></th>                              
                                    </tr>
                                    <c:forEach items="${product}" var="p">
                                        <tr>
                                            <td>${p.getProductID()}</td>
                                            <td>${p.getProductName()}</td>                                              
                                            <td>${p.getUnitPrice()}</td>
                                            <td>${p.getUnitsOnOrder()}</td>
                                            <td>${p.getUnitsInStock()}</td>
                                            <c:forEach var="c" items="${category}">
                                                <c:if test="${c.getCategoryID() == p.getCategoryID()}">
                                                    <td>${c.getCategoryName()} </td>
                                                </c:if>
                                            </c:forEach>
                                            <td>${p.isDiscontinued()}</td>

                                            <td>
                                                <c:url value="product-edit" var="edit">
                                                    <c:param name="id" value="${p.getProductID()}"/>
                                                </c:url>
                                                <a href="${edit}">Edit</a>

                                                <c:url value="product-delete" var="delete">
                                                    <c:param name="id" value="${p.getProductID()}"/>
                                                </c:url>
                                                <button onclick="showMess(${p.getProductID()})">Delete</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div id="paging">
                                <div class="pagination">
                                    <c:if test="${numberOfPage > 1}">
                                        <c:forEach var = "i" begin = "1" end = "${numberOfPage}">
                                            <c:choose>
                                                <c:when test="${i==page}">
                                                    <button class="active">
                                                        <c:if test="${txtSearch eq null}"><div class="pageNumber" ><a  href="product-list?page=${i}">${i}</a></div></c:if>
                                                        <c:if test="${txtSearch ne null}"><div class="pageNumber" ><a  href="product-search?txtSearch=${txtSearch}&page=${i}">${i}</a></div></c:if>
                                                    </button>
                                                </c:when>    
                                                <c:otherwise>
                                                    <button>
                                                     <c:if test="${txtSearch eq null}"><div class="pageNumber" ><a  href="product-list?page=${i}">${i}</a></div></c:if>
                                                        <c:if test="${txtSearch ne null}"><div class="pageNumber" ><a  href="product-search?txtSearch=${txtSearch}&page=${i}">${i}</a></div></c:if>
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer-admin">footer</div>
    </div>
</body>
</html>


<style>
        .active{
            background: green;
        }

    </style>
    <script>
        function showMess(id) {
            let text;
            let option = confirm('Do you want to delete product  : ' + id + ' ? ');
            if (option == true) {
                text = "Delete product complete";
                window.location.href = 'product-delete?id=' + id;
            } else {
                text = "Cancel delete product";
            }
            console.log(text);

        }
    </script>