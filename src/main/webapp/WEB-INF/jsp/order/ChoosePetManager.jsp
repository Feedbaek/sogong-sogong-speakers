<%--
  Created by IntelliJ IDEA.
  User: jinyoo
  Date: 2022/12/01
  Time: 1:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <stripes:form
            beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean">
        <h2>Pet Managers</h2>
        <br>
        <table>
            <tr>
                <th></th>
                <th>Manager ID</th>
                <th>Major</th>
                <th></th>
            </tr>
            <c:forEach var="petManager" items="${actionBean.petManagerList}">
                <c:if test="${petManager.managerId eq 'manager1' || petManager.managerId eq 'manager4' ||
                                petManager.managerId eq 'manager7'}">
                    <tr>
                        <td><img src="../images/${petManager.managerId}.jpeg" width="150" height="150" /></td>
                        <td>${petManager.managerId}</td>
                        <td>${petManager.petType}</td>
                        <td><input type="radio" name="catDog" value="${petManager.managerId}" checked ></td>
                    </tr>
                </c:if>
                <c:if test="${petManager.managerId eq 'manager2' || petManager.managerId eq 'manager5' ||
                                petManager.managerId eq 'manager8'}">
                    <tr>
                        <td><img src="../images/${petManager.managerId}.jpeg" width="150" height="150" /></td>
                        <td>${petManager.managerId}</td>
                        <td>${petManager.petType}</td>
                        <td><input type="radio" name="repFish" value="${petManager.managerId}" checked ></td>
                    </tr>
                </c:if>
                <c:if test="${petManager.managerId eq 'manager3' || petManager.managerId eq 'manager6' ||
                                petManager.managerId eq 'manager9'}">
                    <tr>
                        <td><img src="../images/${petManager.managerId}.jpeg" width="150" height="150" /></td>
                        <td>${petManager.managerId}</td>
                        <td>${petManager.petType}</td>
                        <td><input type="radio" name="bird" value="${petManager.managerId}" checked ></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    <stripes:submit name="submitPetManager" value="submit" /> </stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
