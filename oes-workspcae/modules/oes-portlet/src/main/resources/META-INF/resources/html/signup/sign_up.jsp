<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ page import="priv.barrow.oes.portlet.exception.EmailRegisteredException" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:actionURL name="signUp" var="signUpURL" />

<aui:form action="${signUpURL }" method="post" name="signUpForm">
  <aui:input name="First Name" type="text" label="first-name">
    <aui:validator name="required" />
  </aui:input>
  <aui:input name="Last Name" type="text" label="last-name">
    <aui:validator name="required" />
  </aui:input>
  <aui:input name="Email" type="text" label="email">
    <aui:validator name="required" />
    <aui:validator name="email" />
  </aui:input>
  <liferay-ui:error exception="<%= EmailRegisteredException.class %>" message="this-emial-has-been-registered" />
  <aui:input name="Confirm Email" type="text" label="confirm-email">
    <aui:validator name="required" />
    <aui:validator name="email" />
  </aui:input>
  <label>
    <liferay-ui:message key="i-am-a" />
  </label>
  <aui:input name="Role" type="radio" label="teacher" value="Teacher" />
  <aui:input name="Role" type="radio" label="student" value="Student" />
  <aui:input name="Password" type="password" label="password">
    <aui:validator name="required" />
  </aui:input>
  <aui:input name="Confirm Password" type="password" label="confirm-password">
    <aui:validator name="required" />
  </aui:input>
  <aui:button name="register" type="submit" value="register" />
</aui:form>
