

public class HashTagTokenizer {

	public static void main(String[] args) {
		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String[] dictionary) {
		for (int i=0; i<dictionary.length; i++) {
			if (dictionary[i].equals(word)) {
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
        // Sanity check + Base case
		if (hashtag.isEmpty()) {
            return;
        }
		// enter recursion on lowercase version of the word
		breakLowerHashTag(hashtag.toLowerCase(), dictionary);
	}

	public static void breakLowerHashTag(String lowerHash, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
        if (lowerHash.isEmpty()) {
            return;
        }
        int N = lowerHash.length();
        for (int i = 1; i <= N; i++) {
			String prefix = lowerHash.substring(0, i);
			if (existInDictionary(prefix, dictionary)) {
				System.out.println(prefix);
				breakHashTag(lowerHash.substring(i), dictionary);
				return;
			}
        }
    }
}
