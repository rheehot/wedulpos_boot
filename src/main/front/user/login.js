import './login.scss';
import '../header/header.js';
import $ from 'jquery';
import Common from 'common';

// validate 필요한거
import 'bootstrap';

const $loginForm = $('#loginForm');
const $id = $('#email');
const $password = $('#password');
const $loginBtn = $('#loginBtn');
const $faceBookLoginBtn = $('#faceBookLogin');

// 페이스북 로그인 요청
$faceBookLoginBtn.click(() => {
  FB.login(() => {
    loginFacebookLoginUserInfo();
  }, {scope: 'public_profile,email'});
});

// 로그인한 사용자의 정보 얻기
var loginFacebookLoginUserInfo = function() {
    FB.api('/me', {fields: 'name,email'},  function(response) {
        let param = {};
        param.snsId = response.id;
        param.nickname = response.name;
        param.email = response.email;

        // 사용자 소셜 로그인 요청
        Common.sendAjax({
            url: Common.getFullPath('user/login/facebook'),
            param,
            type: 'POST',
            success: () => {
                Common.pageMove('');
            },
            failed: () => {
                alert(Common.getMessage('user.login.message.checkAccount'));
            }
        });
    });
};

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
