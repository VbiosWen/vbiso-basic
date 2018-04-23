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

<button class="layui-btn">添加</button>
<table class="layui-hide" id="test"></table>


<script src="/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
  layui.use('table', function () {
    var table = layui.table;

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
      ]],
      page: 'true',
      limit: '10',
      limits: [10, 20, 30, 50],
      id: 'test'
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