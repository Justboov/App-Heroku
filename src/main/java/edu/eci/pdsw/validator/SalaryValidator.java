package edu.eci.pdsw.validator;

import java.util.Optional;


import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.validator.*;
import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Utility class to validate an employee's salary
 */
public class SalaryValidator implements EmployeeValidator {

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Optional<ErrorType> validate(Employee employee){
		int pId = employee.getPersonId();
		long salary = employee.getSalary();
		SocialSecurityType sst = employee.getSocialSecurityType();
		if(pId >= 1000 && pId <= 100000) {
			if(salary >= 100 && salary <= 50000) {
				if(salary < 1500) {
					if(sst.equals(SocialSecurityType.SISBEN)) {
						return Optional.empty();
					}else {
						return Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION);
					}
				}
				else if(salary < 10000) {
					if(sst.equals(SocialSecurityType.EPS)) {
						return Optional.empty();
					}else {
						return Optional.of(ErrorType.INVALID_EPS_AFFILIATION);
					}
				}
			}else {
				return Optional.of(ErrorType.INVALID_SALARY);
			}
		}else {
			return Optional.of(ErrorType.INVALID_PERSONID);
		}
		return Optional.empty();
	}
}
