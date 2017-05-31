<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<liferay-theme:defineObjects />

<portlet:defineObjects />

<c:set var="questions" value="${exam.questions }" scope="request" />

<span>Exam Name: </span>
<h2>${exam.name }</h2><br />
<span>Student Name: </span>
<h2>${student.fullName }</h2><br />
<c:set var="wrongCount" value="0" />
<c:forEach items="${questions }" var="question">
    <h3>${question.description }</h3>
    <span>A. </span>
    <span>${question.optionA }</span><br />
    <span>B. </span>
    <span>${question.optionB }</span><br />
    <span>C. </span>
    <span>${question.optionC }</span><br />
    <span>D. </span>
    <span>${question.optionD }</span><br />
    <span>Answer: </span>
    <span>${question.answer }</span><br />
    <span>Choice: </span>
    <c:set var="questionOrder" value="${question.order }" />
    <c:choose>
        <c:when test="${question.answer == resultMap[questionOrder]}">
            <span style="color:green">${resultMap[questionOrder] }</span><br />
        </c:when>
        <c:otherwise>
            <span  style="color:red">${resultMap[questionOrder] }</span><br />
            <c:set var="wrongCount" value="${wrongCount + 1}" />
        </c:otherwise>
    </c:choose>
</c:forEach>
<h2>
    Wrong Answer:(
    <span  style="color:red">${wrongCount }</span>
    /
    <c:out value="${fn:length(questions)}"></c:out>
    )
</h2>