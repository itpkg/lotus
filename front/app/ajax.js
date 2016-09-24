export function post (action, data, success, failed) {
  ajax('post', action, data, success, failed)
}

export function get (action, success, failed) {
  ajax('get', action, null, success, failed)
}

function ajax (method, action, data, success, failed) {
  action = process.env.CONFIG.backend + action
  if (!success) {
    success = function (result) {
      console.log(result)
    }
  }
  if (!failed) {
    failed = function (e) {
      alert(e)
    }
  }
  console.log(action)
  fetch(action, {method: method, body: data})
    .then(function (response) {
      return response.json()
    }).then(function (result) {
      success(result)
    }).catch(function (e) {
      if (failed) {
        failed(e)
      } else {
        alert(e)
      }
    })
}
