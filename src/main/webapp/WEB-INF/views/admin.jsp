<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tags:template>
	<jsp:attribute name="header">
<style>
.bs-example {
	position: relative;
	padding: 45px 15px 15px;
	margin: 0 -15px 15px;
	border-color: #E5E5E5 #EEE #EEE;
	border-style: solid;
	border-width: 1px 0;
	-webkit-box-shadow: inset 0 3px 6px rgba(0, 0, 0, 0.05);
	box-shadow: inset 0 3px 6px rgba(0, 0, 0, 0.05);
}

#myTabs {
	margin-top: 20px;
}
</style>
</jsp:attribute>
	<jsp:attribute name="footer">
<script type="text/javascript"
			src="${pageContext.request.contextPath}/static/js/admin.js"></script>
</jsp:attribute>
	<jsp:body>
	<div class="container" ng-app="app">
		<div class="row">
			<div class="bs-example bs-example-tabs"
					data-example-id="togglable-tabs">
				<ul id="myTabs" class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a id="tab0"
							href="#orderform" id="home-tab" role="tab" data-toggle="tab"
							aria-controls="orderform" aria-expanded="true">所有订单</a></li>
					<li role="presentation"><a id="tab1" href="#types"
							id="types-tab" role="tab" data-toggle="tab" aria-controls="types"
							aria-expanded="true">商品类型编辑</a></li>
					<li role="presentation"><a id="tab2" href="#pro" role="tab"
							id="pro-tab" data-toggle="tab" aria-controls="pro">商品编辑</a></li>
					<li role="presentation"><a id="tab3" href="#about" role="tab"
							id="about-tab" data-toggle="tab" aria-controls="about">所有评论</a></li>
				</ul>

				<div id="myTabContent" class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active orderform"
							id="orderform" aria-labelledby="home-tab"
							ng-controller="orderform">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th>订单id</th>
									<th>地址</th>
									<th>总金额</th>
									<th>手机</th>
									<th>详细信息</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in orderforms">
									<th scope="row">{{item.id}}</th>
									<td>{{item.address}}</td>
									<td>{{item.totalprice}}</td>
									<td>{{item.phone}}</td>
									<th><button class="btn btn-success"
												ng-click="showInfo(item.orderlist,$index)" href="###">
					{{(indexs < $index)?"关闭订单详细信息":"查看订单详细信息"}}</button></th>
								</tr>
								<tr>
								</tbody>
						</table>
						<div ng-show="edit" ng-init="edit=false">
							<div class="row">
							<ul class="list-group">
								<li class="list-group-item"
											ng-repeat="com in commoditys track by $index">
									<p>第{{$index+1}}条：商品id为【{{com.commodityid}}】
										的总数是【{{com.commoditycount}}】个</p>
									<div commodity-directive id="{{com.commodityid}}">
										<p>商品名字：{{res.name}}</p>
										<p>商品描述：{{res.depict}}</p>
										<p>商品厂商：{{res.manufacturer}}</p>
										<p>商品价格：{{res.price}}</p>
										<p>
											商品logo：<img ng-src={{res.img}} width=50 height=50 />
										</p>
									</div>
								</li>
							</ul>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane fade types" id="types"
							aria-labelledby="type-tab" ng-controller="types">
						<div class="row">
							<ul class="list-group">
								<li class="list-group-item">类型</li>
								<li class="list-group-item" ng-repeat="type in types">
									<div>
										<p>
											{{type.name}}
											<button class="btn btn-default pull-right"
													ng-click="delType( type.id )">删除该类型</button>
										</p>
									</div>
								</li>
							</ul>
						</div>
						<div class="row">
							<input placeholder="新类型名字" id="new_type" ng-model="new_type">
							<button class="btn btn-default" ng-click="new_type_fn()">创建新类型</button>
						</div>
					</div>
					<div id="pro" role="tabpanel" class="tab-pane fade" id="pro"
							aria-labelledby="pro-tab" ng-controller="pros">
						<br>
						<p>
							<button type="button" class="btn btn-primary btn-lg"
									data-toggle="modal" ng-click="addPro(com.id)">创建新商品</button>
						</p>
						<ul class="list-group">
							<li class="list-group-item" ng-repeat="com in coms">
								<p>商品名：{{com.name}}</p>
								<p>商品描述：{{com.depict}}</p>
								<p>商品公司：{{com.manufacturer}}</p>
								<p>商品数量：{{com.amount}}</p>
								<p>商品价格：{{com.price}}</p>
								<p>
									商品logo：<img ng-src={{com.img}} width=50 height=50 />
								</p>
								<p>
									<button type="button" class="btn btn-primary "
											data-toggle="modal" ng-click="editPro(com.id)">
										编辑该商品</button>
									<button class="btn btn-default" ng-click="removePro(com.id)">
										删除该商品</button>

								</p>
							</li>
						</ul>
						<div class="modal fade" id="editLabel" tabindex="-1" role="dialog"
								aria-labelledby="editModalLabel" ng-modal="edit_commodity">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="editModalLabel">修改商品</h4>
									</div>
									<div class="modal-body">
										<form autocomplete="off">
											<input value={{edit_commodity.id}} id="comid" type="hidden" />
											<div class="form-group">
												<label for="name">商品名字</label> <input type=text
														class="form-control" id="name"
														value={{edit_commodity.name}} placeholder="name">
											</div>
											<div class="form-group">
												<label for="depict">商品描述</label> <input type=text
														class="form-control" id="depict"
														value="{{edit_commodity.depict}}" placeholder="depict">
											</div>
											<div class="form-group">
												<label for="price">商品价格</label> <input type=text
														class="form-control" id="price"
														value="{{edit_commodity.price}}" placeholder="price">
											</div>
											<div class="form-group">
												<label for="amount">商品个数</label> <input type="text"
														class="form-control" id="amount"
														value="{{edit_commodity.amount}}" placeholder="amount">
											</div>
											<div class="form-group">
												<label for="manufacturer">商品厂商</label> <input type="text"
														class="form-control" id="manufacturer"
														value="{{edit_commodity.manufacturer}}"
														placeholder="manufacturer">
											</div>
											<div class="form-group">
												<label for="img">图片</label> <input type="text"
														class="form-control" id="img" readonly=true
														value="{{edit_commodity.img}}" placeholder="图片路径">
												<input type="file" name="fileField" value="上传文件" id="upload">
											</div>
											<select id="select" ng-controller="select">
												<option ng-repeat="type in types" value="{{type.name}}">
													{{type.name}}
			  									</option>
											</select>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
										<button id="edit" type="button" class="btn btn-primary">保存修改</button>
									</div>
								</div>
							</div>
						</div>
					
					</div>
					<div role="tabpanel" class="comments tab-pane fade" id="about"
							aria-labelledby="about-tab" ng-controller="comments">
						<ul class="list-group">
							<li class="list-group-item"
									ng-repeat="comment in comments track by $index">
								<p>评论列表</p>
								<div commodity-directive id="{{comment.commodityid}}">
									<p>商品名字：{{res.name}}</p>
									<p>商品描述：{{res.depict}}</p>
								</div>
								<div>
									<strong>{{comment.username}} <b>说</b></strong> <span>{{comment.comment}}</span>
								</div>
							</li>
						</ul>
						<nav>
							<ul class="pagination">
								<li>
									<a ng-click="Previous()"><span>上一页</span></a>
								</li>
								<li ng-repeat="page in pageList"
										ng-class="{active: isActivePage(page)}">
									<a ng-click="selectPage(page)">{{ page }}</a>
								</li>
								<li><a ng-click="Next()"><span>下一页</span></a></li>
							</ul>
						</nav>
					</div>
					
				</div>
			</div>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">创建商品</h4>
								</div>
								<div class="modal-body">
									<form>
										<div class="form-group">
											<label for="name">商品名字</label> <input type=text
										class="form-control" id="com_name" placeholder="name">
										</div>
										<div class="form-group">
											<label for="depict">商品描述</label> <input type=text
										class="form-control" id="com_depict" placeholder="depict">
										</div>
										<div class="form-group">
											<label for="price">商品价格</label> <input type=text
										class="form-control" id="com_price" placeholder="price">
										</div>
										<div class="form-group">
											<label for="amount">商品个数</label> <input type="text"
										class="form-control" id="com_amount" placeholder="amount">
										</div>
										<div class="form-group">
											<label for="manufacturer">商品厂商</label> <input type="text"
										class="form-control" id="com_manufacturer"
										" placeholder="manufacturer">
										</div>
										<div class="form-group">
											<label for="img">图片</label> <input type="text"
										class="form-control" id="com_img" readonly=true
										placeholder="图片路径">
											<input type="file" name="fileField" value=上传文件 id="uploads">
										</div>
										<select id="selects" ng-controller="select">
											<option ng-repeat="type in types" value="{{type.name}}">
			  						{{type.name}}
			  					</option>
										</select>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
									<button id="submit" type="button" class="btn btn-primary">保存</button>
								</div>
							</div>
						</div>
					</div>
	</div>
</jsp:body>
</tags:template>