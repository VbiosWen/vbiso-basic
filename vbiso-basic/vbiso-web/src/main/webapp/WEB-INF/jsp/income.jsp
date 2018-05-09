<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<button class="layui-btn" id="addIncome">添加收支记录</button>
<button class="layui-btn" id="addCategory">添加分类</button>
<div class="layui-inline">
    <label class="layui-form-label">查询:</label>
    <div class="layui-input-inline">
        <input type="text" name="incomeDate" id="start" lay-verify="datetime"
               placeholder="开始时间"
               autocomplete="off" class="layui-input"/>
    </div>
    <div class="layui-input-inline">
        <input type="text" name="incomeDate" id="end" lay-verify="datetime"
               placeholder="结束时间"
               autocomplete="off" class="layui-input"/>
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
</div>
<button class="layui-btn" data-type="reload">搜索</button>

<table class="layui-hide" id="test"></table>


<script src="/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
  layui.use(['table', 'element', 'layer', 'form', 'laydate'], function () {
    var table = layui.table;
    var element = layui.element;
    var $ = layui.jquery;
    var form = layui.form;
    var laydate = layui.laydate;

    laydate.render({
      elem: '#start',
      type: 'datetime'
    });
    laydate.render({
      elem: '#end',
      type: 'datetime'
    });
    var categoryResult = null;
    $.ajax({
      url: '/category/selectAll',
      type: 'post',
      async: false,
      contentType: 'application/json;charset=UTF-8',
      success: function (result) {
        select(result);
        callBack(result);
      }
    });

    function callBack(result) {
      categoryResult = result;
    }

    function select(result) {
      var data = result.data;
      data.forEach(function (value) {
        $('#category').append("<option value='" + value.categoryId + "'>" + value.categoryDesc
            + "</option>");
      });
      form.render('select');
    }

    table.render({
      elem: '#test',
      total: 'data.totalCount',
      url: '/income/page',
      cellMinWidth: 80,
      cols: [[
        {field: 'incomeId', title: 'ID', sort: true}
        , {field: 'incomeData', title: '收入总数'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
        , {field: 'incomeDate', title: '日期', templet: '#createTime'}
        , {field: 'incomeDesc', title: '收入详情'}
        , {field: 'categoryId', title: '分类'}
      ]],
      page: 'true',
      limit: '10',
      limits: [10, 15, 20, 30],
      id: 'test',
      done: function (res, curr, count) {
        $("[data-field='categoryId']").children().each(function () {
          var type = $(this);
          categoryResult.data.forEach(function (value) {
            if (type.text() == value.categoryId) {
              type.text(value.categoryDesc);
            }
          })
        });
      }
    });

    $('#addIncome').click(function () {
      layer.open({
        title: '添加收入记录',
        type: '2',
        area: ['550px', '500px'],
        btn: '退出',
        content: '<iframe src="/income/addIncome" height="100%" width="100%" frameborder="0"></iframe>'
      })
    });
    $('#addCategory').click(function () {
      layer.open({
        title: '添加分类',
        type: '2',
        area: ['500px', '200px'],
        btn: '退出',
        content: '<iframe src="/category/addCategory" height="100%" width="100%" frameborder="0" scrolling="no"></iframe>'
      });
    });

    function selectChange() {
      var data = $('#category').val();
      return data;
    }

    var incomeActive = {
      reload: function () {
        var category = selectChange();
        var start = $('#start').val();
        var end = $('#end').val();
        var startVal = Date.parse(start);
        var endVal = Date.parse(end);
        if (isNaN(startVal)) {
          startVal = 0;
        }
        if (isNaN(endVal)) {
          endVal = 0;
        }
        table.reload('test', {
          where: {
            start: startVal,
            end: endVal,
            categoryId: category
          }
        });
      }
    };
    $('.layui-btn').on('click', function () {
      incomeActive.reload();
    });
  });
</script>

<script src="/static/js/dateformat.js"></script>

<script id="createTime" type="text/html">
    {{#
    var date = new Date();
    date.setTime(d.incomeDate);
    return date.Format("yyyy-MM-dd hh:mm:ss");
    }}
</script>


</body>
</html>