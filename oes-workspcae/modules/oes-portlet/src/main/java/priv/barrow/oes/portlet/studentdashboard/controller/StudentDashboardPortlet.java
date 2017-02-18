package priv.barrow.oes.portlet.studentdashboard.controller;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Student",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Student Dashboard",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/studentdashboard/student_dashboard.css",
        "com.liferay.portlet.header-portlet-javascript=/js/studentdashboard/student_dashboard.js",
        "javax.portlet.init-param.view-template=/html/studentdashboard/student_dashboard.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class StudentDashboardPortlet extends MVCPortlet {

}
