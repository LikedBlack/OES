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

package oes.service.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for QuestionContentLink. This utility wraps
 * {@link oes.service.service.impl.QuestionContentLinkLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see QuestionContentLinkLocalService
 * @see oes.service.service.base.QuestionContentLinkLocalServiceBaseImpl
 * @see oes.service.service.impl.QuestionContentLinkLocalServiceImpl
 * @generated
 */
@ProviderType
public class QuestionContentLinkLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link oes.service.service.impl.QuestionContentLinkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of question content links.
	*
	* @return the number of question content links
	*/
	public static int getQuestionContentLinksCount() {
		return getService().getQuestionContentLinksCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link oes.service.model.impl.QuestionContentLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link oes.service.model.impl.QuestionContentLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<oes.service.model.QuestionContentLink> findRecentUpdateQuestionContentLinks(
		int count) {
		return getService().findRecentUpdateQuestionContentLinks(count);
	}

	/**
	* Returns a range of all the question content links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link oes.service.model.impl.QuestionContentLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of question content links
	* @param end the upper bound of the range of question content links (not inclusive)
	* @return the range of question content links
	*/
	public static java.util.List<oes.service.model.QuestionContentLink> getQuestionContentLinks(
		int start, int end) {
		return getService().getQuestionContentLinks(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Adds the question content link to the database. Also notifies the appropriate model listeners.
	*
	* @param questionContentLink the question content link
	* @return the question content link that was added
	*/
	public static oes.service.model.QuestionContentLink addQuestionContentLink(
		oes.service.model.QuestionContentLink questionContentLink) {
		return getService().addQuestionContentLink(questionContentLink);
	}

	/**
	* Creates a new question content link with the primary key. Does not add the question content link to the database.
	*
	* @param questionOrder the primary key for the new question content link
	* @return the new question content link
	*/
	public static oes.service.model.QuestionContentLink createQuestionContentLink(
		long questionOrder) {
		return getService().createQuestionContentLink(questionOrder);
	}

	/**
	* Deletes the question content link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param questionOrder the primary key of the question content link
	* @return the question content link that was removed
	* @throws PortalException if a question content link with the primary key could not be found
	*/
	public static oes.service.model.QuestionContentLink deleteQuestionContentLink(
		long questionOrder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteQuestionContentLink(questionOrder);
	}

	/**
	* Deletes the question content link from the database. Also notifies the appropriate model listeners.
	*
	* @param questionContentLink the question content link
	* @return the question content link that was removed
	*/
	public static oes.service.model.QuestionContentLink deleteQuestionContentLink(
		oes.service.model.QuestionContentLink questionContentLink) {
		return getService().deleteQuestionContentLink(questionContentLink);
	}

	public static oes.service.model.QuestionContentLink fetchQuestionContentLink(
		long questionOrder) {
		return getService().fetchQuestionContentLink(questionOrder);
	}

	/**
	* Returns the question content link with the primary key.
	*
	* @param questionOrder the primary key of the question content link
	* @return the question content link
	* @throws PortalException if a question content link with the primary key could not be found
	*/
	public static oes.service.model.QuestionContentLink getQuestionContentLink(
		long questionOrder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getQuestionContentLink(questionOrder);
	}

	/**
	* Updates the question content link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param questionContentLink the question content link
	* @return the question content link that was updated
	*/
	public static oes.service.model.QuestionContentLink updateQuestionContentLink(
		oes.service.model.QuestionContentLink questionContentLink) {
		return getService().updateQuestionContentLink(questionContentLink);
	}

	public static QuestionContentLinkLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<QuestionContentLinkLocalService, QuestionContentLinkLocalService> _serviceTracker =
		ServiceTrackerFactory.open(QuestionContentLinkLocalService.class);
}