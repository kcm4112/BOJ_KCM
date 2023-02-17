import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Integer [] drink;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        drink = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            drink[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(drink, Collections.reverseOrder()); //내림차순 정렬
        double total = drink[0];
        for(int i=1; i<N; i++) {
            total = total + (drink[i] / 2.0);
        }
        System.out.println(total);

    }
}
