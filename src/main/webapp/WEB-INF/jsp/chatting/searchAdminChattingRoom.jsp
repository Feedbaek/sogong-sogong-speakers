<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-12-02
  Time: 오전 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="ChattingRoom"
             class="org.mybatis.jpetstore.web.actions.ChattingActionBean" />

<div id="BackLink">
  <stripes:link beanclass="org.mybatis.jpetstore.web.actions.AccountActionBean"
                event="viewAllAccountExceptManager">
    Return to Client List
  </stripes:link>
</div>

<div id="Catalog">
  <div id="SearchContent" align="left">
    <stripes:form
            beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
      <stripes:text name="keyword" size="14" />
      <stripes:submit name="searchUserIdOnAccount" value="Search" />
    </stripes:form>
  </div id="SearchContent">
  <h2>Searched Result</h2>
  <br>
  <table>
    <tr>
      <th>User ID</th>
      <th>Chat</th>
    </tr>
    <c:forEach var="searchedChatList" items="${actionBean.accountList}">
      <tr>
        <td>${searchedChatList.username}</td>
        <td>
          <stripes:link class="Button"
                        beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                        event="joinChatting">
            <stripes:param name="customerId" value="${searchedChatList.username}"/>
            <stripes:param name="managerId" value="${actionBean.adminId}"/>
            join
          </stripes:link>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
