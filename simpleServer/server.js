const express = require('express');
const simpleServer = express();
const portNumber = 8088;

simpleServer.get('/api/v1/hello', (req, resp) => {
    var respPayload = {
        message: 'Hello World !'
    }

    resp.send(respPayload);
});

simpleServer.listen(portNumber, () => {
    console.log('Simple Server up: listening port ' + portNumber);
});
