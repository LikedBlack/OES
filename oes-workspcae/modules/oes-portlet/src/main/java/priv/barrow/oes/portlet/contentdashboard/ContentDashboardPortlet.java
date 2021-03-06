package priv.barrow.oes.portlet.contentdashboard;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;

import priv.barrow.model.QuestionRecordLink;
import priv.barrow.oes.portlet.constants.QuestionConstants;
import priv.barrow.oes.portlet.model.Question;
import priv.barrow.oes.portlet.util.DataUtil;
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
        "com.liferay.portlet.footer-portlet-javascript=/js/contentdashboard/content_dashboard.js",
        "javax.portlet.init-param.view-template=/html/contentdashboard/content_dashboard.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class ContentDashboardPortlet extends MVCPortlet {

    private final int RECENT_QUESTION_COUNT = 5;
    private final Log LOG = LogFactoryUtil.getLog(ContentDashboardPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        List<QuestionRecordLink> questionRecordLinks =
                QuestionRecordLinkLocalServiceUtil.findRecentUpdateQuestionReocrdLinks(RECENT_QUESTION_COUNT);
        List<Question> recentUpdateQuestions = QuestionUtil.getQuestions(questionRecordLinks);

        renderRequest.setAttribute(QuestionConstants.RECENT_UPDATE_QUESTIONS, recentUpdateQuestions);
        super.doView(renderRequest, renderResponse);
    }

    @ProcessAction(name = "importQuestion")
    public void importQuestion(ActionRequest actionRequest, ActionResponse actionResponse) {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
        ServiceContext serviceContext = null;
        try {
            serviceContext = ServiceContextFactory.getInstance(DDLRecord.class.getName(), actionRequest);
        } catch (PortalException e) {
            LOG.error("Get ServiceContext from UploadPortletRequest failed.", e);
            return;
        }
        DataUtil.questionImport("/home/barrow/Desktop/questions.json", serviceContext);
    }

}
