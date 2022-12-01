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

<head>
    <style>
        h2{text-align: center}
    </style>
</head>

<div id="BackLink">
    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
        Return to Main Menu
    </stripes:link>
</div>

<div id="Catalog">
    <div align="left"><p>         </p></div>
    <h2>My Pet Manager Messenger</h2>
    <br>
    <table>
        <tr>
            <th>User ID</th>
            <th>Manager ID</th>
            <th></th>
        </tr>
        <c:forEach var="ChattingRoom" items="${actionBean.chattingRoomList}">
            <tr>
                <td>${ChattingRoom.customerId}</td>
                <td>${ChattingRoom.managerId}</td>
                <td><stripes:link class="Button"
                                  beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                  event="joinChatting">
                    <stripes:param name="customerId" value="${ChattingRoom.customerId}"/>
                    <stripes:param name="managerId" value="${ChattingRoom.managerId}"/>
                    join
                </stripes:link></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
