$("#loginForm").submit(function(event) {
  event.preventDefault();

  $.ajax({
    url: "http://localhost:9090/api/auth/login",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify({
      username: $("#username").val(),
      password: $("#password").val()
    }),
    success: function(response) {
      localStorage.setItem("token", response.jwtToken);
      localStorage.setItem("user", response.username);
      window.location.href = "/private/orders.html";
    },
    error: function() {
      alert("Credenciales incorrectas");
    }
  });
});