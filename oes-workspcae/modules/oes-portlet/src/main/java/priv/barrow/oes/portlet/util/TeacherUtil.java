package priv.barrow.oes.portlet.util;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import priv.barrow.model.TeacherUserLink;
import priv.barrow.oes.portlet.model.Teacher;

public class TeacherUtil {

    private static Log LOG = LogFactoryUtil.getLog(TeacherUtil.class);

    public static List<Teacher> getTeachers(List<TeacherUserLink> teacherUserLinks) {
        if (Validator.isNull(teacherUserLinks)) {
            return null;
        }

        List<Teacher> teachers = new ArrayList<>();
        for (TeacherUserLink teacherUserLink : teacherUserLinks) {
            Teacher teacher = getTeacher(teacherUserLink);

            if (Validator.isNotNull(teacher)) {
                teachers.add(teacher);
            }
        }

        return teachers;
    }

    public static Teacher getTeacher(TeacherUserLink teacherUserLink) {
        if (Validator.isNull(teacherUserLink)) {
            return null;
        }

        long teacherNumber = teacherUserLink.getTeacherNumber();
        long userId = teacherUserLink.getUserId();
        User user = null;
        try {
            user = UserLocalServiceUtil.getUser(userId);
        } catch (PortalException e) {
            LOG.error(String.format("Get user by userId [%d] failed.", userId), e);
            return null;
        }

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmailAddress();

        Teacher teacher = new Teacher(userId, teacherNumber, firstName, lastName, email);

        return teacher;
    }

}
