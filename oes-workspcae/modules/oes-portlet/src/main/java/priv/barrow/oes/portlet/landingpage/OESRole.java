package priv.barrow.oes.portlet.landingpage;

import com.liferay.portal.kernel.model.Role;

public enum OESRole {
    STUDENT("Student"), TEACHER("Teacher"), CONTENT_ADMIN("Content Admin"), ADMINISTRATOR("Administrator");

    private String roleName;

    OESRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static boolean contains(Role role) {
        switch(role.getName()) {
            case "Student":
                return true;
            case "Teacher":
                return true;
            case "Content Admin":
                return true;
            case "Administrator":
                return true;
        }

        return false;
    }

}
