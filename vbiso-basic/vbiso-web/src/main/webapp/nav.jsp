<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/16
  Time: 上午11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>

<ul class="layui-nav layui-nav-tree layui-bg-cyan layui-inline" lay-filter="demo">
    <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">默认展开</a>
        <dl class="layui-nav-child">
            <dd lay-id="111"><a href="javascript:;" data-options="{url:'test.jsp',title:'表格'}">选线1</a> </dd>
            <dd><a href="javascript:;">选线2</a> </dd>
            <dd><a href="javascript:;">选线3</a> </dd>
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
</div>

<script src="/layui/layui.all.js"></script>
<script>
    layui.use('element',function () {
      var element=layui.element;
      element.on('nav(demo)',function (elem) {
        var options=eval('('+elem.context.children[0].dataset.options+')');
        var url=options.url;
        var title=options.title;
        element.tabAdd('tabs',{
          title:title,
          content:'<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:100%"></iframe>'
        })
      });

    });
</script>
</body>
</html>
