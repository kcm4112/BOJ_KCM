import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> price = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            price.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(price, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        int q = N / 3; //몫
        int r = N % 3; //나머지
        int total = 0;
        if(q > 0) {
            for(int i=0; i<3*q; i += 3) {
                total = total + price.get(i) + price.get(i+1);
            }
        }
        if(r > 0) {
            for(int i=3*q; i<(3*q + r); i++) {
                total = total + price.get(i);
            }
        }

        System.out.println(total);
    }
}
