let users = [];

import { Client } from "https://ga.jspm.io/npm:@stomp/stompjs@7.0.0/esm6/index.js";

let surveyId = new URLSearchParams(document.location.search).get("id");

const client = new Client({
  brokerURL: 'ws://devpac06:8081/ws',
  onConnect: onConnected
});

client.activate();

function onConnected() {
    // Suscribirse al canal público
    client.subscribe('/survey/' + surveyId, onMessageReceived);

    // Registrar usuario en el chat
    client.publish({ destination: '/survey/' + surveyId,
        body: JSON.stringify({
            type: 'JOIN',
            name: localStorage.getItem("name"),
        })
    });
}

    // Callback de error en la conexión
    function onError() {
        alert('Error al conectar. Por favor, vuelve a intentarlo.');
    }
        // Manejar mensaje recibido
    function onMessageReceived(payload) {
        console.log(`Received: ${payload.body}`);

        const message = JSON.parse(payload.body);

        // Si es un mensaje de unión, agregar a la lista de usuarios
        if (message.type === 'JOIN') {
            users.push(message.sender);
        }
        // Si es un mensaje de abandono, eliminar de la lista de usuarios
        else if (message.type === 'LEAVE') {
          users.splice(users.indexOf(message.sender), 1);
        }

        // Run external callback
        ws.onMessageReceived(message);
    }

const ws = {
  users,
  client,
  onMessageReceived: () => {},
  channel: ''
};

export default ws;
