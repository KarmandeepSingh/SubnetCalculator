
public class Subnetting {

	public static int error = 0;

	private String[] divisions;
	private int[] decIpAddress = new int[4];
	private String[] binIpAddress = new String[4];
	private int subnetMask;

	private int[] decDsm = new int[4];
	private String addressClass;
	private int dsmBits;

	private String[] binGsm = new String[4];

	private String[] binSid = new String[4];
	private int[] decFhip = new int[4];
	private String[] binFhip = new String[4];

	private String[] wildCard = new String[4];

	private String[] broadcastAddress = new String[4];
	private int[] decLhip = new int[4];
	private String[] binLhip = new String[4];

	private int borrowedBits;
	private int hostBits;

	private String subnetIndex = "";

	public void TakeInput(String input) {
		input = input.replaceAll("\\s", "");
		divisions = input.split("\\.|/");

		if (divisions.length != 5) {
			divisions[0] = "NA";
		}

		for (int i = 0; i < 4; i++) {
			if (Integer.parseInt(divisions[i]) > 255 || Integer.parseInt(divisions[4]) == 0) {
				divisions[i] = null;
			}

		}

		for (int i = 0; i < 5; i++) {
			if (i == 4) {
				subnetMask = Integer.parseInt(divisions[i]);
			} else {
				decIpAddress[i] = Integer.parseInt(divisions[i]);
			}

		}

	}

	private void CalculateBinIpAddress() {
		for (int i = 0; i < 4; i++) {
			binIpAddress[i] = String.format("%08d", Integer.parseInt(Integer.toBinaryString(decIpAddress[i])));

		}

	}

	private void CalculateDsm() {
		if (decIpAddress[0] >= 0 && decIpAddress[0] <= 127) {
			addressClass = "Class A";
			decDsm[0] = 255;
			decDsm[1] = 0;
			decDsm[2] = 0;
			decDsm[3] = 0;
			dsmBits = 8;

		} else if (decIpAddress[0] >= 128 && decIpAddress[0] <= 191) {
			addressClass = "Class B";
			decDsm[0] = 255;
			decDsm[1] = 255;
			decDsm[2] = 0;
			decDsm[3] = 0;
			dsmBits = 16;
		} else if (decIpAddress[0] >= 192 && decIpAddress[0] <= 223) {
			addressClass = "Class C";
			decDsm[0] = 255;
			decDsm[1] = 255;
			decDsm[2] = 255;
			decDsm[3] = 0;
			dsmBits = 24;
		}

	}

	private void CalculateBorrowedBits() {
		if (dsmBits > subnetMask)
			borrowedBits = 0;

		else
			borrowedBits = subnetMask - dsmBits;
	}

	private void CalculateHostBits() {
		hostBits = 32 - subnetMask;
	}

	private void CalculateBinGsm() {

		for (int i = 0; i < 4; i++) {
			binGsm[i] = "00000000";
		}

		for (int i = 0; i < 4; i++) {
			if (i < subnetMask / 8) {
				binGsm[i] = "11111111";
			} else if (i == ((subnetMask / 8))) {
				for (int j = 0; j < subnetMask % 8; j++) {
					binGsm[i] = binGsm[i].replaceAll("0", "");
					binGsm[i] = binGsm[i].concat("1");
				}
				for (int j = binGsm[i].length(); j < 8; j++) {
					binGsm[i] = binGsm[i].concat("0");
				}
			}

		}

	}

	private void CalculateSubnetId() {

		for (int i = 0; i < 4; i++) {
			String temp = "";
			for (int j = 0; j < 8; j++) {
				if (binIpAddress[i].charAt(j) == binGsm[i].charAt(j)) {
					temp = temp + binIpAddress[i].charAt(j);
				} else {
					temp = temp + "0";
				}
			}
			binSid[i] = temp;

		}

	}

	private void CalculateWildCard() {
		for (int i = 0; i < 4; i++) {
			String temp = "";
			for (int j = 0; j < 8; j++) {
				if (binGsm[i].charAt(j) == '0') {
					temp = temp + "1";
				} else {
					temp = temp + "0";
				}
			}
			wildCard[i] = temp;
		}

	}

	private void CalculateBroadcastAddress() {
		for (int i = 0; i < 4; i++) {
			broadcastAddress[i] = binSid[i];
		}

		for (int i = 3; i >= 0; i--) {
			if (i > 3 - (hostBits / 8)) {
				broadcastAddress[i] = "11111111";
			} else if (i == (3 - (hostBits / 8))) {
				broadcastAddress[i] = broadcastAddress[i].substring(0, 8 - (hostBits % 8));
				for (int j = (hostBits % 8); j > 0; j--) {
					broadcastAddress[i] = broadcastAddress[i].concat("1");
				}
			}
		}

	}

	private void CalculateSubnetIndex() {
		int si = 0;
		if (dsmBits >= subnetMask || dsmBits == 0)
			subnetIndex = "NA";

		else
			for (int i = 0; i < 4; i++) {
				if (i >= dsmBits / 8) {
					for (int j = 0; j < 8; j++) {
						if (si < subnetMask - dsmBits) {
							subnetIndex += binSid[i].charAt(j);
							si++;
						}
					}
				}
			}

	}

	private void CalculateDecFhip() {
		for (int i = 0; i < 4; i++) {

			if (i == 3) {
				decFhip[i] = Integer.parseInt(binSid[i] + 1, 2);
			} else {
				decFhip[i] = Integer.parseInt(binSid[i], 2);
			}
		}

	}

	private void CalculateBinFhip() {
		for (int i = 0; i < 4; i++) {
			binFhip[i] = String.format("%08d", Integer.parseInt(Integer.toBinaryString(decFhip[i])));

		}
	}

	private void CalculateDecLhip() {
		for (int i = 0; i < 4; i++) {

			if (i == 3) {
				decLhip[i] = Integer.parseInt(broadcastAddress[i], 2);
				decLhip[i] = decLhip[i] - 1;
			} else {
				decLhip[i] = Integer.parseInt(broadcastAddress[i], 2);
			}
		}

	}

	private void CalculateBinLhip() {
		for (int i = 0; i < 4; i++) {
			binLhip[i] = String.format("%08d", Integer.parseInt(Integer.toBinaryString(decLhip[i])));

		}

	}

	public void Calculate() {

		CalculateBinIpAddress();
		CalculateDsm();
		CalculateBinGsm();
		CalculateSubnetId();
		CalculateHostBits();
		CalculateBorrowedBits();
		CalculateBroadcastAddress();
		CalculateSubnetIndex();
		CalculateWildCard();
		CalculateDecFhip();
		CalculateBinFhip();
		CalculateDecLhip();
		CalculateBinLhip();

	}

	public String GetDecIpAddress() {
		String output = "";
		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				output += String.format("%d.", decIpAddress[i]);
			} else
				output += String.format("%d", decIpAddress[i]);
		}

		return output;
	}

	public String GetBinIpAddress() {
		String output = "";

		for (int i = 0; i < 4; i++) {
			if (i == subnetMask / 8) {
				binIpAddress[i] = binIpAddress[i].substring(0, subnetMask % 8) + " "
						+ binIpAddress[i].substring(subnetMask % 8, 8);
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 3) {

				output += String.format("%s", binIpAddress[i]);
			} else
				output += String.format("%s.", binIpAddress[i]);
		}

		return output;
	}

	public String GetDecGsm() {
		String output = "";

		for (int i = 0; i < 4; i++) {
			if (i < 3)
				output += Integer.parseInt(binGsm[i], 2) + ".";
			else
				output += Integer.parseInt(binGsm[i], 2);

		}

		return output;
	}

	public String GetBinGsm() {
		String output = "";
		for (int i = 0; i < 4; i++) {
			if (i == subnetMask / 8) {
				binGsm[i] = binGsm[i].substring(0, subnetMask % 8) + " " + binGsm[i].substring(subnetMask % 8, 8);
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 3) {

				output += String.format("%s", binGsm[i]);
			} else
				output += String.format("%s.", binGsm[i]);
		}

		return output;
	}

	public int GetSubnetMask() {
		return subnetMask;
	}

	public String GetDecWildcard() {
		String output = "";

		for (int i = 0; i < 4; i++) {
			if (i < 3)
				output += Integer.parseInt(wildCard[i], 2) + ".";
			else
				output += Integer.parseInt(wildCard[i], 2);

		}

		return output;
	}

	public String GetBinWildcard() {
		String output = "";
		for (int i = 0; i < 4; i++) {
			if (i == subnetMask / 8) {
				wildCard[i] = wildCard[i].substring(0, subnetMask % 8) + " " + wildCard[i].substring(subnetMask % 8, 8);
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 3) {

				output += String.format("%s", wildCard[i]);
			} else
				output += String.format("%s.", wildCard[i]);
		}

		return output;
	}

	public String GetAddressClass() {
		return addressClass;
	}

	public String GetDecSid() {
		String output = "";

		for (int i = 0; i < 4; i++) {
			if (i < 3)
				output += Integer.parseInt(binSid[i], 2) + ".";
			else
				output += Integer.parseInt(binSid[i], 2);

		}

		return output;
	}

	public String GetBinSid() {
		String output = "";
		String[] temp = new String[4];
		for (int i = 0; i < 4; i++) {
			if (i == subnetMask / 8) {
				temp[i] = binSid[i].substring(0, subnetMask % 8) + " " + binSid[i].substring(subnetMask % 8, 8);
			} else {
				temp[i] = binSid[i];
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 3) {

				output += String.format("%s", temp[i]);
			} else
				output += String.format("%s.", temp[i]);
		}

		return output;
	}

	public String GetDecBroadcastAddress() {
		String output = "";

		for (int i = 0; i < 4; i++) {
			if (i < 3)
				output += Integer.parseInt(broadcastAddress[i], 2) + ".";
			else
				output += Integer.parseInt(broadcastAddress[i], 2);

		}

		return output;
	}

	public String GetBinBroadcastAddress() {
		String output = "";
		String[] temp = new String[4];

		for (int i = 0; i < 4; i++) {
			if (i == subnetMask / 8) {
				temp[i] = broadcastAddress[i].substring(0, subnetMask % 8) + " "
						+ broadcastAddress[i].substring(subnetMask % 8, 8);
			} else {
				temp[i] = broadcastAddress[i];
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 3) {

				output += String.format("%s", temp[i]);
			} else
				output += String.format("%s.", temp[i]);
		}

		return output;
	}

	public String GetDecFhip() {
		String output = "";

		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				output += String.format("%d.", decFhip[i]);
			} else
				output += String.format("%d", decFhip[i]);
		}

		if ((Integer.parseInt(broadcastAddress[3]) - Integer.parseInt(binSid[3])) < 5) {
			output = "NA";
		}

		return output;
	}

	public String GetBinFhip() {
		String output = "";
		String[] temp = new String[4];

		for (int i = 0; i < 4; i++) {
			if (i == subnetMask / 8) {
				temp[i] = binFhip[i].substring(0, subnetMask % 8) + " " + binFhip[i].substring(subnetMask % 8, 8);
			} else {
				temp[i] = binFhip[i];
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 3) {

				output += String.format("%s", temp[i]);
			} else
				output += String.format("%s.", temp[i]);
		}

		if ((Integer.parseInt(broadcastAddress[3]) - Integer.parseInt(binSid[3])) < 5) {
			output = "NA";
		}

		return output;
	}

	public String GetDecLhip() {
		String output = "";

		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				output += String.format("%d.", decLhip[i]);
			} else
				output += String.format("%d", decLhip[i]);
		}

		if ((Integer.parseInt(broadcastAddress[3]) - Integer.parseInt(binSid[3])) < 5) {
			output = "NA";
		}

		return output;
	}

	public String GetBinLhip() {
		String output = "";
		String[] temp = new String[4];
		for (int i = 0; i < 4; i++) {
			if (i == subnetMask / 8) {
				temp[i] = binLhip[i].substring(0, subnetMask % 8) + " " + binLhip[i].substring(subnetMask % 8, 8);
			} else {
				temp[i] = binLhip[i];
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 3) {

				output += String.format("%s", temp[i]);
			} else
				output += String.format("%s.", temp[i]);
		}

		if ((Integer.parseInt(broadcastAddress[3]) - Integer.parseInt(binSid[3])) < 5) {
			output = "NA";
		}

		return output;
	}

	public int GetBorrowedBits() {
		return borrowedBits;
	}

	public int GetHostBits() {
		return hostBits;
	}

	public String GetBinSubnetIndex() {
		return subnetIndex;
	}

	public int GetDecSubnetIndex() {

		if (subnetIndex.equals("NA"))
			return 0;

		else
			return Integer.parseInt(subnetIndex, 2);

	}

}
