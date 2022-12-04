<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-12-04
  Time: 오후 5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>
<div id="Catalog">
    <br>
    <br>
    <div>
        <stripes:link class="Button" style="font-size:35px;"
                      beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
                      event="adminViewCategory">
            PRODUCT MANAGEMENT
        </stripes:link>
    </div>
    <br><br><br><br><br>
    <div>
        <stripes:link class="Button" style="font-size:35px;"
                      beanclass="org.mybatis.jpetstore.web.actions.PetManagerActionBean"
                      event="allManagerList">
            PET MANAGER MANAGEMENT
        </stripes:link>
    </div>
<br>
<br>
<br>
<br>

</div>
<%@ include file="../common/IncludeBottom.jsp"%>
