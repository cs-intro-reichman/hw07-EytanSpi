
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
		if (word1.length() == 0) return word2.length();
		if (word2.length() == 0) return word1.length();
		if (word1.charAt(0) == word2.charAt(0)) return levenshtein(tail(word1), tail(word2));
		int optionA = levenshtein(tail(word1), tail(word2));
		int optionB = levenshtein(tail(word1), word2);
		int optionC = levenshtein(word1, tail(word2));
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
		//Sanity check
		if (dictionary.length == 0) {
			return word;
		}
		int smallestLev = levenshtein(word, dictionary[0]);
		int smallestIndex = 0;
		for (int i=1; i<dictionary.length; i++) {
			int currentLev = levenshtein(word, dictionary[i]);
			if (currentLev < smallestLev) {
				smallestLev = currentLev;
				smallestIndex = i;
			}
		}
		if (smallestLev <= threshold) {
			return dictionary[smallestIndex];
		}
		return word;
	}

}
