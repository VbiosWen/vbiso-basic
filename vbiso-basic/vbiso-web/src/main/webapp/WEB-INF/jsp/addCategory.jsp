<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/5/3
  Time: 下午9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加分类</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline-block">
            <label class="layui-form-label">分类:</label>
            <div class="layui-input-inline">
                <input type="text" name="categoryData" lay-verify="required"
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <button type="button" class="layui-btn btn-submit" lay-submit="" lay-filter="sub">添加
        </button>
    </div>
</form>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
  layui.use(['form'], function () {
    var form = layui.form;
    var $=layui.jquery;
    form.on('submit(sub)', function (data) {
      data.field['userId'] =${sessionScope.get("user").userId};
      $.ajax({
        url: '/category/addCategory.json',
        type: 'post',
        data:JSON.stringify(data.field),
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {
          var context = "";
          if (data.success) {
            context.concat("添加成功");
          } else {
            context.concat("添加失败,请尝试重新添加，或者联系管理员");
          }
          layer.open({
            title: '添加结果',
            btn: '确定',
            content: context
          });
        }
      });
    });
  });
</script>
</body>
</html>
