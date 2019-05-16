import java.util.Scanner;

/*
Input:
5
A + - + -
- + - - +
- + + + -
+ - + - +
B + - + -
Output:
10
 */

public class Main {
    static final int MAX = 99;
    static int n = 0;
    static int minStep = Integer.MAX_VALUE;
    static char[][] graph = new char[MAX][MAX];
    static boolean[][] flag = new boolean[MAX][MAX];
    static int[][] nextStep = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
//        System.out.println(n);

        int startx = 0, starty = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                flag[i][j] = false;
                graph[i][j] = sc.next().charAt(0);
                if (graph[i][j] == 'A') {
                    startx = i;
                    starty = j;
                }
            }
        }

        dfs(0, 0, 0);

        if(minStep == Integer.MAX_VALUE)
            minStep = -1;

        System.out.println(minStep);
    }

    private static void dfs(int x, int y, int stepCount) {
        // 递归基
        if(graph[x][y] == 'B') {
            if(stepCount < minStep) {
                minStep = stepCount;
            }
        }
        // 一般情况
        for(int i = 0; i < 4; i++) {
            int nextx = x + nextStep[i][0];
            int nexty = y + nextStep[i][1];
            if(nextx >= 0 && nexty >= 0 && nextx < n && nexty < n &&
              (!flag[nextx][nexty]) && (graph[x][y] != graph[nextx][nexty])) {
                flag[nextx][nexty] = true;
                dfs(nextx, nexty, stepCount + 1);
                flag[nextx][nexty] = false;
            }
        }
    }
}