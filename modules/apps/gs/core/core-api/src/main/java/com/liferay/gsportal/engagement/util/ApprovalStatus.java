package com.liferay.gsportal.engagement.util;

import com.liferay.gsportal.core.util.CoreConstants;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

public enum ApprovalStatus {
	NEW(1, WorkflowConstants.STATUS_PENDING),
	APPROVED(2, WorkflowConstants.STATUS_APPROVED),
	MAYBE(3, WorkflowConstants.STATUS_DRAFT),
	DENIED(4, WorkflowConstants.STATUS_DENIED),
	OTHER(5, CoreConstants.OTHER);

	ApprovalStatus(int value, int workflowStatusValue) {
		this._value = value;
		this._workflowStatusValue = workflowStatusValue;
	}

	public int getWorkflowStatus() {
		return _workflowStatusValue;
	}

	public int getValue() {
		return _value;
	}

	/**
	 * Returns enum item with given <code>workflowStatusValue</code> or
	 * <code>OTHER</code> if no match found.
	 *
	 * @param workflowStatusValue
	 * @return item with given <code>workflowStatusValue</code> or
	 *         <code>OTHER</code> if no match found
	 */
	public static ApprovalStatus from(int workflowStatusValue) {
		for (ApprovalStatus as : values()) {
			if (as._workflowStatusValue == workflowStatusValue) {
				return as;
			}
		}

		return OTHER;
	}

	private final int _workflowStatusValue;
	private final int _value;
}
