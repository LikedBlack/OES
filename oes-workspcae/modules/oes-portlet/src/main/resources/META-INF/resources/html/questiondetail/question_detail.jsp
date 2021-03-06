<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:actionURL name="deleteQuestion" var="deleteQuestionURL">
    <portlet:param name="questionOrder" value="${questionOrder }"/>
</portlet:actionURL>

<aui:form>
    <liferay-ddm:html
        classNameId="${classNameId }"
        classPK="${classPK }"
        fields="${fields }"
        ddmFormValues="${questionFormValues }"
        readOnly="true"
    />
</aui:form>
<aui:a href="javascript:" onClick="self.location=document.referrer;">
    <liferay-ui:message key="return" />
</aui:a>
<aui:button name="edit" href="/edit-question?questionOrder=${questionOrder }" value="edit" cssClass="btn-primary" icon="icon-edit" />
<aui:button name="delete" href="${deleteQuestionURL }" value="delete" cssClass="btn-danger" icon="icon-remove" />
