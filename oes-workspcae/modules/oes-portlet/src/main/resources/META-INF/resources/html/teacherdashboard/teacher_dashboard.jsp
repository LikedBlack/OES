<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<c:set var="exams" value="${exams }" scope="request" />

<h2>
    <liferay-ui:message key="published-exams" />
</h2>
<table class="table table-hover table-bordered">
    <tr>
        <th></th>
        <th>Exam Name</th>
        <th>Created Time</th>
    </tr>
    <c:forEach items="${exams }" var="exam"  varStatus="status">
        <tr>
            <td>${status.count }</td>
            <td>
                <aui:a href="/exam-situation?examId=${exam.examId }">${exam.name }</aui:a>
            </td>
            <td>
                ${exam.createdTime }
            </td>
        </tr>
    </c:forEach>
</table>

<hr />
<aui:button href="/create-exam" value="New Exam" icon="icon-plus" cssClass="btn btn-primary" />
