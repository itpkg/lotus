export const SIGN_IN = 'auth.sign.in'
export const SIGN_OUT = 'auth.sign.out'

export const SITE_REFRESH = 'auth.site.refresh'

export const USER_INFO = 'auth.user.info'

export const NOTICE_LIST = 'auth.notices.list'
export const NOTICE_ADD = 'auth.notices.add'
export const NOTICE_DEL = 'auth.notices.del'

export function signIn (token) {
  return {type: SIGN_IN, token}
}

export function signOut () {
  return {type: SIGN_OUT}
}

export function refresh (info) {
  return {type: SITE_REFRESH, info}
}

export function userInfo (info) {
  return {type: USER_INFO, info}
}

export function listNotice (notices) {
  return {type: NOTICE_LIST, notices}
}

export function addNotice (notice) {
  return {type: NOTICE_ADD, notice}
}

export function delNotice (id) {
  return {type: NOTICE_DEL, id}
}
