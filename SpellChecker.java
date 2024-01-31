
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		String lowWord1 = word1.toLowerCase();
		String lowWord2 = word2.toLowerCase();
		if (lowWord1.length() == 0) return lowWord2.length();
		if (lowWord2.length() == 0) return lowWord1.length();
		if (lowWord1.charAt(0) == lowWord2.charAt(0)) return levenshtein(tail(word1), tail(lowWord2));
		int optionA = levenshtein(tail(lowWord1), tail(lowWord2));
		int optionB = levenshtein(tail(lowWord1), lowWord2);
		int optionC = levenshtein(lowWord1, tail(lowWord2));
		int minimum = Math.min(Math.min(optionA, optionB), optionC);
		return (1 + minimum);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		int i = 0;
		while (!in.isEmpty()) {
			dictionary[i++] = in.readLine();
		}
		return dictionary;
	}

 	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Sanity check
		String lowWord= word.toLowerCase();
		if (dictionary.length == 0) {
			return lowWord;
		}
		int smallestLev = levenshtein(lowWord, dictionary[0]);
		int smallestIndex = 0;
		for (int i=1; i<dictionary.length; i++) {
			int currentLev = levenshtein(lowWord, dictionary[i]);
			if (currentLev < smallestLev) {
				smallestLev = currentLev;
				smallestIndex = i;
			}
		}
		if (smallestLev <= threshold) {
			return dictionary[smallestIndex];
		}
		return lowWord;
	}

}
