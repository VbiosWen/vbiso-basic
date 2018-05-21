layui.use(['form'], function () {
  var form = layui.form;
  var $ = layui.jquery;
  var layer = layui.layer;
  var patten = /^1[34578]\d{9}$/;
  form.verify({
    mobile: function (value) {
      if (value == '') {
        return '手机号为空';
      }
      if (!patten.test(value)) {
        return '手机号格式不正确';
      }
    },
    inviteCode: function (value) {
      console.log(value);
      if (value == '') {
        return '请输入验证码';
      }
      if (value.length !=6) {
        return '验证码输入有误';
      }
    },
    userNick:function (value) {
      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return '用户名不能有特殊字符';
      }
      if(/(^\_)|(\__)|(\_+$)/.test(value)){
        return '用户名首尾不能出现下划线\'_\'';
      }
      if(/^\d+\d+\d$/.test(value)) {
        return '用户名不能全为数字';
      }
    },
    userPassword: function (value) {
      if (!new RegExp("/^[\S]{6,12}$/")) {
        return '用户密码必须6到12位';
      }
      if (value != "") {
        var agPass = $('#passwordAge').val();
        if (agPass == "") {
          return '请再次输入密码';
        }
        else if (value != agPass) {
          console.log(value,agPass);
          return '两次输入密码不一致';
        }
      }else{
        return '请输入密码';
      }
    }
  });

  form.on('submit(sub)', function (data) {
    $.ajax({
      url:'/user/registerUser',
      type:'post',
      contentType:'application/json;charset=utf-8',
      data:JSON.stringify(data.field),
      success:function (data) {
        var context='';
        if(!data.success){
          context=data.msg;
          layer.open({
            title:'注册失败',
            content:context
          });
        }else{
          location.href="../index.jsp";
        }
      }
    });
  });

  $('#inviteCodeBtn').click(function () {
    var mobile = $('#mobile').val();
    var data = {'mobile': mobile};
    if (mobile == '') {
      $('#mobile').attr("placeholder", "手机号不能为空");
      return false;
    }
    if (!patten.test(mobile)) {
      $('#mobile').val('手机号格式不正确');
      return false;
    }
    $.ajax({
      url: '/user/inviteCode',
      type: 'get',
      contentType: 'application/json;charset=utf-8',
      data: data,
      success: function (data) {
        var context = '';
        if (data.success) {
          context = '发送短信成功，验证码有效时间为五分钟';
        } else {
          context = data.msg;
        }
        layer.open({
          title: '发送短信',
          content: context
        });
      }
    });
  });
});

