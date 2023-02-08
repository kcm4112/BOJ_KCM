import java.io.*;
import java.util.*;

/*
1. 그냥 브루트포스로 모든 경우에 대해서 탐색해보기
2. DFS를 이용해보자.
 */
class Tv {
    int row = 0;
    int col = 0;
    int type = 0;
    Tv(int r, int c, int t) {
        row = r;
        col = c;
        type = t;
    }
}
public class Main {
    static int N, M;
    static int [][] map;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Tv> cctvList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        //맵 입력
        int value = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                value = Integer.parseInt(st.nextToken());
                if(value != 0 && value != 6) { //cctv를 입력받았다면
                    cctvList.add(new Tv(i, j, value));
                    map[i][j] = value;
                    continue;
                }
                map[i][j] = value;
            }
        }

        //cnt는 사무실에 있는 cctvList의 인덱스를 뜻하며, cctvList의 0번째 인덱스의 cctv부터 탐색함!
        dfs(0, map);

        System.out.println(min);

    }

    public static void dfs(int cnt, int [][] map) {

        if(cnt == cctvList.size()) {
            min = Math.min(min, getZeroArea(map));
            return;
        }

        int tvType = cctvList.get(cnt).type; //cctv의 타입을 가져온다.
        int tvRow = cctvList.get(cnt).row;
        int tvCol = cctvList.get(cnt).col;
        int [][] temp;
        if(tvType == 1) {
            //상 하 좌 우 모두 dfs를 들어가야함.
            temp = copyMap(map);
            checkUp(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkBottom(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkLeft(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkRight(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);
        }
        else if(tvType == 2) {
            //상하, 좌우 두개의 dfs를 호출해야함
            temp = copyMap(map);
            checkUp(temp, tvRow, tvCol);
            checkBottom(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkLeft(temp, tvRow, tvCol);
            checkRight(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);
        }
        else if(tvType == 3) {
            //상우, 우하, 하좌, 좌상 4개의 dfs호출
            temp = copyMap(map);
            checkUp(temp, tvRow, tvCol);
            checkRight(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkRight(temp, tvRow, tvCol);
            checkBottom(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkBottom(temp, tvRow, tvCol);
            checkLeft(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkLeft(temp, tvRow, tvCol);
            checkUp(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);


        }
        else if(tvType == 4) {
            //좌상우, 상우하, 우하좌, 하좌상 4개의 dfs를 호출
            temp = copyMap(map);
            checkLeft(temp, tvRow, tvCol);
            checkUp(temp, tvRow, tvCol);
            checkRight(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkUp(temp, tvRow, tvCol);
            checkRight(temp, tvRow, tvCol);
            checkBottom(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkRight(temp, tvRow, tvCol);
            checkBottom(temp, tvRow, tvCol);
            checkLeft(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);

            temp = copyMap(map);
            checkBottom(temp, tvRow, tvCol);
            checkLeft(temp, tvRow, tvCol);
            checkUp(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);
        }
        else if(tvType == 5) {
            //상하좌우 하나의 dfs만 호출
            temp = copyMap(map);
            checkUp(temp, tvRow, tvCol);
            checkBottom(temp, tvRow, tvCol);
            checkLeft(temp, tvRow, tvCol);
            checkRight(temp, tvRow, tvCol);
            dfs(cnt + 1, temp);
        }
    }

    public static void checkUp(int [][] input, int row, int col) { //위쪽의 가능한 구역을 감시구역으로 표시
        for(int r = row-1; r >= 0; r--) {
            if(input[r][col] == 6) { //가다가 벽을 만나면
                return;
            }
            input[r][col] = -1; //탐색 가능 지역은 -1로 바꿔줌
        }
    }
    public static void checkBottom(int [][] input, int row, int col) { //아레쪽의 가능한 구역을 감시구역으로 표시
        for(int r = row+1; r < N ; r++) {
            if(input[r][col] == 6) { //가다가 벽을 만나면
                return;
            }
            input[r][col] = -1; //탐색 가능 지역은 -1로 바꿔줌
        }
    }
    public static void checkLeft(int [][] input, int row, int col) { //왼쪽의 가능한 구역을 감시구역으로 표시
        for(int c = col-1; c >= 0; c--) {
            if(input[row][c] == 6) { //가다가 벽을 만나면
                return;
            }
            input[row][c] = -1; //탐색 가능 지역은 -1로 바꿔줌
        }
    }
    public static void checkRight(int [][] input, int row, int col) { //오른쪽의 가능한 구역을 감시구역으로 표시
        for(int c = col+1; c < M; c++) {
            if(input[row][c] == 6) { //가다가 벽을 만나면
                return;
            }
            input[row][c] = -1; //탐색 가능 지역은 -1로 바꿔줌
        }
    }

    //원본 배열의 보존을 위해 복사하는 메소드를 선언
    public static int [][] copyMap(int [][] map) {
        int [][] copy = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }
    public static int getZeroArea(int [][] input) {
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(input[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
