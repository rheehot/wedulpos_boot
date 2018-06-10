//import Common from 'common';
import '../header/header.js';
import $ from 'jquery';
import SockJS from 'sockjs-client';
import Stomp from '@stomp/stompjs';

const $chatBox = $('.chat_box');
const $messageInput = $('#messageBox');
const $sendBtn = $('.send');

const sock = new SockJS('/stomp-chat');
const client = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.

// 2. connection이 맺어지면 실행된다.
client.connect({}, function () {
  // 3. send(path, header, message)로 메시지를 보낼 수 있다.
  client.send('/publish/chat/join', {}, JSON.stringify({chatRoomId: $('.content').data('room-id'), writer: $('.content').data('member')}));
  // 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
  client.subscribe('/subscribe/chat/room/' + $('.content').data('room-id'), function (chat) {
    let content = JSON.parse(chat.body);
    $chatBox.append('<li>' + content.message + '(' + content.writer + ')</li>');
  });
});

$sendBtn.click(function () {
  let message = $messageInput.val();
  client.send('/publish/chat/message', {}, JSON.stringify({chatRoomId: $('.content').data('room-id'), message: message, writer: $('.content').data('member')}));
  $messageInput.val('');
});
