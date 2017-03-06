<%@page import="java.sql.Timestamp"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="priv.barrow.oes.portlet.util.QuestionUtil"%>
<%@page import="priv.barrow.service.QuestionRecordLinkLocalServiceUtil"%>
<%@page import="priv.barrow.oes.portlet.search.QuestionDisplayTerms"%>
<%@page import="priv.barrow.oes.portlet.search.QuestionSearchTerms"%>
<%@page import="priv.barrow.oes.portlet.search.QuestionSearch"%>
<%@page import="priv.barrow.oes.portlet.model.Question"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%
    ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    Question question = (Question) row.getObject();
    long questionOrder = question.getOrder();
%>

<liferay-ui:icon-menu>
    <liferay-ui:icon image="edit" url="/edit-quesiton?questionOrder=<%= questionOrder %>" target="_blank"/>
    <liferay-ui:icon image="view" url="/question-detail?questionOrder=<%= questionOrder %>" target="_blank" />
</liferay-ui:icon-menu>
