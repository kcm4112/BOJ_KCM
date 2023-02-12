import java.util.*;
import java.io.*;

public class Main {
    static char [] board;
    static char [] answer = new char[100];
    static int count = 0;
    static int cur = 0;
    static int element_A = 0;
    static int element_B = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        board = new char[input.length()];
        for(int i=0; i<input.length(); i++) {
            board[i] = input.charAt(i);
        }

        //.가 나올 때까지 X의 개수 counting
        for(int i=0; i< board.length; i++) {
            if(board[i] == 'X') {
                count++;
            }
            if(board[i] == '.'){
                if(count % 2 == 0) {
                    solve();
                    sb.append(".");
                }
                else {
                    System.out.println(-1);
                    return;
                }
            }
        }

        if(count > 0) {
            if(count % 2 == 0) {
                solve();
            }
            else {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(sb);


    }
    public static void solve() {
        while(count > 0) {
            if(count >= 4) {
                sb.append("AAAA");
                count = count - 4;
            }
            else {
                sb.append("BB");
                count = count - 2;
            }
        }

    }
}
