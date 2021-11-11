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
                    //showGreeting(JSON.parse(greeting.body).content);
                    var imgString = JSON.parse(greeting.body).content
                   // $("#i0").attr('src', "/images/"+imgString);
                    $("#ls0").append('<img src="/images/'+imgString+'"/>')
                    //var img = document.createElement("img")
                   // img.setAttribute('src',"/images/"+imgString);
                   // document.getElementById("ls0").append(img)

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
