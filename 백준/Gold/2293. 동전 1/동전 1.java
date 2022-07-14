import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int [] dp = new int[k+1];
        for(int i=1; i<=n; i++){
            int value = Integer.parseInt(br.readLine());
            for(int j=value; j<=k;j++){
                if(j-value==0){
                    dp[j] = dp[j] + 1;
                }
                else if (j-value>0){
                    dp[j] = dp[j] + dp[j-value];
                }
            }

        }

        System.out.println(dp[k]);
    }
}
