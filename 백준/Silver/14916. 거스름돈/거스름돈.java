import java.util.*;
import java.io.*;

public class Main {
    static int remain = 0;
    static int total = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //거스름돈 저장.
        remain = Integer.parseInt(br.readLine());
        //5원짜리가 가장 많이 들어가야 최적의 값이 된다.
        //우선 5원짜리가 최대일 경우부터 해보고, 안되면?
        //5원짜리의 개수를 하나씩 줄여가면서 해보자!

        int maxFive = remain / 5;

        for(int i = maxFive; i>= 0; i--) {
            int remain_Except5 = remain - 5*i; //5원을 제외하고 남은 돈 저장
            if(remain_Except5 % 2 == 0) {
                //만약, 남은 돈이 2원짜리로 다 줄 수 있다면?
                //2원 개수를 i에다가 더해주고, 반복문을 종료한다.
                total = i + remain_Except5/2;
                break;
            }
            //만약 나눌 수 없다면? i를 1 감소시키며 계속 검사!
        }
        if(total == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(total);
        }
    }
}
