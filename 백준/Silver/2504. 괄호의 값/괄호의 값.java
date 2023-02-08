import java.util.*;
import java.io.*;

/*
1. cal = 1, answer = 0 으로 초기화
2. 여는 괄호가 나왔을 때는 무조건 stack에 push -> ( = 2, [ = 3을 cal에 곱해줌,
3. 만약 닫는 괄호가 나왔다면? 일단 스택은 비어있지 않아야함. (여는 괄호가 하나라도 있어야 하니깐!)
4. 올바른 괄호가 되려면 닫는 괄호가 나왔을 때, 바로 이전의 값이 그 괄호의 여는 괄호여야 함. (이게 괄호가 성립하기 위한 최소 조건임!)
5. 생각을 해보자
6. 만약에 현재 [ [ ] ] 라고 해보자. 로직에 따라서 스택에는 [이 남아있을 것이다. 마지막 문자인 ]가 나왔을 경우에는 어떻게 되나?
7. [가 나올 떄마다 3을 곱해주었기 때문에 이미 9라는 값이 3번째 문자인 ]가 나올 때 정답에 더해졌다.
8. 그렇다면, 마지막 ]가 나올 때는 어떡하나? -> 바로 이전 인덱스가 [ 인지 확인해주어야 한다.
9. 만약 바로 전 인덱스가 [가 아니라면? 그냥 스택에서 제거를 해주어야 한다.
10. 3을 나누어 주기만 하고, answer는 건드리면 안됨. 단지 괄호를 닫는 연산만 해주는 것이다!

5. 예제 1번에서 2 * (2 + 3 * 3) 은 4 + 6 * 3 이라는 것 유념하자.
일단 여기까지 하고 코드 ㄱ ㄱ
*/
public class Main {
    static String S = "";
    static int cal = 1;
    static int answer = 0;
    static Stack<String> stack = new Stack<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        for(int i=0; i<S.length(); i++) {
            String curWord = S.charAt(i) + "";

            if(curWord.equals("(")) { //여는 괄호일 경우, 무조건 스택에 push하고 해당 값만큼 cal에 곱해준다.
                stack.push(curWord);
                cal = cal * 2;
            }
            else if(curWord.equals("[")) {
                stack.push(curWord);
                cal = cal * 3;
            }
            else if(curWord.equals(")")) {
                //만약 닫는 괄호가 들어왔는데 스택이 비어있거나 top이 여는 괄호가 아니라면?
                //틀린 문제임
                if(stack.isEmpty() || !stack.peek().equals("(")) {
                    answer = 0;
                    break;
                }
                else if((S.charAt(i-1)+"").equals("(")) { //만약 최소 조건을 만족한다면?
                    answer = answer + cal;
                }
                cal = cal / 2;
                stack.pop();
            }
            else if(curWord.equals("]")) {
                if(stack.isEmpty() || !stack.peek().equals("[")) {
                    answer = 0;
                    break;
                }
                else if( (S.charAt(i-1)+"").equals("[") ) {
                    answer = answer + cal;
                }
                cal = cal / 3;
                stack.pop();
            }
        }
        //만약 문자열을 모두 검사했는데 스택에 남아있는게 있다?
        //아직 닫지 못한 괄호가 있다는 것이므로, 틀린 문제가 된다.
        if(!stack.isEmpty()) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
