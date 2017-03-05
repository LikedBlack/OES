package priv.barrow.oes.portlet.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import priv.barrow.oes.portlet.model.Exam;

public class ExamUtil {

    private static final Log LOG = LogFactoryUtil.getLog(ExamUtil.class);

    public static Map<String, Object> getExamValueMap(Fields fields) {
        Locale defaultLocale = LocaleUtil.getDefault();
        Map<String, Object> valueMap = new HashMap<>();

        Field questionCountField = fields.get("QuestionCount");
        Field examNameField = fields.get("ExamName");

        int questionCount = (int) questionCountField.getValue(defaultLocale);
        String examName = (String) examNameField.getValue(defaultLocale);

        valueMap.put("questionCount", questionCount);
        valueMap.put("examName", examName);

        return valueMap;
    }

    public static List<Exam> getExams(List<DDLRecord> examRecords) {
        List<Exam> exams = new ArrayList<>();
        for (DDLRecord record : examRecords) {
            long examRecordId = record.getRecordId();
//            List<ExamQuestionLink> examQuestionLinks = ExamQuestionLinkUtil.findByexamRecordId(examRecordId);
//            int questionCount = examQuestionLinks.size();
//            List<Question> questions = QuestionUtil.getQuestions(examQuestionLinks);
            String name = StringPool.BLANK;
            long creatorId = record.getUserId();
            Date createdTime = record.getCreateDate();
            User creator = null;
            try {
                creator = UserLocalServiceUtil.getUser(creatorId);
            } catch (PortalException e) {
                e.printStackTrace();
            }
            String creatorName = creator.getFullName();

//            Exam exam = new Exam(name, questionCount, questions, creatorId, creatorName, createdTime);
            System.out.println("-------------------");
        }

        return exams;
    }

}
