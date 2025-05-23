const baseUrl = "http://devpac06:8081";

function headers() {
  let headers = {'Content-Type': 'application/json'};
  if (localStorage.getItem("token")) {
    headers['Authorization'] = 'Bearer ' + localStorage.getItem("token");
  }
  return headers;
}

function get(url) {
    return fetch(baseUrl + url, {
      method: 'GET',
      headers: headers(),
    }).then(res => {
      if (res.status == 401) {
        localStorage.removeItem("token");
        location.href = "/login.html";
      }
      return res;
    });
}

function post(url, body) {
  return fetch(baseUrl + url, {
      method: 'POST',
      headers: headers(),
      body: JSON.stringify(body)
    }).then(res => {
      if (res.status == 401 && !location.pathname.endsWith('login.html') && !location.pathname.endsWith('register.html')) {
        localStorage.removeItem("token");
        location.href = "/login.html";
      }
      return res;
    });
}

const http = {get, post};

export default http;
