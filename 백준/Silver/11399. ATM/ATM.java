import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int [] atm;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        atm = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            atm[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(atm); //오름차순 정렬
        int temp = 0;
        int total = 0;
        int cnt = 0;
        for(int time : atm) {
            temp = temp + time;
            total = total + temp;
        }
        System.out.println(total);

    }
}
