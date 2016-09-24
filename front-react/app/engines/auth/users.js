import React from 'react'
import i18next from 'i18next'

import Form from '../../components/Form'

export const SignIn = React.createClass({
  onSubmit (rst) {
    console.log(rst)
  },
  render () {
    return <Form
      title={i18next.t('auth.users.sign_in')}
      fields={[
        {
          id: 'email',
          type: 'email',
          label: i18next.t('auth.attributes.user.email'),
          placeholder: ''
        },
        {
          id: 'password',
          type: 'password',
          label: i18next.t('auth.attributes.user.password'),
          placeholder: ''
        }
      ]}
      submit = {this.onSubmit}
      action="/users/sign_in"
      />
  }
})

export const SignUp = React.createClass({
  render () {
    return <div>sign up</div>
  }
})

export const Confirm = React.createClass({
  render () {
    return <div>confirm</div>
  }
})

export const Unlock = React.createClass({
  render () {
    return <div>unlock</div>
  }
})

export const ForgotPassword = React.createClass({
  render () {
    return <div>forgot password</div>
  }
})
