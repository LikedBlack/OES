package priv.barrow.oes.portlet.addquestion.controller;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Content Admin",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Add Question",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/addquestion/add_question.css",
        "com.liferay.portlet.header-portlet-javascript=/js/addquestion/add_question.js",
        "javax.portlet.init-param.view-template=/html/addquestion/add_question.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class AddQuestionPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        long classNameId = PortalUtil.getClassNameId(DDMStructure.class);
        System.out.println("----------------" + classNameId);
        renderRequest.setAttribute("classNameId", classNameId);

        super.doView(renderRequest, renderResponse);
    }

}
