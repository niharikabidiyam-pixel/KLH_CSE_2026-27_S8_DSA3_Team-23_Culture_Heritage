
import java.util.Scanner;

public class RabinKarp {
    static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int base = 256;
        int prime = 101;
        int patternHash = 0;
        int textHash = 0;
        int power = 1;

        if (m > n) {
            System.out.println("Pattern not found.");
            return;
        }

        for (int i = 0; i < m - 1; i++) {
            power = (power * base) % prime;
        }

        for (int i = 0; i < m; i++) {
            patternHash =
                (base * patternHash + pattern.charAt(i)) % prime;
            textHash =
                (base * textHash + text.charAt(i)) % prime;
        }

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash &&
                text.substring(i, i + m).equals(pattern)) {
                System.out.println("Pattern found at index " + i);
            }

            if (i < n - m) {
                textHash = (base *
                    (textHash - text.charAt(i) * power)
                    + text.charAt(i + m)) % prime;

                if (textHash < 0) {
                    textHash += prime;
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