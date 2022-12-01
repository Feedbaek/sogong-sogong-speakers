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
  <h2>Pet Manager Memo</h2>

  <stripes:form beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean">
    <table>
      <tr>
        <th>petName</th>
        <th>petCategory</th>
        <th>petAge</th>
        <th>startDate</th>
      </tr>
      <tr>
        <td>
            <stripes:text name="memo.petName"
            >${actionBean.memo.petName}
            </stripes:text>
        </td>
        <td>
            <stripes:text name="memo.petCategory"
            >${actionBean.memo.petCategory}
            </stripes:text>
        </td>
        <td>
            <stripes:text name="memo.petAge"
            >${actionBean.memo.petAge}
            </stripes:text>
        </td>
        <td>
            ${actionBean.memo.startDate}
        </td>
      </tr>
      <tr>
          <td colspan="4">
            <stripes:textarea name="memo.evalLog" rows="10" cols="92"
                >${actionBean.memo.evalLog}
            </stripes:textarea>
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
