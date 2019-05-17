<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat Room</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <style>
        .list-friend :hover{
            cursor: pointer;
            color: #18f;
        }
    </style>
</head>
<body>
<h2>Chat WebSocket || <a href="<%=request.getContextPath()%>/logout">Logout</a></h2>
<div  style="float: left; width: 20%;">
    <h4>Friends:</h4>
    <ul id="list-friend" class="list-group list-group-flush list-friend">
        <li onclick="chatWith('admin')" class="list-group-item">Admin</li>
        <li onclick="chatWith('home')" class="list-group-item">Home</li>
        <li onclick="chatWith('conan')" class="list-group-item">Conan</li>
        <li onclick="chatWith('kid')" class="list-group-item">Kid</li>
    </ul>
</div>

<div id="chat-frame">
    <h4>Chat:</h4>
    <div id="name"></div>
    <input autofocus onkeypress="sendMessage(event)" id="textMessage" type="text" />
    <input readonly onclick="sendMessage()" value="Send Message" type="button"/> <br/><br/>

    <textarea id="textAreaMessage" rows="10" cols="50"></textarea>
</div>
</body>
<script type="text/javascript">
    //get parameter from url
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    //get username
    const username = getUrlVars()["username"];
    //create websocket
    const websocket = new WebSocket("ws://localhost:8080/websocket/messenger/" + username);
    const messageSend = document.getElementById("textMessage");
    const messageWindow = document.getElementById("textAreaMessage");

    class Message{
        constructor(sender, receiver, content) {
            this.sender = sender;
            this.receiver = receiver;
            this.content = content;
        }
    }

    websocket.onopen = function() {
        //status.value = "Connected";
        // alert(websocket.sessionId);
    };
    websocket.onmessage = function(message) {
        var object = JSON.parse(message.data);
        console.log(object);

        if(object.content === "online"){
            appendOnlineFriend(object.sender, "");
        } else if(object.content === "offline"){
            removeOnlineFriend(object.sender, "");
        } else {
            messageWindow.value += object.sender + ": " + object.content + "\n";
        }
    };
    websocket.onclose = function() {
        messageWindow.value = "Server disconnected";
    };
    websocket.onerror = function(message) {
        messageWindow.value += "Error: " + message + "\n";
    };

    function sendMessage() {
        if (typeof websocket != 'undefined' && websocket.readyState === WebSocket.OPEN && messageSend.value.length > 0) {
            websocket.send(messageSend.value);
            messageSend.value = "";
        } else {
            console.log("State of websocket is not open");
        }
    }

    function sendMessage(event) {
        if (event.keyCode === 13){
            if (typeof websocket != 'undefined' && websocket.readyState === WebSocket.OPEN && messageSend.value.length > 0) {
                websocket.send(messageSend.value);
                messageSend.value = "";
            } else {
                console.log("State of websocket is not open");
            }
        }
    }

    function chatWith(username) {
        var user = document.getElementsByName("username");
        var message;

        //delete message window
        messageWindow.value = "";

        message = "user-" + username;

        if (typeof websocket != 'undefined' && websocket.readyState === WebSocket.OPEN) {
            websocket.send(message);
        }
    }

    function appendOnlineFriend(newusername, name){
        const container = document.getElementById("list-friend");
        const element = document.createElement('li');

        element.className = "list-group-item";
        element.id = newusername;
        element.textContent = newusername;

        element.addEventListener('click', () => {
            var user = document.getElementsByName("username");
            var message;

            //delete message window
            messageWindow.value = "";

            message = "user-" + newusername;

            if (typeof websocket != 'undefined' && websocket.readyState === WebSocket.OPEN) {
                websocket.send(message);
            }
        })

        container.appendChild(element);
    }

    function removeOnlineFriend(username, name){
        var li = document.getElementById(username);

        li.parentNode.removeChild(li);
    }

    $( document ).ready(function() {
        console.log( "ready!" );
    });
</script>
</html>

<!--    function sendMessage() {-->
<!--        if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {-->
<!--            var message = new Message("hoangan", "hoaian", "Hello");-->
<!--            websocket.send(JSON.stringify(message))-->
<!--            messageSend.value ="";-->
<!--        }-->
<!--    }-->