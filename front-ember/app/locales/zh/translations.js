export default {
  // "some.translation.key": "Text for some.translation.key",
  //
  // "a": {
  //   "nested": {
  //     "key": "Text for a.nested.key"
  //   }
  // },
  //
  // "key.with.interpolation": "Text with {{anInterpolation}}"
  buttons: {
    submit: '提交'
  },
  messages: {
    are_you_sure: '你确定？',
    success: '操作成功',
    failed: '操作失败: {{reason}}'
  },
  auth: {
    models: {
      user: '用户'
    },
    attributes: {
      user: {
        name: '用户名',
        email: '邮箱',
        password: '密码',
        passwordConfirm: '密码确认'
      }
    },
    install: {
      title: '初始化数据',
    },
    users: {
      sign_in: '登录',
      sign_up: '注册',

      change_your_password: '修改你的密码',
      forgot_your_password: '忘记密码？',
      send_me_reset_password_instructions: "请给我发送重设密码的邮件",

      didn_t_receive_confirmation_instructions: '没有收到确认邮件？',
      send_confirmation_instructions: "几分钟后，您将收到确认帐号的电子邮件.",
      resend_confirmation_instructions: "重新发送确认邮件",

      didn_t_receive_unlock_instructions: '没有收到解锁邮件？',
      resend_unlock_instructions: "重发解锁邮件",
      send_unlock_instructions: "几分钟后，您将收到一封解锁帐号的邮件.",
      unlocked: "您的帐号已成功解锁，您现在已登录."
    }
  }
};
