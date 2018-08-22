import Common from 'common';
import '../header/header.js';
import $ from 'jquery';
import SockJS from 'sockjs-client';
import Stomp from '@stomp/stompjs';
import './message.scss';
import Choices from 'choices';

const $chatBox = $('.chatBox');
const $messageInput = $('#messageBox');
const $sendBtn = $('.send');
const messageTargetInput = document.getElementById('messageTargetInput');
const $messageBox = $('#messageBox');
const $messageTextBox = $('#messageTextBox');

const sock = new SockJS('/stomp-chat');
const client = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.
const choices = new Choices(messageTargetInput, {
  delimiter: ',',
  editItems: true,
  maxItemCount: 1,
  removeItemButton: true,
  addItemText: function(value) {
    return 'Please Enter to add user <b>"' + String(value) + '"</b>';
  },
});

messageTargetInput.addEventListener('addItem', () => {
  //choices.removeItemsByValue(event.detail.value);
  $messageBox.attr('disabled', false);

}, false);

messageTargetInput.addEventListener('removeItem', (event) => {

  if (!confirm(Common.getMessage('message.message.close'))) {
    choices.setValue([
      {value: event.detail.value, label: event.detail.label}
    ]);
  }
  $messageBox.attr('disabled', true);
}, false);

// 2. connection이 맺어지면 실행된다.
client.connect({}, function () {
  // 3. send(path, header, message)로 메시지를 보낼 수 있다.
  client.send('/publish/chat/join', {}, JSON.stringify({chatRoomId: $('.content').data('room-id'), writer: $('.content').data('member')}));
  // 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
  client.subscribe('/subscribe/chat/room/' + $('.content').data('room-id'), function (chat) {
    let content = JSON.parse(chat.body);
    $chatBox.append('<div class="chatMessage">' + content.message + '(' + content.writer + ')</div>');
    $messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
  });
});

$messageBox.keydown((e) => {
  if (e.keyCode == 13) {
    $sendBtn.trigger('click');
  }
});

$sendBtn.click(function () {
  let message = $messageInput.val();
  client.send('/publish/chat/message', {}, JSON.stringify({chatRoomId: $('.content').data('room-id'), message: message, writer: $('.content').data('member')}));
  $messageInput.val('');
});
