<%@include file="template/header.jsp" %>
<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="<%=request.getContextPath()%>/account/signup" style="color: red;">SIGN UP</a></span>
            <span><a href="<%=request.getContextPath()%>/account/signin" >SIGN IN</a></span>
        </div>
        <div id="form-content">
            <form action="" method="post">
                <label>Company name<span style="color: red;">*</span></label><br/>
                <input name="companyName" id="companyName" type="text"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgCompanyName")!=null){
                            out.print(request.getAttribute("msgCompanyName"));
                        }
                    %>
                </div>
                <!--<span class="msg-error">Company name is required</span><br/>-->
                <label>Contact name<span style="color: red;">*</span></label><br/>
                <input name="contactName" id="contactName" type="text"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgContactName")!=null){
                            out.print(request.getAttribute("msgContactName"));
                        }
                    %>
                </div>
                <!--<span class="msg-error">Contact name is required</span><br/>-->
                <label>Contact title<span style="color: red;">*</span></label><br/>
                <input name="contactTitle" id="contactTitle" type="text"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgContactTitle")!=null){
                            out.print(request.getAttribute("msgContactTitle"));
                        }
                    %>
                </div>
                <!--<span class="msg-error">Contact title required</span><br/>-->
                <label>Address<span style="color: red;">*</span></label><br/>
                <input name="address" id="address" type="text"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgAddress")!=null){
                            out.print(request.getAttribute("msgAddress"));
                        }
                    %>
                </div>
                <!--<span class="msg-error">Address is required</span><br/>-->
                <label>Email<span style="color: red;">*</span></label><br/>
                <input name="txtEmail" id="email" type="text"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgEmail")!=null){
                            out.print(request.getAttribute("msgEmail"));
                        }
                    %>
                </div>
                <!--<span class="msg-error">Email is required</span><br/>-->
                <label>Password<span style="color: red;">*</span></label><br/>
                <input name="txtPass" id="pass" type="password"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgPass")!=null){
                            out.print(request.getAttribute("msgPass"));
                        }
                    %>
                </div>
                <!--<span class="msg-error">Password is required</span><br/>-->
                <label>Re-Password<span style="color: red;">*</span></label><br/>
                <input name="txtRepass" type="password"/><br/>
                <div style="color :red">
                    <%
                        if(request.getAttribute("msgRepass")!=null){
                            out.print(request.getAttribute("msgRepass"));
                        }
                    %>
                </div>
                <!--<span class="msg-error" id="Repass">Re-Password is required</span><br/>-->
                <div></div>
                <input type="submit" value="SIGN UP" style="margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>