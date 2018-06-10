<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container" id="loginContainer">
  <form name="joinForm" id="joinForm" class="form-signin" onsubmit="return false;" method="post">
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <h2 class="form-signin-heading"><spring:message code="user.login.message.join"/></h2>
    <br>

	<!-- �̸��� -->
    <div class="marginBox10">
      <label for="inputJoinNickName" class="sr-only"></label>
      <input type="text" id="joinNickName" style="width: 71%; display:inline-block;" name="nickname" class="form-control" placeholder="<spring:message code="user.login.message.nickName"/>" required="">
      <button type="button" id="nickNameCheckBtn" style="display:inline-block; margin-bottom: 3px;" class="checkbtn btn btn-info"><spring:message code="user.login.message.nickNameCheck"/></button>
    </div>

    <div class="marginBox10">
      <label for="inputJoinEmail" class="sr-only"></label>
      <input type="email" id="joinEmail" style="width: 71%; display:inline-block;" name="email" class="form-control" placeholder="<spring:message code="user.login.message.email"/>" required="">
      <button type="button" id="emailCheckBtn" style="display:inline-block; margin-bottom: 3px;" class="checkbtn btn btn-info"><spring:message code="user.login.message.emailCheck"/></button>
    </div>

    <!-- ������ȣ Ȯ�� -->
    <div id="joinOtpBox" class="joinOtpBox marginBox10 displayNone">
      <label for="inputEmail" class="sr-only"></label>
      <input type="text" id="otpNum" style="width: 54%; display:inline-block;" name="otpNum" class="form-control" placeholder="<spring:message code="user.login.message.cert_number"/>">
      <div class="otpBox text-danger">
      	<span id="otpTimeOut">02:30</span>
      </div>
      <button type="button" id="otpCheckBtn" style="display:inline-block; margin-bottom: 3px;" class="btn btn-info"><spring:message code="user.login.message.cert_check"/></button>
    </div>

    <label for="inputPassword" class="sr-only"><spring:message code="user.login.message.password"/></label>
    <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message code="user.login.message.password"/>" required="">
    <input type="password" id="passwordCheck" name="passwordCheck" class="form-control" placeholder="<spring:message code="user.login.message.passwordCheck"/>" required="">
    <button id="joinBtn" class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="user.login.message.join"/></button>
  </form>
</div>

<script src="/dist/user/join.js"></script>
