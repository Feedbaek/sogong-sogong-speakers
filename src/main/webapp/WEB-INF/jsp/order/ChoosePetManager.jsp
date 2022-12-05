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
    <stripes:form
            beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean">
        <h2>JPetStore's Pet Manager List</h2>
        <br>
        <table>
            <tr>
                <th></th>
                <th>Manager ID</th>
                <th>Category</th>
                <th>Profile</th>
                <th>Pick</th>
            </tr>
            <c:forEach var="petManager" items="${actionBean.petManagerList}">
                <c:if test="${petManager.catdog eq true}">
                    <tr>
                        <td><img src="../images/${petManager.managerId}.jpeg" width="150" height="150" /></td>
                        <td>${petManager.managerId}</td>
                        <td>${petManager.petType}</td>
                        <td>
                            NAME  : ${petManager.name}<br>
                            AGE   : ${petManager.age}<br>
                            CAREER : ${petManager.since} years<br>
                            MANAGE : ${petManager.manage}
                        </td>
                        <td><input type="radio" name="catDog" value="${petManager.managerId}" checked ></td>
                    </tr>
                </c:if>
                <c:if test="${petManager.repfish eq true}">
                    <tr>
                        <td><img src="../images/${petManager.managerId}.jpeg" width="150" height="150" /></td>
                        <td>${petManager.managerId}</td>
                        <td>${petManager.petType}</td>
                        <td>
                            NAME  : ${petManager.name}<br>
                            AGE   : ${petManager.age}<br>
                            CAREER : ${petManager.since} years<br>
                            MANAGE : ${petManager.manage}
                        </td>
                        <td><input type="radio" name="repFish" value="${petManager.managerId}" checked ></td>
                    </tr>
                </c:if>
                <c:if test="${petManager.bird eq true}">
                    <tr>
                        <td><img src="../images/${petManager.managerId}.jpeg" width="150" height="150" /></td>
                        <td>${petManager.managerId}</td>
                        <td>${petManager.petType}</td>
                        <td>
                            NAME  : ${petManager.name}<br>
                            AGE   : ${petManager.age}<br>
                            CAREER : ${petManager.since} years<br>
                            MANAGE : ${petManager.manage}
                        </td>
                        <td><input type="radio" name="bird" value="${petManager.managerId}" checked></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    <stripes:submit name="submitPetManager" value="submit" /> </stripes:form>
</div>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>
