<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/9
  Time: 下午8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/layui/css/layui.css">

</head>
<body>

<div class="layui-main">
    <header class="layui-elip">个人收支管理系统</header>
    <form class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="account" required lay-verify="required" placeholder="手机号" autocomplete="off" class="layui-input"/>
        </div>
    </form>
</div>


<script src="../layui/layui/layui.js"></script>
</body>
</html>
