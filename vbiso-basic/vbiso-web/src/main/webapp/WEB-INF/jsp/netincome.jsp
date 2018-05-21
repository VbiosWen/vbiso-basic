<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/5/14
  Time: 下午12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>净收入统计</title>
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

<div>
    <span style="font-size: 25px;color: crimson " id="netIncomeSpan"></span>
</div>

<div id="netincomeTree" style="height: 400px;width: 600px;float: left;display: inline;"></div>
<div id="netincomeBar" style="height: 400px;width: 600px;float: left;display: inline;"></div>
<script src="/layui/layui.js"></script>
<script src="/echats/echarts.js"></script>
<script src="/static/js/getDayOfYear.js"></script>
<script type="text/javascript">
  layui.use(['form', 'laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var $ = layui.jquery;
    var layer = layui.layer;
    var netincomeTree = echarts.init(document.getElementById("netincomeTree"));
    var netincomeBar=echarts.init(document.getElementById("netincomeBar"));
    laydate.render({
      elem: '#start',
      type: 'date'
    });
    laydate.render({
      elem: '#end',
      type: 'date'
    });

    getData();

    function getData() {
      var start = $('#start').val();
      var end = $('#end').val();
      var startVal = 0;
      var endVal = 0;
      var msg='';
      if (start == '') {
        startVal = 0;
      }else {
        startVal=Date.parse(start);
      }
      if (end == '') {
        var date = new Date();
        endVal = date.getTime();
      }else{
        endVal=Date.parse(end);
      }
      if(startVal==0){
        msg+='您距今为止的净收入总额为';
      }else{
        var dayOFYear=getDayOfYear(startVal);
        var dayE=getDayOfYear(endVal);
        msg+='您在'+(dayE-dayOFYear)+'天的总收入为：';
      }
      var param = {
        "start": startVal,
        "end": endVal,
        "userId":${sessionScope.get("user").data.userId}
      };
      $.ajax({
        url: '/netincome/totalNetIncome',
        type: 'post',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(param),
        success: function (result) {
          if (result.success) {
            var data = result.data;
            buildData(data,msg);
          } else {
            layer.open({
              title: '错误',
              content: '查询错误，请联系管理员'
            });
          }
        }
      });

      $.ajax({
        url:'/netincome/totalNetIncomeByCat',
        type:'post',
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(param),
        success:function (result) {
          if(result.success){
            var data=result.data;
            buildParam(data);
          }else{
            layer.open({
              title:'错误',
              content:'查询错误，请联系管理员'
            });
          }
        }
      });
    }

    function buildParam(data) {
      var catAttr=new Array();
      var valueAttr=new Array();
      var index=0;
      for(var dt in data){
       if(data[dt]!=0){
         var key=dt;
         if(data[dt]<0){
           key+="支出"
         }else{
           key+="收入"
         }
         catAttr[index]=key;
         var dat={'value':Math.abs(data[dt]),'name':key};
         valueAttr[index]=dat;
         index++;
       }
      }
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
          data: catAttr
        },
        series : [
          {
            name: '收入详细',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:valueAttr,
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
      netincomeBar.setOption(option);
    }

    $('.layui-btn').on('click', function () {
      getData();
    });

    function buildData(data,content) {
      content+=(data.incomeData-data.expensesData);
      $('#netIncomeSpan').text(content);
      var option = {
        title: {
          text: '净收入饼状图',
          subtext: '根据个人收支记录统计',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['收入', '支出']
        },
        series: [
          {
            name: '详细信息',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              {value: data.incomeData, name: '收入'},
              {value: data.expensesData, name: '支出'},
            ],
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
      netincomeTree.setOption(option);
    }

  });
</script>
</body>
</html>
