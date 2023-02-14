import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int [] distance;
    static int [] price;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        distance = new int[N-1];
        price = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        //거리 입력
        for(int i=0; i<N-1; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }
        //리터 당 가격 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        //처음에는 무조건 다음 지역으로 이동해야하는 거리만큼 주유를 하고,
        // i+1 지역의 리터당 가격이 i지역의 리터 당 가격보다 작을 경우에만 i+1지역의 가격으로 다음 지역까지 이동할 거리만큼 주유를 한다.
        for(int i=0; i<N-1; i++) {
            if(price[i+1] > price[i]) {
                //만약 다음 지역의 리터당 가격이 더 비싸면?
                //이전 지역에서 기름을 더 넣어야 최적의 경우이므로, 현재 지역의 리터당 가격을
                //이전 지역의 가격으로 바꾸어준다.
                price[i+1] = price[i];
            }
        }
        long answer = 0;
        for(int i=0; i<N-1; i++) {
            answer = answer + (price[i] * distance[i]);
        }
        System.out.println(answer);
    }
}

