<%--suppress ALL --%>
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
<button class="layui-btn" id="addExpense">添加</button>
<div class="layui-inline">
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
<div class="expensesTable">
    <table class="layui-hide" id="expensesTable" lay-filter="expenses"></table>
</div>


<script src="/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
  layui.use(['table', 'element', 'layer', 'laydate', 'form'], function () {
    var table = layui.table;
    var element = layui.element;
    var form = layui.form;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    laydate.render({
      elem:'#start',
      type:'datetime'
    });
    laydate.render({
      elem:'#end',
      type:'datetime'
    })
    var start={
      max:new Date(),
      min:new Date(),
      istoday:true,
      choose:function (datas) {
        end.min=datas;
        end.start=datas;
      }
    };
    var end={
      max:new Date(),
      min:new Date(),
      istoday:false,
      choose:function (datas) {
        start.max=datas;
      }
    };
    $('#start').on('click',function () {
      start.elem=this;
    });
    $('#end').on('click',function () {
      end.elem=this;
    });

    // laydate.render({
    //   elem:'#start,#end',
    //   type:'datetime'
    // });
    var categoryResult = null;
    $.ajax({
      url: '/category/selectAll',
      type: 'post',
      async: false,
      contentType: 'application/json;charset=UTF-8',
      success: function (result) {
        select(result);
        callback(result);
      }
    });

    function callback(result) {
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
      elem: '#expensesTable',
      total: 'data.totalCount',
      url: '/expense/page',
      cellMinWidth: 80,
      cols: [[
        {field: 'expensesId', title: 'ID', sort: true}
        , {field: 'expensesData', title: '支出金额', type: 'double'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
        , {field: 'expensesDate', title: '日期', templet: '#createTime'}
        , {field: 'expensesDesc', title: '支出详情'}
        , {field: 'categoryId', name: 'category', title: '分类'}
        ,{field:'right',title:'操作',width:178,align:'center',toolbar:'#barDemo'}
      ]],
      page: 'true',
      limit: '10',
      limits: [10, 15, 20, 30],
      id: 'expenseTable',
      done: function (res, curr, count) {
        console.log(res);
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

    table.on('tool(expenses)',function (obj) {
      var event=obj.event;
      var data=obj.data;
      if(event=='del'){
        layer.confirm('确定删除吗',function (index) {
          $.ajax({
            url:'/expense/delSingleExpenses?expensesId='+data.expensesId,
            type:'get',
            success:function (result) {
              if(result.success){
                obj.del();
                layer.close(index);
                layer.msg('删除成功',{icon:6});
              }else{
                layer.close(index);
                layer.msg('删除失败',{icon:6});
              }
            }
          });
        });
      }
    });

    $('#addExpense').click(function () {
      layer.open({
        title: '添加支出记录',
        type: '2',
        area: ['550px', '500px'],
        btn: '退出',
        yes:function (index) {
          location.reload();
        },
        content: '<iframe src="/expense/add" height="100%" width="100%" frameborder="0"></iframe>'
      })
    });

    function selectChange() {
      var data = $('#category').val();
      return data;
    }

    var expensesActive = {
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
        table.reload('expenseTable', {
          where: {
            start: startVal,
            end: endVal,
            categoryId: category
          }
        });
      }
    };
    $('.layui-btn').on('click', function () {
      expensesActive.reload();
    });
  });
</script>

<script src="/static/js/dateformat.js"></script>

<script id="createTime" type="text/html">
    {{#
    var date = new Date();
    date.setTime(d.expensesDate);
    return date.Format("yyyy-MM-dd hh:mm:ss");
    }}
</script>


<script type="text/html" id="barDemo">
    <a class="layui-icon" style="color: red;font-weight: bold;" lay-event="del">&#x1006;</a>
</script>

</body>
</html>