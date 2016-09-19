// import cms from './cms'
import auth from './auth'

const engines = {
  // cms,
  auth
}

export default {
  routes () {
    return process.env.CHAOS.engines.reduce(function (obj, en) {
      return obj.concat(engines[en].routes)
    }, [])
  },
  reducers () {
    return process.env.CHAOS.engines.reduce(function (obj, en) {
      return Object.assign(obj, engines[en].reducers)
    }, {})
  }
}
