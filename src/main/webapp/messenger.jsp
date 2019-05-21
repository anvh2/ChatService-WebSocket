<!DOCTYPE html>
<html>
<head>
	<title>Messenger</title>
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="style.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2">
				<div class="container-recent">
					
				</div>
			</div>
			<div class="col-sm-7">
					<div class="container-chat">
						<div class="message-header">
							<div class="active">
								<p>Sherlock Home</p>
							</div>
						</div>
						<div class="chat-frame">
							<div class="msg-inbox">
								<div class="chats">
									<div class="msg-frame" id="msg-frame">
<%--										<div class="received-chats">--%>
<%--											--%>
<%--											<div class="received-msg">--%>
<%--												<p>Hi, my name is Home. I'm a student of University of Science</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Hello</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>My name is An. I'm student in US too. What is your major?</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="received-chats">--%>
<%--											--%>
<%--											<div class="received-msg">--%>
<%--												<p>My major is Infomation Technology, about you?</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Oh shit, me too.</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Awesome, where are you now? Can I met you?</p>--%>
<%--											</div>--%>
<%--										</div>--%>
<%--										--%>
<%--										<div class="received-chats">--%>
<%--											--%>
<%--											<div class="received-msg">--%>
<%--												<p>Right, I'm in canteen.</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Ok, I be there!</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Awesome, where are you now? Can I met you?</p>--%>
<%--											</div>--%>
<%--										</div>--%>
<%--										--%>
<%--										<div class="received-chats">--%>
<%--											--%>
<%--											<div class="received-msg">--%>
<%--												<p>Right, I'm in canteen.</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Ok, I be there!</p>--%>
<%--											</div>--%>
<%--										</div>--%>
<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Awesome, where are you now? Can I met you?</p>--%>
<%--											</div>--%>
<%--										</div>--%>
<%--										--%>
<%--										<div class="received-chats">--%>
<%--											--%>
<%--											<div class="received-msg">--%>
<%--												<p>Right, I'm in canteen.</p>--%>
<%--											</div>--%>
<%--										</div>--%>

<%--										<div class="sent-chats">--%>
<%--											<div class="sent-msg">--%>
<%--												<p>Ok, I be there!</p>--%>
<%--											</div>--%>
<%--										</div>--%>
									</div>
								</div>
							</div>
						</div>
						<div class="write-msg">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="write message..." id="msg-input" onkeypress="sendMessage(event)">
								<div class="input-group-append">
									<span class="input-group-text">
										<i class="fa fa-paper-plane"></i>
									</span>
								</div>
							</div>
						</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="container-friend">
					<ul id="list-friend" class="list-group list-group-flush list-friend">
					</ul>
				</div>
			</div>
		</div>
	</div>
	

</body>

<script type="text/javascript" src="client.js"></script>


</html>