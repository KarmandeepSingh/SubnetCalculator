
public class SubnettingRun {

	public static void main(String[] args) {
		Input keyboard = new Input();
		boolean control = false;

		String input;

		int run = 1;

		do {
			Subnetting s1 = new Subnetting();
			do {

				System.out.print("Enter ip address: ");

				input = keyboard.inputFullString();
				ErrorChecker ec = new ErrorChecker();
				control = ec.check(input);

			} while (control == true);
			s1.TakeInput(input);

			s1.Calculate();
			String[] headers = { "Address:", "Netmask:", "Wildcard:", "Subnet (Network):", "Broadcast:",
					"HostMin (FHIP):", "HostMax (LHIP):" };

			System.out.printf("%-20s %21s %52s %n", headers[0], s1.GetDecIpAddress(), s1.GetBinIpAddress());

			System.out.printf("%-20s %15s = %3d %52s %n", headers[1], s1.GetDecGsm(), s1.GetSubnetMask(),
					s1.GetBinGsm());

			System.out.printf("%-20s %21s %52s %n", headers[2], s1.GetDecWildcard(), s1.GetBinWildcard());

			System.out.println("=>");

			System.out.printf("%-20s %18s/%2d %52s (%s) %n", headers[3], s1.GetDecSid(), s1.GetSubnetMask(),
					s1.GetBinSid(), s1.GetAddressClass());

			System.out.printf("%-20s %21s %52s %n", headers[4], s1.GetDecBroadcastAddress(),
					s1.GetBinBroadcastAddress());

			System.out.printf("%-20s %21s %52s %n", headers[5], s1.GetDecFhip(), s1.GetBinFhip());

			System.out.printf("%-20s %21s %52s %n", headers[6], s1.GetDecLhip(), s1.GetBinLhip());

			if (s1.GetBorrowedBits() > 0) {
				System.out.printf("Number of borrowed bits: %d %n", s1.GetBorrowedBits());

				System.out.printf("Number of subnets: %.0f %n", Math.pow(2, s1.GetBorrowedBits()));
			} else {
				System.out.printf("Number of borrowed bits: %d %n", 0);

				System.out.printf("Number of subnets: %s %n", "NA");
			}

			System.out.printf("Subnet Index ( %s ): %d %n", s1.GetBinSubnetIndex(), s1.GetDecSubnetIndex());

			if (s1.GetHostBits() > 0) {
				System.out.printf("Number of host bits= %d %n", s1.GetHostBits());

				System.out.printf("Number of host addresses: %.0f %n", Math.pow(2, s1.GetHostBits()) - 2);
			} else {
				System.out.printf("Number of host bits: %d %n", 0);

				System.out.printf("Number of host addresses: %s %n", "NA");
			}

			do {
				System.out.println("1. Continue Program");
				System.out.println("2. Exit the program");
				System.out.print("Enter: ");
				run = keyboard.inputPositiveInteger();

				if (run != 1 && run != 2) {
					System.err.println("Incorrect option entered, try again");
				}

			} while (run != 1 && run != 2);

		} while (run == 1);

		System.out.println("Thank You for using the program");
		System.out.println("Program by Karmandeep Singh");

	}

}
