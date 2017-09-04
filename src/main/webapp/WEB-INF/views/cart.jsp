<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tags:template>
	<jsp:attribute name="header">
<style>
.commodity {
	margin: 10px;
	width: 260px;
}

#cart {
	margin-top: 100px;
}
</style>
</jsp:attribute>
	<jsp:attribute name="footer">
<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/cart.js"></script>
</jsp:attribute>
	<jsp:body>
  	<div class="container content">
  		<div id="cart" class="row" ng-controller="cart">
  			<ul class="list-group">
  				<li class="list-group-item pull-left commodity"
						ng-repeat="order in list track by $index">
 					<p>商品</p>
        			<div commodity-directive id="{{order.commodityid}}">
        				<p>商品名：{{res.name}}</p>
        				<p>商品描述：{{res.depict}}</p>
        				<p>商品厂商：{{res.manufacturer}}</p>
        				<p>商品价格：{{res.price}}</p>
        				<p>商品logol：<img ng-src={{res.img}} width=50 height=50 />
							</p>
	        			<p>
	        				购买商品个数
		        			<span class="badge">
		        				{{order.commoditycount}}
							</span>

							
							<p style="display: none">{{order.price=res.price}}</p>
	        			</p>
	        			<button class="btn btn-danger"
								ng-click="comCount(-1, order.commodityid)">少买一个</button>
	        			<button class="btn btn-warning"
								ng-click="comCount(1, order.commodityid)">再来一个</button>
        			</div>
  				</li>
  			</ul>

  			<div class="row">
				<button class="button button-glow button-border button-rounded button-primary dropdown-toggle">总金额：{{sum}}</button>
			</div>
  			<div class="row">
	  			<label class="clearfix">
	  				输入用户地址
	  				<input type="text" class="form-control" id="address">
	  			</label>
	  			<br>
	  			<label class="clearfix">
					输入收货人手机
	  				<input type="number" class="form-control" id="phone">
	  			</label>
	  			<br>
	  			<button id="submit" class="btn btn-success">
					提交信息
				</button>
				</div>
  		</div>
  	</div>
</jsp:body>
</tags:template>