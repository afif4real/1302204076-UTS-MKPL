package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	private static final int MAX_CHILDREN = 3;
	private static final int TAX_RATE_PERCENT = 5;
	private static final int ANNUAL_TAX_FREE_INCOME_SINGLE = 54000000;
	private static final int ANNUAL_TAX_FREE_INCOME_MARRIED = ANNUAL_TAX_FREE_INCOME_SINGLE + 4500000;
	private static final int ANNUAL_TAX_FREE_INCOME_PER_CHILD = 1500000;

	//memasukan angka-angka kedalam variabel agar mudah dibaca dan di maintain (bad smell 1)

	//memisahkan fungsi yang berada pada function calculateTax menjadi beberapa function agar
	//code mudah dibaca dan di maintain (bad smell 2)

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
		int annualTaxFreeIncome = calculateAnnualTaxFreeIncome(isMarried, numberOfChildren);
		int annualTaxableIncome = calculateAnnualTaxableIncome(monthlySalary, otherMonthlyIncome, numberOfMonthsWorked, deductible, annualTaxFreeIncome);
		int annualTax = (int) Math.round(annualTaxableIncome * (TAX_RATE_PERCENT / 100.0));
		return Math.max(annualTax, 0);
	}

	private static int calculateAnnualTaxFreeIncome() {

	}

	private static int calculateAnnualTaxableIncome() {

	}

	
}
