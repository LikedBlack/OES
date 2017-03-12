package priv.barrow.oes.portlet.reviewexam.controller;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Student",
        "com.liferay.portlet.instanceable=true",
        "com.liferay.portlet.ajaxable=true",
        "com.liferay.portlet.requires-namespaced-parameters=false",
        "javax.portlet.display-name=review Exam",
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

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        // TODO Auto-generated method stub
        super.doView(renderRequest, renderResponse);
    }

}
