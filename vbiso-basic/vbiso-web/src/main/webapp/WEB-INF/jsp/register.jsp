<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/16
  Time: 上午9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/login.css">
</head>
<body>
<div class="layui-box main">
    <form class="layui-form layui-form-pane" method="post">
        <div class="layui-form-item">
            <h3 style="font-family: 'Apple Braille';color: #dddddd; font-size: 22px">个人收支管理系统</h3>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号：</label>
            <div class="layui-input-inline">
                <input type="text" name="mobile" id="mobile" class="layui-input" lay-verify="mobile"
                       placeholder="手机号" autocomplete="on" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称：</label>
            <div class="layui-input-inline">
                <input type="text" name="userNick" id="userNick" class="layui-input" lay-verify="userNick"
                       placeholder="昵称" autocomplete="on" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码:</label>
            <div class="layui-input-inline">
                <input type="text" name="inviteCode" id="inviteCode" lay-verify="inviteCode"
                       class="layui-input" placeholder="验证码"
                       autocomplete="off" maxlength="7"/>
            </div>
            <button class="layui-btn layui-btn-warm" type="button" id="inviteCodeBtn">发送验证码</button>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="password" id="password" lay-verify="userPassword"
                       class="layui-input" placeholder="密码" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码：</label>

            <div class="layui-input-inline">
                <input type="password" name="password" id="passwordAge" lay-verify="userPasswordAge"
                       class="layui-input" placeholder="确认密码" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别:</label>
            <div class="radioSex">
                <input type="radio" name="sex" id="man" value="0" title="男">
                <input type="radio" name="sex" id="woman" value="1" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <button type="button" class="layui-btn btn-submit" lay-submit="" lay-filter="sub">注册
            </button>
        </div>
    </form>


</div>
<script src="/layui/layui.js"></script>
<script src="/static/js/getDayOfYear.js"></script>

<script src="/static/js/register.js"></script>

</body>
</html>
