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

<div id="BackLink">
    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
        Return to Main Menu
    </stripes:link>
</div>

<div id="Catalog">
    <div id="SearchContent" align="left">
        <stripes:form
                beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
            <stripes:text name="keyword" size="14" />
            <stripes:submit name="searchUserID" value="Search" />
        </stripes:form>
    </div id="SearchContent">
    <h2>Searched Result</h2>
    <br>
    <table>
        <tr>
            <th>Manager ID</th>
            <th>User ID</th>
            <th>Memo</th>
            <th>Chat</th>
        </tr>
        <c:forEach var="searchedChatList" items="${actionBean.chattingRoomList}">
            <tr>
                <td>${searchedChatList.managerId}</td>
                <td>${searchedChatList.customerId}</td>
                <td>
                    <stripes:link class="Button"
                                  beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                  event="memoChatting" target="_blank">
                        <stripes:param name="customerId" value="${searchedChatList.customerId}"/>
                        <stripes:param name="managerId" value="${searchedChatList.managerId}"/>
                        open
                    </stripes:link>
                </td>
                <td>
                    <stripes:link class="Button"
                                  beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                  event="joinChatting">
                        <stripes:param name="customerId" value="${searchedChatList.customerId}"/>
                        <stripes:param name="managerId" value="${searchedChatList.managerId}"/>
                        join
                    </stripes:link>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
