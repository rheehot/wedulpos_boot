<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container" id="loginContainer">
  <form id="findForm" class="form-signin" onsubmit="return false;" method="post">
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <h2 class="form-signin-heading"><spring:message code="user.login.message.sendTempPw"/></h2>
    <span id="findPwSubMsg"><spring:message code="user.login.message.findsubMsg"/></span>
    <br>
    <br>
    <div>
      <label for="inputEmail" class="sr-only"><spring:message code="user.login.message.findpw"/></label>
      <input type="email" id="email" name="email" class="form-control findEmail" placeholder="<spring:message code="user.login.message.email"/>" required="">
      <button id="findPw" class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="user.login.message.send"/></button>
    </div>
  </form>
</div>

<script src="/dist/user/findpw.js"></script>
