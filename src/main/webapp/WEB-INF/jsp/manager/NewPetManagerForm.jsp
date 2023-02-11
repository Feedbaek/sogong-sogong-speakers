<%--

       Copyright 2010-2022 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          https://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <stripes:form
        beanclass="org.mybatis.jpetstore.web.actions.PetManagerActionBean"
        focus="" method="post" enctype="multipart/form-data">

    <h3>Pet Manager Information</h3>

    <table>
        <tr>
            <td>Manager ID:</td>
            <td><stripes:text name="name" /></td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><stripes:text name="password" /></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><stripes:text name="repeatedPassword" /></td>
        </tr>
    </table>

    <h3>Account Information</h3>

    <table>
        <tr>
            <td>First name:</td>
            <td><stripes:text name="firstName" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><stripes:text name="lastName" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><stripes:text size="40" name="email" /></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><stripes:text name="phone" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><stripes:text size="40" name="address1" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><stripes:text size="40" name="address2" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><stripes:text name="city" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><stripes:text size="4" name="state" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><stripes:text size="10" name="zip" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><stripes:text size="15" name="country" /></td>
        </tr>
    </table>

    <h3>Profile Information</h3>

    <table>
        <tr>
            <td>Photo:</td>
            <td>
                <stripes:file name="photo" />
            </td>
        </tr>
        <tr>
            <td>Category:</td>
            <td><stripes:select name="petType">
                <stripes:options-collection collection="${actionBean.petTypeList}" />
            </stripes:select></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><stripes:text size="4" name="age"/></td>
        </tr>
        <tr>
            <td>Career:</td>
            <td><stripes:text size="4" name="since"/></td>
        </tr>
        <tr>
            <td>Manage:</td>
            <td><stripes:text size="40" name="manage"/></td>
        </tr>

    </table>

    <stripes:submit name="newAccount" value="Save Account Information" />

</stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>