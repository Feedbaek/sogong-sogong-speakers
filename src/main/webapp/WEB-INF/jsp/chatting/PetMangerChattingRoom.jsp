<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-11-27
  Time: 오후 9:42
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html; charset=utf-8"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="sdyn" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>


<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="ChattingRoom"
             class="org.mybatis.jpetstore.web.actions.ChattingActionBean" />

<div id="BackLink">
    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
        Return to Main Menu
    </stripes:link>
</div>

<div id="Catalog">
    <div id="SearchContent" align="left">
        <stripes:form
                beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
            <sdyn:text name="keyword" size="14" placeholder="User ID"/>
            <stripes:submit name="searchUserID" value="Search" />
        </stripes:form>
    </div id="SearchContent">
    <h2>My Client Messenger</h2>
    <br>
    <table>
        <tr>
            <th>Manager ID</th>
            <th>User ID</th>
            <th>Memo</th>
            <th>Chat</th>
            <th>Check</th>
        </tr>
        <c:forEach var="managerChatList" items="${actionBean.adminChatList}">
            <tr>
                <td>${managerChatList.managerId}</td>
                <td>${managerChatList.customerId}</td>
                <td>
                    <stripes:link class="Button"
                                  beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                  event="memoChatting" target="_blank">
                        <stripes:param name="customerId" value="${managerChatList.customerId}"/>
                        <stripes:param name="managerId" value="${managerChatList.managerId}"/>
                        open
                    </stripes:link>
                </td>
                <td>
                    <stripes:link class="Button"
                                  beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                  event="joinChatting">
                    <stripes:param name="customerId" value="${managerChatList.customerId}"/>
                    <stripes:param name="managerId" value="${managerChatList.managerId}"/>
                        join
                    </stripes:link>
                </td>
                <td>
                    <c:set var="flag" value="false"/>
                    <c:forEach var="alarm" items="${actionBean.alarms}">
                        <c:if test="${alarm.senderId eq managerChatList.customerId}">
                            <c:if test="${alarm.alarm eq 'on'}">
                                <c:set var="flag" value="true"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${flag}">
                            not check
                        </c:when>
                        <c:otherwise>
                            all check
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
