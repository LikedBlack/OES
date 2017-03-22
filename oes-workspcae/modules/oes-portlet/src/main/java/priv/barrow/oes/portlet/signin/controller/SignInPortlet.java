package priv.barrow.oes.portlet.signin.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author barrow
 */
@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Sign In",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/signin/sign_in.css",
        "com.liferay.portlet.header-portlet-javascript=/js/signin/sign_in.js",
        "javax.portlet.init-param.view-template=/html/signin/sign_in.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class SignInPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        boolean isSignedIn = themeDisplay.isSignedIn();
        if (isSignedIn) {
            String fullName = themeDisplay.getUser().getFullName();
            renderRequest.setAttribute("fullName", fullName);
        }

        renderRequest.setAttribute("isSignedIn", isSignedIn);
        super.doView(renderRequest, renderResponse);
    }

    @ProcessAction(name = "login")
    public void login(ActionRequest actionRequest, ActionResponse actionResponse) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
