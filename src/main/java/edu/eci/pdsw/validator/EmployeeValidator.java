package edu.eci.pdsw.validator;

import java.util.Optional;
import edu.eci.pdsw.model.Employee;

/**
 * Basic interface to validate an employee
 */
@FunctionalInterface
public interface EmployeeValidator {

	/**
	 * Validates if an employee is correctly created.
	 * 
	 * @param employee The employee to validate
	 * @return An optional with a possible error type, or
	 *         {@linkplain Optional#empty()} if no error is found
	 */
	Optional<ErrorType> validate(Employee employee);
}
