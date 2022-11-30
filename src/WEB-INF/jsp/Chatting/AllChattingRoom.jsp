<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-11-27
  Time: 오후 9:42
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html; charset=utf-8"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="ChattingRoom"
             class="org.mybatis.jpetstore.web.actions.ChattingActionBean" />


<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
  Return to Main Menu</stripes:link></div>

<h2>Pet Manager Chatting List</h2>

<div style="height: 500px; width: 700px">
    <table style="float:left; margin-right: 5%; margin-left: 5%">
      <tr>
        <th>User ID</th>
        <th>Manager ID</th>
      </tr>
      <c:forEach var="managerChatList1" items="${actionBean.adminChatList1}">
        <tr>
          <td>${managerChatList1.customerId}</td>
          <td>${managerChatList1.managerId}</td>
        </tr>
      </c:forEach>
  </table>
    <table style="float:left; margin-right: 5%; margin-left: 5%">
      <tr>
        <th>User ID</th>
        <th>Manager ID</th>
      </tr>
      <c:forEach var="managerChatList2" items="${actionBean.adminChatList2}">
        <tr>
          <td>${managerChatList2.customerId}</td>
          <td>${managerChatList2.managerId}</td>
        </tr>
     </c:forEach>
  </table>
    <table style="float:left; margin-right: 5%; margin-left: 10%">
        <tr>
          <th>User ID</th>
          <th>Manager ID</th>
        </tr>
        <c:forEach var="managerChatList3" items="${actionBean.adminChatList3}">
          <tr>
            <td>${managerChatList3.customerId}</td>
            <td>${managerChatList3.managerId}</td>
          </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
