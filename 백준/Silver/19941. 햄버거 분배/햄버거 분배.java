import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean [] visited;
    static String input;
    static int total = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N];

        input = br.readLine(); //길이는 N이다.
        start();
        System.out.println(total);




    }
    public static void start() {
        for (int i=0; i<input.length(); i++) {
            if(input.charAt(i) == 'P') {
//                System.out.printf("P`s index is : %d \n", i);
                if(checkLeft(i)) {
                }
                else {
                    checkRight(i);
                }
            }
        }
    }

    public static boolean checkLeft(int location) {
        int flag = -1;
        int index = 0;
        if(location == 0) {
            return false;
        }
        for(int i=location-1; i>=location-K; i--) { //왼쪽으로 탐색
            if( i>=0 && input.charAt(i) == 'H' && !visited[i]) {
                //만약 햄버거를 만났다면?
                //가장 왼쪽에 있는 햄버거를 찾아야하며
                //그 위치의 햄버거가 이미 다른 사람이 먹은 햄버거가 아니어야 한다.
                index = i;
                flag = 1;
            }
            else {
                continue;
            }
        }
        if(flag == 1) {
            visited[index] = true;
            total++;
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean checkRight(int location) {
        if(location == input.length()-1) {
            return false;
        }
        int flag = -1;
        int index = 0;
        for(int i=location+1; i<=location+K; i++) { //왼쪽으로 탐색
            if(i<input.length() && input.charAt(i) == 'H' && !visited[i]) {
                //만약 햄버거를 만났다면?
                //가장 왼쪽에 있는 햄버거를 찾아야하며
                //그 위치의 햄버거가 이미 다른 사람이 먹은 햄버거가 아니어야 한다.
                index = i;
                visited[i] = true;
                flag = 1;
                break;
            }
        }
        if(flag == 1) {
            total++;
            return true;
        }
        else {
            return false;
        }
    }
}
