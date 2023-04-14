package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private LocalDate joinDate;


	private boolean isForeigner;
	private boolean isMale;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate joinDate, boolean isForeigner, boolean isMale) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;
		this.isMale = isMale;

		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {
		switch (grade) {
			case 1:
				monthlySalary = 3000000;
				break;
			case 2:
				monthlySalary = 5000000;
				break;
			case 3:
				monthlySalary = 7000000;
				break;
			default:
				throw new IllegalArgumentException("Invalid grade value");
		}

		if (isForeigner) {
			monthlySalary *= 1.5;
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate currentDate = LocalDate.now();
		int monthsWorkedThisYear = joinDate.getMonthValue() <= currentDate.getMonthValue() ? currentDate.getMonthValue() - joinDate.getMonthValue() + 1 : 12 - joinDate.getMonthValue() + currentDate.getMonthValue() + 1;

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorkedThisYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
