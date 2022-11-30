<%--
  Created by IntelliJ IDEA.
  User: jinyoo
  Date: 2022/12/01
  Time: 1:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <h2>Pet Managers</h2>
    <br>
    <table>
        <tr>
            <th>Manager ID</th>
            <th>Major</th>
        </tr>
        <c:forEach var="petManager" items="${actionBean.petManagerList}">
            <tr>
                <td>${petManager.managerId}</td>
                <td>${petManager.petType}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
