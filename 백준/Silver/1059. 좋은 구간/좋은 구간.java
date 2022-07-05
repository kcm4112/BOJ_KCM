import java.util.*;
import java.io.*;
/*전체적인 풀이방법*/
//1. 주어진 집합을 오름차순으로 정렬.
//2. 집합 내에서 N이 존재하는 구간을 찾는다.
//3. N보다 작은 수 중 가장 작은수에서 1씩 증가시켜주며 N까지 구간 수를 구한다. (N-fir)
//4. N보다 큰 수에 대하여 3번 과정을 반복한다.
//5. N을 사이에 두고 양쪽 원소들 간의 구간 (sec-N) * (N-fir)을 해준다.
//6. N이 S집합의 최솟값보다 작을 경우를 생각해 주어야 한다.


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //array size와 n의 값이 올바르게 할당되었다는 것을 확인.
        int S = Integer.parseInt(br.readLine());
        int [] array = new int[S];
        String s = br.readLine();
        String [] list = s.split(" ");
        int N = Integer.parseInt(br.readLine());
        //Int 배열 내에 집합 원소 저장 완료.
        for(int i=0;i<S;i++){
            array[i] = Integer.parseInt(list[i]);
        }
        Arrays.sort(array);
        int answer = 0;

        // 배열 내에 N과 동일한 값이 있을 경우 답은 0.
        for(int i=0;i<array.length;i++){
            if(array[i] == N){
                answer = 0;
                System.out.println(answer);
            }
        }
        if(N < array[0]){
            answer = array[0]-1-N + N-1 + (array[0]-1-N) * (N-1) ;
            System.out.println(answer);
        }
        else{
            for(int i=0;i<array.length-1;i++){
                int fir = array[i]+1;
                int sec = array[i+1]-1;
                if(fir<=N && N<=sec){
                    if(N==fir && N==sec){
                        answer = 0;
                        System.out.println(answer);
                        break;
                    }
                    answer = (sec-N) + (N-fir) + (sec-N) * (N-fir);
                    System.out.println(answer);
                    break;
                }

            }
        }
    }

}
