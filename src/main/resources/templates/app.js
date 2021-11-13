function sendName() {
    stompClient.send("/app/pull", {}, JSON.stringify({'name': 'Test'}));
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/result', function (greeting) {
                    var imgString = JSON.parse(greeting.body).content[0]
                    $("#ls0").append('<img src="/images/'+imgString+'"/>')
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    //setConnected(false);
    console.log("Disconnected");
}
