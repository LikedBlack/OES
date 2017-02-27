<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:actionURL name="updateQuestion" var="updateQuestionURL" />

<aui:form action="${updateQuestionURL }" method="POST">
<aui:fieldset>
    <liferay-ddm:html
        classNameId="${classNameId }"
        classPK="${classPK }"
        fields="${fields }"
        ddmFormValues="${questionFormValues }"
        readOnly="true"
    />
</aui:fieldset>
    <aui:a href="#">
        <liferay-ui:message key="return" />
    </aui:a>
    <aui:a href="/edit-question?questionOrder=${questionOrder }">
        <liferay-ui:message key="edit" />
    </aui:a>
</aui:form>