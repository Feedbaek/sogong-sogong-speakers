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
    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.PetManagerActionBean"
                  event="allManagerList">
        View Pet Manager List
    </stripes:link>
</div>

<div id="Catalog">
    <div align="left"><p>         </p></div>
    <c:choose>
        <c:when test="${actionBean.chattingRoomList.size() eq 0}">
            <h2>Why don't you register for JPetstore's Pet Manager Messenger Service?</h2>
            <br>
            <p>
                When purchasing JPetstore's pet, you can register for this service on the shopping cart page :)
            </p>
        </c:when>
        <c:otherwise>
            <h2>My Pet Manager Messenger</h2>
            <br>
            <table>
                <tr>
                    <th>User ID</th>
                    <th>Manager ID</th>
                    <th>Notification</th>
                    <th></th>
                </tr>
                <c:forEach var="ChattingRoom" items="${actionBean.chattingRoomList}">
                    <tr>
                        <td>${ChattingRoom.customerId}</td>
                        <td>${ChattingRoom.managerId}</td>
                        <td>
                            <c:set var="flag" value="false"/>
                            <c:forEach var="alarm" items="${actionBean.alarms}">
                                <c:if test="${alarm.senderId eq ChattingRoom.managerId}">
                                    <c:if test="${alarm.alarm eq 'on'}">
                                        <c:set var="flag" value="true"/>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${flag}">
                                    <b>Not Read</b>
                                </c:when>
                                <c:otherwise>
                                    Read
                                </c:otherwise>
                            </c:choose>
                        </td>
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
        </c:otherwise>
    </c:choose>
    <br/>
    <br/>
    <br/>
    <table>
        <th>Admin</th>
        <th>Notification</th>
        <th></th>
        <tr>
            <td>ACID</td>
            <td>
                <c:set var="flag" value="false"/>
                <c:forEach var="alarm" items="${actionBean.alarms}">
                    <c:if test="${alarm.senderId eq 'ACID'}">
                        <c:if test="${alarm.alarm eq 'on'}">
                            <c:set var="flag" value="true"/>
                        </c:if>
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${flag}">
                        <b>Not Read</b>
                    </c:when>
                    <c:otherwise>
                        Read
                    </c:otherwise>
                </c:choose>
            </td>
            <td><stripes:link class="Button"
                              beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                              event="joinChatting">
                <stripes:param name="customerId" value="${sessionScope.accountBean.username}"/>
                <stripes:param name="managerId" value="${actionBean.adminId}"/>
                join
            </stripes:link></td>
        </tr>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
