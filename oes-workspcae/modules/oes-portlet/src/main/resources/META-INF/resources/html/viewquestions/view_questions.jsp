<%@ page import="java.sql.Timestamp"%>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="priv.barrow.oes.portlet.util.QuestionUtil"%>
<%@ page import="priv.barrow.service.QuestionRecordLinkLocalServiceUtil"%>
<%@ page import="priv.barrow.oes.portlet.search.QuestionDisplayTerms"%>
<%@ page import="priv.barrow.oes.portlet.search.QuestionSearchTerms"%>
<%@ page import="priv.barrow.oes.portlet.search.QuestionSearch"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    PortletURL portletURL = renderResponse.createRenderURL();
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
    <aui:input name="keyword" type="text" />
    <liferay-ui:search-container searchContainer="<%= new QuestionSearch(renderRequest, portletURL) %>">
        <%
            QuestionSearchTerms searchTerms = (QuestionSearchTerms) searchContainer.getSearchTerms();
            QuestionDisplayTerms displayTerms = (QuestionDisplayTerms) searchContainer.getDisplayTerms();
        %>
        <aui:nav-bar>
            <liferay-ui:input-search name="<%= displayTerms.SEARCH_QUESTION_ORDER_START %>" placeholder="Question order start" showButton="false" />
            <liferay-ui:input-search name="<%= displayTerms.SEARCH_QUESTION_ORDER_END %>" placeholder="Question order end" showButton="false" />
            <liferay-ui:input-search name="<%= displayTerms.SEARCH_UPDATE_DATE_START %>" placeholder="Update date start" showButton="false" />
            <liferay-ui:input-search name="<%= displayTerms.SEARCH_UPDATE_DATE_END %>" placeholder="Update date end" showButton="false" />
            <liferay-ui:input-search name="<%= displayTerms.SEARCH_QUESTION_KEYWORD %>" placeholder="Question keyword" showButton="false" />
            <liferay-ui:input-search name="<%= displayTerms.SEARCH_USER_NAME_KEYWORD %>" placeholder="Creator keyword" showButton="false" />
            <aui:button value="search" type="submit" />
        </aui:nav-bar>

        <liferay-ui:search-container-results>
        <%
            results = QuestionUtil.getQuestions(QuestionRecordLinkLocalServiceUtil.searchQuestionReocrdLinks(searchTerms.getQuestionOrderStart(), searchTerms.getQuestionOrderEnd(), searchTerms.getUpdateDateStart(), searchTerms.getUpdateDateEnd(), searchTerms.getQuestionKeyword(), searchTerms.getUserNameKeyword(), searchContainer.getStart(), searchContainer.getEnd()));
            total = QuestionRecordLinkLocalServiceUtil.countSearchQuestionReocrdLinks(searchTerms.getQuestionOrderStart(), searchTerms.getQuestionOrderEnd(), searchTerms.getUpdateDateStart(), searchTerms.getUpdateDateEnd(), searchTerms.getQuestionKeyword(), searchTerms.getUserNameKeyword());
            searchContainer.setTotal(total);
            searchContainer.setResults(results);
        %>
        </liferay-ui:search-container-results>
        <liferay-ui:search-container-row className="priv.barrow.oes.portlet.model.Question" modelVar="question">
            <liferay-ui:search-container-column-text name="order" orderableProperty="order" property="order" />
            <liferay-ui:search-container-column-text name="description" property="description" href="/question-detail?questionOrder=${question.order }" />
            <liferay-ui:search-container-column-text name="Creator" property="creatorName" />
            <liferay-ui:search-container-column-text name="Create time" property="createdTime" />
            <liferay-ui:search-container-column-text name="Update time" property="modifiedTime" />
            <liferay-ui:search-container-column-jsp name="action" path="/META-INF/resources/html/viewquestions/question_action.jsp" />
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator />
    </liferay-ui:search-container>
</aui:form>

