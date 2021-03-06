package priv.barrow.oes.portlet.viewqustions;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import priv.barrow.model.QuestionRecordLink;
import priv.barrow.oes.portlet.constants.QuestionConstants;
import priv.barrow.service.QuestionRecordLinkLocalServiceUtil;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Content Admin",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=View Questions",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/viewquestions/view_questions.css",
        "com.liferay.portlet.footer-portlet-javascript=/js/viewquestions/view_questions.js",
        "javax.portlet.init-param.view-template=/html/viewquestions/view_questions.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class ViewQuestionsPortlet extends MVCPortlet {

    private Log LOG = LogFactoryUtil.getLog(ViewQuestionsPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        super.doView(renderRequest, renderResponse);
    }

    @ProcessAction(name = QuestionConstants.DELETE_QUESTION)
    public void deleteQuestion(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
        long questionOrder = ParamUtil.get(actionRequest, QuestionConstants.QUESTION_ORDER,
                                QuestionConstants.INEXISTENT_QUESTION_ORDER);
        if (questionOrder == QuestionConstants.INEXISTENT_QUESTION_ORDER) {
            return;
        }

        QuestionRecordLink questionRecordLink = null;
        try {
            questionRecordLink = QuestionRecordLinkLocalServiceUtil.getQuestionRecordLink(questionOrder);
        } catch (PortalException e) {
            LOG.error(String.format("Get QuestionRecordLink failed. questionOrder: [%d]", questionOrder), e);
            return;
        }

        questionRecordLink.setActive(false);
        QuestionRecordLinkLocalServiceUtil.updateQuestionRecordLink(questionRecordLink);

        actionResponse.sendRedirect("/view-questions");

    }

}
