<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template>
<jsp:attribute name="header">
<style>
.list {
	margin: 20px;
}

.margin80 {
	margin-top: 80px;
}
.list pull-left{
	width:10px;
}
</style>
</jsp:attribute>
<jsp:attribute name="footer">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/list.js"></script>
</jsp:attribute>
<jsp:body >
	<div id="form" class="container margin80" ng-controller="form">
		<div class="row">
			<h1>未支付列表</h1>
			<ul class="list-group">
				<li class="list-group-item"
					ng-repeat="item in items track by $index" ng-if="item.pay==0">
					<p>
						<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
						收货人地址：{{item.address}}
					</p>
					<p>
						<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
						收货人手机：{{item.phone}}
					</p>
					<p>
						<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
						商品总价格：{{item.totalprice}}
					</p>
					<div parse id={{item.orderlist}}>
						<div class="list pull-left" ng-repeat="order in  orderlist track by $index">
							<div commodity-directive id="{{order.commodityid}}">
								<p>商品名：{{res.name}}</p>
								<p>商品描述:{{res.depict}}</p>
								<p>商品厂商:{{res.manufacturer}}</p>
								<p>商品价格:{{res.price}}</p>
								<p>
									商品logo:<img ng-src={{res.img}} width=50 height=50 />
								</p>
								<p>
									购买的商品个数: <span class="badge"> {{order.commoditycount}}
									</span>
								</p>
							</div>
						</div>
					</div>

					<button class="btn btn-default" ng-click="pay(item.id)">
						支付</button>
					<button class="btn btn-default" ng-click="del(item.id)">
								取消订单
							</button>
					<div class="clearfix">
					
					</div>
				</li>
			</ul>

			<br>
			<h1>已支付列表</h1>
			<ul class="list-group">
				<li class="list-group-item"
					ng-repeat="item in items track by $index" ng-if="item.pay==1">
					<p>
						<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
						收货人地址：{{item.address}}
					</p>
					<p>
						<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
						收货人手机：{{item.phone}}
					</p>
					<p>
						<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
						商品总价格：{{item.totalprice}}
					</p>
					<div parse id={{item.orderlist}}>
						<div class="list pull-left"
							ng-repeat="order in  orderlist track by $index">
							<div commodity-directive id="{{order.commodityid}}">
								<p>商品名:{{res.name}}</p>
								<p>商品描述:{{res.depict}}</p>
								<p>商品厂商:{{res.manufacturer}}</p>
								<p>商品价格:{{res.price}}</p>
								<p>
									商品logo:<img ng-src={{res.img}} width=50 height=50 />
								</p>
								<p>
									购买的商品个数: <span class="badge"> {{order.commoditycount}}
									</span>
								</p>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
				</li>
			</ul>
		</div>
	</div>
</jsp:body>
</tags:template>