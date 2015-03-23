package slmt.security;

public class BruteForceMD5 {

	private final static char[] ALL_CHARS = new String(
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
			.toCharArray();

	private static int count = 0;
	private static final int REPORT_BASE = 1000000;

	public static void main(String[] args) {
		int maxLen;
		String targetMD5;
		String salt = "";

		// fetches arguments
		if (args.length < 2) {
			System.out
					.println("Arguments: [Max Length] [MD5 Hash To Be Tested] ([Salt])");
			return;
		}

		try {
			maxLen = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("The first argument must be a number");
			return;
		}
		targetMD5 = args[1];
		if (args.length > 2)
			salt = args[2];

		// shows information
		System.out.println("There are " + numOfCombination(maxLen)
				+ " waiting to be tested.");

		// tests all combinations
		String result = tryAllCombination(1, maxLen, "",
				targetMD5.toLowerCase(), salt);

		if (result == null)
			System.out.println("Not found.");
		else
			System.out.println("Found it!!! It's '" + result + "'.");
	}

	/*
	 * Tries all possible combination using recursion.
	 */
	private static String tryAllCombination(int currentLevel, int finalLength,
			String lastWord, String targetMD5, String salt) {
		for (int i = 0; i < ALL_CHARS.length; i++) {
			// tests
			String testWord = lastWord + ALL_CHARS[i];
			String hashedWord = MD5Hasher.hash(testWord + salt);
			if (hashedWord.equals(targetMD5))
				return testWord;

			countAndReportStatus();

			// goes deeper
			if (currentLevel < finalLength) {
				String result = tryAllCombination(currentLevel + 1,
						finalLength, testWord, targetMD5, salt);
				if (result != null)
					return result;
			}
		}

		return null;
	}

	private static void countAndReportStatus() {
		count++;

		if (count % REPORT_BASE == 0)
			System.out.println(count + " words has been tried.");
	}

	private static long numOfCombination(int length) {
		long result = 0;
		for (int i = 1; i <= length; i++)
			result += Math.pow(ALL_CHARS.length, i);
		return result;
	}
}
