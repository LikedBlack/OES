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
    </c:when>
    <c:otherwise>
        <h4>Please choose your teacher firstly.</h4>
        <%@ include file="../search/teacher_search.jsp" %>
    </c:otherwise>
</c:choose>
