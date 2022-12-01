<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-12-01
  Time: 오후 9:00
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="../common/IncludeTop.jsp"%>
<%@ taglib prefix="sdyn" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>

<jsp:useBean id="ChattingRoom"
             class="org.mybatis.jpetstore.web.actions.AccountActionBean"/>

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
            <stripes:submit name="searchUserIdOnAccount" value="Search" />
        </stripes:form>
    </div id="SearchContent">

    <h2>Clients Messenger</h2><br/>
    <table>
        <tr>
            <th>User ID</th>
            <th>User Name</th>
            <th>Chat</th>
        </tr>
        <c:forEach var="user" items="${actionBean.accountList}">
            <tr>
                <td>${user.username}</td>
                <td>${user.firstName} ${user.lastName}</td>
                <td align="center"><stripes:link class="Button"
                                  beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                  event="joinChatting">
                    <stripes:param name="customerId" value="${user.username}"/>
                    <stripes:param name="managerId" value="${sessionScope.accountBean.username}"/>
                    join
                </stripes:link></td>
            </tr>
        </c:forEach>
    </table>
</div>



<%@ include file="../common/IncludeBottom.jsp"%>