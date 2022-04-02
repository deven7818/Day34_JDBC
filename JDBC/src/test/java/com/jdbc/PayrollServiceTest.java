package com.jdbc;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.jdbc.PayrollService.IOService.FILE_IO;
/**
 * Unit test for simple App.
 */
public class PayrollServiceTest {

	static PayrollService employeePayrollService;
	
	public static void initializeConstructor() {
		employeePayrollService = new PayrollService();
	}
	
	@BeforeClass
	public void printWelcomeMessage() {
		employeePayrollService.printWelcomeMessage();
	}
	
	@Test
	public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
		PayrollData[] arrayOfEmps = {
				new PayrollData(1, "Bill", 100000.0),
				new PayrollData(2, "Terisa", 200000.0),
				new PayrollData(3, "Charlie", 300000.0)
		};
		PayrollService payrollService;
		payrollService = new PayrollService(Arrays.asList(arrayOfEmps));
		payrollService.writeEmployeePayrollData(FILE_IO);
		payrollService.printData(FILE_IO);
		long entries = payrollService.countEntries(FILE_IO);
		Assert.assertEquals(3, entries);
	}
	
	@Test
	public void givenFileOnReadingFileShouldMatchEmployeeCount() {
		PayrollService employeePayrollService = new PayrollService();
		List<PayrollData> entries = employeePayrollService.readPayrollData(FILE_IO);
		Assert.assertEquals(3, entries.size());
	}

	
	@Test
	public void givenEmployeePayrollinDB_whenRetrieved_ShouldMatch_Employee_Count() throws PayrollException {
		List<PayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(PayrollService.IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollData.size());
	}
	
	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_shouldSynchronizewithDataBase() throws PayrollException {
		List<PayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(PayrollService.IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("Deven",000000.00);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Teresa");
		Assert.assertTrue(result);
	}
}
