import java.util.*;

public class PlagiarismChecker {

    static Set<String> getWords(String text) {
        text = text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
        String[] words = text.split("\\s+");
        return new HashSet<>(Arrays.asList(words));
    }

    static double calculateSimilarity(String text1, String text2) {
        Set<String> set1 = getWords(text1);
        Set<String> set2 = getWords(text2);

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        if (union.size() == 0)
            return 0;

        return ((double) intersection.size() / union.size()) * 100;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first document:");
        String doc1 = sc.nextLine();

        System.out.println("Enter second document:");
        String doc2 = sc.nextLine();

        double similarity = calculateSimilarity(doc1, doc2);

        System.out.printf("Similarity: %.2f%%\n", similarity);

        if (similarity >= 60)
            System.out.println("Result: Plagiarized");
        else
            System.out.println("Result: Original");

        sc.close();
    }
}