
import java.util.Scanner;

public class FuzzyMatching {
    static int distance(String a, String b) {
        int m = a.length();
        int n = b.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1],
                        Math.min(dp[i - 1][j], dp[i][j - 1])
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter search word: ");
        String search = sc.nextLine().toLowerCase();

        System.out.print("Enter a word to compare: ");
        String word = sc.nextLine().toLowerCase();

        int d = distance(search, word);

        System.out.println("Edit distance: " + d);

        if (d == 0) {
            System.out.println("Exact match!");
        } else if (d <= 2) {
            System.out.println("Fuzzy match found!");
        } else {
            System.out.println("No close match found.");
        }

        sc.close();
    }
}
