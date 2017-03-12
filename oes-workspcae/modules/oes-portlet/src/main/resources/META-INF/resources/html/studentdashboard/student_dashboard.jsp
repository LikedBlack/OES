<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<c:set var="hasTeacher" value="${hasTeacher }" scope="request"/>

<c:choose>
    <c:when test="${hasTeacher }">
        <h3>
            <liferay-ui:message key="exams-to-do" />
        </h3>
        <c:forEach items="${toDoExams }" var="exam">
            <span>${exam.name }</span>
            <aui:a href="/take-exam?examId=${exam.examId }">
                <liferay-ui:message key="start" />
            </aui:a>
        </c:forEach>
        <h3>
            <liferay-ui:message key="exams-in-progress" />
        </h3>
        <c:forEach items="${inProgressExams }" var="exam">
            <span>${exam.name }</span>
            <aui:a href="/take-exam?examId=${exam.examId }">
                <liferay-ui:message key="continue" />
            </aui:a>
        </c:forEach>
        <h3>
            <liferay-ui:message key="exams-completed" />
        </h3>
        <c:forEach items="${doneExams }" var="exam">
            <span>${exam.name }</span>
            <aui:button value="review" />
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h4>
            <liferay-ui:message key="please-choose-your-teacher-firstly" />
        </h4>
        <%@ include file="../search/teacher_search.jsp" %>
    </c:otherwise>
</c:choose>
