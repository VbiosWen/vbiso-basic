
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/layui/layui/css/layui.css"/>
    <title>Title</title>
</head>
<body>

<div class="login-box" align="center">
    <div class="layui-main" align="center">
    <form class="layui-form layui-form-pane" method="post" action="/login">
        <div class="layui-form-item">
            <h3>xx后台登录系统</h3>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">账号：</label>

            <div class="layui-input-inline">
                <input type="text" name="account" class="layui-input" lay-verify="account" placeholder="账号" autocomplete="on" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>

            <div class="layui-input-inline">
                <input type="password" name="password" class="layui-input" lay-verify="password" placeholder="密码" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码：</label>

            <div class="layui-input-inline">
                <input type="number" name="code" class="layui-input" lay-verify="code" placeholder="验证码" maxlength="4"/><img src="img/v.png" onclick="getImage()" title="单击刷新验证码" id="img_rand_code" alt="">
            </div>
        </div>
        <div class="layui-form-item">
            <button type="reset" class="layui-btn layui-btn-danger btn-reset">重置</button>
            <button type="submit" class="layui-btn btn-submit" lay-submit="" lay-filter="sub">立即登录</button>
        </div>
    </form>
    </div>
</div>
<script src="/layui/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form'],function () {
        var form =layui.form(),$=layui.jquery,layer=layui.layer;
        form.verify({
            account:function (value) {
                if (value==''){
                    return '请输入用户名';
                }
            },
            password:function (value) {
                if(value==''){
                    return '请输入密码';
                }
            }
        });
        form.on('submit(sub)',function (data) {
            layer.alert(JSON.stringify(data,field),{
                title:'最终提交的信息'
            });
            return false;
        })
    });
</script>
</body>
</html>
