<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:actionURL name="searchQuestions" var="searchQuestionsURL" />

<aui:form action="${searchQuestionsURL }" method="POST">
    <span>
        <liferay-ui:message key="question-no" />
    </span>
    <aui:input name="questionNo" type="text" />
    <span>
        <liferay-ui:message key="keyword" />
    </span>
    <aui:input name="keyword" type="text" />
    <span>
        <liferay-ui:message key="per-page" />
    </span>
    <aui:select name="perPage">
        <aui:option value="5">5</aui:option>
        <aui:option value="10">10</aui:option>
        <aui:option value="20">20</aui:option>
    </aui:select>
    <aui:button name="search" type="submit" value="search" />
</aui:form>

