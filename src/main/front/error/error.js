import './error.scss';
import Common from 'common';
import $ from 'jquery';

const $errorRefreshMsg = $('#errorRefreshMsg');

// 페이지 리로드
Common.setLanguage('error');
$errorRefreshMsg.text(Common.getMessage('error.message.refresh'));


// let refreshPage = setInterval(function() {
//   Common.pageMove();
//   clearInterval(refreshPage);
// }, 2000);
