package priv.barrow.oes.portlet.signup.controller;

import java.io.IOException;
import java.util.Locale;

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
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import priv.barrow.oes.portlet.constants.CommanConstants;
import priv.barrow.oes.portlet.exception.EmailRegisteredException;
import priv.barrow.oes.portlet.signup.constant.Constants;
import priv.barrow.oes.portlet.signup.model.SignUpModel;

/**
 * @author barrow
 */
@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Sign Up",
        "javax.portlet.init-param.template-path=/",
        "com.liferay.portlet.header-portlet-css=/css/signup/sign_up.css",
        "com.liferay.portlet.header-portlet-javascript=/js/signup/sign_up.js",
        "javax.portlet.init-param.view-template=/html/signup/sign_up.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)

public class SignUpPortlet extends MVCPortlet {

    private static final Log LOG = LogFactoryUtil.getLog(SignUpPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        if (themeDisplay.isSignedIn()) {
            include(CommanConstants.REDIRECT_HOME_JSP, renderRequest, renderResponse);
            return;
        }

        super.doView(renderRequest, renderResponse);
    }

    @ProcessAction(name = "signUp")
    public void signUp(ActionRequest actionRequest, ActionResponse actionResponse) {
        SignUpModel signUpModel = getSignUpModel(actionRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();

        try {
            verifyEmail(actionRequest, companyId, signUpModel.getEmail());
        } catch (EmailRegisteredException e) {
            LOG.error(String.format(e.getMessage() + "Email: [%s]", signUpModel.getEmail()), e);
            return;
        }

        Locale defaultLocale = Locale.getDefault();
        Role role = null;
        try {
            role = RoleLocalServiceUtil.getRole(companyId, signUpModel.getRole());
        } catch (PortalException e) {
            LOG.error(String.format("Get role by role name [%s] failed.", signUpModel.getRole()), e);
            return;
        }

        try {
            User newUser = UserLocalServiceUtil.addUserWithWorkflow(Constants.DEFAULT_CREATOR_USER_ID,
                    companyId,
                    false,
                    signUpModel.getPassword(),
                    signUpModel.getConfirmPassword(),
                    true,
                    signUpModel.getFirstName(),
                    signUpModel.getEmail(),
                    0,
                    null,
                    defaultLocale,
                    signUpModel.getFirstName(),
                    null,
                    signUpModel.getLastName(),
                    0,
                    0,
                    false,
                    Constants.DEFAULT_BIRTH_MONTH,
                    Constants.DEFAULT_BIRTH_DAY,
                    Constants.DEFAULT_BIRTH_YEAR,
                    StringPool.BLANK,
                    null,
                    null,
                    new long[]{role.getRoleId()},
                    null,
                    true,
                    null);
        } catch (PortalException e) {
            LOG.error("Create new user failed.");
            return;
        }

        try {
            actionResponse.sendRedirect("/sign-in");
        } catch (IOException e) {
            LOG.error("Send redirect to sign in page failed.");
        }

    }

    private SignUpModel getSignUpModel(ActionRequest actionRequest) {
        String firstName = ParamUtil.get(actionRequest, Constants.FIRST_NAME, StringPool.BLANK);
        String lastName = ParamUtil.get(actionRequest, Constants.LAST_NAME, StringPool.BLANK);
        String role = ParamUtil.get(actionRequest, Constants.ROLE, StringPool.BLANK);
        String email = ParamUtil.get(actionRequest, Constants.EMAIL, StringPool.BLANK);
        String confirmEmail = ParamUtil.get(actionRequest, Constants.CONFIRM_EMAIL, StringPool.BLANK);
        String password = ParamUtil.get(actionRequest, Constants.PASSWORD, StringPool.BLANK);
        String confirmPassword = ParamUtil.get(actionRequest, Constants.CONFIRM_PASSWORD, StringPool.BLANK);

        SignUpModel signUpModel =
                new SignUpModel(firstName, lastName, email, confirmEmail, role, password, confirmPassword);

        return signUpModel;
    }

    private boolean verifyEmail(ActionRequest actionRequest, long companyId, String email)
                throws EmailRegisteredException {

        User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);
        if (Validator.isNotNull(user)) {
            EmailRegisteredException exception = new EmailRegisteredException("This email has been registered.");
            SessionErrors.add(actionRequest, exception.getClass());
        }

        return true;
    }

}