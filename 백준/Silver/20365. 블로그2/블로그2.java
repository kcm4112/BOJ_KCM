import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String input = br.readLine();
        char firstColor = input.charAt(0);
        char temp = ' ';
        char reverseTemp = ' ';
        int flag = 1;
        int cnt = 0; // 진행하면서 firstColor와 다른 놈들을 몇 개나 만나는지 저장
        for(int i=1; i<N; i++) {
            temp = input.charAt(i);
            if(temp == firstColor) {
                flag = 1;
                continue;
            }
            else {
                if(flag == 1) {
                    cnt++;
                    flag = -1;
                }
                else if(flag == -1) {
                    continue;
                }
            }
        }
        System.out.println(cnt + 1);
    }
}
