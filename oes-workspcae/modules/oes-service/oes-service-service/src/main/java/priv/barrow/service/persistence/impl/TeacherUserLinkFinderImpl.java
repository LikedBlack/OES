package priv.barrow.service.persistence.impl;

import java.math.BigInteger;
import java.util.List;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import priv.barrow.model.TeacherUserLink;
import priv.barrow.model.impl.TeacherUserLinkImpl;
import priv.barrow.service.persistence.TeacherUserLinkFinder;

public class TeacherUserLinkFinderImpl extends TeacherUserLinkFinderBaseImpl implements TeacherUserLinkFinder {

    private Log LOG = LogFactoryUtil.getLog(TeacherUserLinkFinderImpl.class);
    private final String ENTITY_NAME = "TeacherUserLink";
    private final String SEARCH_TEACHER_USERS = TeacherUserLinkFinder.class.getName()
            + ".searchTeacherUsers";
    private final String COUNT_SEARCH_TEACHER_USERS = TeacherUserLinkFinder.class.getName()
            + ".countSearchTeacherUsers";

    @SuppressWarnings("unchecked")
    @Override
    public List<TeacherUserLink> searchTeacherUsers(String keyword, long start, long end) {
        if (Validator.isNull(keyword)) {
            keyword = StringPool.BLANK;
        }

        long limit = end - start;
        long offset = start;

        String sql = CustomSQLUtil.get(getClass(), SEARCH_TEACHER_USERS);

        Session session = openSession();
        List<TeacherUserLink> teacherUserLinks = null;
        try {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(ENTITY_NAME, TeacherUserLinkImpl.class);
            // Changes keyword to lower case to ignore case.
            query.setString(0, keyword.toLowerCase());
            query.setLong(1, limit);
            query.setLong(2, offset);
            teacherUserLinks = query.list();
        } catch (Exception e) {
            LOG.error(String.format("Excute sql meet an error. SQL: %s", sql), e);
        } finally {
            closeSession(session);
        }

        return teacherUserLinks;
    }

    @Override
    public int countSearchTeacherUsers(String keyword) {
        if (Validator.isNull(keyword)) {
            keyword = StringPool.BLANK;
        }

        String sql = CustomSQLUtil.get(getClass(), COUNT_SEARCH_TEACHER_USERS);

        Session session = openSession();
        try {
            SQLQuery query = session.createSQLQuery(sql);
            // Changes keyword to lower case to ignore case.
            query.setString(0, keyword.toLowerCase());
            return ((BigInteger) query.uniqueResult()).intValue();
        } catch (Exception e) {
            LOG.error(String.format("Excute sql meet an error. SQL: %s", sql), e);
        } finally {
            closeSession(session);
        }
        return 0;
    }

}
