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
                <th>Show ChattingRoom</th>
            </c:if>
        </tr>
          <c:forEach var="petManager" items="${actionBean.petManagerList}">
<%--              <c:if test="${petManager.managerId eq 'manager1' || petManager.managerId eq 'manager4'--%>
<%--                                || petManager.managerId eq 'manager7'}">--%>
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
                          <td><stripes:link class="Button"
                                            beanclass="org.mybatis.jpetstore.web.actions.ChattingActionBean"
                                            event="viewChattingRoom">
                              <stripes:param name="managerId" value="${petManager.managerId}"/>
                            Show
                          </stripes:link></td>
                      </c:if>
                  </tr>
<%--              </c:if>--%>
              <%--<c:if test="${petManager.managerId eq 'manager2' || petManager.managerId eq 'manager5'
                                || petManager.managerId eq 'manager8'}">
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
                  </tr>
              </c:if>
              <c:if test="${petManager.managerId eq 'manager3' || petManager.managerId eq 'manager6'
                                || petManager.managerId eq 'manager9'}">
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
                  </tr>
              </c:if>--%>
          </c:forEach>
      </table>
  </div>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>
