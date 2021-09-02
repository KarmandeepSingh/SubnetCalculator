
public class ErrorChecker {

	public boolean check(String ip) {
		boolean out = false;
		Subnetting s2 = new Subnetting();

		try {
			s2.TakeInput(ip);
			s2.Calculate();
			s2.GetAddressClass();

			s2.GetDecBroadcastAddress();
			s2.GetDecFhip();
			s2.GetDecGsm();
			s2.GetDecIpAddress();
			s2.GetDecLhip();
			s2.GetDecSid();
			s2.GetDecSubnetIndex();
			s2.GetDecWildcard();

			s2.GetBinBroadcastAddress();

			s2.GetBinFhip();

			s2.GetBinGsm();

			s2.GetBinIpAddress();

			s2.GetBinLhip();

			s2.GetBinSid();

			s2.GetBinSubnetIndex();

			s2.GetBinWildcard();

			s2.GetBorrowedBits();
			s2.GetHostBits();
			s2.GetSubnetMask();

			if (s2.GetSubnetMask() > 32) {
				throw new Exception();
			}
			if(s2.GetBorrowedBits()==0) {
				throw new Exception();
			}
			if(s2.GetBinFhip()=="NA")
				throw new Exception();

		} catch (Exception e) {
			System.err.println("Please enter a valid ip address");

			out = true;
		}

		return out;

	}

}
