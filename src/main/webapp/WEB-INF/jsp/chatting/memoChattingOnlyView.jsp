<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-12-04
  Time: 오후 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sdyn" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>
<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="ChattingRoom"
             class="org.mybatis.jpetstore.web.actions.ChattingActionBean" />


<div id="BackLink">
  <stripes:link
          beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
    Return to Main Menu
  </stripes:link>
</div>

<div id="Catalog">
  <p>    </p>
  <h2>Manager's Memo</h2>
  <h3 style="color:#aaaaaa;font-size:15px;">customerId: ${actionBean.customerId}</h3>
  <br>
  <stripes:form beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
    <table>
      <tr>
        <th>Pet Name</th>
        <th>Pet Category</th>
        <th>Pet Age</th>
        <th>Start Date</th>
      </tr>
      <tr>
        <td>
          <sdyn:text disabled="true" name="memo.petName" placeholder="name">
            ${actionBean.memo.petName}
          </sdyn:text>
        </td>
        <td>
          <sdyn:text disabled="true" name="memo.petCategory" placeholder="category">
            ${actionBean.memo.petCategory}
          </sdyn:text>
        </td>
        <td>
          <sdyn:text disabled="true" name="memo.petAge" placeholder="age">
            ${actionBean.memo.petAge}
          </sdyn:text>
        </td>
        <td>
            ${actionBean.memo.startDate}
        </td>
      </tr>
      <tr>
        <td colspan="4">
          <sdyn:textarea disabled="true" name="memo.evalLog" rows="10" cols="92" placeholder="Memo">
            ${actionBean.memo.evalLog}
          </sdyn:textarea>
        </td>
      </tr>
    </table>
    <stripes:param name="customerId" value="${actionBean.customerId}"/>
    <stripes:param name="managerId" value="${actionBean.managerId}"/>
    <br/>
    <stripes:select name="memo.curStatus" disabled="true">
      <stripes:option value="excellent">excellent</stripes:option>
      <stripes:option value="good">good</stripes:option>
      <stripes:option value="bad">bad</stripes:option>
    </stripes:select>
  </stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
