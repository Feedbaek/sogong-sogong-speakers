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

<table>
    <tr>
        <th>User ID</th>
        <th>Manager ID</th>
    </tr>
    <c:forEach var="ChattingRoom" items="${actionBean.chattingRoomList}">
        <tr>
            <td>${ChattingRoom.customerId}</td>
            <td>${ChattingRoom.managerId}</td>
        </tr>
    </c:forEach>
</table>



<%@ include file="../common/IncludeBottom.jsp"%>
