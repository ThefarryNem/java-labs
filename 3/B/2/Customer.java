import java.util.Arrays;
import java.util.Comparator;

public class Customer {
	public static void main(String[] args) {
		var one = new Customer(2, "BBB", "BBB", "BBB", "ХЗ", "111111111222", "2234556789");
		var two = new Customer(1, "AAA", "AAA", "AAA", "ХЗ", "111111111111", "1234556789");

		var arr = new Customer[] { one, two };

		sortArrayByName(arr);

		System.out.println((Arrays.toString(arr)));
		System.out.println(Arrays.toString(getCustomersByCreditCardRange("111111111111", "111111111119", arr)));
	}

	private static void sortArrayByName(Customer[] arr) {
		Arrays.sort(arr, Comparator.comparing(Customer::getLastName));
	}

	private static Customer[] getCustomersByCreditCardRange(String startRange, String endRange, Customer[] arr) {
		Customer[] filteredCustomers = new Customer[0];

		for (Customer customer : arr) {
			String creditCardNumber = customer.getCreditCardNumber();
			if (creditCardNumber.compareTo(startRange) >= 0 && creditCardNumber.compareTo(endRange) <= 0) {

				filteredCustomers = Arrays.copyOf(filteredCustomers, filteredCustomers.length + 1);
				filteredCustomers[filteredCustomers.length - 1] = customer;
			}
		}
		return filteredCustomers;
	}

	private int id;
	private String lastName;
	private String firstName;
	private String middleName;
	private String address;
	private String creditCardNumber;
	private String bankAccountNumber;

	public Customer(int id, String lastName, String firstName, String middleName, String address,
			String creditCardNumber, String bankAccountNumber) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", middleName='" + middleName + '\'' +
				", address='" + address + '\'' +
				", creditCardNumber='" + creditCardNumber + '\'' +
				", bankAccountNumber='" + bankAccountNumber + '\'' +
				'}';
	}
}
