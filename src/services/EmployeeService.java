package services;

import java.util.List;
import java.util.function.Predicate;

import entities.Employee;

public class EmployeeService {
	public static double filteredSum(List<Employee> emps, Predicate<Employee> criteria) {
		double sum = 0;
		for (Employee emp : emps) {
			if (criteria.test(emp)) {
				sum += emp.getSalary();
			}
		}
		return sum;
	}
}
