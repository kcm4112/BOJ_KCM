import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Integer> customer = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            customer.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(customer, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2) {
                    return 1;
                }
                else if(o1 == o2) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        //순서 = i+1 -> i가 0부터 시작하기때문에.
        //팁 = 주려고 생각했던 돈 - (등수 - 1)
        long total = 0;
        int tip = 0;
        for(int i=0; i<customer.size(); i++) {
            tip = customer.get(i) - (i+1 - 1);
            if(tip < 0 ) { //받을 돈이 음수이면? 더이상 진행할 필요가 없다.
                break;
            }
            else {
                total = total + tip;
            }
        }
        System.out.println(total);
    }
}
