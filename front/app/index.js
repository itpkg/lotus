require('bootstrap/dist/css/bootstrap.css')
require('bootstrap/dist/css/bootstrap-theme.css')
require('./index.css')

import i18next from 'i18next'
import LanguageDetector from 'i18next-browser-languagedetector'

import main from './main'
import {LOCALE} from './constants'

i18next
    .use(LanguageDetector)
    .init({
      detection: {
        order: ['querystring', 'localStorage', 'cookie', 'navigator'],
        lookupQuerystring: LOCALE,
        lookupCookie: LOCALE,
        lookupLocalStorage: LOCALE,

        caches: ['localStorage', 'cookie'],
        cookieMinutes: 365 * 24 * 60
      }
    },
    (_, t) => {
      console.log('lang: ' + i18next.language)
      main('root')
    }
  )
