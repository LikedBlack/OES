package priv.barrow.service.persistence.impl;

import java.util.List;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import priv.barrow.model.TeacherUserLink;
import priv.barrow.model.impl.TeacherUserLinkImpl;
import priv.barrow.service.persistence.TeacherUserLinkFinder;

public class TeacherUserLinkFinderImpl extends TeacherUserLinkFinderBaseImpl implements TeacherUserLinkFinder {

    private Log LOG = LogFactoryUtil.getLog(TeacherUserLinkFinderImpl.class);
    private final String ENTITY_NAME = "TeacherUserLink";
    private final String SEARCH_TEACHER_USERS = TeacherUserLinkFinder.class.getName()
            + ".searchTeacherUsers";

    @SuppressWarnings("unchecked")
    @Override
    public List<TeacherUserLink> searchTeacherUsers(String keyword) {
        String sql = CustomSQLUtil.get(getClass(), SEARCH_TEACHER_USERS);

        Session session = openSession();
        List<TeacherUserLink> teacherUserLinks = null;
        try {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(ENTITY_NAME, TeacherUserLinkImpl.class);
            // Changes keyword to lower case to ignore case.
            query.setString(0, keyword.toLowerCase());
            teacherUserLinks = query.list();
        } catch (Exception e) {
            LOG.error(String.format("Excute sql meet an error. SQL: %s", sql), e);
        } finally {
            closeSession(session);
        }

        return teacherUserLinks;
    }

}