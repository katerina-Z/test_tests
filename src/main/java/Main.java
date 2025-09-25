public class Main {
    public static int countVowels(String s) {
        if (s == null) return 0;
        int count = 0;
        String vowels = "aeiuoAEIOU";
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}
