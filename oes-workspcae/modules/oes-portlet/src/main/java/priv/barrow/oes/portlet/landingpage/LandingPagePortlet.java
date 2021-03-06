package priv.barrow.oes.portlet.landingpage;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=Tools",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Loading...",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/html/landingpage/landing_page.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class LandingPagePortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();
        String landingPage = null;
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (OESRole.contains(role)) {
                ExpandoBridge expandoBridge = role.getExpandoBridge();
                landingPage = (String) expandoBridge.getAttribute("Landing Page", false);
                if (Validator.isNull(landingPage) || Validator.isBlank(landingPage)) {
                    landingPage = "/home";
                }
                break;
            }
        }

        renderRequest.setAttribute("landingPage", landingPage);
        super.doView(renderRequest, renderResponse);
    }

}
