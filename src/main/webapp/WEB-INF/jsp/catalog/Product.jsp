<%--

       Copyright 2010-2022 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          https://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

--%>
<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="catalog"
	class="org.mybatis.jpetstore.web.actions.CatalogActionBean" />

<div id="BackLink">
	<c:if test="${actionBean.isadmin == true}">
		<stripes:link
				beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
				event="viewCategory">
			<stripes:param name="categoryId"
						   value="" />
			Return to Product
		</stripes:link></div>
	</c:if>


	<c:if test="${actionBean.isadmin == false}">
		<stripes:link
				beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
				event="viewCategory">
			<stripes:param name="categoryId"
						   value="${actionBean.product.categoryId}" />
			Return to ${actionBean.product.categoryId}
		</stripes:link></div>
	</c:if>


<div id="Catalog">

<h2>${actionBean.product.name}</h2>

<table>
	<tr>
		<th>Item ID</th>
		<th>Product ID</th>
		<th>Description</th>
		<th>List Price</th>
		<th>&nbsp;</th>
		<c:if test="${actionBean.isadmin == true}">
		<th>&nbsp;</th>
		</c:if>

	</tr>
	<c:forEach var="item" items="${actionBean.itemList}">
		<tr>
			<td><stripes:link
				beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
				event="viewItem">
				<stripes:param name="itemId" value="${item.itemId}" />
				${item.itemId}
			</stripes:link></td>
			<td>${item.product.productId}</td>
			<td>${item.attribute1} ${item.attribute2} ${item.attribute3}
			${item.attribute4} ${item.attribute5} ${actionBean.product.name}</td>
			<td><fmt:formatNumber value="${item.listPrice}"
				pattern="$#,##0.00" /></td>

			<c:if test="${actionBean.isadmin == false}">
			<td><stripes:link class="Button"
				beanclass="org.mybatis.jpetstore.web.actions.CartActionBean"
				event="addItemToCart">
				<stripes:param name="workingItemId" value="${item.itemId}" />
        	Add to Cart
	        </stripes:link></td>
			</c:if>

			<c:if test="${actionBean.isadmin == true}">
				<td><stripes:link class="Button"
								  beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
								  event="ItemUpdatePage">
					<stripes:param name="itemId" value="${item.itemId}" />
					UPDATE
				</stripes:link></td>
				<td><stripes:link class="Button"
								  beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
								  event="ItemDelete">
					<stripes:param name="productId" value="${actionBean.productId}" />
					<stripes:param name="itemId" value="${item.itemId}" />
					DELETE
				</stripes:link></td>
			</c:if>

		</tr>
	</c:forEach>
</table>
<c:if test="${actionBean.isadmin == true}">
	<td><stripes:link class="Button"
					  beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
					  event="ItemAddPage">
		<stripes:param name="productId" value="${actionBean.productId}" />
		Add
	</stripes:link></td>
</c:if>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>





