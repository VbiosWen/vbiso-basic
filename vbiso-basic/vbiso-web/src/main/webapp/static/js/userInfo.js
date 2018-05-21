layui.use(['form'], function () {
  var form = layui.form;
  var $ = layui.jquery;
  $.ajax({
    url: '/user/getUserInfo',
    type: 'post',
    contentType: 'application/json;charset=UTF-8',
    success: function (result) {
      if (result.success == true) {
        insertValue(result.data);
      }
    }
  });

  function insertValue(data) {
    $('#userNick').attr("value", data.userNick);
    $('#incomeData').attr("value", data.incomeData);
    $('#expensesData').attr("value", data.expensesData);
   // $("input[name='sex']").get(0).checked = true;
    $('#sumData').attr("value", data.sumData);
    $(".radioSex").find("input[type='sex']").removeAttr("checked");
    if (data.sex == '2') {
      console.log(data.sex);
      $('#woman').attr("checked","checked");
    }else{
      $('#man').attr("checked","checked");
    }
    form.render('radio');
  }
  form.verify({
    userNick:function (value,item) {
      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return '用户名不能有特殊字符';
      }
      if(/(^\_)|(\__)|(\_+$)/.test(value)){
        return '用户名首尾不能出现下划线\'_\'';
      }
      if(/^\d+\d+\d$/.test(value)){
        return '用户名不能全为数字';
      }
    },
    userPassword:function (value) {
      if(!new RegExp("/^[\S]{6,12}$/")){
        return '用户密码必须6到12位';
      }
      if(value!=""){
        console.log("test");
        var agPass=$('#userPasswordAg').val();
        if(agPass==""){
          return '请再次输入密码';
        }
        else if(value!=agPass){
          return '两次输入密码不一致';
        }
      }
    }
  });
  form.on('submit(sub)',function (data) {
    if(data.field['userPassword']==""){
      delete data.field['userPassword'];
    }
    if(data.field['userPasswordAg']==""){
      delete data.field['userPasswordAg'];
    }
    delete data.field['incomeData'];
    delete data.field['expensesData'];
    delete data.field['sumData'];
    $.ajax({
      url:'/user/updateUserInfo',
      type: 'post',
      contentType: 'application/json;charset=UTF-8',
      data:JSON.stringify(data.field),
      success:function (data) {
        if(data.success){
          layer.open({
            title:'修改用户信息',
            btn:'确定',
            content:'修改用户信息成功'
          });
        }else{
          layer.open({
            title:'修改用户信息',
            btn:'确定',
            content:'修改失败，请联系管理员'
          });
        }
      }
    })
  });
});