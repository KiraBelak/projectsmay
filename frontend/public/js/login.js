import http from './fetch.js';

function login(e) {
  let errorMessage = document.getElementById("errorMessage");
  let username = document.getElementById("floatingInput");
  let password = document.getElementById("floatingPassword");

  errorMessage.style.display = "none";
  http.post('/auth/login', {username:username.value||'',password:password.value||''})
    .then(response => response.json())
    .then(response => {
      if (!response.token) throw new Error(response.message || 'Unknown error');
      localStorage.setItem("token", response.token);
      localStorage.setItem("userId", response.userId);
      localStorage.setItem("name", username.value);
      location.href = "http://devpac06:8080/";
    })
    .catch(err => {
       errorMessage.innerHTML = err;
       errorMessage.style.display='block';
    });
    e.preventDefault();
}

document.body.onload = () => {
  document.querySelector("form").addEventListener("submit", login);
};
