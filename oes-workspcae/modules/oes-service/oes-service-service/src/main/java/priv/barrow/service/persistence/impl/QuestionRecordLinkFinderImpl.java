package priv.barrow.service.persistence.impl;

import java.util.List;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import priv.barrow.model.QuestionRecordLink;
import priv.barrow.model.impl.QuestionRecordLinkImpl;
import priv.barrow.service.persistence.QuestionRecordLinkFinder;

public class QuestionRecordLinkFinderImpl extends QuestionRecordLinkFinderBaseImpl implements QuestionRecordLinkFinder {

    private Log LOG = LogFactoryUtil.getLog(QuestionRecordLinkFinderImpl.class);
    private final String ENTITY_NAME = "QuestionRecordLink";
    private final String FIND_RECENT_UPDATE_QUESTION_REOCRD_LINKS = QuestionRecordLinkFinder.class.getName()
            + ".findRecentUpdateQuestionReocrdLinks";

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionRecordLink> findRecentUpdateQuestionReocrdLinks(int count) {

        String sql = CustomSQLUtil.get(getClass(), FIND_RECENT_UPDATE_QUESTION_REOCRD_LINKS);
        Session session = openSession();
        List<QuestionRecordLink> questionRecordLinks = null;

        try {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(ENTITY_NAME, QuestionRecordLinkImpl.class);
            query.setInteger(0, count);
            questionRecordLinks = query.list();
        } catch (Exception e) {
            LOG.error(String.format("Excute sql meet an error. SQL: %s", sql), e);
        } finally {
            closeSession(session);
        }

        return questionRecordLinks;
    }

}
