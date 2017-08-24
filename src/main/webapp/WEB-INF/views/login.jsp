<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<h1>请先登录</h1>
	<script type="text/javascript">
		setTimeout(location.href="index", 100000 );
	</script>
  <body>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/login.js"></script>
  </body>
</html>
