layui.use('element', function () {
  var $ = layui.jquery;
  var element = layui.element;
  var netIncomeBar = echarts.init(document.getElementById("netIncomeBar"));
  var netIncomeCatBar = echarts.init(document.getElementById("netIncomeCatBar"));
  var incomeLine = echarts.init(document.getElementById("incomeLine"));
  var expensesLine = echarts.init(document.getElementById("expensesLine"));

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
        text: '个人收入饼状图',
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
    netincomeBar.setOption(option);
  }

  function buildData(data, content) {
    content += (data.incomeData - data.expensesData);
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