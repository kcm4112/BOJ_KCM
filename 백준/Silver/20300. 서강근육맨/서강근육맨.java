import java.io.*;
import java.util.*;

/**
 * 일단 절대적으로, 근손실 정도를 오름차순으로 정렬했을 때,
 * 첫 번째 값과 마지막 값의 합이 잠정적 최소값임은 부정할 수 없음.
 * 운동기구의 개수가 홀수개일때는 제일 마지막 값, 짝수개일대는 1번째값과 제일 마지막 값의 합으로
 * 근손실 최대값을 설정한다.

 * 이후에,  (2 + N-1), (3 + N-2) 값과 비교하며 잠정적 최소값보다 커지는 경우에는 answer가 되는 최대값을 변경해준다.
 * 그 이유는, 잠정적 최대값보다 더 커지는 경우라도 그 값이 문제에서 말하는 최대값이 될 수밖에 없기 떄문
 * 어쨋든, M을 가장 작게 만드는 것이 목적이니 만드시 처음과 마지막에서 점점 중앙으로 가까이 오면서 더한 값과, 잠정적 최대값을 비교해주어야 함
 */
public class Main {
    static int N;
    static long [] weight;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            weight[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(weight); //오름차순으로 정렬

        //운동기구의 개수가 짝수일 경우
        if(N%2 == 0) {
            long implicitMax = Integer.MIN_VALUE;
            for(int i=0; i<N/2; i++) {
                implicitMax = Math.max(implicitMax, weight[i] + weight[N-i-1]);
            }
            System.out.println(implicitMax);
        }
        else { //운동기구의 개수가 홀수일 경우
            long implicitMax = weight[N-1];
            for(int i=0; i<N/2-1; i++) {
                implicitMax = Math.max(implicitMax, weight[i] + weight[N-i-2]);
            }
            System.out.println(implicitMax);
        }
    }
}
