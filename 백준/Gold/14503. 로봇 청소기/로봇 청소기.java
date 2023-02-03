import java.util.*;
import java.io.*;

public class Main {
    static int N, M, curDir, row, col;
    static int [][] map;
    static int answer = 0;
    static int cnt = 0; //4방향 중 청소할 수 없는 공간의 개수를 담을 변수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());

        //로봇의 출발 위치
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        // 북 동 남 서 -> 0 1 2 3
        curDir = Integer.parseInt(st.nextToken());

        //테스트케이스 입력값 삽입
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 출발 위치는 반드시 청소해주어야 한다.
        map[row][col] = -1; //청소 표시는 -1로 해줌
        answer ++;
        startCleaning();
        System.out.println(answer);

    }
    public static void startCleaning() {
        while(row >= 0 && row < N && col >= 0 && col < M) {
            if(cnt == 4) { // 만약 4방향 모두 청소할 수 없다면?
                //현재 바라보고 있는 위치의 반대방향 즉, (dir + 2) % 4 방향을 체크해야함
                int sideDir = (curDir + 2) % 4;
                if(!checkReverse(sideDir)) { //만약 후진할 수 없다면?
                    break;
                }
                else { // 후진할 수 있다면?
                    startBack(); // 후진 시켜주어야 함.
                    cnt = 0;
                    continue;
                }

            }
            curDir = (curDir + 3) % 4; //현재 방향을 기준으로 왼쪽을 탐색할 방향으로 지정
            if(curDir == 0) { //북
                int nr = row -1; //탐색할 위치를
                if(map[nr][col] == 1 || map[nr][col] == -1) { //벽이거나 청소된 지역이라면
                    cnt++;
                    continue; //다시 방향을 구하는 곳으로 돌아간다.
                }
                else {
                    map[nr][col] = -1;
                    answer++;
                    row = nr;
                    cnt = 0;
                }

            }
            else if(curDir == 1) { //동
                int nc = col + 1;
                if(map[row][nc] == 1 || map[row][nc] == -1) {
                    cnt++;
                    continue;
                }
                else {
                    map[row][nc] = -1;
                    answer++;
                    col = nc;
                    cnt = 0;
                }
            }
            else if(curDir == 2) { //남
                int nr = row + 1;
                if(map[nr][col] == 1 || map[nr][col] == -1) {
                    cnt++;
                    continue;
                }
                else {
                    map[nr][col] = -1;
                    answer++;
                    row = nr;
                    cnt = 0;
                }
            }
            else if(curDir == 3) { //서
                int nc = col - 1;
                if(map[row][nc] == 1 || map[row][nc] == -1) {
                    cnt++;
                    continue;
                }
                else {
                    map[row][nc] = -1;
                    answer++;
                    col = nc;
                    cnt = 0;
                }
            }
        }
    }

    public static boolean checkReverse(int sideDir) {
        //현재 바라보고 있는 방향 curdir의 반대 방향에 따라서 체크해야함
        if(sideDir == 0) { //현재 남쪽, 반대쪽 북쪽
            int sideRow = row - 1;
            int sideCol = col;
            if(map[sideRow][sideCol] == 1) {
                return false;
            }
        }
        else if(sideDir == 1) { // 현재 서쪽, 반대쪽 동쪽
            int sideRow = row;
            int sideCol = col + 1;
            if(map[sideRow][sideCol] == 1) {
                return false;
            }
        }
        else if(sideDir == 2) { // 현재 북쪽, 반대쪽 남쪽
            int sideRow = row + 1;
            int sideCol = col;
            if(map[sideRow][sideCol] == 1) {
                return false;
            }
        }
        else if(sideDir == 3) { // 현재 동쪽, 반대쪽 서쪽
            int sideRow = row;
            int sideCol = col - 1;
            if(map[sideRow][sideCol] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void startBack() {
        if(curDir == 0) { //북쪽을 보고있다면 남쪽으로 이동시켜줌
            row = row + 1;
            col = col;
        }
        else if(curDir == 1) { // 동쪽을 보고있다면 서쪽으로 이동시켜줌
            row = row;
            col = col - 1;
        }
        else if(curDir == 2) { // 남쪽을 보고있다면 북쪽으로 이동시켜줌
            row = row - 1;
            col = col;
        }
        else if(curDir == 3) { // 서쪽을 보고있다면 동쪽으로 이동시켜줌
            row = row;
            col = col + 1;
        }
    }
}
