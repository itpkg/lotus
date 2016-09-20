import React from 'react'
import {Route, IndexRoute} from 'react-router'

import {Index, AboutUs} from './home'
import {Callback as Oauth2Callback} from './oauth2'
import {Dashboard as PersonalDashboard} from './personal'

export default [
  <IndexRoute key="auth.index" component={Index}/>,
  <Route key="auth.home" path="home" component={Index}/>,
  <Route key="auth.about_us" path="about-us" component={AboutUs}/>,
  <Route key="auth.oauth2/callback" path="oauth2/callback" component={Oauth2Callback}/>,
  <Route key="auth.personal/dashboard" path="personal/dashboard" component={PersonalDashboard}/>
]
