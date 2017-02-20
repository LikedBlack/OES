package priv.barrow.oes.portlet.teacherdashboard.controller;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Teacher",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Teacher Dashboard",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/teacherdashboard/teacher_dashboard.css",
        "com.liferay.portlet.header-portlet-javascript=/js/teacherdashboard/teacher_dashboard.js",
        "javax.portlet.init-param.view-template=/html/teacherdashboard/teacher_dashboard.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class TeacherDashboardPortlet extends MVCPortlet {

}