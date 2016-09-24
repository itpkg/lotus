export function ajax (method, action, data, success, failed) {
  console.log(`todo: ${method} ${action}`)
  // action = process.env.CONFIG.backend + action
  // switch (method) {
  //   case 'post':
  //     fetch(action, {method: method, body: data})
  //       .then(function (response) {
  //         return response.json()
  //       }).then(function (result) {
  //         console.log(result)
  //       }).catch(function (e) {
  //         // console.log('aaa')
  //         alert(e)
  //         // console.log(e)
  //       })
  //     break
  //   case 'get':
  //     fetch(action)
  //       .then(function (response) {
  //         return response.json()
  //       }).then(function (result) {
  //         console.log(result)
  //       }).catch(function (e) {
  //         // console.log('aaa')
  //         alert(e)
  //         // console.log(e)
  //       })
  //     break
  //   default:
  //     console.log(`unknown ${method} ${action}`)
  // }
}

export function isSignIn () {}

export function isAdmin () {}

export function onDelete () {}
