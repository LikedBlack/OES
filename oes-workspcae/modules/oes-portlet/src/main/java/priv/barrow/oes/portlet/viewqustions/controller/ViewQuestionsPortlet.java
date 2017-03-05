package priv.barrow.oes.portlet.viewqustions.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import priv.barrow.oes.portlet.model.Question;
import priv.barrow.oes.portlet.util.QuestionUtil;
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

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        Timestamp updateDateStart = new Timestamp(0);
        Timestamp updateDateEnd = new Timestamp(System.currentTimeMillis());
        List<Question> questions = QuestionUtil.getQuestions(QuestionRecordLinkLocalServiceUtil.searchQuestionReocrdLinks(0, 15, updateDateStart, updateDateEnd, "h", "Ba", 100, 0));
        super.doView(renderRequest, renderResponse);
    }

}
