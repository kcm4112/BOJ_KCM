import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> answer = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String [] a = s.split(" ");
        int [] list = new int[N];
        int [] dp = new int [N];
        //주어진 배열 생성
        for(int i=0;i<a.length;i++){
            list[i] = Integer.parseInt(a[i]);
            dp[i] = 1;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                if(list[i]>list[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        int max = 0;
        for(int v : dp){
            if(v>max){
                max = v;
            }
        }
        System.out.println(max);

    }
}
