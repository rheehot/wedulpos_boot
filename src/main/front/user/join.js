import './login.scss';
import Common from 'common';
import $ from 'jquery';
import '../header/header.js';
import Spinner from 'spin';

// validate 필요한거
import 'bootstrap';
import 'validate';
import 'jquery-validate';

let isCheckEmail = false;
let initialTime;
let checkTimeout;
const $emailCheckBtn = $('#emailCheckBtn');
const $otpCheckBtn = $('#otpCheckBtn');
const $joinEmail = $('#joinEmail');
const $joinOtpBox = $('#joinOtpBox');
const $otpTimeOut = $('#otpTimeOut');
const $otpNum = $('#otpNum');
const $password = $('#password');
const $passwordCheck = $('#passwordCheck');
const $joinBtn = $('#joinBtn');
const $joinForm = $('#joinForm');

$.validator.addMethod('passwordChkVal', function(value /*,element*/) {
  return value && Common.isContainUpperCase(value) && value.length > 7;
});

$.validator.addMethod('passwordReChkVal', function(value /*,element*/) {
  return value && Common.isContainUpperCase(value) && value.length > 7 && $password.val() == $passwordCheck.val();
});

// join btn
$joinBtn.click(() => {
  if (!isCheckEmail) {
    alert(Common.getMessage('user.join.message.checkEmail'));
    return;
  }

  if (!$password.val() || !$passwordCheck.val()) {
    alert(Common.getMessage('user.join.message.checkPassword'));
    return;
  }

  if ($password.val() != $passwordCheck.val()) {
    alert(Common.getMessage('user.join.message.checkPassword'));
    return;
  }

  // 회원가입 요청
  Common.sendAjax({
    url: Common.getFullPath('user/join'),
    param: $joinForm.serialize(),
    type: 'POST',
    success: () => {
      Common.pageMove('');
    },
    failed: () => {
      alert(Common.getMessage('common.message.fail'));
    }
  });
});

// joinForm validate
$('#joinForm').validate({
  rules: {
    email: {email:true, required:true},
    password: {passwordChkVal:true, required:true},
    passwordCheck: {passwordReChkVal:true, required:true}
  },
  messages: {
    email: Common.getMessage('user.join.message.checkEmail'),
    password: Common.getMessage('user.join.message.password'),
    passwordCheck: Common.getMessage('user.join.message.passwordCheck')
  },
  tooltip_options: {
    email: {placement:'right',html:true, trigger:'focus'},
    password: {html:true, trigger:'focus'},
    passwordCheck: {placement:'right',html:true, trigger:'focus'}
  }
});

// email 확인 버튼 클릭
$emailCheckBtn.click(() => {
  if (!Common.emailValidate($joinEmail.val())) {
    alert(Common.getMessage('user.join.message.validate_email'));
    return;
  }

  if (isCheckEmail) {
    alert(Common.getMessage('user.join.message.already_cert'));
    return;
  }

  var spinner = new Spinner({color:'#000', lines: 12}).spin(document.getElementById('joinForm'));

  // 이메일 체크 요청
  Common.sendAjax({
    url: Common.getFullPath('user/email'),
    param: { 'email' : $joinEmail.val() },
    type: 'POST',
    success: (e) => {
      if (e && e.length) {
        alert(e);
        spinner.stop();
      } else {
        alert(Common.getMessage('user.join.message.successEmail'));
        $joinOtpBox.removeClass('displayNone');
        checkOtpTimeOut();
        spinner.stop();
      }
    },
    failed: () => {
      alert(Common.getMessage('common.message.fail'));
      spinner.stop();
    }
  });
});

// otp timeout check
var checkOtpTimeOut = function() {
  initialTime = 180;
  $otpTimeOut.text('03:00');

  checkTimeout = setInterval(function() {
    if (initialTime <= 0) {
      clearInterval(checkTimeout);
      isCheckEmail = false;
      alert(Common.getMessage('user.join.message.endOtpTime'));
      $joinOtpBox.addClass('displayNone');
    }

    var min = parseInt(initialTime / 60);
    var sec = parseInt(initialTime % 60);

    min = min <= 0 ? '00:' : '0' + min + ':';
    sec = sec < 10 ? '0' + new String(sec) : sec;

    $otpTimeOut.text(min + sec);
    initialTime--;
  }, 1000);
};

// 인증번호 확인
$otpCheckBtn.click(() => {

  // 인증번호 리턴
  if (!$otpNum.val()) {
    alert(Common.getMessage('user.join.message.checkOtp'));
    return;
  }

  // 인증서 확인 요청
  Common.sendAjax({
    url: Common.getFullPath('user/cert/check'),
    param: { 'otp' : $otpNum.val() },
    type: 'POST',
    success: (e) => {
      if (Common.isSuccess(e)) {
        alert(Common.getMessage('user.login.message.success_otp'));
        $joinOtpBox.addClass('displayNone');
        isCheckEmail = true;
        clearInterval(checkTimeout);
      } else {
        alert(Common.getMessage('user.login.message.wrong_otp'));
        isCheckEmail = false;
      }
    },
    failed: () => {
      alert(Common.getMessage('common.message.fail'));
      isCheckEmail = false;
    }
  });
});
