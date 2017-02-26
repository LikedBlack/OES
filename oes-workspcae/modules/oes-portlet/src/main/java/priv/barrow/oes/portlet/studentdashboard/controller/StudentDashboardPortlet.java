package priv.barrow.oes.portlet.studentdashboard.controller;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import priv.barrow.model.StudentTeacherLink;
import priv.barrow.service.StudentTeacherLinkLocalServiceUtil;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Student",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Student Dashboard",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/studentdashboard/student_dashboard.css",
        "com.liferay.portlet.footer-portlet-javascript=/js/studentdashboard/student_dashboard.js",
        "javax.portlet.init-param.view-template=/html/studentdashboard/student_dashboard.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class StudentDashboardPortlet extends MVCPortlet {

    private final Log LOG = LogFactoryUtil.getLog(StudentDashboardPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();

        StudentTeacherLink studentTeacherLink = null;
        try {
            studentTeacherLink = StudentTeacherLinkLocalServiceUtil.getStudentTeacherLink(userId);
        } catch (PortalException e) {
            LOG.error(String.format("Get StudentTeacherLink by studentId [%d] faield.", userId), e);
        }

        boolean haveTeacher = false;
        long teacherId = 0L;
        if (Validator.isNotNull(studentTeacherLink)) {
            teacherId = studentTeacherLink.getTeacherId();
            if (teacherId > 0) {
                haveTeacher = true;
            }
        }

        renderRequest.setAttribute("haveTeacher", haveTeacher);

        super.doView(renderRequest, renderResponse);
    }

}
