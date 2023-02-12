import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int count = 1;
    static int maxWeight = 0;
    static int ans = Integer.MIN_VALUE;
    static ArrayList<Integer> rope = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            rope.add(Integer.parseInt(br.readLine()));
        }

        //로프를 정렬
        Collections.sort(rope);

        //예를 들어, 16, 16, 32, 32, 60이라고 하면
        //정답은 80이다... 어떻게 구하나?
        //내가 임의의 로프를 사용했다고 하면, 그 로프보다 허용 무게가 더 크거가 같은 로프들을 모두 사용하는 것이 무조건 이득!
        for(int i=0; i<rope.size(); i++) {
            maxWeight = Math.max(maxWeight, rope.get(i) * (N-i));
        }
        System.out.println(maxWeight);

    }
}
