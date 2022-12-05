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
  Return to Main Menu</stripes:link>
</div>

<div id="Catalog">
    <p>    </p>
    <h2>Pet Manager Messenger Management</h2>
    <br>
          <table>
              <tr>
                  <th>Manager ID</th>
                  <th>User ID</th>
                  <th>Memo</th>
                  <th>Change</th>
              </tr>
              <c:forEach var="managerChatList" items="${actionBean.chattingRoomList}">
                  <tr>
                    <td>${managerChatList.managerId}</td>
                    <td>${managerChatList.customerId}</td>
                    <td><stripes:link class="Button" style="font-size:14px;"
                                      beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                      event="memoChatting" target="_blank">
                      <stripes:param name="customerId" value="${managerChatList.customerId}"/>
                      <stripes:param name="managerId" value="${managerChatList.managerId}"/>
                      View
                    </stripes:link></td>
                      <td><stripes:link class="Button" style="font-size:14px;"
                                        beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                        event="viewUpdateChattingRoom">
                          <stripes:param name="customerId" value="${managerChatList.customerId}"/>
                          <stripes:param name="managerId" value="${managerChatList.managerId}"/>
                          Update
                      </stripes:link></td>
                      <%--<td><stripes:link class="Button" style="font-size:14px;"
                                        beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                        event="viewDeletionCheck">
                          <stripes:param name="customerId" value="${managerChatList.customerId}"/>
                          <stripes:param name="managerId" value="${managerChatList.managerId}"/>
                          Delete
                      </stripes:link></td>--%>
                  </tr>
              </c:forEach>
          </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
