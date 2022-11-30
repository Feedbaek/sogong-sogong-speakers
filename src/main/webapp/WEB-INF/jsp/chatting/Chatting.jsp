<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-11-29
  Time: 오후 6:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.Timestamp"%>
<%@ include file="../common/IncludeTop.jsp"%>

<style>
    .BorderBoundaryBox{
        background-color: #eeeeee;
        border:#c1c1c1 1px solid;
        padding: 10px;margin-left: 20%;margin-right: 20%;
        overflow-y:scroll;
        min-height: 300px;
        max-height: 500px;
        width: 60%; height: fit-content;
    }
    #MyChattingBox{
        background-color: #c1c1c1;
        border:#005e21 2px solid;
        padding: 5px;
        overflow: hidden;
        width: fit-content;
        max-width: 25%;
        font-size:15px;
        text-align: left;
        word-break: break-word;
        margin-left: auto;
    }
    #OppositeChattingBox{
        background-color: #c1c1c1;
        border:#c1c1c1 1px solid;
        padding: 5px;
        overflow: hidden;
        width: fit-content;
        max-width: 25%;
        font-size:15px;
        text-align: left;
        word-break: break-word;
        margin-right: auto;
    }
    #nameTag{
        font-size:8px;
    }
    #timeStampTag{
        font-size:3px;
        color:#aaaaaa;
    }
</style>


<div class="BorderBoundaryBox" align="center" id="mydiv">
        <stripes:form beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean" >
                <c:forEach var="ChattingLine" items="${actionBean.chattingLog}">
                    <c:choose>
                        <c:when test="${ChattingLine.senderId eq sessionScope.accountBean.username}">
                            <div id="nameTag" align="Right">:me</div>
                            <div id="MyChattingBox" align="right"> ${ChattingLine.chattingLog}</div>
                            <div id="timeStampTag" align="Right">${ChattingLine.timeTable}</div>
                        </c:when>
                        <c:otherwise>
                            <div id="nameTag" align="Left" style="font-style: italic"> ${ChattingLine.senderId}:</div>
                            <div id="OppositeChattingBox" align="Left"> ${ChattingLine.chattingLog}</div>
                            <div id="timeStampTag" align="LEFT">${ChattingLine.timeTable}</div>
                        </c:otherwise>
                    </c:choose>
                    <br/>
                </c:forEach>
        </stripes:form>
</div>

<div align="center" style="padding: 30px;">
    <stripes:form
            beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
    <stripes:text name="chattingLine" size="small"/>
        <stripes:param name="customerId" value="${actionBean.customerId}"/>
        <stripes:param name="managerId" value="${actionBean.managerId}"/>
        <stripes:param name="timestamp" value="${nowTime}"/>
    <stripes:submit name="insertChatting" value="Send"/>
    </stripes:form>
</div>

<script type="text/javascript">
    var objDiv = document.getElementById("mydiv");
    objDiv.scrollTop = objDiv.scrollHeight;
    console.log(objDiv.scrollTop, objDiv.scrollHeight);
</script>

<%@ include file="../common/IncludeBottom.jsp"%>
