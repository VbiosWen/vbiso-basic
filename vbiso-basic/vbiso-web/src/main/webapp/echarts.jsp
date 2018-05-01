<%--
  Created by IntelliJ IDEA.
  User: vbisowen
  Date: 2018/4/28
  Time: 下午4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/echats/echarts.js"></script>
</head>
<body>

<div id="main" style="width: 600px;height: 400px">
</div>

<script type="text/javascript">
  var mychart = echarts.init(document.getElementById('main'));
  var option = {
    title: {
      text: '1'
    },
    tooltip: {},
    xAxis: {
      data: ['一月', '二月', '三月', '四月', '五月', '六月']
    },
    yAxis: {},
    series: [{
      name: '销量',
      type: 'bar',
      data: [5, 20, 36, 10, 10, 20]
    }]
  };
  mychart.setOption(option);
</script>

</body>
</html>
