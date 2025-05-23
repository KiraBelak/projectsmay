$(document).ready(function () {
    let token = localStorage.getItem("token");
    let usuarioLogueado = localStorage.getItem("user");
    let ordenesTemporales = [];

    if (!token) {
        alert("Necesitas iniciar sesión primero");
        window.location.href = "/auth/login.html";
    } else {
        $("#currentUser").text("User: " + usuarioLogueado);
        $.ajax({
            url: "http://localhost:9090/api/public/menu",
            type: "GET",
            headers: { Authorization: "Bearer " + token },
            success: function (menus) {
                let tbody = $("#menuTableBody");
                tbody.empty();

                menus.forEach(menu => {
                    let nombreCompleto = `${menu.category.name} - ${menu.description}`;

                    tbody.append(`
                        <tr>
                            <td>${nombreCompleto}</td>
                            <td>${menu.restaurant.name}</td>
                            <td>$${menu.price.toFixed(2)}</td>
                            <td><input type="number" class="cantidad-input" data-id="${menu.id}" data-price="${menu.price}" min="1" value="1"></td>
                            <td><button class="add-btn" data-id="${menu.id}" data-price="${menu.price}" data-name="${nombreCompleto}">Agregar</button></td>
                        </tr>
                    `);
                });
            },
            error: function () {
                alert("Error al obtener el menú, verifica tu sesión");
                localStorage.removeItem("token");
                window.location.href = "login.html";
            }
        });

        $("#searchMenu").on("keyup", function () {
            let value = $(this).val().toLowerCase();

            $("#menuTableBody tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
            });
        });

        let socket = new SockJS("/websocket");
        let stompClient = Stomp.over(socket);

        stompClient.connect({}, function () {
            console.log("Conectado a WebSocket");
            stompClient.subscribe("/topic/ordenes", function (message) {
                let nuevaOrden = JSON.parse(message.body);
                if (nuevaOrden.usuario !== usuarioLogueado) {
                    ordenesTemporales.push(nuevaOrden);
                    actualizarTablaOrdenes();
                }
            });
            stompClient.subscribe("/topic/removeOrder", function (message) {
                let ordenEliminada = JSON.parse(message.body);
                ordenesTemporales = ordenesTemporales.filter(orden => orden.orden !== ordenEliminada.orden || orden.usuario !== ordenEliminada.usuario);
                actualizarTablaOrdenes();
            });
        });

        $(document).on("click", ".add-btn", function () {
            let itemName = $(this).data("name");
            let itemPrice = $(this).data("price");
            let cantidad = $(this).closest("tr").find(".cantidad-input").val();
            let total = (cantidad * itemPrice).toFixed(2);

            let nuevaOrden = {
                usuario: usuarioLogueado,
                orden: itemName,
                cantidad: cantidad,
                total: total
            };

            ordenesTemporales.push(nuevaOrden);
            actualizarTablaOrdenes();
            stompClient.send("/app/newOrder", {}, JSON.stringify(nuevaOrden));
        });

        function actualizarTablaOrdenes() {
            let tbody = $("#orderTableBody");
            tbody.empty();
            let totalGeneral = 0;
            let usuarioLogueado = localStorage.getItem("user");

            ordenesTemporales.forEach((orden, index) => {
                totalGeneral += parseFloat(orden.total);
                let esMiOrden = orden.usuario === usuarioLogueado ? "mi-orden" : "";
                let botonEliminar = orden.usuario === usuarioLogueado ? `<button class="delete-btn" data-index="${index}">❌</button>` : "";

                tbody.append(`
            <tr class="${esMiOrden}">
                <td>${orden.usuario}</td>
                <td>${orden.orden}</td>
                <td>${orden.cantidad}</td>
                <td>$${orden.total}</td>
                <td>${botonEliminar}</td>
            </tr>
        `);
            });

            tbody.append(`
        <tr>
            <td colspan="3"><strong>Total General:</strong></td>
            <td><strong>$${totalGeneral.toFixed(2)}</strong></td>
        </tr>
    `);
        }


        $(document).on("click", ".delete-btn", function () {
            let index = $(this).data("index");
            let usuarioLogueado = localStorage.getItem("user");

            if (ordenesTemporales[index].usuario === usuarioLogueado) {
                let ordenEliminada = ordenesTemporales[index];
                ordenesTemporales.splice(index, 1);
                actualizarTablaOrdenes();

                if (stompClient && stompClient.connected) {
                    stompClient.send("/app/removeOrder", {}, JSON.stringify(ordenEliminada));
                }
            } else {
                alert("No puedes eliminar pedidos de otros usuarios.");
            }
        });

        $("#confirmOrder").click(function () {
            if (ordenesTemporales.length === 0) {
                alert("No hay pedidos en la lista.");
                return;
            }

            $.ajax({
                url: "https://tu-api.com/ordenes",
                type: "POST",
                contentType: "application/json",
                headers: { Authorization: "Bearer " + token },
                data: JSON.stringify(ordenesTemporales),
                success: function () {
                    alert("Pedido confirmado con éxito");
                    ordenesTemporales = [];
                    actualizarTablaOrdenes();
                },
                error: function () {
                    alert("Error al confirmar el pedido.");
                }
            });
        });
    }
});
