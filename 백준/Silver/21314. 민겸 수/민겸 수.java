import java.io.*;
import java.util.*;

/**
 * 주의할 점 : 최대값을 구할 떄, K가 없는 경우 즉, M만 존재하는 경우 예를 들어서 MM일 경우
 * 10보다 11로 바꾸면 더 값이 크다는 것을 주의하자!
 */
public class Main {
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        int mFlag = -1; //M의 카운팅 여부를 저장
        char temp = ' ';

        //최대값 구하는 과정
        for(int i=0; i<input.length(); i++) {
            temp = input.charAt(i);
            //m을 찾으면 mFlag = 1로 변경하고 sb에 삽입
            if(temp == 'M') {
                if(i == input.length()-1) {
                    sb.append(temp);
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
                else {
                    mFlag = 1;
                    sb.append(temp);
                }
            }
            //K를 만났는데 mFlag가 1이라면? sb에 K까지 저장하고, list에 삽입하기.
            else if(temp == 'K') {
                if(mFlag == 1) {
                    sb.append(temp);
                    list.add(sb.toString());
                    sb = new StringBuilder();
                    mFlag = -1;
                }
                else if(mFlag == -1) {
                    sb.append(temp);
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }

        //최대값 계산하기 {MK, K, MMK} 로 일단 파싱완료
        String max = makeMaxValue();
        //최소값 계산하기
        String min = makeMinValue();

        //출력
        System.out.println(max);
        System.out.println(min);
    }

    public static String makeMaxValue() {
        String listElement = "";
        StringBuilder maxString = new StringBuilder();
        int m_count = 0;
        int k_count = 0;
        for(int i=0; i<list.size(); i++) {
            listElement = list.get(i);
            char temp = ' ';
            m_count = 0;
            k_count = 0;

            for(int j=0; j<listElement.length(); j++) {
                temp = listElement.charAt(j);
                if(temp == 'M') {
                    m_count++;
                }
                else if(temp == 'K') {
                    k_count++;
                }
            }
            //하나의 요소에 대하여 M의 개수와 K의 개수를 파악완료했으면 이제 계산해야함.
            if(k_count > 0) { //k가 하나라도 있는 경우
                maxString.append("5");
                for(int k=0; k<m_count; k++) {
                    maxString.append("0");
                }
            }
            else if(k_count == 0) { //k가 하나도 없는 경우
                for(int k=0; k<m_count; k++) {
                    maxString.append("1");
                }
            }
        }
        return maxString.toString();
    }
    public static String makeMinValue() {
        String listElement = "";
        StringBuilder minString = new StringBuilder();
        int m_count = 0;
        int k_count = 0;
        for(int i=0; i<list.size(); i++) {
            listElement = list.get(i);
            char temp = ' ';
            m_count = 0;
            k_count = 0;

            for(int j=0; j<listElement.length(); j++) {
                temp = listElement.charAt(j);
                if(temp == 'M') {
                    m_count++;
                }
                else if(temp == 'K') {
                    k_count++;
                }
            }
            //하나의 요소에 대하여 M의 개수와 K의 개수를 파악완료했으면 이제 계산해야함.
            if(m_count > 0) { //k이 하나라도 있는 경우
                minString.append("1");
                for(int k=0; k<m_count-1; k++) {
                    minString.append("0");
                }
            }
            if(k_count > 0) { //k가 하나라도 있는 경우
                minString.append("5");
            }
        }


        return minString.toString();
    }
}
