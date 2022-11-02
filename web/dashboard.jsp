<%@include file="template/headerAdmin.jsp" %>
<%
    ArrayList<Order_Detail> od = new OrderDAO().getAllOrder_Details();
    double sum = 0 ;
    for(Order_Detail o: od) {
        sum += (o.getUnitPrice() * o.getQuantity() * (1-o.getDiscount()));
    }
    DecimalFormat df = new DecimalFormat("$ #0.00"); 
    
    int countCus = new CustomerDAO().getCountCustomer();
    int countGuest = countCus - new AccountDAO().getCountAccount() ;   
    int countNewCusMonth = new CustomerDAO().getCountNewCusInOneMonth();
    request.setAttribute("odDAO",new OrderDAO());
%>
<div id="content-right">
    <div class="path-admin">DASHBOARD</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="dashboard-1">
                <div id="dashboard-1-container">
                    <div class="dashboard-item">
                        <div class="dashboard-item-title">Weekly Sales</div>
                        <div class="dashboard-item-content">$47K</div>
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
            <div id="dashboard-2">
                <div id="chart" style="text-align: center;">
                    <div id="chart1">
                        <h3>Statistic Orders (Month)</h3>
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
                        data: [${odDAO.countOrderByMonth(1)},${odDAO.countOrderByMonth(2)},${odDAO.countOrderByMonth(3)}
                            ,${odDAO.countOrderByMonth(4)},${odDAO.countOrderByMonth(5)},
    ${odDAO.countOrderByMonth(6)},${odDAO.countOrderByMonth(7)},${odDAO.countOrderByMonth(8)},
    ${odDAO.countOrderByMonth(9)},${odDAO.countOrderByMonth(10)},
    ${odDAO.countOrderByMonth(11)},${odDAO.countOrderByMonth(12)}],
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
        var yValues = [<%=countCus%>,40];
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
