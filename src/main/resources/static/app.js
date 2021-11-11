function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': "Fred"}));
    stompClient.send("/app/hello2", {}, JSON.stringify({'name': "Fred"}));
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/response', function (greeting) {
            //showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/res', function (greeting) {
                    //showGreeting(JSON.parse(greeting.body).content);
                    var imgString = JSON.parse(greeting.body).content
                    $("#i0").attr('src', "/images/"+imgString);

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
