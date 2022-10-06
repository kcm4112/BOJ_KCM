//일단 단어의 최대 길이가 100이기 때문에 순열로 모든 조합을 구하면 시간 무조건 초과.

import java.util.*;
import java.io.*;

public class Main {
    static int T;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        int idx = 0;
        for(int i=0; i<T; i++) {
            List<Character> next = new ArrayList<>(); //idx부터 뒤에있는 알파벳들을 저장할 list
            String s = br.readLine();
            char [] input = s.toCharArray(); //입력받은 문자열을 char배열로 바꾼다.
            int len = input.length;
            idx = find(input, len); //사전 순으로 앞에있는 알파벳이 나온 지점. -> 그럼 사전순으로 뒤에 단어가 더 존재한다는 뜻.
//            System.out.println(idx);

            if(idx == -1) {
                sb.append(s + "\n");
            }

            else {
                for(int j=idx;j<len;j++) {
                    next.add(input[j]);
                }

                Collections.sort(next); //오름차순으로 정렬.
                int change = 0;
                int idx2 = next.indexOf(input[idx]); //next리스트 중 idx위치의 알파벳이 어느 index에 있는지 반환.
                for(int j=idx2; j<next.size(); j++) {
                    //만약, 위에서 찾은 idx위치의 문자가 K라면, K 이후에 바로 사전순으로 오는 알파벳을 찾을 것이다.
                    if(next.get(j) != input[idx]) {
                        change = j;
                        break;
                    }
                }
                for(int j=0;j<idx;j++) {
                    sb.append(input[j]);
                }
                sb.append(next.get(change));
                next.remove(change);

                for(int j=0;j<next.size(); j++) {
                    sb.append(next.get(j));
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());


    }

    public static int find(char [] c, int size) {
        int min = c[size-1];
        for(int i=size-2; i>=0; i--) { //뒤에서부터 탐색.
            if(c[i] < min) {
                return i; //사전순으로 앞에있는 알파벳이 나오면 그 index를 리턴.
            }
            else {
                min = c[i]; //만약, 아니라면 기준 알파벳을 현재 알파벳으로 바꿔준다.
            }
        }
        return -1; //주어진 단어가 사전순으로 가장 마지막에 있는 단어라는 뜻.
    }
}
