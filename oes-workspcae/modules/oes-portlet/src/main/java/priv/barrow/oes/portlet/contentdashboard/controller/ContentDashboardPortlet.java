package priv.barrow.oes.portlet.contentdashboard.controller;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import priv.barrow.model.QuestionRecordLink;
import priv.barrow.oes.portlet.contentdashboard.constans.Constans;
import priv.barrow.oes.portlet.model.Question;
import priv.barrow.oes.portlet.util.QuestionUtil;
import priv.barrow.service.QuestionRecordLinkLocalServiceUtil;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Content Admin",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Content Dashboard",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/contentdashboard/content_dashboard.css",
        "com.liferay.portlet.header-portlet-javascript=/js/contentdashboard/content_dashboard.js",
        "javax.portlet.init-param.view-template=/html/contentdashboard/content_dashboard.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class ContentDashboardPortlet extends MVCPortlet {

    private final int RECENT_QUESTION_COUNT = 5;

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        List<QuestionRecordLink> questionRecordLinks =
                QuestionRecordLinkLocalServiceUtil.findRecentUpdateQuestionReocrdLinks(RECENT_QUESTION_COUNT);
        List<Question> recentUpdateQuestions = QuestionUtil.getQuestions(questionRecordLinks);

        renderRequest.setAttribute(Constans.RECENT_UPDATE_QUESTIONS, recentUpdateQuestions);
        super.doView(renderRequest, renderResponse);
    }

}
