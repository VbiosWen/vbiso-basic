<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/5/9
  Time: 下午4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
<div class="layui-inline" style="float: left;display: inline;height: auto;width: 100%">
    <label class="layui-form-label">查询:</label>
    <div class="layui-input-inline">
        <input type="text" name="expensesDate" id="start" lay-verify="datetime"
               placeholder="开始时间"
               autocomplete="off" class="layui-input"/>
    </div>
    <div class="layui-input-inline">
        <input type="text" name="expensesDate" id="end" lay-verify="datetime"
               placeholder="结束时间"
               autocomplete="off" class="layui-input"/>
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>


<div id="line"  style="width: 600px;height: 300px;float: left;display: inline"></div>
<div id="moreLine" style="width: 600px;height: 300px;float: left;display: inline"></div>
<div id="bar" style="width: 600px;height: 300px;float: left;display: inline"></div>
<div id="tree" style="height:300px;width: 600px;float: left;display: inline"></div>
<script src="/layui/layui.js"></script>
<script src="/echats/echarts.js"></script>
<script src="/static/js/getDayOfYear.js"></script>
<script type="text/javascript">
    layui.use(['form','laydate'],function () {
      var form=layui.form;
      var laydate=layui.laydate;
      var $=layui.jquery;
      var mycharts = echarts.init(document.getElementById("line"));
      var myMoreCharts = echarts.init(document.getElementById("moreLine"));
      var myBarCharts=echarts.init(document.getElementById("bar"));
      var myTreeCharts=echarts.init(document.getElementById("tree"));
      laydate.render({
        elem: '#start',
        type: 'datetime'
      });
      laydate.render({
        elem: '#end',
        type: 'datetime'
      });
      getData();
      function getData() {
        var start = $('#start').val();
        var end = $('#end').val();
        var endVal=0;
        var startVal=0;
        if (end == '') {
          var date = new Date();
          var mill = date.getMilliseconds();
          var second = date.getSeconds();
          var min = date.getMinutes();
          var hours = date.getHours();
          endVal = date.getTime() - (mill + second * 1000 + min * 60 * 1000 + hours * 60 * 60 * 1000);
          if (start == '') {
            startVal = endVal - 7 * 24 * 60 * 60 * 1000;
          }else{
            startVal=Date.parse(end);
          }
        }else {
          if(start==''){
            startVal=0;
          }else{
            startVal=Date.parse(start);
          }
          if(end==''){
            endVal=new Date().getTime();
          }else{
            endVal=Date.parse(end);
          }
        }


        var data = {};
        data.userId =${sessionScope.get("user").data.userId};
        data.start = startVal;
        data.end = endVal;
        var date = new Date();
        $.ajax({
          url: '/expense/selectExpensesDay',
          type: 'post',
          data: JSON.stringify(data),
          contentType: 'application/json;charset=UTF-8',
          success: function (result) {
            var data = result.data;
            each(data);
          }
        });
        $.ajax({
          url: '/expense/expensesCategory',
          type: 'post',
          data: JSON.stringify(data),
          contentType: 'application/json;charset=UTF-8',
          success: function (result) {
            //console.log(startVal,endVal);
            eachLine(result.data, startVal, endVal);
          }
        });
        $.ajax({
          url:'/expense/everyCategory',
          type:'post',
          data:JSON.stringify(data),
          contentType:'application/json;charset=UTF-8',
          success:function (result) {
            eachBar(result.data);
          }
        });
      }
    });
</script>
</body>
</html>
