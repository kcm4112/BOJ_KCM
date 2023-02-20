import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //수식 입력
        String input = br.readLine();
        int noNimus = 0; //-를 만나기 전 값들을 더해주는 곳
        int meetMinus = 0; //-를 만나서 다음 -를 만날 때까지의 값을 저장하는 곳
        int flag = 1; // -를 한 번 만난 상태이면 -1로 저장한다. -를 처음 만난 상황이면 1을 저장한다.
        // -를 만나기 전까지의 숫자는 계속 더해준다.
        for(int i=0; i<input.length(); i++) {
            //-를 만나면 그 이후는 다 더해준다!
            if(flag == 1) { //-를 만난 적이 없는 경우
                if(input.charAt(i) >= '0' && input.charAt(i) <= '9') { //숫자일 경우
                    if(i == input.length()-1) {
                        sb.append(input.charAt(i));
                        noNimus = noNimus + Integer.parseInt(sb.toString());
                    }
                    else {
                        sb.append(input.charAt(i));
                    }
                }
                else if(input.charAt(i) == '-') {
                    flag = -1;
                    noNimus = noNimus + Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                }
                else if(input.charAt(i) == '+') {
                    noNimus = noNimus + Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                }
            }
            else if(flag == -1) { //-를 한번이라도 만난 적이 있는 경우
                if(input.charAt(i) >= '0' && input.charAt(i) <= '9') { //숫자일 경우
                    if(i == input.length()-1) {
                        sb.append(input.charAt(i));
                        meetMinus = meetMinus + Integer.parseInt(sb.toString());
                    }
                    else {
                        sb.append(input.charAt(i));
                    }
                }
                else if(input.charAt(i) == '-' || input.charAt(i) == '+') {
                    meetMinus = meetMinus + Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        int answer = noNimus - meetMinus;
        System.out.println(answer);

    }
}
