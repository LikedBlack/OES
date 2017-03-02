<%@page import="priv.barrow.service.TeacherUserLinkLocalServiceUtil"%>
<%@page import="priv.barrow.oes.portlet.util.TeacherUtil"%>
<%@page import="priv.barrow.oes.portlet.search.TeacherSearchTerms"%>
<%@page import="priv.barrow.oes.portlet.search.TeacherSearch"%>

<%
PortletURL portletURL = renderResponse.createRenderURL();
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
    <aui:input name="keyword" type="text" />
    <liferay-ui:search-container searchContainer="<%= new TeacherSearch(renderRequest, portletURL) %>"
        emptyResultsMessage="no-teacher-found"
    >
        <%
            TeacherSearchTerms searchTerms = (TeacherSearchTerms) searchContainer.getSearchTerms();
        %>
        <liferay-ui:search-container-results>
        <%
            results = TeacherUtil.getTeachers(TeacherUserLinkLocalServiceUtil.searchTeacherUsers(keyword));
            total = results.size();
            searchContainer.setTotal(total);
            searchContainer.setResults(results);
        %>
        </liferay-ui:search-container-results>
        <liferay-ui:search-container-row className="priv.barrow.oes.portlet.model.Teacher" escapedModel="<%= true %>" modelVar="teacher">
            <liferay-ui:search-container-column-text name="teacher-number" orderable="false" orderableProperty="userId" property="number" />
            <liferay-ui:search-container-column-text name="first-name" orderable="false" property="firstName" />
            <liferay-ui:search-container-column-text name="last-name" orderable="false" property="lastName" />
            <liferay-ui:search-container-column-text name="email" orderable="false" property="email" />
            <portlet:actionURL name="choose" var="chooseURL">
                <portlet:param name="teacherNumber" value="${teacher.number }" />
            </portlet:actionURL>
            <liferay-ui:icon-delete image="delete" url="${choose }" />
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator />
    </liferay-ui:search-container>
</aui:form>
