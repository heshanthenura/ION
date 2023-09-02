const userList = document.getElementById("online-list")
var id = document.currentScript.getAttribute('id');
var name = document.currentScript.getAttribute('name');

var socket = new SockJS('/websocket');
stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {

    console.log('Connected: ' + frame);

    stompClient.subscribe('/topic/connect', function(data) {
        const p = document.createElement("p")
        p.textContent = data.body
        p.setAttribute("id",data.body)
        userList.appendChild(p)
        console.log("connected "+data)
    });

    stompClient.subscribe('/topic/disconnect', function(data) {
        const p = document.getElementById(data.body)
        p.remove();
        console.log("disocnnected "+data)
    });

    stompClient.subscribe('/topic/users/'+id, function(data) {
        var users = JSON.parse(data.body);
        alert(users)
        users.forEach(function(user) {
            const p = document.createElement("p")
            p.textContent = user.uuid
            p.setAttribute("id",user.uuid)
            userList.appendChild(p)
            console.log(user.uuid);
        });
    });

    stompClient.send("/app/connect",{},id);
    stompClient.send("/app/users",{},id);

});

window.addEventListener("beforeunload", function (event) {
    stompClient.send("/app/disconnect",{},id);
});