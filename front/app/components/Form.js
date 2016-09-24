import React, {PropTypes} from 'react'
import {connect} from 'react-redux'
import {
  Form,
  Col,
  FormGroup,
  Button,
  ControlLabel,
  FormControl
} from 'react-bootstrap'
import i18next from 'i18next'

import {post} from '../ajax'

const Widget = React.createClass({
  getInitialState () {
    const {fields} = this.props
    var data = fields.reduce(function (obj, fld) {
      var o = {}
      o[fld.id] = fld.value
      return Object.assign(obj, o)
    }, {})
    // console.log(data)
    return data
  },
  handleChange: function (e) {
    // console.log(this.state)
    var o = {}
    o[e.target.id] = e.target.value
    this.setState(o)
  },
  handleSubmit: function (e) {
    e.preventDefault()
    const {action, fields, submit} = this.props
    var state = this.state
    var data = fields.reduce(function (obj, fld) {
      var o = {}
      o[fld.id] = state[fld.id]
      return Object.assign(obj, o)
    }, {})
    post(action, data, submit)
  },
  render () {
    const {title, fields} = this.props
    return <fieldset>
      <legend>{title}</legend>
      <Form horizontal onSubmit={this.handleSubmit}>
      {fields.map(function (field) {
        switch (field.type) {
          case 'text':
            return (<FormGroup key={field.id} controlId={field.id}>
              <Col componentClass={ControlLabel} sm={2}>
                {field.label}
              </Col>
              <Col sm={10}>
                <FormControl type="text" placeholder={field.placeholder} />
              </Col>
            </FormGroup>)
          case 'email':
            return (<FormGroup key={field.id} controlId={field.id}>
              <Col componentClass={ControlLabel} sm={2}>
                {field.label}
              </Col>
              <Col sm={8}>
                <FormControl value={this.state.value} onChange={this.handleChange} type="email" placeholder={field.placeholder} />
              </Col>
            </FormGroup>)
          case 'password':
            return (<FormGroup value={this.state.value} onChange={this.handleChange} key={field.id} controlId={field.id}>
              <Col componentClass={ControlLabel} sm={2}>
                {field.label}
              </Col>
              <Col sm={6}>
                <FormControl type="password" placeholder={field.placeholder} />
              </Col>
            </FormGroup>)
          default:
            console.log('unknown type ' + field.type)
            return <input key={field.id} type='hidden' value={field.value}/>
        }
      }.bind(this))}

      <FormGroup>
        <Col sm={10} smOffset={2}>
          <Button type="submit">
            {i18next.t('buttons.submit')}
          </Button>
        </Col>
      </FormGroup>
      </Form>
    </fieldset>
  }
})

Widget.propTypes = {
  title: PropTypes.string.isRequired,
  action: PropTypes.string.isRequired,
  submit: PropTypes.func.isRequired,
  fields: PropTypes.array.isRequired
}

export default connect(state => ({}), dispatch => ({}))(Widget)
