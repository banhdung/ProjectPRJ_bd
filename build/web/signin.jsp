<%@include file="template/header.jsp" %>
<div id="content">
    <div style="color :red">
        <%
            if(request.getAttribute("msg")!=null){
                out.print(request.getAttribute("msg"));
            }
        %>
    </div>

    <div id="form">
        <div id="form-title">
            <span><a href="<%=request.getContextPath()%>/account/signup">SIGN UP</a></span>
            <span><a href="<%=request.getContextPath()%>/account/signin" style="color: red;">SIGN IN</a></span>
        </div>
        <div id="form-content">
            <form action="signin" method="post">
                <label>Email<span style="color: red;">*</span></label><br/>
                <input  type="text" id="email" name="txtEmail"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgEmail")!=null){
                            out.print(request.getAttribute("msgEmail"));
                        }
                    %>
                </div>
                <!--                            <span class="msg-error"></span><br/>-->
                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" id="pass" name="txtPass"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgPass")!=null){
                            out.print(request.getAttribute("msgPass"));
                        }
                    %>
                </div>
                <!--                            <span class="msg-error"></span><br/>-->
                <div><a href="forgot.html">Forgot password?</a></div>
                <input onclick="checkInput()" type="submit" value="SIGN IN"/><br/>
                <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
                <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>