import React, {PropTypes} from 'react'
import {connect} from 'react-redux'
import {
  Tabs,
  Tab,
  ListGroup,
  ListGroupItem,
  Thumbnail,
  Button,
  FormGroup,
  ControlLabel,
  FormControl
} from 'react-bootstrap'

const Widget = React.createClass({
  render (title, fields, method, action, submit) {
    return <fieldset>
      <legend>{title}</legend>
      <form>
      {fields.map(function (field) {
        switch (field.type) {
          case 'text':
            return <FieldGroup
              id={field.id}
              type="text"
              label={field.label}
              placeholder={field.placeholder}
            />
          case 'email':
            return <FieldGroup
              id={field.id}
              type="password"
              label={field.label}
              placeholder={field.placeholder}
            />
          default:
            console.log('unknown type ' + field.type)
            return <input type='hidden' value={field.value}/>
        }
      })}
      </form>
    </fieldset>
  }
})

Widget.propTypes = {
  title: PropTypes.string.isRequired,
  method: PropTypes.string.isRequired,
  action: PropTypes.string.isRequired,
  submit: PropTypes.func.isRequired,
  fields: PropTypes.array.isRequired
}

export default connect(state => ({}), dispatch => ({}))(Widget)
