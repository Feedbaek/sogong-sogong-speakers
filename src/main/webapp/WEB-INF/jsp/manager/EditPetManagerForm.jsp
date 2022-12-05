<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
  <stripes:form
    beanclass="org.mybatis.jpetstore.web.actions.PetManagerActionBean"
    focus=""
    method="post"
    enctype="multipart/form-data"
  >
    <h3>User Information</h3>

    <table>
      <tr>
        <td>User ID:</td>
        <td><stripes:text name="account.username" readonly="true"/></td>
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
        <td>
          <stripes:text name="account.firstName" value="${account.firstName}"/>
        </td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td>
          <stripes:text name="account.lastName" />
        </td>
      </tr>
      <tr>
        <td>Email:</td>
        <td>
          <stripes:text name="account.email" />
        </td>
      </tr>
      <tr>
        <td>Phone:</td>
        <td>
          <stripes:text name="account.phone" />
        </td>
      </tr>
      <tr>
        <td>Address 1:</td>
        <td>
          <stripes:text name="account.address1"/>
        </td>
      </tr>
      <tr>
        <td>Address 2:</td>
        <td>
          <stripes:text name="account.address2" />
        </td>
      </tr>
      <tr>
        <td>City:</td>
        <td><stripes:text name="account.city" /></td>
      </tr>
      <tr>
        <td>State:</td>
        <td>
          <stripes:text
            name="account.state"
            size="4"
          />
        </td>
      </tr>
      <tr>
        <td>Zip:</td>
        <td>
          <stripes:text
            name="account.zip"
            size="10"
          />
        </td>
      </tr>
      <tr>
        <td>Country:</td>
        <td>
          <stripes:text
            name="account.country"
            size="15"
          />
        </td>
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
        <td>
          <stripes:select name="petType">
            <stripes:options-collection
              collection="${actionBean.petTypeList}"
            />
          </stripes:select>
        </td>
      </tr>
      <tr>
        <td>Age:</td>
        <td>
          <stripes:text
            size="4"
            name="petManager.age"
          />
        </td>
      </tr>
      <tr>
        <td>Career:</td>
        <td>
          <stripes:text
            size="4"
            name="petManager.since"
          />
        </td>
      </tr>
      <tr>
        <td>Manage:</td>
        <td>
          <stripes:text
            size="40"
            name="petManager.manage"
          />
        </td>
      </tr>
    </table>

    <stripes:submit name="editAccount" value="Edit Account Information" />
  </stripes:form>

  <%@ include file="../common/IncludeBottom.jsp"%>
</div>
