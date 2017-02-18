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

package oes.service.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the QuestionContentLink service. Represents a row in the &quot;oes_QuestionContentLink&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see QuestionContentLinkModel
 * @see oes.service.model.impl.QuestionContentLinkImpl
 * @see oes.service.model.impl.QuestionContentLinkModelImpl
 * @generated
 */
@ImplementationClassName("oes.service.model.impl.QuestionContentLinkImpl")
@ProviderType
public interface QuestionContentLink extends QuestionContentLinkModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link oes.service.model.impl.QuestionContentLinkImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<QuestionContentLink, Long> QUESTION_ORDER_ACCESSOR =
		new Accessor<QuestionContentLink, Long>() {
			@Override
			public Long get(QuestionContentLink questionContentLink) {
				return questionContentLink.getQuestionOrder();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<QuestionContentLink> getTypeClass() {
				return QuestionContentLink.class;
			}
		};
}