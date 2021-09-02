
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

	/** Takes user input **/
	Scanner keyboard = new Scanner(System.in); // Takes user input
	/** Takes input from a file **/
	Scanner input;// Takes input from a file
	/** Directory of the file from which the scanner take input **/
	protected String path; // Directory of the file from which the scanner take input

	/**
	 * inputs string with .next
	 * 
	 * @return the string entered by the user
	 */
	public String inputString() {
		String input = keyboard.next();
		keyboard.nextLine();
		return input;
	}// end method

	/**
	 * Inputs string with .nextline
	 * 
	 * @return The full string entered by the user
	 */

	public String inputFullString() {// inputs string with .nextline
		String input = keyboard.nextLine();
		return input;
	}// end method

	/**
	 * Inputs integer
	 * 
	 * @return the integer entered by the user
	 */
	public int inputInteger() {// inputs integer
		int input = 0;
		// used for the do while loop which keeps on running until the user enters valid
		// info
		boolean control = true;
		do {
			try {

				input = keyboard.nextInt();
				keyboard.nextLine();
				control = false;
			} catch (InputMismatchException ime) {
				System.err.print("Wrong datatype entered: ");
				keyboard.nextLine();
			}
		} while (control == true); // end do-while loop

		return input;
	}// end method

	/**
	 * Inputs only numbers greater than 0
	 * 
	 * @return the integer entered by the user
	 */
	public int inputPositiveInteger() {
		int input = 0;
		// used for the do while loop which keeps on running until the user enters valid
		// info
		boolean control = true;
		do {
			try {
				input = inputInteger();
				if (input < 0) {
					throw new ArithmeticException();
				}
				if (input == 0) {
					throw new InputMismatchException();
				}
				control = false;

			} catch (InputMismatchException ime) {
				System.out.print("Cannot enter 0, Try again: ");
			}

			catch (ArithmeticException e) {
				System.out.print("Please enter a positive integer: ");
			}
		} while (control == true);// end do-while loop

		return input;

	}// end main method

	/**
	 * Inputs double
	 * 
	 * @return The double entered by the user
	 */
	public double inputDouble() {// inputs double
		double input = 0;

		// used for the do while loop which keeps on running until the user enters valid
		// info
		boolean control = true;
		do {
			try {

				input = keyboard.nextDouble();
				keyboard.nextLine();
				control = false;
			} catch (InputMismatchException ime) {
				System.err.print("Wrong datatype entered: ");
				keyboard.nextLine();
			}
		} while (control == true);// end do-while loop

		return input;
	}// end method

	/**
	 * Inputs only numbers greater than 0
	 * 
	 * @return the double entered by the user
	 */
	public double inputPositiveDouble() {
		double input = 0;
		// used for the do while loop which keeps on running until the user enters valid
		// info
		boolean control = true;
		do {
			try {
				input = inputDouble();
				if (input < 0) {
					throw new ArithmeticException();
				}
				if (input == 0) {
					throw new InputMismatchException();
				}
				control = false;

			} catch (InputMismatchException ime) {
				System.out.print("Cannot enter 0, Try again: ");
			}

			catch (ArithmeticException e) {
				System.out.print("Please enter a positive double: ");
			}
		} while (control == true);// end do-while loop

		return input;

	}// end main method

	/**
	 * Inputs long
	 * 
	 * @return Long entered by the user
	 */
	public long inputLong() {// inputs long
		long input = 0;

		// used for the do while loop which keeps on running until the user enters valid
		// info
		boolean control = true;
		do {
			try {

				input = keyboard.nextLong();
				keyboard.nextLine();
				control = false;
			} catch (InputMismatchException ime) {
				System.err.print("Wrong datatype entered: ");
				keyboard.nextLine();
			}
		} while (control == true);// end do-while loop

		return input;
	}// end method

	/**
	 * Inputs only numbers greater than 0
	 * 
	 * @return the long entered by the user
	 */
	public long inputPositiveLong() {
		long input = 0;
		// used for the do while loop which keeps on running until the user enters valid
		// info
		boolean control = true;
		do {
			try {
				input = inputLong();
				if (input < 0) {
					throw new ArithmeticException();
				}
				if (input == 0) {
					throw new InputMismatchException();
				}
				control = false;

			} catch (InputMismatchException ime) {
				System.out.print("Cannot enter 0, Try again: ");
			}

			catch (ArithmeticException e) {
				System.out.print("Please enter a positive long: ");

			}
		} while (control == true);// end do-while loop
		return input;

	}// end method

	/**
	 * Opens scanner in the directory provided in the parameter
	 * 
	 * @param path Directory in which the file is present
	 */
	public void openFile(String path) {
		this.path = path;
		try {
			input = new Scanner(Paths.get(path));
		} catch (FileNotFoundException fnfe) {
			System.out.print(fnfe);
		} catch (IOException ioe) {

			System.out.println(ioe);
		}
	}

	/**
	 * Checks whether there's any other data in the file
	 * 
	 * @return True if there is any other data in the file otherwise returns false
	 */
	public boolean readHasNext() {

		if (input.hasNext()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Inputs next integer from the file
	 * 
	 * @return Integer read from the file
	 */
	public int readNextInt() {

		int read = 0;
		read = input.nextInt();
		return read;
	}

	/**
	 * Inputs next double from the file
	 * 
	 * @return Double read from the file
	 */
	public double readNextDouble() {

		double read = 0;
		read = input.nextDouble();
		return read;
	}

	/**
	 * Inputs next string from the file
	 * 
	 * @return String read from the file
	 */
	public String readNextString() {

		String read;
		read = input.next();
		return read;
	}

	/**
	 * Inputs next long from the file
	 * 
	 * @return Long read from the file
	 */
	public long readNextLong() {

		long read = 0;
		read = input.nextLong();
		return read;
	}

}// end class
