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
    <aui:input name="questionOrder" type="hidden" value="${questionOrder }" />
    <liferay-ddm:html
        classNameId="${classNameId }"
        classPK="${classPK }"
        fields="${fields }"
        ddmFormValues="${questionFormValues }"
        checkRequired="true"
        fieldsNamespace="editQuestionForm"
    />
    <aui:button type="submit" value="save" />
</aui:form>