
import java.util.Scanner;

public class KMPSearch {
    static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            System.out.println("Empty pattern.");
            return;
        }

        int[] lps = new int[m];

        // Build the LPS array
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        // Search for the pattern
        i = 0;
        int j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println(
                    "Pattern found at index " + (i - j)
                );
                j = lps[j - 1];
            } else if (i < n &&
                       text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        System.out.print("Enter the pattern: ");
        String pattern = sc.nextLine();

        search(text, pattern);

        sc.close();
    }
}