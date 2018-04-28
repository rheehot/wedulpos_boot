import './login.scss';
import '../header/header.js';
import $ from 'jquery';
import Common from 'common';

// validate 필요한거
import 'bootstrap';
import 'validate';
import 'jquery-validate';

const $loginForm = $('#loginForm');
const $id = $('#email');
const $password = $('#password');
const $loginBtn = $('#loginBtn');

// 로그인 요청
$loginBtn.click(() => {
  if (!Common.emailValidate($id.val())) {
    alert(Common.getMessage('user.join.message.checkEmail'));
    return;
  }

  if (!$password.val()) {
    alert(Common.getMessage('user.join.message.checkPasswd'));
    return;
  }

  Common.sendAjax({
    url: Common.getFullPath('user/login'),
    param: $loginForm.serialize(),
    type: 'POST',
    success: () => {
      Common.pageMove('');
    },
    failed: () => {
      alert(Common.getMessage('user.login.message.checkAccount'));
    }
  });
});
