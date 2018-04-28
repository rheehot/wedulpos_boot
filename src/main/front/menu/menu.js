import Common from 'common';
import $ from 'jquery';
import './menu.scss';

const $wedulManagerBox = $('#wedulManagerBox');
const $naverBlogBox = $('#naverBlogBox');

const $menuA = $('.menuA');
const $menuIcon = $('.menuIcon');

const currentPage = location.href;

// menu
const $todoMenu = $('#todoMenu');
const $scheduleMenu = $('#scheduleMenu');
const $moneyMenu = $('#moneyMenu');

// menu icon
const $moneyMenuIcon = $('#moneyMenuIcon');
const $moneySubMenu = $('#moneySubMenu');
const $subMenuA = $('.subMenuA');

// mone subMenu
const $moneyMonth = $('#moneyMonth');
const $moneyWallet = $('#moneyWallet');
const $moneyStatistic = $('#moneyStatistic');
const $moneySetting = $('#moneySetting');

// 현재 페이지에 따라 메뉴 색생 변경
var selectCurrPageMenu = function() {
  $menuIcon.removeClass('menuSelected');
  $moneyMenuIcon.removeClass('fa-angle-up').addClass('fa-angle-down');
  $subMenuA.removeClass('subMenuSelected');

  if ( currentPage.indexOf('todo') > 0) { // To do list
    $todoMenu.addClass('menuSelected');
  } else if ( currentPage.indexOf('schedule') > 0) { // schedule
    $scheduleMenu.addClass('menuSelected');
  } else if ( currentPage.indexOf('money') > 0) { // 가계부
    $moneyMenu.addClass('menuSelected');
    $moneyMenuIcon.removeClass('fa-angle-down').addClass('fa-angle-up');
    $moneySubMenu.removeClass('displayNone');

    // 가계부 서브메뉴
    if ( currentPage.indexOf('money/month') > 0 ) {
      $moneyMonth.addClass('subMenuSelected');
    } else if ( currentPage.indexOf('money/wallet') > 0 ) {
      $moneyWallet.addClass('subMenuSelected');
    } else if ( currentPage.indexOf('money/statistic') > 0 ) {
      $moneyStatistic.addClass('subMenuSelected');
    } else if ( currentPage.indexOf('money/setting') > 0 ) {
      $moneySetting.addClass('subMenuSelected');
    }
  }
};
selectCurrPageMenu();

// 메뉴 클릭 (url 이동)
$menuA.click(function(e) {
  switch (e.currentTarget.id) {
    case 'todoMenu':
      Common.pageMove('todo');
      break;
    case 'scheduleMenu':
      Common.pageMove('schedule');
      break;
    case 'moneyMenu':
      Common.pageMove('money/month');
    default:
      break;
  }
});

// 서브메뉴 클릭 시 url 이동
$subMenuA.click(function(e) {
  switch (e.currentTarget.id) {
    case 'moneyMonth':
      Common.pageMove('money/month');
      break;
    case 'moneyWallet':
      Common.pageMove('money/wallet');
      break;
    case 'moneyStatistic':
      Common.pageMove('money/statistic');
      break;
    case 'moneySetting':
      Common.pageMove('money/setting');
      break;
    default:
      break;
  }
});

// wedul manager 클릭 시 이동
$wedulManagerBox.click(function() {
  window.open('https://play.google.com/store/apps/details?id=com.wedul.diary', '_blank');
});

// 블로그 클릭 시 이동
$naverBlogBox.click(function() {
  window.open('https://rokking1.blog.me', '_blank');
});

// 메뉴 페이지 이동.
function menuGoPage(path) {
  Common.pageMove(path);
}
