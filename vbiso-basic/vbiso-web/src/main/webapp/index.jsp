<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/15
  Time: 下午9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <title>个人收支管理系统</title>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">个人收支管理系统</div>
        <ul class="layui-nav layui-layout-left" id="header">
            <%--<li class="layui-nav-item"><a href="#" class="showPage" data-id="showPage">首页</a></li>--%>
            <li class="layui-nav-item"><i class="layui-icon layui-icon-face-smile" id="refresh">&#xe669;</i>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"
                         style="align-items: center;">
                    <span>${sessionScope.get("user").data.userNick}</span>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="#" class="userInfo" data-id="userInfo" title="基本资料"
                           data-url="/user/userInfo">基本资料</a></dd>
                    <dd><a href="#" class="categoryManager" data-id="categoryManager"
                           title="分类管理" data-url="/category/returnCategory">分类管理</a></dd>
                    <dd><a href="/user/removeSession">退出登录</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-size-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="incomeExpense">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">收支记录</a>
                    <dl class="layui-nav-child">
                        <dd><a href="#" class="incomeNotes" data-id="incomeNotes" title="收入记录"
                               data-url="/income/listPage"
                               data-type="tabAdd" id="incomeNotes">收入记录</a></dd>
                        <dd><a href="#" class="expenseNotes" data-id="expenseNotes" title="支出记录"
                               data-url="/expense/listPage">支出记录</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-nav-tree" lay-filter="countMap">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">收支统计</a>
                    <dl class="layui-nav-child">
                        <dd><a href="#" class="incomeCount" data-id="incomeCount" title="收入统计"
                               data-url="/income/incomeCount" data-type="tabAdd" id="incomeCount">收入统计</a>
                        </dd>
                        <dd><a href="#" class="expensesCount" data-id="expensesCount" title="支出统计"
                               data-url="/expense/expensesCount" data-type="tabAdd"
                               id="expensesCount">支出统计</a></dd>
                        <dd><a href="#" class="netincomeCount" data-id="netincomeCount"
                               title="净收入统计"
                               data-url="/netincome/netincomeCount" data-type="tabAdd"
                               id="netincomeCount">净收入统计</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-nav-tree" lay-filter="warn">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="expensesWarn" data-id="expensesWarn" title="消费预警"
                       data-url="/warnExpenses/warn"
                       data-type="tabAdd" id="expensesWarn" href="#">消费预警</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <div class="layui-tab" id="tabs" lay-filter="tabs" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this"  data-id="showPage">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show" lay-id="showPage">
                    <div id="netIncomeBar" style="height: 300px;width: 600px;float: left;display: inline;"></div>
                    <div id="netIncomeCatBar" style="height: 300px;width: 600px;float: left;display: inline;"></div>
                    <div id="incomeLine" style="height: 300px;width: 600px;float: left;display: inline;"></div>
                    <div id="expensesLine" style="height: 300px;width: 600px;float: left;display: inline;"></div>
                </div>

            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © vbiso.com - 个人收支管理系统
    </div>
</div>

<script src="/layui/layui.js"></script>
<script src="/echats/echarts.js"></script>
<script type="text/javascript">
  layui.use(['element','layer'], function () {
    var $ = layui.jquery;
    var element = layui.element;
    var layer=layui.layer;
    var netIncomeBar = echarts.init(document.getElementById("netIncomeBar"));
    var netIncomeCatBar = echarts.init(document.getElementById("netIncomeCatBar"));
    var incomeLine = echarts.init(document.getElementById("incomeLine"));
    var expensesLine = echarts.init(document.getElementById("expensesLine"));

    getData();

    // element.tab({
    //   headerElem:'#header>.showPage',
    //   bodyElem:'#showPage>.layui-tab-item'
    // });

    function getData() {
      var param={
        "start":0,
        "end":Date.now(),
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
            buildData(data);
          } else {
            layer.open({
              title: '错误',
              content: '查询错误，请联系管理员'
            });
          }
        }
      });
      $('#refresh').click(function () {
        location.reload();
      })
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
      $.ajax({
        url: '/expense/selectExpensesDay',
        type: 'post',
        data: JSON.stringify(param),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          var data = result.data;
          each(data);
        }
      });

      $.ajax({
        url: '/income/selectIncomeDay',
        type: 'post',
        data: JSON.stringify(param),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          var data = result.data;
          eachNetIncome(data);
        }
      });
    }

    function each(data) {
      var xData = new Array();
      var yData = new Array();
      data.forEach(function (value, item) {
        var date = new Date();
        date.setTime(value.expensesDate);
        xData[item] = date.toLocaleDateString();
        yData[item] = value.expensesData;
      });
      insertLineData(xData, yData);
    }


    function eachNetIncome(data) {
      var xData = new Array();
      var yData = new Array();
      data.forEach(function (value, item) {
        var date = new Date();
        date.setTime(value.incomeDate);
        xData[item] = date.toLocaleDateString();
        yData[item] = value.incomeData;
      });
      insertIncomeLineData(xData, yData);
    }

    function insertIncomeLineData(xData, yData) {
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
      incomeLine.setOption(line);
    }


    function insertLineData(xData, yData) {
      var line = {
        title: {
          text: '日支出统计'
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
            name: '支出记录',
            type: 'line',
            barWidth: '60%',
            data: yData
          }
        ]
      };
      expensesLine.setOption(line);
    }

    function buildParam(data) {
      var catAttr = new Array();
      var valueAttr = new Array();
      var index = 0;
      for (var dt in data) {
        if (data[dt] != 0) {
          var key = dt;
          if (data[dt] < 0) {
            key += "支出"
          } else {
            key += "收入"
          }
          catAttr[index] = key;
          var dat = {'value': Math.abs(data[dt]), 'name': key};
          valueAttr[index] = dat;
          index++;
        }
      }
      var option = {
        title: {
          text: '个人净收入饼状图',
          subtext: '根据个人收入记录统计',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: catAttr
        },
        series: [
          {
            name: '收入详细',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: valueAttr,
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
      netIncomeBar.setOption(option);
    }

    function buildData(data) {
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
      netIncomeCatBar.setOption(option);
    }

    var active = {
      tabAdd: function (url, id, name) {
        element.tabAdd('tabs', {
          title: name,
          autoRefresh: true,
          content: '<iframe scrolling="auto" frameborder="0" src="' + url
          + '" style="width:100%;height:100%"></iframe>',
          id: id
        })
      },
      tabChange: function (id) {
        element.tabChange('tabs', id);
      },
      tabDelete: function (id) {
        element.tabDelete('tabs', id);
      },
      tabDeleteAll: function (ids) {
        $.each(ids, function (i, item) {
          element.tabDelete('tabs', item);
        });
      }
    };
    $('.incomeNotes').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.expenseNotes').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($('.layui-tab-title li[lay-id]'), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.userInfo').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.incomeCount').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.expensesCount').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.netincomeCount').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.expensesWarn').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          console.log($(this).attr("lay-id"));
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });

    $('.categoryManager').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
            dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"),
              dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
  });
</script>
</body>
</html>
