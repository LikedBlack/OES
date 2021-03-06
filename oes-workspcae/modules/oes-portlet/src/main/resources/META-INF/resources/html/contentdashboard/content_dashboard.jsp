<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:actionURL name="importQuestion" var="importQuestionURL"/>

<c:set var="questions" value="${recentUpdateQuestions }" scope="request" />

<h4>Recent Update Questions:</h4>
<c:forEach items="${questions }" var="question">
    <span>${question.order }</span>
    <aui:a href="/question-detail?questionOrder=${question.order }">${question.description }</aui:a>
    <aui:a href="/edit-question?questionOrder=${question.order }">
        <liferay-ui:message key="edit" />
    </aui:a>
    <br />
</c:forEach>
<br />
<aui:a calss="btn btn-lg btn-primary btn-default" href="/view-questions">
    <liferay-ui:message key="view-questions" />
</aui:a>
<aui:a calss="btn btn-lg btn-primary btn-default" href="/add-question">
    <liferay-ui:message key="add-question" />
</aui:a>
<aui:a calss="btn btn-lg btn-primary btn-default" style="display: none" href="${importQuestionURL }">Import Questions</aui:a>
