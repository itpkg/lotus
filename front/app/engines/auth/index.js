import reducers from './reducers'
import routes from './routes'

import enUS from './en_US'
import zhCN from './zh_CN'

const engine = {
  reducers: reducers,
  routes: routes,
  locales: {'en-US': enUS, 'zh-CN': zhCN}
}

export default engine
