$( document ).ready(function() {
    console.log( "ready!" );
    //alert(sessionStorage.getItem("username"));

    clearMessage();

    scrollToLastContent();
});

class User {
    constructor(username, fullname){
        this.username = username;
        this.fullname = fullname;
    }
}

class Message {
    constructor(sender, receiver, content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}

/**
 * get parameter from url
 */
const getUrlVars = () => {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
};

//get username
const username = getUrlVars()["username"];
//save user data to session storage
sessionStorage.setItem("username", username);
//create websocket
const websocket = new WebSocket("ws://localhost:8080/websocket/messenger/" + username);

websocket.onopen = function() {
    // var message = "user-home";
    //
    // if (typeof websocket != 'undefined' && websocket.readyState === WebSocket.OPEN) {
    //     websocket.send(message);
    // }
};
websocket.onmessage = function(message) {
    var messObj = JSON.parse(message.data);
    console.log(messObj);

    if(messObj.content === "online"){
        appendOnlineFriend(messObj);
    } else if(messObj.content === "offline") {
        removeOfflineFriend(messObj);
    } else {
        appendMessage(event, messObj);
    }
};
websocket.onclose = function(){
   
};
websocket.onerror = function(message){
    
};

/**
 * append friend when they online
 * @param {Message} messObj 
 */
function appendOnlineFriend(messObj){
    var container = document.getElementById("list-friend");
    var element = document.createElement('li');

    element.className = "list-group-item";
    element.id = messObj.sender;
    element.textContent = messObj.sender;

    element.addEventListener('click', () => {
        //delete message window
        clearMessage();

        var message = "user-" + messObj.sender;

        if (typeof websocket != 'undefined' 
        && websocket.readyState === WebSocket.OPEN) {
            websocket.send(message);
        }
    });

    container.appendChild(element);
}

/**
 * clear message in chat-frame
 */
function clearMessage(){
    $('div#msg-frame').find('div').remove();
}

/**
 * remove friend when he/she offline
 * @param {Message} messObj 
 */
function removeOfflineFriend(messObj){
    var li = document.getElementById(messObj.sender);

    li.parentNode.removeChild(li);
}

/**
 * append message to chat-frame and send to server
 * type == 'send' or 'receive'
 * @param {Event} event
 * @param {Message} messObj 
 */
function appendMessage(event, messObj){
    var msgFrame = document.getElementById("msg-frame");
    var p = document.createElement('p');
    var div = document.createElement('div');
    var element = document.createElement('div');

    if (messObj.sender === username){
        // //get value in input form to append
        // messObj = new Message('', '', messObj.content);
        //
        // //send message object to server
        // sendMessage(messObj);
        div.className = "sent-msg";
    } else {
        div.className = "received-msg";
    }

    p.textContent = messObj.content;
    div.appendChild(p);

    //set class for element
    if (messObj.sender === username){
        element.className = "sent-chats";
    } else {
        element.className = "received-chats";
    }

    element.appendChild(div);
    msgFrame.appendChild(element);
    //clear input form
    document.getElementById("msg-input").value = "";

    scrollToLastContent();
}

// /**
//  * Send message to server
//  * @param {Message} messObj
//  */
// function sendMessage(messObj){
//     if(typeof websocket != 'undefined'
//         && websocket.readyState === WebSocket.OPEN//kiem tra socket co mo ko
//         && messObj.content.length > 0){//neu tin nhan la rong thi khong gui
//         websocket.send(messObj.content);
//         //websocket.send(JSON.stringify(messObj));
//     }
// }

// /**
//  * Send message to server
//  * @param {Event} event
//  */
function sendMessage(event) {
    if (event.keyCode === 13){
        var message = document.getElementById('msg-input');

        if(typeof websocket != 'undefined'
            && websocket.readyState === WebSocket.OPEN//kiem tra socket co mo ko
            && message.value.length > 0) {//neu tin nhan la rong thi khong gui
            websocket.send(message.value);

            message.value = "";
        }
    }
}

/**
 *
 */
function scrollToLastContent() {
    var scroll = document.getElementById("msg-frame");

    scroll.scrollTop = scroll.scrollHeight;
}