import React from 'react'
import ReactDOM from 'react-dom'
import { createStore, combineReducers } from 'redux'
import { Provider } from 'react-redux'
import { Router, Route, browserHistory } from 'react-router'
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'

console.log('react version: ' + React.version)
console.log('lotus version: ' + process.env.CONFIG.version)

import root from './engines'
import Layout from './components/Layout'
import NoMatch from './components/NoMatch'

const reducers = root.reducers()
const store = createStore(
  combineReducers({
    ...reducers,
    routing: routerReducer
  })
)

const history = syncHistoryWithStore(browserHistory, store)

export default function (id) {
  ReactDOM.render(
    <Provider store={store}>
      <Router history={history}>
        <Route path="/" component={Layout}>
          {root.routes()}
          <Route path="*" component={NoMatch}/>
        </Route>
      </Router>
    </Provider>,
    document.getElementById(id)
  )
}