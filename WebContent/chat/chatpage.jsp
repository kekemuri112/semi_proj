<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
</head>
<body onload="getMessages();">
<div id="content">
  <% if (application.getAttribute("messages") != null) {%>
  <%= application.getAttribute("messages")%>
  <% }%>
</div>
<form>
     <input type="text" id="message" name="message" />
      <input type="button" onclick="postMessage();" value="SHOUT" />
</form>
<script>
    function postMessage() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "shoutServlet?t="+new Date(), false);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var users_id = escape('${users_id }');
        var messageText = escape(document.getElementById("message").value);
        document.getElementById("message").value = "";
        xmlhttp.send("name="+users_id+"&message="+messageText);
    }
    var messagesWaiting = false;
    function getMessages(){
        if(!messagesWaiting){
            messagesWaiting = true;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange=function(){
                if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                    messagesWaiting = false;
                    var contentElement = document.getElementById("content");
                    contentElement.innerHTML = xmlhttp.responseText + contentElement.innerHTML;
                }
            }
            xmlhttp.open("GET", "shoutServlet?t="+new Date(), true);
            xmlhttp.send();
        }
    }
    setInterval(getMessages, 1000);
</script>
</body>
</html>