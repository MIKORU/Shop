<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template>
<jsp:attribute name="header">
<style>
#admin {
	margin-top: 100px;
}
</style>
</jsp:attribute>
<jsp:attribute name="footer">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>
</jsp:attribute>
<jsp:body >
	<div class="container">
		<div id="admin">
			<div class="row">
			</div>
		</div>
		<br>
		<c:if test="${name!=null}">
			<div id="user" class="row" ng-controller="user">
				<button class="btn btn-success" ng-click="edit=!edit">
					{{edit?"查看用户信息":"更新用户信息"}}</button>
				<br> <br>
				<div ng-show="!edit" ng-init="edit=false">
					<p>用户名:{{user.name}}</p>
					<p>默认收货地址:{{user.address}}</p>
					<p>默认手机:{{user.phone}}</p>
					<p>用户邮箱:{{user.mail}}</p>
					<p>金额: {{user.money}}RMB</p>
				</div>
				<div ng-show="edit">
					<form>
						<div class="form-group">
							<label for="name">用户名</label> <input type="text"
								class="form-control" id="name" placeholder="name"
								ng-model="user.name">
						</div>
						<div class="form-group">
							<label for="password">密码</label> <input type="password"
								class="form-control" id="password" placeholder="Password"
								ng-model="user.password">
						</div>
						<div class="form-group">
							<label for="defaultAddress">默认收货地址</label> <input type="text"
								class="form-control" name="address" id="address"
								ng-model="user.address">
						</div>
						<div class="form-group"> 
							<label for="defaultPhone">默认手机</label> <input type="text"
								class="form-control" name="phone" id="phone"
								ng-model="user.phone">
						</div>
						<div class="form-group">
							<label for="mail">用户邮箱</label> <input type="text"
								class="form-control" name="mail" id="mail" ng-model="user.mail">
							<div>金额: {{user.money}}RMB</div>
						<button id="submit" type="submit" class="btn btn-default">提交</button>
					</form>
				</div>
			</div>
		</c:if>
	</div>
</jsp:body>
</tags:template>