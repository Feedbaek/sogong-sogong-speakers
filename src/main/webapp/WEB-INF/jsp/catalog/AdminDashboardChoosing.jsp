<%--
  Created by IntelliJ IDEA.
  User: 00110
  Date: 2022-12-04
  Time: 오후 5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>
<div id="Catalog">
    <br> <br> <br> <br>
    <div>
        <stripes:link class="Button" style="font-size:20px;"
                      beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
                      event="adminViewCategory">
            Product Management
        </stripes:link>
    </div>
    <br>
    <div>
        <stripes:link class="Button" style="font-size:20px;"
                      beanclass="org.mybatis.jpetstore.web.actions.PetManagerActionBean"
                      event="allManagerList">
            Pet Manager Management
        </stripes:link>
    </div>
<br>
<br>
<br>
<br>

</div>
<%@ include file="../common/IncludeBottom.jsp"%>
