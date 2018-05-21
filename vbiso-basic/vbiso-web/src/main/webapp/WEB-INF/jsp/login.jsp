<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/15
  Time: 下午9:57
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
<div class="login-box main">
    <form class="layui-form layui-form-pane" method="post" action="/user/login">
        <div class="layui-form-item">
            <h3 style="font-family: 'Apple Braille';color: #dddddd; font-size: 22px">个人收支管理系统</h3>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">账号：</label>

            <div class="layui-input-inline">
                <input type="text" name="mobile" id="mobile" class="layui-input" lay-verify="account"
                       placeholder="账号" autocomplete="on" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>

            <div class="layui-input-inline">
                <input type="password" name="password" id="password" class="layui-input" lay-verify="password"
                       placeholder="密码" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <button type="reset" class="layui-btn layui-btn-danger btn-reset"><a
                    href="/user/register">注册</a></button>
            <button type="button" class="layui-btn btn-submit" lay-submit="" lay-filter="sub">立即登录
            </button>
        </div>
    </form>
</div>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
  layui.use(['form', 'layer'], function () {
    var $ = layui.jquery, form = layui.form, layer = layui.layer;
    // 验证
    form.verify({
      account: function (value) {
        if (value == "") {
          return "请输入用户名";
        }
      },
      password: function (value) {
        if (value == "") {
          return "请输入密码";
        }
      }
    });
    // 提交监听
    form.on('submit(sub)', function (data) {
      // layer.alert(JSON.stringify(data.field), {
      //   title: '最终的提交信息'
      // });
      $.ajax({
        type: 'post',
        url: '/user/login',
        contentType:'application/json;charset=UTF-8',
        data:JSON.stringify(data.field),
        success: function (data) {
          console.log(data);
         if(data.success){
           location.href="../index.jsp";
         }else {
           layer.open({
             title:'登录失败',
             type:1,
             btn:'确定',
             content:'用户名或者密码错误，请重新登录'
           });
         }
        }
      });
    })
  })

  //刷新验证码
  function getImage() {
//  $("#img_rand_code").attr("src", "" + Date());
    alert("123");
  }


</script>
</body>
</html>
