<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="hp100 content" data-room-id="1" data-member="wedul">
	<div id="messageTargetSearch">
		<div id="messageTargetLabel">
			<label for="messageTargetInput"><spring:message code="message.title.user"/></label>
		</div>
		<input id="messageTargetInput" class="form-control" type="text" placeholder="Search..">
	</div>
  <div id="messageArea">
    <div id="messageTextBox">
      <ul class="chatBox">
      </ul>
    </div>

    <div id="messageTargetInputBox">
      <textarea id="messageBox" rows="4" cols="50" class="form-control">
      </textarea>
      <button id="messageBoxBtn" class="btn btn-lg btn-primary btn-block send">보내기</button>
    </div>
  </div>

</div>
<script src="/dist/message/message.js"></script>
