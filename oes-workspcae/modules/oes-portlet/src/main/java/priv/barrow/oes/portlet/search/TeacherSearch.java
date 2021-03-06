package priv.barrow.oes.portlet.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.search.SearchContainer;

import priv.barrow.model.TeacherUserLink;

public class TeacherSearch extends SearchContainer<TeacherUserLink> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String EMPTY_RESULT_MESSAGE = "no-teachers-found";

    static List<String> headerNames = new ArrayList<String>();

    static {
        headerNames.add("Teacher Number");
        headerNames.add("First Name");
        headerNames.add("Last Name");
        headerNames.add("Email");
        headerNames.add("Action");
    }

    public TeacherSearch(PortletRequest portletRequest, PortletURL portletURL) {
        super(portletRequest, new TeacherDisplayTerms(portletRequest), new TeacherSearchTerms(portletRequest),
                DEFAULT_CUR_PARAM, DEFAULT_DELTA, portletURL, headerNames, EMPTY_RESULT_MESSAGE);

        TeacherDisplayTerms displayTerms = (TeacherDisplayTerms) getDisplayTerms();
        portletURL.setParameter(TeacherDisplayTerms.SEARCH_KEYWORD, displayTerms.getKeyword());
    }

}
