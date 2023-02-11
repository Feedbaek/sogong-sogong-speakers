<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-11-29
  Time: 오후 6:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="../common/IncludeTop.jsp"%>


<div style="margin:auto;align:center;">
<stripes:form beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean" >
    <stripes:textarea name="ChattingBox" rows="20" cols="100">
        <c:forEach var="ChattingLine" items="${actionBean.chattingLog}">
            ${ChattingLine.chattingLog}
        </c:forEach>
    </stripes:textarea>
</stripes:form>
</div>

<stripes:form
        beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
  <stripes:text name="chattingLine"/>
    <stripes:param name="customerId" value="${actionBean.customerId}"/>
    <stripes:param name="managerId" value="${actionBean.managerId}"/>
  <stripes:submit name="insertChatting" value="Send"/>
</stripes:form>

<%@ include file="../common/IncludeBottom.jsp"%>
