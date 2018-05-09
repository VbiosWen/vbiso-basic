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
            <li class="layui-nav-item"><a href="#">首页</a></li>
            <li class="layui-nav-item"><a href="#">个人中心</a></li>
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
                        data-url="/income/incomeCount" data-type="tabAdd" id="incomeCount">收入统计</a></dd>
                        <dd><a href="#" class="expensesCount" data-id="expensesCount" title="支出统计"
                        data-url="/expense/expensesCount" data-type="tabAdd" id="expensesCount">支出统计</a></dd>
                        <dd><a href="#" class="netincomeCount" data-id="netincomeCount" title="净收入统计"
                        data-url="/netincome/netincomeCount" data-type="tabAdd" id="netincomeCount">净收入统计</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-nav-tree" lay-filter="warn">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="#">消费预警</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <div class="layui-tab" id="tabs" lay-filter="tabs" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">首页内容</div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © vbiso.com - 个人收支管理系统
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
  layui.use('element', function () {
    var $ = layui.jquery;
    var element = layui.element;
    var active = {
      tabAdd: function (url, id, name) {
        element.tabAdd('tabs', {
          title: name,
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
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.expenseNotes').on('click',function () {
      var dataId=$(this);
      if($('.layui-tab-title li[lay-id]').length<=0){
        active.tabAdd(dataId.attr("data-url"),dataId.attr("data-id"),dataId.attr("title"));
      }else{
        var isData=false;
        $.each($('.layui-tab-title li[lay-id]'),function () {
          if($(this).attr("lay-id")==dataId.attr("data-id")){
            isData=true;
          }
        });
        if(isData==false){
          active.tabAdd(dataId.attr("data-url"),dataId.attr("data-id"),dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
    $('.userInfo').on('click', function () {
      var dataId = $(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
   $('.incomeCount').on('click',function () {
     var dataId=$(this);
     if ($('.layui-tab-title li[lay-id]').length <= 0) {
       active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
     } else {
       var isData = false;
       $.each($(".layui-tab-title li[lay-id]"), function () {
         if ($(this).attr("lay-id") == dataId.attr("data-id")) {
           isData = true;
         }
       });
       if (isData == false) {
         active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
       }
     }
     active.tabChange(dataId.attr("data-id"));
   });
    $('.expensesCount').on('click',function () {
      var dataId=$(this);
      if ($('.layui-tab-title li[lay-id]').length <= 0) {
        active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
      } else {
        var isData = false;
        $.each($(".layui-tab-title li[lay-id]"), function () {
          if ($(this).attr("lay-id") == dataId.attr("data-id")) {
            isData = true;
          }
        });
        if (isData == false) {
          active.tabAdd(dataId.attr("data-url"), dataId.attr("data-id"), dataId.attr("title"));
        }
      }
      active.tabChange(dataId.attr("data-id"));
    });
  });
</script>
</body>
</html>
