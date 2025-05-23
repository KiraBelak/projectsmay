import http from './fetch.js';

function login(e) {
  let errorMessage = document.getElementById("errorMessage");
  let username = document.getElementById("floatingInput");
  let email = document.getElementById("emailInput");
  let password = document.getElementById("floatingPassword");

  errorMessage.style.display = "none";
  http.post('/auth/register', {name:username.value||'',email:email.value||'',password:password.value||''})
    .then(response => response.json())
    .then(response => {
      if (!response.token) throw new Error(response.message || 'Unknown error');
      location.href = "http://devpac06:8080/login.html";
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
