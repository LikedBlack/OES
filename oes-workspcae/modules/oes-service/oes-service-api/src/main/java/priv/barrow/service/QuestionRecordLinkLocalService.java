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

package priv.barrow.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import priv.barrow.model.QuestionRecordLink;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

/**
 * Provides the local service interface for QuestionRecordLink. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see QuestionRecordLinkLocalServiceUtil
 * @see priv.barrow.service.base.QuestionRecordLinkLocalServiceBaseImpl
 * @see priv.barrow.service.impl.QuestionRecordLinkLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface QuestionRecordLinkLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QuestionRecordLinkLocalServiceUtil} to access the question record link local service. Add custom service methods to {@link priv.barrow.service.impl.QuestionRecordLinkLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public int countSearchQuestionReocrdLinks(long questionOrderStart,
		long questionOrderEnd, Timestamp updateDateStart,
		Timestamp updateDateEnd, java.lang.String questionKeyword,
		java.lang.String userNameKeyword);

	/**
	* Returns the number of question record links.
	*
	* @return the number of question record links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getQuestionRecordLinksCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link priv.barrow.model.impl.QuestionRecordLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link priv.barrow.model.impl.QuestionRecordLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	public List<QuestionRecordLink> findByDdlRecordId(long ddlRecordId);

	public List<QuestionRecordLink> findRandomQuestionReocrdLinks(int count);

	public List<QuestionRecordLink> findRecentUpdateQuestionReocrdLinks(
		int count);

	/**
	* Returns a range of all the question record links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link priv.barrow.model.impl.QuestionRecordLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of question record links
	* @param end the upper bound of the range of question record links (not inclusive)
	* @return the range of question record links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QuestionRecordLink> getQuestionRecordLinks(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QuestionRecordLink> searchQuestionReocrdLinks(
		long questionOrderStart, long questionOrderEnd,
		Timestamp updateDateStart, Timestamp updateDateEnd,
		java.lang.String questionKeyword, java.lang.String userNameKeyword,
		long start, long end);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Adds the question record link to the database. Also notifies the appropriate model listeners.
	*
	* @param questionRecordLink the question record link
	* @return the question record link that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public QuestionRecordLink addQuestionRecordLink(
		QuestionRecordLink questionRecordLink);

	/**
	* Creates a new question record link with the primary key. Does not add the question record link to the database.
	*
	* @param questionOrder the primary key for the new question record link
	* @return the new question record link
	*/
	public QuestionRecordLink createQuestionRecordLink(long questionOrder);

	/**
	* Deletes the question record link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param questionOrder the primary key of the question record link
	* @return the question record link that was removed
	* @throws PortalException if a question record link with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public QuestionRecordLink deleteQuestionRecordLink(long questionOrder)
		throws PortalException;

	/**
	* Deletes the question record link from the database. Also notifies the appropriate model listeners.
	*
	* @param questionRecordLink the question record link
	* @return the question record link that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public QuestionRecordLink deleteQuestionRecordLink(
		QuestionRecordLink questionRecordLink);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QuestionRecordLink fetchQuestionRecordLink(long questionOrder);

	/**
	* Returns the question record link with the primary key.
	*
	* @param questionOrder the primary key of the question record link
	* @return the question record link
	* @throws PortalException if a question record link with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QuestionRecordLink getQuestionRecordLink(long questionOrder)
		throws PortalException;

	/**
	* Updates the question record link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param questionRecordLink the question record link
	* @return the question record link that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public QuestionRecordLink updateQuestionRecordLink(
		QuestionRecordLink questionRecordLink);

	public void addQuestionRecordLink(long recordId,
		java.lang.String questionDescription);
}