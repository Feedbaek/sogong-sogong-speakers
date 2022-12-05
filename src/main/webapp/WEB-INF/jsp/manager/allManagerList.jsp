<%--
  Created by IntelliJ IDEA.
  User: jinyoo
  Date: 2022/12/01
  Time: 1:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<style>
  #PetmanagerList td,th {
    text-align: center;
    vertical-align: middle;
  }
</style>

<div id="Catalog">
  <div id = "PetmanagerList">
      <h2>JPetStore's Pet Manager List</h2>
      <br>
      <table>
        <tr>
          <th></th>
          <th>Manager ID</th>
          <th>Category</th>
          <th>Profile</th>
            <c:if test="${sessionScope.permission eq 'admin'}">
                <th>Edit Account</th>
                <th>ChattingRoom List</th>
            </c:if>
        </tr>
          <c:forEach var="petManager" items="${actionBean.petManagerList}">
                  <tr>
                      <td><img src="../images/${petManager.managerId}.jpeg" width="150" height="150" /></td>
                      <td>${petManager.managerId}</td>
                      <td>${petManager.petType}</td>
                      <td>
                          Name  : ${petManager.name}<br>
                          Age   : ${petManager.age}<br>
                          Career : ${petManager.since} years<br>
                          Manage : ${petManager.manage}
                      </td>
                      <c:if test="${sessionScope.permission eq 'admin'}">
                          <td>
                              <stripes:link class="Button"
                                            beanclass="org.mybatis.jpetstore.web.actions.PetManagerActionBean"
                                            event="editPetManagerAccount">
                                  <stripes:param name="managerId" value="${petManager.managerId}"/>
                                  Edit
                              </stripes:link>
                          </td>
                          <td><stripes:link class="Button" style="font-size:20px;"
                                            beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                            event="viewChattingRoom">
                              <stripes:param name="managerId" value="${petManager.managerId}"/>
                            Show
                          </stripes:link></td>
                      </c:if>
                  </tr>
          </c:forEach>
      </table>
  </div>
    <c:if test="${sessionScope.permission eq 'admin'}">
        <stripes:form
                beanclass="org.mybatis.jpetstore.web.actions.PetManagerActionBean">
            <stripes:submit name="newAccountForm" value="Add Pet Manager" />
        </stripes:form>
    </c:if>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>
