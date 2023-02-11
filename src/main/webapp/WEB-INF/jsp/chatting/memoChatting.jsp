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
    <stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
        Return to Main Menu
    </stripes:link>
</div>

<div id="Catalog">
    <p>    </p>
    <h2>My Memo</h2>
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
            <sdyn:text name="memo.petName" placeholder="name">
                ${actionBean.memo.petName}
            </sdyn:text>
        </td>
        <td>
            <sdyn:text name="memo.petCategory" placeholder="category">
                ${actionBean.memo.petCategory}
            </sdyn:text>
        </td>
        <td>
            <sdyn:text name="memo.petAge" placeholder="age">
                ${actionBean.memo.petAge}
            </sdyn:text>
        </td>
        <td>
            ${actionBean.memo.startDate}
        </td>
      </tr>
      <tr>
          <td colspan="4">
            <sdyn:textarea name="memo.evalLog" rows="10" cols="92" placeholder="Memo">
                ${actionBean.memo.evalLog}
            </sdyn:textarea>
          </td>
      </tr>
    </table>
      <stripes:param name="customerId" value="${actionBean.customerId}"/>
      <stripes:param name="managerId" value="${actionBean.managerId}"/>
    <br/>
    <stripes:select name="memo.curStatus">
      <stripes:option value="excellent">excellent</stripes:option>
      <stripes:option value="good">good</stripes:option>
      <stripes:option value="bad">bad</stripes:option>
    </stripes:select>
    <stripes:submit name="saveMemo" value="Save"/>
  </stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
