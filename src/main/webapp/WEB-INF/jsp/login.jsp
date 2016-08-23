<%--
  Created by IntelliJ IDEA.
  User: chenyj
  Date: 16/8/23
  Time: 上午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登陆</title>
</head>
<body>
<form action="j_spring_security_check" method="post"  id="login-form" >
        <ul>
            <li><input name="j_username" type="text" class="loginuser" placeholder="用户名" onclick="JavaScript:this.value=''"/></li>
            <li><input name="j_password" type="password" class="loginpwd" placeholder="密码" onclick="JavaScript:this.value=''"/></li>
            <li><input name="" type="submit" class="loginbtn" value="登录"   />
                <%--<label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>--%>
                <label><a href="#">忘记密码？</a></label></li>
        </ul>
</form>
</body>
</html>
