<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-12-04
  Time: 오후 7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>
<%@ taglib prefix="sdyn" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>
<jsp:useBean id="ChattingRoom"
             class="org.mybatis.jpetstore.web.actions.ChattingActionBean"/>

<div id="Catalog">
  <h2>UPDATE CHATTING ROOM</h2>
  <stripes:form beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
  <table>
    <tr>
      <th>Manager ID</th>
      <th>Customer ID</th>
    </tr>
    <tr>
      <td><sdyn:select name="updatedChattingRoom.managerId">
        <c:forEach var="manager" items="${actionBean.petManagerList}">
          <c:if test="${manager.managerId eq actionBean.managerId}">
            <sdyn:option value="${manager.managerId}" selected="true">${manager.managerId}</sdyn:option>
          </c:if>
          <c:if test="${manager.managerId ne actionBean.managerId}">
            <sdyn:option value="${manager.managerId}">${manager.managerId}</sdyn:option>
          </c:if>
        </c:forEach>
      </sdyn:select></td>
      <td><sdyn:text name="updatedChattingRoom.customerId">"${actionBean.customerId}"</sdyn:text></td>
    </tr>
  </table>
    <stripes:submit name="updateChattingRoom"  value="update"/>
  </stripes:form>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>