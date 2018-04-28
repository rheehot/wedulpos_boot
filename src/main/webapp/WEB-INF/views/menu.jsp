<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sideBarBox">
  <div id="menuList">
    <!-- TO-DO List -->
  	<div id="todoMenuBox" class="menuBox">
      <h2>
        <a id="todoMenu" class="menuA">
          <i class="fas fa-check-square menuIcon"></i>
          <span class="sidebarMenu"><spring:message code='common.message.todo'/></span>
          <i class="menuClickIcon menuIcon"></i>
        </a>
      </h2>
  	</div>

    <!-- �쇱�� -->
  	<div id="scheduleMenuBox" class="menuBox">
      <h2>
        <a id="scheduleMenu" class="menuA">
          <i class="far fa-calendar-alt menuIcon"></i>
  		    <span class="sidebarMenu"><spring:message code='common.message.schedule'/></span>
          <i id="scheduleMenuIcon" class="menuClickIcon menuIcon"></i>
        </a>
      </h2>
  	</div>

    <!-- 媛�怨�遺� -->
  	<div id="moneyMenuBox" class="menuBox">
      <h2>
        <a id="moneyMenu" class="menuA">
          <span class="fab fa-bitcoin menuIcon"></span>
    	  <span class="sidebarMenu"><spring:message code='common.message.accounts'/></span>
          <i id="moneyMenuIcon" class="menuClickIcon fas fa-angle-down menuIcon"></i>
        </a>
      </h2>
      <div id="moneySubMenu" class="displayNone subMenu">
        <ul>
          <li id="moneyMonth" class="subMenuA"><spring:message code='common.message.account.statistic' /></li>
          <li id="moneyWallet" class="subMenuA"><spring:message code='common.message.account.property' /></li>
          <li id="moneyStatistic" class="subMenuA"><spring:message code='common.message.account.statistic' /></li>
          <li id="moneySetting" class="subMenuA"><spring:message code='common.message.account.setting' /></li>
        </ul>
      </div>
  	</div>
  </div>

  <!-- ���⑤� -->
  <div class="sidebarBottom">
    <div class="sideBottomCon">
      <strong>
        <a>
          <div id="wedulManagerIcon"></div>
          <span id="wedulManagerBox"><spring:message code='common.message.wedulmanager' /></span>
        </a>
      </strong>
      <strong>
        <a>
          <div id="naverIcon"></div>
          <span id="naverBlogBox"><spring:message code='common.message.blog' /></span>
        </a>
      </strong>
    </div>
  </div>
</div>
<script type="text/javascript" src="/dist/menu/menu.js"></script>
