import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        //테스트케이스만큼 반복
        for(int i=0; i<T; i++) {
            N = Integer.parseInt(br.readLine());
            String [] stock = br.readLine().split(" ");
            solve(stock);
        }
    }
    public static void solve(String [] price) {
        //맨 뒤에 값을 Max값이라고 초기화하고, 거꾸로 탐색하면서
        //만약 현재 Max값보다 주가가 낮으면, Max - price[i] 해준 값이 이익이 된다.
        //만약 현재 Max값보다 주가가 크면?
        //주가는 감소했다는 뜻이므로, Max값을 price[i]값으로 바꾸어주고, 사고 파는 행위는 하지 않는다.
        int temp = 0;
        long profit = 0;
        int max = Integer.parseInt(price[price.length-1]);
        for(int i=price.length-2; i>=0; i--) {
            temp = Integer.parseInt(price[i]);
            if(temp < max) {
                profit = profit + (max - temp);
            }
            else  {
                max = Integer.parseInt(price[i]);
            }
        }
        System.out.println(profit);
    }
}
