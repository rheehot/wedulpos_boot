<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container" id="loginContainer">
  <form id="loginForm" class="form-signin" onsubmit="return false;" method="post">
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <h2 class="form-signin-heading">WedulPos</h2>
    <br>
    <label for="inputEmail" class="sr-only"><spring:message code="user.login.message.email"/></label>
    <input type="email" id="email" name="id" class="form-control" placeholder="<spring:message code="user.login.message.email"/>" required="">
    <label for="inputPassword" class="sr-only"><spring:message code="user.login.message.password"/></label>
    <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message code="user.login.message.password"/>" required="">
    <button id="loginBtn" class="loginBtn btn btn-lg btn-primary btn-block" type="submit"><spring:message code="user.login.message.login"/></button>
    <button id="faceBookLogin" class="loginBtn btn btn-lg btn-primary btn-block"><i class="fab fa-facebook"></i><span> <spring:message code="user.login.message.facebook"/></span></button>
    <br>
    <div class="login-help">
      <a href="/user/join" class="signup"><spring:message code="user.login.message.join"/></a>
      <a href="/user/findpw" class="last"><spring:message code="user.login.message.findpw"/></a>
    </div>
  </form>
</div>

<script src="/dist/user/login.js"></script>
