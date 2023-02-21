import java.io.*;
import java.util.*;

/**
 * 연산의 최소 횟수를 구하기 위해서 A[r][c] != B[r][c] 일 경우는 일단 무조건 연산을 수행하는데
 * A[r][c] 지점이 왼쪽 위 꼭지점이 되도록하여 연산을 수행해야한다.
 * 그 이유는 한번 바꾼 자리를 다시 바꾸는 행위를 하게 되면 최소횟수를 구할 수 없기 때문에!
 */
public class Main {
    static int N, M;
    static int [][] A;
    static int [][] B;
    static BufferedReader br;
    static StringTokenizer st;
    static int count = 0;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        B = new int[N][M];

        //A, B 입력받기
        initMatrix(A);
        initMatrix(B);

        findDifferent();
        if(checkMatrix()) {
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }

    }

    public static void initMatrix(int [][] matrix) throws IOException{
        int value = 0;
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<M; j++) {
                value = Integer.parseInt(s.charAt(j) + "");
                matrix[i][j] = value;
            }
        }
    }
    public static void findDifferent() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(A[i][j] != B[i][j]) {
                    operation(i, j);
                    count++;
                }
            }
        }
    }
    public static void operation(int r, int c) {
        if(r+2 < N && c + 2 < M) {
            for(int i=r; i<r+3; i++) {
                for(int j=c; j<c+3; j++) {
                    A[i][j] = (A[i][j]==0) ? 1 : 0;
                }
            }
        }
    }
    public static boolean checkMatrix() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
