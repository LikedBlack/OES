package priv.barrow.oes.portlet.reviewexam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import priv.barrow.model.ExamData;
import priv.barrow.oes.portlet.constants.ExamConstants;
import priv.barrow.oes.portlet.constants.StudentConstants;
import priv.barrow.oes.portlet.exception.ParameterException;
import priv.barrow.oes.portlet.model.Exam;
import priv.barrow.oes.portlet.util.ExamUtil;
import priv.barrow.service.ExamDataLocalServiceUtil;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Student",
        "com.liferay.portlet.instanceable=true",
        "com.liferay.portlet.ajaxable=true",
        "com.liferay.portlet.requires-namespaced-parameters=false",
        "javax.portlet.display-name=Review Exam",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.footer-portlet-css=/css/reviewexam/review_exam.css",
        "com.liferay.portlet.footer-portlet-javascript=/js/reviewexam/review_exam.js",
        "javax.portlet.init-param.view-template=/html/reviewexam/review_exam.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class ReviewExamPortlet extends MVCPortlet {

    private Log LOG = LogFactoryUtil.getLog(ReviewExamPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        HttpServletRequest request =
                PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
        long examId = ParamUtil.get(request, ExamConstants.EXAM_ID, ExamConstants.INEXISTENT_EXAM_ID);
        long studentId = ParamUtil.get(request, StudentConstants.STUDENT_ID, StudentConstants.INEXISTENT_STUDENT_ID);

        if (examId == ExamConstants.INEXISTENT_EXAM_ID || studentId == StudentConstants.INEXISTENT_STUDENT_ID) {
            throw new ParameterException("Lack of parameters. examId or studentId.");
        }

        DDLRecord examRecord = null;
        try {
            examRecord = DDLRecordLocalServiceUtil.getDDLRecord(examId);
        } catch (PortalException e) {
            LOG.error(String.format("Get examRecord by examRecordId failed. examId: [%d]", examId), e);
            include(helpTemplate, renderRequest, renderResponse);
            return;
        }

        Exam exam = ExamUtil.getExam(examRecord);
        List<ExamData> examDatas = ExamDataLocalServiceUtil.findByExamIdAndStudentId(examId, studentId);
        Map<Long, String> resultMap = ExamUtil.convertToResultMap(examDatas);

        User student = null;
        try {
            student = UserLocalServiceUtil.getUser(studentId);
        } catch (PortalException e) {
            LOG.error(String.format("Get student by studentId failed. studentId: [%d]", studentId), e);
        }

        renderRequest.setAttribute(ExamConstants.EXAM, exam);
        renderRequest.setAttribute(ExamConstants.RESULT_MAP, resultMap);
        renderRequest.setAttribute(StudentConstants.STUDENT, student);

        super.doView(renderRequest, renderResponse);
    }

}
