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

<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
    Return to Main Menu</stripes:link></div>

<div id="Catalog">
    <h2>${actionBean.product.name}</h2>
<stripes:form
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
        focus="">

    <table>
        <tr>
            <th>ProductId</th>
            <th>Item ID</th>
            <th>Description</th>
            <th>list Price</th>
            <th>Quantity</th>
        </tr>
        <tr>
            <td>${actionBean.productId} </td>
            <td><stripes:text name="itemId" value= ""/> </td>
            <td><stripes:text name="attribute1" value=""/> </td>
            <td><stripes:text name="listPrice" value="" /></td>
            <td><stripes:text name="quantity" value="" /></td>

        </tr>
    </table>
    <stripes:param name="productId" value="${actionBean.productId}" />
    <stripes:submit name="DBItemAdd" value="Submit" />
</stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>


