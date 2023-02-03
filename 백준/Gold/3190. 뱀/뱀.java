import java.util.*;
import java.io.*;
/*보드판의 바깥쪽 상하좌우에 벽이 있다.
예를 들어 N==6인 경우 인덱스가 7인곳, 0인곳에 벽이 있다는 뜻.
문제에서 맨 위의 왼쪽 끝이 [1,1]이라고 했으므로, 실제 보드판의 유효한 영역은 1~6이라는 뜻.
{북 = 0}, {동 = 1}, {남 = 2}, {서 = 3}
 L명령 : (현재dir+1) % 4, D명령 : (현재dir+3) % 4
종료조건
1. 다음 이동하는 곳의 대가리 위치가 row, col < 1인 경우,
2. 내 몸통이 있는 지역으로 이동하는 경우
*/

class Point{
    int row = 0;
    int col = 0;
    Point(int r, int c) {
        row = r;
        col = c;
    }
}

public class Main {
    static int N, K, L, curDir;
    static String [] dir = new String[10001];
    static int [][] map;
    static int time = 0;
    static int flag = 0;
    static ArrayList<Point> snake = new ArrayList<>(); //뱀의 위치를 저장하기 위한 리스트
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        K = Integer.parseInt(br.readLine()); //사과의 개수
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int apple_row = Integer.parseInt(st.nextToken());
            int apple_col = Integer.parseInt(st.nextToken());
            map[apple_row][apple_col] = -1; //사과의 위치를 -1로 저장하였다! 나중에 먹으면 0으로 꼭 바꿔주기!
        }

        //방향 변환 정보 개수
        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            dir[time] = command; //각 초에 해당하는 명령 저장!
        }
        curDir = 1; //뱀의 초기 방향 (오른쪽)
        snake.add(new Point(1, 1)); //뱀의 초기 위치 저장!
        while(true){
            if(flag == -1) {
                break;
            }
            start();
        }
        System.out.println(time);
    }
    public static void start() {
        time++; //시간을 늘려줌.
        flag = 0;
        Point head = snake.get(0); //우선 뱀의 머리의 위치를 가져옴
//        System.out.printf("head row : %d, head col : %d\n", head.row, head.col);
//        System.out.println("curDir : " + curDir);
        if(curDir == 0) { //현재 방향이 위쪽
            int nr = head.row - 1;
            int nc = head.col;
            if(!checkArea(nr, nc)) { //벽이거나 자기 몸통인지 체크
                flag = -1;
                return;
            }
            checkApple(nr, nc);
        }
        else if(curDir == 1) { //현재 방향이 오른쪽
            int nr = head.row;
            int nc = head.col+1;
            if(!checkArea(nr, nc)) {
                flag = -1;
                return;
            }
            checkApple(nr, nc);
        }
        else if(curDir == 2) { //현재 방향이 아래쪽
            int nr = head.row + 1;
            int nc = head.col;
            if(!checkArea(nr, nc)) {
                flag = -1;
                return;
            }
            checkApple(nr, nc);
        }
        else if(curDir == 3) { //현재 방향이 왼쪽
            int nr = head.row;
            int nc = head.col - 1;
            if(!checkArea(nr, nc)) {
                flag = -1;
                return;
            }
            checkApple(nr, nc);
        }
        //만약 방향을 전환해야할 시간이라면?
        if(dir[time] != null && ( dir[time].equals("L") || dir[time].equals("D") ) ) {
            String value = dir[time];
            changeDir(value);
        }
    }
    public static boolean checkArea(int nr, int nc) {
        //벽이랑 부딪히는 경우
        if(nr < 1 || nr >= N+1 || nc < 1 || nc >= N+1) {
            return false;
        }
        //자기 몸통이랑 쳐박는 경우
        for(int i=0; i<snake.size(); i++) {
            Point v = snake.get(i);
            if(nr == v.row && nc == v.col) {
                return false;
            }
        }
        return true;
    }
    public static void checkApple(int nr, int nc) {
        if(map[nr][nc] == -1) { //만약 이동하는 위치에 사과가 있다면?
            map[nr][nc] = 0; //그 사과는 먹어주고.
            snake.add(0, new Point(nr, nc)); //대가리 위치를 바꿔준다.
        }
        else { //사과가 없다면?
            snake.add(0, new Point(nr, nc)); //대가리 위치를 바꿔주고
            snake.remove(snake.size()-1); //꼬리가 위치한 칸을 비워줌!
        }
    }
    public static void changeDir(String command) {
        if(command.equals("L")) { //왼쪽으로 90도 이동시킨다.
            curDir = (curDir+3) % 4;
        }
        else {
            curDir = (curDir+1) % 4;
        }
    }
}
