package com.liferay.gsportal.core.exception;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;

public class StaffMemberException extends PortalException {

	public StaffMemberException(List<String> errors) {
		_errors = errors;
	}

	public StaffMemberException(String error) {
		_errors = new ArrayList<String>();
		_errors.add(error);
	}

	public void addError(String error) {
		_errors.add(error);
	}

	public void addErrors(List<String> error) {
		_errors.addAll(error);
	}

	public List<String> getErrors() {
		return _errors;
	}

	private List<String> _errors;
}
