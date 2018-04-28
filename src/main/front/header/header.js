import './header.scss';
import $ from 'jquery';
import Common from 'common';
import '../common/baselayout.js';

const $headerLogo = $('#headerLogo');
const $loginButton = $('.loginButton');
const $logoutButton = $('#logoutBox');

// weather location
const weatherLocations = [
  {'common.message.location.seoul' : '1835848'},
  {'common.message.location.incheon' : '1843561'},
  {'common.message.location.daejeon' : '1835235'},
  {'common.message.location.jeju' : '1846266'},
  {'common.message.location.busan' : '1838524'},
  {'common.message.location.gwangju' : '1841811'}
];
const $weatherMessage = $('#weatherMessage');
const $weatherIcon = $('#weatherIcon');
let count = 0;

const token = $("meta[name='_csrf']").attr("content");
const token_header = $("meta[name='_csrf_header']").attr("content");

// 날씨 표시
const weatherDisplay = () => {
  Common.sendAjax({
    url: Common.getFullPath('weather'),
    type: 'POST',
    success: (e) => {
      getWeaterData(e);
      setInterval(function() {
        getWeaterData(e);
      }, 5000);
    }
  });
};

$logoutButton.click(() => {
  if (confirm(Common.getMessage('common.message.account.logout'))) {
    let param = {};
    param[`${token_header}`] = token;

    // 로그아웃
    Common.sendAjax({
      url: Common.getFullPath('user/logout'),
      type: 'POST',
      param,
      success: () => {
        Common.pageMove('');
      },
      failed: () => {
        alert(Common.getMessage('common.message.fail'));
      }
    });
  }
});

// 날씨 데이터 가져오기 함수
const getWeaterData = (data) => {
  $weatherIcon.prop('src', `https://openweathermap.org/img/w/${data[count].icon}.png`);
  $weatherMessage.text(Common.getMessage(Object.keys(weatherLocations[count])[0]) + ' ' + Number(data[count].temp - 273.15).toFixed(2) + ' ℃' );

  if (count >= 5) {
    count = 0;
  } else {
    count++;
  }
};

weatherDisplay();

// header 페이지 이동
$headerLogo.click(function() {
  Common.pageMove('');
});

// 로그인 회원가입 페이지 이동
$loginButton.click(function(e) {
  switch (e.currentTarget.id) {
    case 'loginBox':
      Common.pageMove('user/login');
      break;
    case 'joinBox':
      Common.pageMove('user/join');
      break;
    default:
      break;
  }
});
