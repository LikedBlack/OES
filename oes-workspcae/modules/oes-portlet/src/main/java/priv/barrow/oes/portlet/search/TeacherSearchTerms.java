package priv.barrow.oes.portlet.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

public class TeacherSearchTerms extends TeacherDisplayTerms {

    public TeacherSearchTerms(PortletRequest portletRequest) {
        super(portletRequest);

        keyword = DAOParamUtil.getString(portletRequest, SEARCH_KEYWORD);
    }

}
