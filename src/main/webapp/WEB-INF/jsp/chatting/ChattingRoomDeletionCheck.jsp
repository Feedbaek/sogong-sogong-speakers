<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-12-05
  Time: 오전 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

  <h2>DO YOU REALLY WANT TO DELETE THE MESSAGE ROOM?</h2>
  <stripes:link class="Button" style="font-size:15px"
                beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                event="deleteChattingRoom">
    YES
  </stripes:link>
  <stripes:link class="Button" style="font-size:15px"
                beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                event="viewChattingRoom">
    NO
  </stripes:link>


</div>

<%@ include file="../common/IncludeBottom.jsp"%>