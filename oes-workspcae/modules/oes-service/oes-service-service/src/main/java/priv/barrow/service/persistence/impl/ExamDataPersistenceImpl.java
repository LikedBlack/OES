/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package priv.barrow.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import priv.barrow.exception.NoSuchExamDataException;

import priv.barrow.model.ExamData;
import priv.barrow.model.impl.ExamDataImpl;
import priv.barrow.model.impl.ExamDataModelImpl;

import priv.barrow.service.persistence.ExamDataPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the exam data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExamDataPersistence
 * @see priv.barrow.service.persistence.ExamDataUtil
 * @generated
 */
@ProviderType
public class ExamDataPersistenceImpl extends BasePersistenceImpl<ExamData>
	implements ExamDataPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExamDataUtil} to access the exam data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExamDataImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, ExamDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, ExamDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER =
		new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, ExamDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByExamIdAndStudentIdAndQuestionOrder",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER =
		new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, ExamDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByExamIdAndStudentIdAndQuestionOrder",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ExamDataModelImpl.EXAMID_COLUMN_BITMASK |
			ExamDataModelImpl.STUDENTID_COLUMN_BITMASK |
			ExamDataModelImpl.QUESTIONORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER =
		new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByExamIdAndStudentIdAndQuestionOrder",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the exam datas where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @return the matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentIdAndQuestionOrder(
		long examId, long studentId, long questionOrder) {
		return findByExamIdAndStudentIdAndQuestionOrder(examId, studentId,
			questionOrder, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the exam datas where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @return the range of matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentIdAndQuestionOrder(
		long examId, long studentId, long questionOrder, int start, int end) {
		return findByExamIdAndStudentIdAndQuestionOrder(examId, studentId,
			questionOrder, start, end, null);
	}

	/**
	 * Returns an ordered range of all the exam datas where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentIdAndQuestionOrder(
		long examId, long studentId, long questionOrder, int start, int end,
		OrderByComparator<ExamData> orderByComparator) {
		return findByExamIdAndStudentIdAndQuestionOrder(examId, studentId,
			questionOrder, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the exam datas where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentIdAndQuestionOrder(
		long examId, long studentId, long questionOrder, int start, int end,
		OrderByComparator<ExamData> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER;
			finderArgs = new Object[] { examId, studentId, questionOrder };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER;
			finderArgs = new Object[] {
					examId, studentId, questionOrder,
					
					start, end, orderByComparator
				};
		}

		List<ExamData> list = null;

		if (retrieveFromCache) {
			list = (List<ExamData>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ExamData examData : list) {
					if ((examId != examData.getExamId()) ||
							(studentId != examData.getStudentId()) ||
							(questionOrder != examData.getQuestionOrder())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_EXAMDATA_WHERE);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_EXAMID_2);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_STUDENTID_2);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_QUESTIONORDER_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExamDataModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(examId);

				qPos.add(studentId);

				qPos.add(questionOrder);

				if (!pagination) {
					list = (List<ExamData>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ExamData>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first exam data in the ordered set where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching exam data
	 * @throws NoSuchExamDataException if a matching exam data could not be found
	 */
	@Override
	public ExamData findByExamIdAndStudentIdAndQuestionOrder_First(
		long examId, long studentId, long questionOrder,
		OrderByComparator<ExamData> orderByComparator)
		throws NoSuchExamDataException {
		ExamData examData = fetchByExamIdAndStudentIdAndQuestionOrder_First(examId,
				studentId, questionOrder, orderByComparator);

		if (examData != null) {
			return examData;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("examId=");
		msg.append(examId);

		msg.append(", studentId=");
		msg.append(studentId);

		msg.append(", questionOrder=");
		msg.append(questionOrder);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExamDataException(msg.toString());
	}

	/**
	 * Returns the first exam data in the ordered set where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching exam data, or <code>null</code> if a matching exam data could not be found
	 */
	@Override
	public ExamData fetchByExamIdAndStudentIdAndQuestionOrder_First(
		long examId, long studentId, long questionOrder,
		OrderByComparator<ExamData> orderByComparator) {
		List<ExamData> list = findByExamIdAndStudentIdAndQuestionOrder(examId,
				studentId, questionOrder, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last exam data in the ordered set where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching exam data
	 * @throws NoSuchExamDataException if a matching exam data could not be found
	 */
	@Override
	public ExamData findByExamIdAndStudentIdAndQuestionOrder_Last(long examId,
		long studentId, long questionOrder,
		OrderByComparator<ExamData> orderByComparator)
		throws NoSuchExamDataException {
		ExamData examData = fetchByExamIdAndStudentIdAndQuestionOrder_Last(examId,
				studentId, questionOrder, orderByComparator);

		if (examData != null) {
			return examData;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("examId=");
		msg.append(examId);

		msg.append(", studentId=");
		msg.append(studentId);

		msg.append(", questionOrder=");
		msg.append(questionOrder);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExamDataException(msg.toString());
	}

	/**
	 * Returns the last exam data in the ordered set where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching exam data, or <code>null</code> if a matching exam data could not be found
	 */
	@Override
	public ExamData fetchByExamIdAndStudentIdAndQuestionOrder_Last(
		long examId, long studentId, long questionOrder,
		OrderByComparator<ExamData> orderByComparator) {
		int count = countByExamIdAndStudentIdAndQuestionOrder(examId,
				studentId, questionOrder);

		if (count == 0) {
			return null;
		}

		List<ExamData> list = findByExamIdAndStudentIdAndQuestionOrder(examId,
				studentId, questionOrder, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the exam datas before and after the current exam data in the ordered set where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * @param PK the primary key of the current exam data
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next exam data
	 * @throws NoSuchExamDataException if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData[] findByExamIdAndStudentIdAndQuestionOrder_PrevAndNext(
		long PK, long examId, long studentId, long questionOrder,
		OrderByComparator<ExamData> orderByComparator)
		throws NoSuchExamDataException {
		ExamData examData = findByPrimaryKey(PK);

		Session session = null;

		try {
			session = openSession();

			ExamData[] array = new ExamDataImpl[3];

			array[0] = getByExamIdAndStudentIdAndQuestionOrder_PrevAndNext(session,
					examData, examId, studentId, questionOrder,
					orderByComparator, true);

			array[1] = examData;

			array[2] = getByExamIdAndStudentIdAndQuestionOrder_PrevAndNext(session,
					examData, examId, studentId, questionOrder,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExamData getByExamIdAndStudentIdAndQuestionOrder_PrevAndNext(
		Session session, ExamData examData, long examId, long studentId,
		long questionOrder, OrderByComparator<ExamData> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_EXAMDATA_WHERE);

		query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_EXAMID_2);

		query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_STUDENTID_2);

		query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_QUESTIONORDER_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ExamDataModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(examId);

		qPos.add(studentId);

		qPos.add(questionOrder);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(examData);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExamData> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the exam datas where examId = &#63; and studentId = &#63; and questionOrder = &#63; from the database.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 */
	@Override
	public void removeByExamIdAndStudentIdAndQuestionOrder(long examId,
		long studentId, long questionOrder) {
		for (ExamData examData : findByExamIdAndStudentIdAndQuestionOrder(
				examId, studentId, questionOrder, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(examData);
		}
	}

	/**
	 * Returns the number of exam datas where examId = &#63; and studentId = &#63; and questionOrder = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param questionOrder the question order
	 * @return the number of matching exam datas
	 */
	@Override
	public int countByExamIdAndStudentIdAndQuestionOrder(long examId,
		long studentId, long questionOrder) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER;

		Object[] finderArgs = new Object[] { examId, studentId, questionOrder };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EXAMDATA_WHERE);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_EXAMID_2);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_STUDENTID_2);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_QUESTIONORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(examId);

				qPos.add(studentId);

				qPos.add(questionOrder);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_EXAMID_2 =
		"examData.examId = ? AND ";
	private static final String _FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_STUDENTID_2 =
		"examData.studentId = ? AND ";
	private static final String _FINDER_COLUMN_EXAMIDANDSTUDENTIDANDQUESTIONORDER_QUESTIONORDER_2 =
		"examData.questionOrder = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EXAMIDANDSTUDENTID =
		new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, ExamDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByExamIdAndStudentId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTID =
		new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, ExamDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByExamIdAndStudentId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ExamDataModelImpl.EXAMID_COLUMN_BITMASK |
			ExamDataModelImpl.STUDENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTID = new FinderPath(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByExamIdAndStudentId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the exam datas where examId = &#63; and studentId = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @return the matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentId(long examId, long studentId) {
		return findByExamIdAndStudentId(examId, studentId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the exam datas where examId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @return the range of matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentId(long examId, long studentId,
		int start, int end) {
		return findByExamIdAndStudentId(examId, studentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the exam datas where examId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentId(long examId, long studentId,
		int start, int end, OrderByComparator<ExamData> orderByComparator) {
		return findByExamIdAndStudentId(examId, studentId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the exam datas where examId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching exam datas
	 */
	@Override
	public List<ExamData> findByExamIdAndStudentId(long examId, long studentId,
		int start, int end, OrderByComparator<ExamData> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTID;
			finderArgs = new Object[] { examId, studentId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EXAMIDANDSTUDENTID;
			finderArgs = new Object[] {
					examId, studentId,
					
					start, end, orderByComparator
				};
		}

		List<ExamData> list = null;

		if (retrieveFromCache) {
			list = (List<ExamData>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ExamData examData : list) {
					if ((examId != examData.getExamId()) ||
							(studentId != examData.getStudentId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_EXAMDATA_WHERE);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTID_EXAMID_2);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTID_STUDENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExamDataModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(examId);

				qPos.add(studentId);

				if (!pagination) {
					list = (List<ExamData>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ExamData>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first exam data in the ordered set where examId = &#63; and studentId = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching exam data
	 * @throws NoSuchExamDataException if a matching exam data could not be found
	 */
	@Override
	public ExamData findByExamIdAndStudentId_First(long examId, long studentId,
		OrderByComparator<ExamData> orderByComparator)
		throws NoSuchExamDataException {
		ExamData examData = fetchByExamIdAndStudentId_First(examId, studentId,
				orderByComparator);

		if (examData != null) {
			return examData;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("examId=");
		msg.append(examId);

		msg.append(", studentId=");
		msg.append(studentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExamDataException(msg.toString());
	}

	/**
	 * Returns the first exam data in the ordered set where examId = &#63; and studentId = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching exam data, or <code>null</code> if a matching exam data could not be found
	 */
	@Override
	public ExamData fetchByExamIdAndStudentId_First(long examId,
		long studentId, OrderByComparator<ExamData> orderByComparator) {
		List<ExamData> list = findByExamIdAndStudentId(examId, studentId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last exam data in the ordered set where examId = &#63; and studentId = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching exam data
	 * @throws NoSuchExamDataException if a matching exam data could not be found
	 */
	@Override
	public ExamData findByExamIdAndStudentId_Last(long examId, long studentId,
		OrderByComparator<ExamData> orderByComparator)
		throws NoSuchExamDataException {
		ExamData examData = fetchByExamIdAndStudentId_Last(examId, studentId,
				orderByComparator);

		if (examData != null) {
			return examData;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("examId=");
		msg.append(examId);

		msg.append(", studentId=");
		msg.append(studentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExamDataException(msg.toString());
	}

	/**
	 * Returns the last exam data in the ordered set where examId = &#63; and studentId = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching exam data, or <code>null</code> if a matching exam data could not be found
	 */
	@Override
	public ExamData fetchByExamIdAndStudentId_Last(long examId, long studentId,
		OrderByComparator<ExamData> orderByComparator) {
		int count = countByExamIdAndStudentId(examId, studentId);

		if (count == 0) {
			return null;
		}

		List<ExamData> list = findByExamIdAndStudentId(examId, studentId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the exam datas before and after the current exam data in the ordered set where examId = &#63; and studentId = &#63;.
	 *
	 * @param PK the primary key of the current exam data
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next exam data
	 * @throws NoSuchExamDataException if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData[] findByExamIdAndStudentId_PrevAndNext(long PK,
		long examId, long studentId,
		OrderByComparator<ExamData> orderByComparator)
		throws NoSuchExamDataException {
		ExamData examData = findByPrimaryKey(PK);

		Session session = null;

		try {
			session = openSession();

			ExamData[] array = new ExamDataImpl[3];

			array[0] = getByExamIdAndStudentId_PrevAndNext(session, examData,
					examId, studentId, orderByComparator, true);

			array[1] = examData;

			array[2] = getByExamIdAndStudentId_PrevAndNext(session, examData,
					examId, studentId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExamData getByExamIdAndStudentId_PrevAndNext(Session session,
		ExamData examData, long examId, long studentId,
		OrderByComparator<ExamData> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EXAMDATA_WHERE);

		query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTID_EXAMID_2);

		query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTID_STUDENTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ExamDataModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(examId);

		qPos.add(studentId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(examData);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExamData> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the exam datas where examId = &#63; and studentId = &#63; from the database.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 */
	@Override
	public void removeByExamIdAndStudentId(long examId, long studentId) {
		for (ExamData examData : findByExamIdAndStudentId(examId, studentId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(examData);
		}
	}

	/**
	 * Returns the number of exam datas where examId = &#63; and studentId = &#63;.
	 *
	 * @param examId the exam ID
	 * @param studentId the student ID
	 * @return the number of matching exam datas
	 */
	@Override
	public int countByExamIdAndStudentId(long examId, long studentId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTID;

		Object[] finderArgs = new Object[] { examId, studentId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EXAMDATA_WHERE);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTID_EXAMID_2);

			query.append(_FINDER_COLUMN_EXAMIDANDSTUDENTID_STUDENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(examId);

				qPos.add(studentId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EXAMIDANDSTUDENTID_EXAMID_2 = "examData.examId = ? AND ";
	private static final String _FINDER_COLUMN_EXAMIDANDSTUDENTID_STUDENTID_2 = "examData.studentId = ?";

	public ExamDataPersistenceImpl() {
		setModelClass(ExamData.class);
	}

	/**
	 * Caches the exam data in the entity cache if it is enabled.
	 *
	 * @param examData the exam data
	 */
	@Override
	public void cacheResult(ExamData examData) {
		entityCache.putResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataImpl.class, examData.getPrimaryKey(), examData);

		examData.resetOriginalValues();
	}

	/**
	 * Caches the exam datas in the entity cache if it is enabled.
	 *
	 * @param examDatas the exam datas
	 */
	@Override
	public void cacheResult(List<ExamData> examDatas) {
		for (ExamData examData : examDatas) {
			if (entityCache.getResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
						ExamDataImpl.class, examData.getPrimaryKey()) == null) {
				cacheResult(examData);
			}
			else {
				examData.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all exam datas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ExamDataImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the exam data.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExamData examData) {
		entityCache.removeResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataImpl.class, examData.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExamData> examDatas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExamData examData : examDatas) {
			entityCache.removeResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
				ExamDataImpl.class, examData.getPrimaryKey());
		}
	}

	/**
	 * Creates a new exam data with the primary key. Does not add the exam data to the database.
	 *
	 * @param PK the primary key for the new exam data
	 * @return the new exam data
	 */
	@Override
	public ExamData create(long PK) {
		ExamData examData = new ExamDataImpl();

		examData.setNew(true);
		examData.setPrimaryKey(PK);

		return examData;
	}

	/**
	 * Removes the exam data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param PK the primary key of the exam data
	 * @return the exam data that was removed
	 * @throws NoSuchExamDataException if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData remove(long PK) throws NoSuchExamDataException {
		return remove((Serializable)PK);
	}

	/**
	 * Removes the exam data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the exam data
	 * @return the exam data that was removed
	 * @throws NoSuchExamDataException if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData remove(Serializable primaryKey)
		throws NoSuchExamDataException {
		Session session = null;

		try {
			session = openSession();

			ExamData examData = (ExamData)session.get(ExamDataImpl.class,
					primaryKey);

			if (examData == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExamDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(examData);
		}
		catch (NoSuchExamDataException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ExamData removeImpl(ExamData examData) {
		examData = toUnwrappedModel(examData);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(examData)) {
				examData = (ExamData)session.get(ExamDataImpl.class,
						examData.getPrimaryKeyObj());
			}

			if (examData != null) {
				session.delete(examData);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (examData != null) {
			clearCache(examData);
		}

		return examData;
	}

	@Override
	public ExamData updateImpl(ExamData examData) {
		examData = toUnwrappedModel(examData);

		boolean isNew = examData.isNew();

		ExamDataModelImpl examDataModelImpl = (ExamDataModelImpl)examData;

		Session session = null;

		try {
			session = openSession();

			if (examData.isNew()) {
				session.save(examData);

				examData.setNew(false);
			}
			else {
				examData = (ExamData)session.merge(examData);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ExamDataModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((examDataModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						examDataModelImpl.getOriginalExamId(),
						examDataModelImpl.getOriginalStudentId(),
						examDataModelImpl.getOriginalQuestionOrder()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER,
					args);

				args = new Object[] {
						examDataModelImpl.getExamId(),
						examDataModelImpl.getStudentId(),
						examDataModelImpl.getQuestionOrder()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTIDANDQUESTIONORDER,
					args);
			}

			if ((examDataModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						examDataModelImpl.getOriginalExamId(),
						examDataModelImpl.getOriginalStudentId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTID,
					args);

				args = new Object[] {
						examDataModelImpl.getExamId(),
						examDataModelImpl.getStudentId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EXAMIDANDSTUDENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXAMIDANDSTUDENTID,
					args);
			}
		}

		entityCache.putResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
			ExamDataImpl.class, examData.getPrimaryKey(), examData, false);

		examData.resetOriginalValues();

		return examData;
	}

	protected ExamData toUnwrappedModel(ExamData examData) {
		if (examData instanceof ExamDataImpl) {
			return examData;
		}

		ExamDataImpl examDataImpl = new ExamDataImpl();

		examDataImpl.setNew(examData.isNew());
		examDataImpl.setPrimaryKey(examData.getPrimaryKey());

		examDataImpl.setPK(examData.getPK());
		examDataImpl.setExamId(examData.getExamId());
		examDataImpl.setStudentId(examData.getStudentId());
		examDataImpl.setQuestionOrder(examData.getQuestionOrder());
		examDataImpl.setResult(examData.getResult());

		return examDataImpl;
	}

	/**
	 * Returns the exam data with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the exam data
	 * @return the exam data
	 * @throws NoSuchExamDataException if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExamDataException {
		ExamData examData = fetchByPrimaryKey(primaryKey);

		if (examData == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExamDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return examData;
	}

	/**
	 * Returns the exam data with the primary key or throws a {@link NoSuchExamDataException} if it could not be found.
	 *
	 * @param PK the primary key of the exam data
	 * @return the exam data
	 * @throws NoSuchExamDataException if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData findByPrimaryKey(long PK) throws NoSuchExamDataException {
		return findByPrimaryKey((Serializable)PK);
	}

	/**
	 * Returns the exam data with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the exam data
	 * @return the exam data, or <code>null</code> if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
				ExamDataImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ExamData examData = (ExamData)serializable;

		if (examData == null) {
			Session session = null;

			try {
				session = openSession();

				examData = (ExamData)session.get(ExamDataImpl.class, primaryKey);

				if (examData != null) {
					cacheResult(examData);
				}
				else {
					entityCache.putResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
						ExamDataImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
					ExamDataImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return examData;
	}

	/**
	 * Returns the exam data with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param PK the primary key of the exam data
	 * @return the exam data, or <code>null</code> if a exam data with the primary key could not be found
	 */
	@Override
	public ExamData fetchByPrimaryKey(long PK) {
		return fetchByPrimaryKey((Serializable)PK);
	}

	@Override
	public Map<Serializable, ExamData> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ExamData> map = new HashMap<Serializable, ExamData>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ExamData examData = fetchByPrimaryKey(primaryKey);

			if (examData != null) {
				map.put(primaryKey, examData);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
					ExamDataImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ExamData)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EXAMDATA_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ExamData examData : (List<ExamData>)q.list()) {
				map.put(examData.getPrimaryKeyObj(), examData);

				cacheResult(examData);

				uncachedPrimaryKeys.remove(examData.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ExamDataModelImpl.ENTITY_CACHE_ENABLED,
					ExamDataImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the exam datas.
	 *
	 * @return the exam datas
	 */
	@Override
	public List<ExamData> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the exam datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @return the range of exam datas
	 */
	@Override
	public List<ExamData> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the exam datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of exam datas
	 */
	@Override
	public List<ExamData> findAll(int start, int end,
		OrderByComparator<ExamData> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the exam datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExamDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of exam datas
	 * @param end the upper bound of the range of exam datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of exam datas
	 */
	@Override
	public List<ExamData> findAll(int start, int end,
		OrderByComparator<ExamData> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ExamData> list = null;

		if (retrieveFromCache) {
			list = (List<ExamData>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EXAMDATA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXAMDATA;

				if (pagination) {
					sql = sql.concat(ExamDataModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ExamData>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ExamData>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the exam datas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ExamData examData : findAll()) {
			remove(examData);
		}
	}

	/**
	 * Returns the number of exam datas.
	 *
	 * @return the number of exam datas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXAMDATA);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ExamDataModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the exam data persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ExamDataImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_EXAMDATA = "SELECT examData FROM ExamData examData";
	private static final String _SQL_SELECT_EXAMDATA_WHERE_PKS_IN = "SELECT examData FROM ExamData examData WHERE PK IN (";
	private static final String _SQL_SELECT_EXAMDATA_WHERE = "SELECT examData FROM ExamData examData WHERE ";
	private static final String _SQL_COUNT_EXAMDATA = "SELECT COUNT(examData) FROM ExamData examData";
	private static final String _SQL_COUNT_EXAMDATA_WHERE = "SELECT COUNT(examData) FROM ExamData examData WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "examData.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExamData exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExamData exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ExamDataPersistenceImpl.class);
}