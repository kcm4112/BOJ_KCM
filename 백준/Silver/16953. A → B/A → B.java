import java.io.*;
import java.util.*;

public class Main {
    static int A, B;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bfs(A, B, 0);
        if(min != Integer.MAX_VALUE) {
            System.out.println(min + 1);
        }
        else {
            System.out.println(-1);
        }
    }
    public static void bfs(long curNum, int b, int count) {
        if(curNum != b) {
            if(curNum < b) {
                bfs(curNum*2, b, count + 1);
                bfs(curNum*10 + 1, b, count + 1);
            }
            else if(curNum > b) {
                return;
            }
        }
        else if(curNum == b) {
            min = Math.min(min, count);
        }
    }
}
