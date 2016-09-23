import React from 'react'
import {Route, IndexRoute} from 'react-router'

import {Index, AboutUs} from './home'
import {SignIn, SignUp, Unlock, Confirm, ForgotPassword} from './users'

export default [
  <IndexRoute key="auth.index" component={Index}/>,
  <Route key="auth.home" path="home" component={Index}/>,
  <Route key="auth.about-us" path="about-us" component={AboutUs}/>,
  <Route key="auth.users.sign-in" path="users/sign-in" component={SignIn}/>,
  <Route key="auth.users.sign-up" path="users/sign-up" component={SignUp}/>,
  <Route key="auth.users.confirm" path="users/confirm" component={Confirm}/>,
  <Route key="auth.users.unlock" path="users/unlock" component={Unlock}/>,
  <Route key="auth.users.forgot-password" path="users/forgot-password" component={ForgotPassword}/>
]
