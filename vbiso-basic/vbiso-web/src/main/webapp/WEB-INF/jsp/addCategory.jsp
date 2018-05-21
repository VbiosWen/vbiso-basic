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
        <div class="layui-inline">
            <label class="layui-form-label">分类名称</label>
            <div class="layui-input-inline">
                <input type="text" name="categoryDesc" id="categoryDesc" lay-verify="required"
                       autocomplete="off" class="layui-input"/>
            </div>
            <button class="layui-icon" type="button" style="color: lightseagreen;font-weight: bold;font-size: 25px;" id="add">&#xe654;</button>
        </div>
    </div>
</form>
<div id="categoryManager">
    <table class="layui-hide" id="categoryTable" lay-filter="category"></table>
</div>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
  layui.use(['form','table'], function () {
    var form = layui.form;
    var $=layui.jquery;
    var table=layui.table;

    table.render({
      elem:'#categoryTable',
      url:'/category/selectPage',
      page:false,
      cols:[[
        {field:'categoryId',title:'分类ID'},
        {field:'categoryDesc',title:'分类名称'},
        {field:'right',title:'操作',width:178,align:'center',toolbar:'#barDemo'}
      ]]
    });

    $('#add').click(function () {
      var data=$('#categoryDesc').val();
      console.log(data);
      if(data==''){
        layer.msg('分类名称为空');
      }else{
        var userId=${sessionScope.get("user").data.userId}
        var param={"categoryData":data,"userId":userId};
        $.ajax({
          url:'/category/addCategory.json',
          type:'post',
          contentType:'application/json;charset=utf-8',
          data:JSON.stringify(param),
          success:function (result) {
            if(result.success){
              layer.msg('添加成功');
              location.reload();
            }
          }
        });
      }
    });
    table.on('tool(category)',function (obj) {
      var event=obj.event;
      var data=obj.data;
      if(event=='del'){
        layer.confirm('删除分类后对应数据也将会被删除',function (index) {
          $.ajax({
            url:'/category/delSingleCategory?categoryId='+data.categoryId,
            type:'get',
            success:function (result) {
              if(result.success){
                obj.del();
                layer.close(index);
                layer.msg('删除成功',{icon:6});
              }else{
                layer.close(index);
                layer.msg('删除失败',{icon:6});
              }
            }
          });
        });
      }
    });

  });
</script>

<script type="text/html" id="barDemo">
    <a class="layui-icon" style="color: red;font-weight: bold;" lay-event="del">&#x1006;</a>
</script>

</body>
</html>
