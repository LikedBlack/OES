<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:resourceURL var="resourceURL" />
<c:set var="questions" value="${exam.questions }" scope="request"/>

<h1>${exam.name }</h1>
<aui:input name="switch" type="checkbox" label="auto-switch-next-question" /><br />
<aui:input name="resourceURL" type="hidden" value="<%=resourceURL.toString()%>" />
<aui:input name="examId" type="hidden" value="${exam.examId }" />
<aui:input name="studentId" type="hidden" value="${studentId }" />
<aui:button name="prevQuestion" value="Previous" disabled="true" />
<div class="question-container">
    <div class="question-list">
        <c:forEach items="${questions }" var="question">
            <div class="question-card">
                <span>${question.description }</span><br />
                <aui:input name="${question.order }" type="radio" value="A" label="${question.optionA }" /><br />
                <aui:input name="${question.order }" type="radio" value="B" label="${question.optionB }" /><br />
                <aui:input name="${question.order }" type="radio" value="C" label="${question.optionC }" /><br />
                <aui:input name="${question.order }" type="radio" value="D" label="${question.optionD }" /><br />
                <hr />
            </div>
        </c:forEach>
    </div>
</div>
<aui:button name="nextQuestion" value="Next" />