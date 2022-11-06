<%@include file="template/headerAdmin.jsp" %>
<%
    ArrayList<Order_Detail> od = new OrderDAO().getAllOrder_Details();
    double sum = 0 , sum1=0 ;
    for(Order_Detail o: od) {
        sum += (o.getUnitPrice() * o.getQuantity() * (1-o.getDiscount()));
    }
    DecimalFormat df = new DecimalFormat("$ #0.00"); 
      
    ArrayList<Order_Detail> odt = new OrderDAO().getWeeklySaleByOrdDet();
       for(Order_Detail o: odt) {
        sum1 += (o.getUnitPrice() * o.getQuantity() *(1-o.getDiscount()));
    }
    
    int countCus = new CustomerDAO().getCountCustomer();
    int countGuest = countCus - new AccountDAO().getCountAccount() ;   
    int countNewCusMonth = new CustomerDAO().getCountNewCusInOneMonth();
    request.setAttribute("odDAO",new OrderDAO());
%>
<c:set value="${year}" var="year"></c:set>
    <div id="content-right">
        <div class="path-admin">DASHBOARD</b></div>
        <div class="content-main">
            <div id="content-main-dashboard">
                <div id="dashboard-1">
                    <div id="dashboard-1-container">
                        <div class="dashboard-item">
                            <div class="dashboard-item-title">Weekly Sales</div>
                            <div class="dashboard-item-content"><%=df.format(sum1)%></div>
                        </div>
                        <div class="dashboard-item">
                            <div class="dashboard-item-title">Total Orders</div>
                            <div class="dashboard-item-content"><%=df.format(sum)%></div>
                    </div>
                    <div class="dashboard-item">
                        <div class="dashboard-item-title">Total Customers</div>
                        <div class="dashboard-item-content"><%=countCus%></div>
                    </div>
                    <div class="dashboard-item">
                        <div class="dashboard-item-title">Total Guest</div>
                        <div class="dashboard-item-content"><%=countGuest%></div>
                    </div>
                </div>
            </div>
            <form action="OrderYearDash" method="post">
                Search by year <input  type="number" name="orderYear" >
                <input type="submit">
            </form>
            <div id="dashboard-2">
                <div id="chart" style="text-align: center;">
                    <div id="chart1">
                        <h3>Statistic Orders (Month) in ${year}</h3>
                        <canvas id="myChart1" style="width: 100%;"></canvas>
                    </div>
                    <div id="chart2">
                        <canvas id="myChart2" style="width: 80%;"></canvas>
                    </div>
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
<script>
    function OrdersChart() {
        var xValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

        new Chart("myChart1", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                        data: [${odDAO.countOrderByMonth(1,year)},${odDAO.countOrderByMonth(2,year)},${odDAO.countOrderByMonth(3,year)}
                            ,${odDAO.countOrderByMonth(4,year)},${odDAO.countOrderByMonth(5,year)},
    ${odDAO.countOrderByMonth(6,year)},${odDAO.countOrderByMonth(7,year)},${odDAO.countOrderByMonth(8,year)},
    ${odDAO.countOrderByMonth(9,year)},${odDAO.countOrderByMonth(10,year)},
    ${odDAO.countOrderByMonth(11,year)},${odDAO.countOrderByMonth(12,year)}],
                        borderColor: "sienna",
                        fill: true
                    }]
            },
            options: {
                legend: {display: false}
            }
        });
    }

    function CustomersChart() {
        var xValues = ["Total", "New customer"];
        var yValues = [<%=countCus%>,1];
        var barColors = ["green", "red"];

        new Chart("myChart2", {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                        backgroundColor: barColors,
                        data: yValues
                    }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "New Customers (30 daily Avg)"
                }
            }
        });
    }

    OrdersChart();
    CustomersChart();
</script>
