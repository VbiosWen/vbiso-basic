<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/16
  Time: 下午3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加收入信息</title>
    <link rel="stylesheet" href="/layui/css/layui.css" charset="UTF-8">
</head>
<body>
<form action="" class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">日期</label>
            <div class="layui-input-inline">
                <input type="text" name="incomeDate" id="date" lay-verify="datetime"
                       placeholder="yyyy-MM-dd hh:mm:ss"
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">收入数目</label>
            <div class="layui-input-inline">
                <input type="number" name="incomeData" lay-verify="required|number"
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-inline layui-form">
        <label class="layui-form-label">分类:</label>
        <div class="layui-input-inline">
            <select name="category" id="category" lay-verify="required" lay-search=""
                    lay-filter="selectFilter">
                <option value="0">请选择分类</option>
            </select>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
        <legend>编辑内容</legend>
    </fieldset>
    <div class="layui-form-item">
        <div style="margin-bottom: 20px; width: 500px;">
            <textarea class="layui-textarea" id="editIncome" style="display: none"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <button type="button" class="layui-btn btn-submit" lay-submit="" lay-filter="sub">添加
        </button>
    </div>
</form>


<script src="/layui/layui.js"></script>
<script>
 layui.use(['form','layedit','laydate','element','layer'],function () {
   var form = layui.form;
   var layedit = layui.layedit;
   var laydate = layui.laydate;
   var element = layui.element;
   var layer = layui.layer;
   var $=layui.jquery;
   laydate.render({
     elem:'#date',
     type:'datetime'
   });
   $.ajax({
     url: '/category/selectAll',
     type: 'post',
     contentType: 'application/json;charset=UTF-8',
     success: function (result) {
       select(result);
     }
   });

   function select(result) {
     var data = result.data;
     data.forEach(function (value) {
       $('#category').append("<option value='" + value.categoryId + "'>" + value.categoryDesc
           + "</option>");
     });
     form.render('select');
   }

   var index=layedit.build('editIncome');
   form.on('submit(sub)',function (data) {
     var editTest=layedit.getText(index);
     data.field["desc"]=editTest;
     var dateStr=data.field["incomeDate"];
     var time=Date.parse(dateStr);
     data.field["incomeDate"]=time;
     $.ajax({
       url:'/income/add',
       type:'post',
       contentType:'application/json;charset=UTF-8',
       data:JSON.stringify(data.field),
       success:function (data) {
         var flag=data.success;
         var msg=data.msg;
         console.log(flag);
         if(flag==true){
           layer.open({
             title:'返回信息',
             content:'添加成功，您可以继续添加'
           });
         }else{
           layer.open({
             title:'返回信息',
             content:msg
           })
         }
       }
     });
   });

 });
</script>
</body>
</html>
