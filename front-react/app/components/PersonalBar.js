import React, {PropTypes} from 'react'
import i18next from 'i18next'
import {NavDropdown, MenuItem} from 'react-bootstrap'
import {connect} from 'react-redux'
import {IndexLinkContainer} from 'react-router-bootstrap'
import {browserHistory} from 'react-router'

import {signOut} from '../engines/auth/actions'
import {isSignIn, ajax} from '../utils'

const Widget = React.createClass({
  render () {
    const {user, onSignOut} = this.props

    return isSignIn(user)
          ? (
              <NavDropdown title={i18next.t('auth.welcome', {name: user.sub})} id="personal-bar">
                  <IndexLinkContainer to='/personal/dashboard'>
                      <MenuItem>{i18next.t('auth.dashboard')}</MenuItem>
                  </IndexLinkContainer>
                  <MenuItem divider/>
                  <MenuItem onClick={onSignOut}>{i18next.t('auth.sign_out')}</MenuItem>
              </NavDropdown>
          )
          : (
              <NavDropdown title={i18next.t('auth.users.sign_in_or_up')} id="personal-bar">
                <IndexLinkContainer to='/users/sign-in'>
                  <MenuItem>{i18next.t('auth.users.sign_in')}</MenuItem>
                </IndexLinkContainer>
                <IndexLinkContainer to='/users/sign-up'>
                  <MenuItem>{i18next.t('auth.users.sign_up')}</MenuItem>
                </IndexLinkContainer>
                <IndexLinkContainer to='/users/forgot-password'>
                  <MenuItem>{i18next.t('auth.users.forgot_password')}</MenuItem>
                </IndexLinkContainer>
                <IndexLinkContainer to='/users/confirm'>
                  <MenuItem>{i18next.t('auth.users.confirm')}</MenuItem>
                </IndexLinkContainer>
                <IndexLinkContainer to='/users/unlock'>
                  <MenuItem>{i18next.t('auth.users.unlock')}</MenuItem>
                </IndexLinkContainer>
              </NavDropdown>
          )
  }
})

Widget.propTypes = {
  user: PropTypes.object.isRequired,
  info: PropTypes.object.isRequired,
  onSignOut: PropTypes.func.isRequired
}

export default connect(state => ({user: state.currentUser, info: state.siteInfo}), dispatch => ({
  onSignOut: function () {
    ajax('delete', '/users/signOut', null, function () {
      dispatch(signOut())
      browserHistory.push('/')
    })
  }
}))(Widget)
