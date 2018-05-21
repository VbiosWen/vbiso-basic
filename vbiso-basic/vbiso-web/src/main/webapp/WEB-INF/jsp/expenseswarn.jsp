<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/5/16
  Time: 下午3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支出预警</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>

<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline-block">
            <label class="layui-form-label">提醒时间:</label>
            <div class="layui-input-inline">
                <input type="text" id="startDate" name="startDate" lay-verify="time"
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline-block">
            <label class="layui-form-label">提醒金额</label>
            <div class="layui-input-inline">
                <input type="text"  name="categoryData" id="categoryData" lay-verify="required"
                autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否提醒:</label>
        <div class="radioSex">
            <input type="radio" name="open" id="off" value="0" title="关">
            <input type="radio" name="open" id="on" value="1" title="开">
        </div>
    </div>
    <div class="layui-form-item">
        <button type="button" class="layui-btn btn-submit" lay-submit="" lay-filter="sub">设置
        </button>
    </div>
</form>

<script src="/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form','laydate'],function () {
      var form=layui.form;
      var laydate=layui.laydate;
      var $=layui.jquery;
      laydate.render({
        elem:'#startDate',
        type:'time',
        format:'HH时mm分'
      });
      var userId=${sessionScope.get("user").data.userId};
      $.ajax({
        url: '/warnExpenses/getWarnInfo?userId='+userId,
        type: 'GET',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
         if(result.success){
           insertValue(result.data);
         }
        }
      });

      function insertValue(data) {
        $('#startDate').attr("value",data.startDate);
        $('#categoryData').attr("value",data.categoryData);
        $(".radioSex").find("input[type='sex']").removeAttr("checked");
        if (data.open == '1') {
          $('#on').attr("checked","checked");
        }else{
          $('#off').attr("checked","checked");
        }
        form.render('radio');
      }
      form.on('submit(sub)',function (data) {
        var field=data.field;
        field['userId']=userId;
        $.ajax({
          url:'/warnExpenses/insertWarnInfo',
          type:'post',
          contentType:'application/json;charset=utf-8',
          data:JSON.stringify(field),
          success:function (result) {
            var context=result.msg;
            layer.open({
              url:'返回信息',
              content:context
            });
          }
        })
      });
    });
</script>
</body>
</html>
