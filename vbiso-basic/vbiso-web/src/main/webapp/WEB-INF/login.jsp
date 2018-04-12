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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>个人收支管理系统</title>
    <link rel="stylesheet" href="/layui/layui/css/layui.css">
    <link rel="stylesheet" href="/style/divForm.css"/>

</head>
<body>

<div id="divForm" class="layui-main">
    <h2>
        <header class="layui-elip">登陆</header>
    </h2>
    <form class="layui-form layui-form-pane" action="" id="loginForm">
        <div id="mobile" class="layui-input-inline">
            <input type="text" name="account" required lay-verify="required" placeholder="手机号" autocomplete="off"
                   class="layui-input"/>
        </div>
        <br/>
        <div class="layui-input-inline">
            <input type="password" name="account" required lay-verify="required" placeholder="密码" autocomplete="off"
                   class="layui-input"/>
        </div>
        <input type="submit" class="layui-btn layui-btn-normal" id="formSub" />
    </form>
</div>


<script src="/layui/layui/layui.js"></script>
<script>
    layui.use(['form', 'layer'], function () {
        var form = layui.form, $ = layui.jquery;
        var layer = layui.layer;
        form.on('submit(formSub)', function (data) {
           layer.open({
              title:test,
               content:JSON.stringify(data.field)
           });
            return true;
        });
    });
</script>
</body>
</html>
