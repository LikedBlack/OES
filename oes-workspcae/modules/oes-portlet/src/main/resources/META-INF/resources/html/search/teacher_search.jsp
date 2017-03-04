<%@page import="priv.barrow.oes.portlet.search.TeacherDisplayTerms"%>
<%@page import="priv.barrow.service.TeacherUserLinkLocalServiceUtil"%>
<%@page import="priv.barrow.oes.portlet.util.TeacherUtil"%>
<%@page import="priv.barrow.oes.portlet.search.TeacherSearchTerms"%>
<%@page import="priv.barrow.oes.portlet.search.TeacherSearch"%>
<%@page import="javax.portlet.PortletURL"%>

<%
    PortletURL portletURL = renderResponse.createRenderURL();
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
    <aui:input name="keyword" type="text" />
    <liferay-ui:search-container searchContainer="<%= new TeacherSearch(renderRequest, portletURL) %>">
        <%
            TeacherSearchTerms searchTerms = (TeacherSearchTerms) searchContainer.getSearchTerms();
            TeacherDisplayTerms displayTerms = (TeacherDisplayTerms) searchContainer.getDisplayTerms();
        %>

        <liferay-ui:input-search cssClass="form-search" name="<%= displayTerms.SEARCH_KEYWORD %>" placeholder="<%= displayTerms.SEARCH_KEYWORD %>" />

        <liferay-ui:search-container-results>
        <%
            results = TeacherUtil.getTeachers(TeacherUserLinkLocalServiceUtil.searchTeacherUsers(searchTerms.getKeyword(), searchContainer.getStart(), searchContainer.getEnd()));
            total = TeacherUserLinkLocalServiceUtil.countSearchTeacherUsers(searchTerms.getKeyword());
            searchContainer.setTotal(total);
            searchContainer.setResults(results);
        %>
        </liferay-ui:search-container-results>
        <liferay-ui:search-container-row className="priv.barrow.oes.portlet.model.Teacher" escapedModel="<%= true %>" modelVar="teacher">
            <liferay-ui:search-container-column-text name="teacher-number" orderableProperty="number" property="number" />
            <liferay-ui:search-container-column-text name="first-name" property="firstName" />
            <liferay-ui:search-container-column-text name="last-name" property="lastName" />
            <liferay-ui:search-container-column-text name="email" property="email" />
            <portlet:actionURL name="chooseTeacher" var="chooseTeacherURL">
                <portlet:param name="teacherId" value="${teacher.userId }" />
            </portlet:actionURL>
            <%
                final String href = "location.href='" + chooseTeacherURL.toString()+"'";
            %>
            <liferay-ui:search-container-column-button href="<%= href %>" name="choose"  />
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator />
    </liferay-ui:search-container>
</aui:form>