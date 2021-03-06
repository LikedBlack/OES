package priv.barrow.oes.portlet.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import priv.barrow.model.ExamQuestionLink;
import priv.barrow.model.QuestionRecordLink;
import priv.barrow.oes.portlet.constants.QuestionConstants;
import priv.barrow.oes.portlet.model.Question;
import priv.barrow.service.QuestionRecordLinkLocalServiceUtil;

public class QuestionUtil {

    private static Log LOG = LogFactoryUtil.getLog(QuestionUtil.class);

    public static List<Question> getQuestions(List<QuestionRecordLink> questionRecordLinks) {
        if (Validator.isNull(questionRecordLinks)) {
            return null;
        }

        List<Question> questions = new ArrayList<>();
        for (QuestionRecordLink link : questionRecordLinks) {

            Question question = getQuestion(link);
            if (Validator.isNotNull(question)) {
                questions.add(question);
            }
        }

        return questions;
    }

    public static Question getQuestion(QuestionRecordLink questionRecordLink) {
        if (Validator.isNull(questionRecordLink)) {
            return null;
        }

        DDLRecord record = null;
        long recordId = questionRecordLink.getDdlRecordId();

        try {
            record = DDLRecordLocalServiceUtil.getDDLRecord(recordId);
        } catch (PortalException e) {
            LOG.error(String.format("Get DDLRcord by recordId [%d] failed.", recordId), e);
        }

        if (Validator.isNull(record)) {
            return null;
        }

        DDMFormValues ddmFormValues = null;
        try {
            ddmFormValues = record.getDDMFormValues();
        } catch (PortalException e) {
            LOG.error(String.format("get DDMFormValues from record failed. recordId: [%d]", recordId));
        }

        if (Validator.isNull(ddmFormValues)) {
            return null;
        }

        Locale locale = LocaleUtil.getDefault();
        Map<String, List<DDMFormFieldValue>> fieldValues = ddmFormValues.getDDMFormFieldValuesMap();

        DDMFormFieldValue descriptionFormFieldValue = fieldValues.get(QuestionConstants.DESCRIPTION_FIELD_NAME).get(0);
        DDMFormFieldValue optionAFormFieldValue = fieldValues.get(QuestionConstants.OPTION_A_FIELD_NAME).get(0);
        DDMFormFieldValue optionBFormFieldValue = fieldValues.get(QuestionConstants.OPTION_B_FIELD_NAME).get(0);
        DDMFormFieldValue optionCFormFieldValue = fieldValues.get(QuestionConstants.OPTION_C_FIELD_NAME).get(0);
        DDMFormFieldValue optionDFormFieldValue = fieldValues.get(QuestionConstants.OPTION_D_FIELD_NAME).get(0);
        DDMFormFieldValue answerFormFieldValue = fieldValues.get(QuestionConstants.ANSWER_FIELD_NAME).get(0);

        Value descriptionValue = descriptionFormFieldValue.getValue();
        Value optionAValue = optionAFormFieldValue.getValue();
        Value optionBValue = optionBFormFieldValue.getValue();
        Value optionCValue = optionCFormFieldValue.getValue();
        Value optionDValue = optionDFormFieldValue.getValue();
        Value answerValue = answerFormFieldValue.getValue();

        String description = descriptionValue.getString(locale);
        String optionA = optionAValue.getString(locale);
        String optionB = optionBValue.getString(locale);
        String optionC = optionCValue.getString(locale);
        String optionD = optionDValue.getString(locale);
        String answer = answerValue.getString(locale);
        answer = answer.substring(2, 3);

        long creatorId = record.getUserId();
        String creatorName = record.getUserName();
        Date createdTime = record.getCreateDate();
        Date modifiedTime = record.getModifiedDate();

        Question question = new Question(
                        questionRecordLink.getQuestionOrder(),
                        description,
                        optionA,
                        optionB,
                        optionC,
                        optionD,
                        answer,
                        creatorId,
                        creatorName,
                        createdTime,
                        modifiedTime);

        return question;
    }

    public static Question getQuestion(ExamQuestionLink examQuestionLink) {
        long questionRecordId = examQuestionLink.getQuestionRecordId();
        String version = examQuestionLink.getQuestionRecordVersion();

        DDLRecordVersion ddlRecordVersion = null;
        try {
            ddlRecordVersion = DDLRecordVersionLocalServiceUtil.getRecordVersion(questionRecordId, version);
        } catch (PortalException e) {
            LOG.error(String.format(
                    "Get DDLRecordVersion by ddlRecordId and ddlRecordVersion failed. record: [%d], version: [%s]",
                    questionRecordId, version), e);
            return null;
        }

        DDMFormValues ddmFormValues = null;
        try {
            ddmFormValues = ddlRecordVersion.getDDMFormValues();
        } catch (StorageException e) {
            LOG.error(String.format("Get DDMFormValues from DDLRecordVersion failed. recordVersionId: [%d]",
                    ddlRecordVersion.getRecordVersionId()), e);
        }

        Locale locale = LocaleUtil.getDefault();
        Map<String, List<DDMFormFieldValue>> fieldValues = ddmFormValues.getDDMFormFieldValuesMap();

        DDMFormFieldValue descriptionFormFieldValue = fieldValues.get(QuestionConstants.DESCRIPTION_FIELD_NAME).get(0);
        DDMFormFieldValue optionAFormFieldValue = fieldValues.get(QuestionConstants.OPTION_A_FIELD_NAME).get(0);
        DDMFormFieldValue optionBFormFieldValue = fieldValues.get(QuestionConstants.OPTION_B_FIELD_NAME).get(0);
        DDMFormFieldValue optionCFormFieldValue = fieldValues.get(QuestionConstants.OPTION_C_FIELD_NAME).get(0);
        DDMFormFieldValue optionDFormFieldValue = fieldValues.get(QuestionConstants.OPTION_D_FIELD_NAME).get(0);
        DDMFormFieldValue answerFormFieldValue = fieldValues.get(QuestionConstants.ANSWER_FIELD_NAME).get(0);

        Value descriptionValue = descriptionFormFieldValue.getValue();
        Value optionAValue = optionAFormFieldValue.getValue();
        Value optionBValue = optionBFormFieldValue.getValue();
        Value optionCValue = optionCFormFieldValue.getValue();
        Value optionDValue = optionDFormFieldValue.getValue();
        Value answerValue = answerFormFieldValue.getValue();

        String description = descriptionValue.getString(locale);
        String optionA = optionAValue.getString(locale);
        String optionB = optionBValue.getString(locale);
        String optionC = optionCValue.getString(locale);
        String optionD = optionDValue.getString(locale);
        String answer = answerValue.getString(locale);
        answer = answer.substring(2, 3);

        QuestionRecordLink questionRecordLink =
                QuestionRecordLinkLocalServiceUtil.findByDdlRecordId(questionRecordId).get(0);

        DDLRecord record = DDLRecordLocalServiceUtil.fetchDDLRecord(questionRecordId);

        long creatorId = record.getUserId();
        String creatorName = record.getUserName();
        Date createdTime = record.getCreateDate();
        Date modifiedTime = record.getModifiedDate();

        Question question = new Question(
                        questionRecordLink.getQuestionOrder(),
                        description,
                        optionA,
                        optionB,
                        optionC,
                        optionD,
                        answer,
                        creatorId,
                        creatorName,
                        createdTime,
                        modifiedTime);

        return question;
    }

}
