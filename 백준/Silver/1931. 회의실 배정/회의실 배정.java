import java.io.*;
import java.util.*;

class Meet implements Comparable<Meet>{
    int start = 0;
    int end = 0;
    Meet(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public int compareTo(Meet o) {
        if(this.end < o.end) {
            return -1;
        }
        else if(this.end == o.end) {
            return this.start - o.start;
        }
        else {
            return 1;
        }
    }
}
public class Main {
    static int N;
    static ArrayList<Meet> meet = new ArrayList<Meet>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meet.add(new Meet(start, end));
        }

        //회의 종료시간을 기준으로 오름차순 정렬
        Collections.sort(meet);
        int curStart = meet.get(0).start;
        int curEnd = meet.get(0).end;
        int count = 1;
        for(int i=1; i<N; i++) {
            //시작시간이 현재 진행되고있는 회의의 종료시간보다 같거나 늦은 경우에만 다음 회의를 진행시킬 수 있다.
            if(meet.get(i).start >= curEnd) {
                count++;
                curEnd = meet.get(i).end;
            }
            else {
                continue;
            }
        }
        System.out.println(count);
    }
}
