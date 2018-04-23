<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/16
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
<div class="layui-tab layui-tab-card" lay-filter="demo" style="height: 300px;">
    <ul class="layui-tab-title">
        <li class="layui-this">演示说明</li>
        <li>标题一</li>
        <li>标题二</li>
        <li>标题三</li>
    </ul>
    <div class="layui-tab-content" style="padding: 10px;">
        <div class="layui-tab-item layui-show">
            在这里，你将以最直观的形式体验Layui！在编辑器中可以执行layui相关的一切代码。
            <br>你也可以点击左侧导航针对性地试验我们提供的示例。
            <!--<br><br><a target="_blank" href="https://jq.qq.com/?_wv=1027&k=41lnosl" class="layui-btn">加入Layui开发者交流群</a>（系网友提供）-->
        </div>
        <div class="layui-tab-item">内容1</div>
        <div class="layui-tab-item">内容2</div>
        <div class="layui-tab-item">内容3</div>
    </div>
</div>

<div id="pageDemo"></div>

<script src="/layui/layui.all.js"></script>
<script type="text/javascript">
  $(function() {
    layui.use('layer', function() {
      var $ = layui.jquery,
          layer = layui.layer;
      layui.use(['layer', 'laypage', 'element'], function() {
        var layer = layui.layer,
            laypage = layui.laypage,
            element = layui.element();

        //向世界问个好
        layer.msg('Hello World');

        //监听Tab切换
        element.on('tab(demo)', function(data) {
          layer.msg('切换了：' + this.innerHTML);
          console.log(data);
        });

        //分页
        laypage({
          cont: 'pageDemo' //分页容器的id
          ,
          pages: 100 //总页数
          ,
          skin: '#5FB878' //自定义选中色值
          //,skip: true //开启跳页
          ,
          jump: function(obj, first) {
            if(!first) {
//									layer.msg('第' + obj.curr + '页');
              11
            }
          }
        });
      });
    });
  });
</script>
</body>
</html>
