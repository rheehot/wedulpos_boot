import './login.scss';
import Common from 'common';
import '../header/header.js';
import $ from 'jquery';
import Spinner from 'spin';

// validate 필요한거
import 'bootstrap';

const $findPw = $('#findPw');
const $findForm = $('#findForm');
const $findEmail = $('.findEmail');

// find form 추가 버튼
$findPw.click(() => {
  if (!Common.emailValidate($findEmail.val())) {
    alert(Common.getMessage('user.join.message.validate_email'));
    return;
  }

  var spinner = new Spinner({color:'#000', lines: 12}).spin(document.getElementById('findForm'));

  // 임시 비밀번호 발급 요청
  Common.sendAjax({
    url: Common.getFullPath('user/send/temppw'),
    param: $findForm.serialize(),
    type: 'POST',
    success: (e) => {
      if (e && e.length) {
        spinner.stop();
        alert(e);
      } else {
        spinner.stop();
        alert(Common.getMessage('user.login.message.findsubMsg'));
        Common.pageMove('login');
      }
    },
    failed: () => {
      spinner.stop();
      alert(Common.getMessage('user.find.message.fail_temp_pw'));
    }
  });
});
