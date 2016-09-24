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
    submit: 'Submit'
  },
  auth: {
    models: {
      user: 'User'
    },
    attributes: {
      user: {
        name: 'Name',
        email: 'Email',
        password: 'Password',
        password_confirmation: 'Password confirmation'
      }
    },
    users: {
      sign_in: 'Sign in',
      sign_up: 'Sign up',

      forgot_your_password: 'Forgot your password?',
      send_me_reset_password_instructions: 'Send me reset password instructions',

      didn_t_receive_confirmation_instructions: "Didn't receive confirmation instructions?",
      resend_confirmation_instructions: 'Resend confirmation instructions',
      send_confirmation_instructions: 'You will receive an email with instructions for how to confirm your email address in a few minutes.',

      didn_t_receive_unlock_instructions: "Didn't receive unlock instructions?",
      resend_unlock_instructions: 'Resend unlock instructions',
      send_unlock_instructions: 'You will receive an email with instructions for how to unlock your account in a few minutes.',
      unlocked: 'Your account has been unlocked successfully. Please sign in to continue.'
    }
  }
};
