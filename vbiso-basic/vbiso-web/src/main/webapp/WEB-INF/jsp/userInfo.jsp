<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/30
  Time: 下午11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/layui/css/layui.css" charset="UTF-8">
    <title>修改个人信息</title>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">昵称:</label>
            <div class="layui-input-inline">
                <input type="text" id="userNick" name="userNick" lay-verify="required"
                       autocomplete="off"
                       class="layui-input"/>
            </div>
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
        <label class="layui-form-label">新密码:</label>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="password" name="userPassword" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码:</label>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="password" name="userPasswordAg" autocomplete="off"
                       class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">收入总数:</label>
            <div class="layui-input-inline">
                <input type="number" id="incomeData" name="incomeData" lay-verify="required"
                       autocomplete="off"
                       class="layui-input" readonly="readonly"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">支出总数:</label>
            <div class="layui-input-inline">
                <input type="number" id="expensesData" name="expensesData" lay-verify="required"
                       autocomplete="off"
                       class="layui-input" readonly="readonly"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">净收入总数:</label>
            <div class="layui-input-inline">
                <input type="number" id="sumData" name="sumData" lay-verify="required"
                       autocomplete="off"
                       class="layui-input" readonly="readonly"/>
            </div>
        </div>
    </div>
</form>

<script src="/layui/layui.js">
</script>
<script type="text/javascript">
  layui.use(['form'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    $.ajax({
      url: '/user/getUserInfo',
      type: 'post',
      contentType: 'application/json;charset=UTF-8',
      success: function (result) {
        if (result.success == true) {
          insertValue(result.data);
        }
      }
    });

    function insertValue(data) {
      console.log(data);
      $('#userNick').attr("value", data.userNick);
      $('#incomeData').attr("value", data.incomeData);
      $('#expensesData').attr("value", data.expensesData);
      $("input[name='sex']").get(0).checked = true;
      $('#sumData').attr("value", data.sumData);
      $(".radioSex").find("input[type='sex']").removeAttr("checked");
      if (data.sex == '1') {
        $('#woman').attr("checked","checked");
      }else{
        $('#man').attr("checked","checked");
      }
      form.render('radio');
    }
    form.verify({
      userNick:function (value,item) {
        if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
          return '用户名不能有特殊字符';
        }
        if(/(^\_)|(\__)|(\_+$)/.test(value)){
          return '用户名首尾不能出现下划线\'_\'';
        }
        if(/^\d+\d+\d$/.test(value)){
          return '用户名不能全为数字';
        }
      },
      userPassword:function (value, item) {
        if(!new RegExp("/^[\S]{6,12}$/")){
          return '用户密码必须6到12位';
        }
        if(value!=null){

        }
      }
    });
  });
</script>

</body>
</html>
