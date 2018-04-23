<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/19
  Time: 下午6:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tab测试</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>

<ul class="layui-nav layui-nav-tree layui-bg-cyan layui-inline" lay-filter="demo">
    <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">默认展开</a>
        <dl class="layui-nav-child">
            <dd><a href="#" class="site-demo-active" data-id="111" data-type="tabAdd">选线1</a></dd>
            <dd><a href="#" class="site-demo-active" data-id="222" data-type="tabAdd">选线2</a></dd>
            <dd><a href="javascript:;">选线3</a></dd>
        </dl>
    </li>
</ul>

<div class="layui-body" id="container">
    <div class="layui-tab" lay-filter="tabs" lay-allowClose="true">
        <ul class="layui-tab-title">
            <li class="layui-this">首页</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">首页内容</div>
        </div>
    </div>
</div>

<script src="layui/layui.js"></script>
<script>

  layui.use('element', function () {
    var $ = layui.jquery;
    var element = layui.element;
    var active = {
      tabAdd: function (url, id, name) {
        element.tabAdd('tabs', {
          title: name,
          content: 'test',
          id: id
        })
      },
      tabChange: function (id) {
        element.tabChange('tabs', id);
      },
      tabDelete: function (id) {
        element.tabDelete('tabs', id);
      },
      tabDeleteAll:function (ids) {
        $.each(ids,function (i, item) {
          element.tabDelete('tabs',item);
        });
      }
    };
    $('.site-demo-active').on('click',function () {
      var dataId=$(this);
      if($('.layui-tab-title li[lay-id]').length<=0){
        active.tabAdd(dataId.attr("data-url"),dataId.attr("data-id"),dataId.attr("data-title"));
      }else{
        var isData=false;
        $.each($(".layui-tab-title li[lay-id]"),function () {
          if($(this).attr("lay-id")==dataId.attr("data-id")){
            isData=true;
          }
        });
        if (isData==false){
          active.tabAdd(dataId.attr("data-url"),dataId.attr("data-id"),dataId.attr("data-title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
  });
</script>


</body>
</html>
