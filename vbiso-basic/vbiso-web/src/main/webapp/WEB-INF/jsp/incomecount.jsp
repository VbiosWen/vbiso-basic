<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/5/3
  Time: 下午2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收入统计</title>
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
<script src="/echats/echarts.js"></script>
<script src="/layui/layui.js"></script>
<script src="/static/js/getDayOfYear.js"></script>
<script type="text/javascript">
  layui.use(['form', 'laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var $ = layui.jquery;
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
        url: '/income/selectIncomeDay',
        type: 'post',
        data: JSON.stringify(data),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          var data = result.data;
          each(data);
        }
      });
      $.ajax({
        url: '/income/incomeCategory',
        type: 'post',
        data: JSON.stringify(data),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          //console.log(startVal,endVal);
          eachLine(result.data, startVal, endVal);
        }
      });
      $.ajax({
        url:'/income/everyCategory',
        type:'post',
        data:JSON.stringify(data),
        contentType:'application/json;charset=UTF-8',
        success:function (result) {
          eachBar(result.data);
        }
      });
    }

    function eachBar(data) {
      var catArr=new Array();
      var incomeData=new Array();
      var treeIncomeData=new Array();
      for(var i=0;i<data.length;i++){
        catArr[i]=data[i].category;
        var inData={'value':data[i].sum,'name':data[i].category};
        incomeData.push(inData);
        treeIncomeData.push(data[i].sum);
      }
      var treeOption = {
        title:{
          text:'个人收入树状图',
          subtext:'根据个人收入记录统计',
          x:'center'
        },
        xAxis: {
          type: 'category',
          data: catArr
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: treeIncomeData,
          type: 'bar'
        }]
      };
      myTreeCharts.setOption(treeOption);
      var option={
        title : {
          text: '个人收入饼状图',
          subtext: '根据个人收入记录统计',
          x:'center'
        },
        tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: catArr
        },
        series : [
          {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:incomeData,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      myBarCharts.setOption(option);
    }

    function eachLine(data, startDate, endDate) {
      var incomeData = {};
      //console.log(startDate);
      var start = getDayOfYear(startDate);
      var end = getDayOfYear(endDate);
      var sum = end - start;
      var dateArr = new Array();
      var date = new Date();
      for (var i = 0; i <= sum; i++) {
        var date1 = startDate + i * 24 * 60 * 60 * 1000;
        date.setTime(date1);
        dateArr[i] = date.toLocaleDateString();
      }
      for (var dt in data) {
        var catIncData=new Array();
        for(var i=0;i<(end-start);i++){
          catIncData[i]=0;
        }
        console.log(catIncData);

        //console.log(data[dt]);
        for(var value=0;value<data[dt].length;value++){
          var now=getDayOfYear(data[dt][value].incomeDate);
          var temp=now-start;
          catIncData[temp]=data[dt][value].incomeData;
         // console.log(data[dt][value].incomeData);
        }
        incomeData[dt] = catIncData;
      }
      //console.log(incomeData);
      insertMoreLineData(dateArr, incomeData);
    }

    function buildSeries(incomeData) {
      var series = [];
      for (var dt in incomeData) {
        var item = {
          name: dt,
          type: 'line',
          stack: '总数',
          data: incomeData[dt]
        }
        series.push(item);
      }
      //console.log(series);
      return series;
    }
    function buildCat(incomeData) {
      var cat=[];
      for(var dt in incomeData){
        cat.push(dt);
      }
      return cat;
    }

    function insertMoreLineData(dateArr, incomeData) {
      var series = buildSeries(incomeData);
      var cat=buildCat(incomeData);
      console.log(cat);
      console.log(series);
      var moreLine = {
        title: {
          text: '日收入分类统计'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: cat
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: dateArr
        },
        yAxis: {
          type: 'value'
        },
        series: series
      }
      myMoreCharts.setOption(moreLine);
    }

    function each(data) {
      var xData = new Array();
      var yData = new Array();
      data.forEach(function (value, item) {
        var date = new Date();
        date.setTime(value.incomeDate);
        xData[item] = date.toLocaleDateString();
        yData[item] = value.incomeData;
      });
      insertLineData(xData, yData);
    }

    $('.layui-btn').on('click', function () {
      getData();
    });

    function insertLineData(xData, yData) {
      mycharts.title = '日收入统计';
      var line = {
        title: {
          text: '日收入统计'
        },
        color: ['#3398DB'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '3%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: xData,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '收入记录',
            type: 'line',
            barWidth: '60%',
            data: yData
          }
        ]
      };
      mycharts.setOption(line);
    }

  });


</script>
</body>
</html>
