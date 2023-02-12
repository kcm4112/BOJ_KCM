import java.util.*;
import java.io.*;

public class Main {
    static int N, M, T;
    static int first = 0;
    static int last = 0;
    static int total = 0; //총 이동횟수 저장!
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(br.readLine());
        //박스의 시작 범위는 가장 왼쪽에서부터 M만큼이다.
        //박스가 차지하는 범위의 first와 last를 저장해준다.
        first = 1;
        last = M;
        for(int i=0; i<T; i++) {
            int apple = Integer.parseInt(br.readLine());
            if(apple <= last && apple >= first) {
                //박스의 범위 내에서 떨어진다면?
                continue;
            }
            /*
            주의해야할 점 : first나 last의 위치를 바꾸어줄 때 순서도 중요함!
             */
            else if(apple > last) {
                //만약 last보다 높은 인덱스에서 떨어지면?
                total = total + (apple - last); //이동거리를 우선 더해주고
                first = first + (apple - last); //first의 위치도 이동시켜준다.
                last = apple; //박스를 그 위치까지 이동시켜준다.

            }
            else if(apple < first) {
                //만약 first보다 낮은 인덱스에서 떨어지면?
                total = total + (first - apple); //이동거리를 우선 더해주고
                last = last - (first - apple); //last의 위치를 당겨주고
                first = apple; //박스를 그 위치까지 이동시켜주고
            }
        }
        System.out.println(total);
    }
}
