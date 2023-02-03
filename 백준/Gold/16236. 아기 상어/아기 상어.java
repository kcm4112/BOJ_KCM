import java.io.*;
import java.util.*;

class Point { //상어 또는 물고기의 위치를 저장할
    int row = 0;
    int col = 0;
    Point(int r, int c) {
        row = r;
        col = c;
    }
}
public class Main {
    static int N, sharkRow, sharkCol;
    static int [][] sea; //초기 바다의 정보를 저장할 배열
    static int [][] time;
    static int sharkSize = 2; //초기 아기 상어의 사이즈 저장
    static int eat = 0;
    static int totalTime = 0; //정답 시간을 저장할 변수
    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];
        time = new int[N][N];

        for(int i=0; i<N; i++) { //바다 상태 입력받기
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if(sea[i][j] == 9) { //만약 상어의 위치를 입력 받았다면?
                    //상어의 위치를 저장하고, 그 영역을 0으로 바꾸어 준다.
                    sharkRow = i;
                    sharkCol = j;
                    sea[i][j] = 0;
                }
            }
        }

        Point location = new Point(-1, -1);
        while(true) {
            //현재 상어의 위치를 중심으로 우선순위가 가장 높은 먹위를 찾기위해 bfs탐색 시작
            location = bfs(sharkRow, sharkCol);
            if(location.row != -1 && location.col != -1) { //만약 먹을 수 있는 물고기를 찾았다면?
                // 만약 현재 상어의 Size와 먹은 개수가 동일하다면?
                // 상어의 Size를 업데이트 시켜주고 상어의 위치를 변경해주어야 한다.
                eat++;
                sea[location.row][location.col] = 0; //먹은 물고기는 없애주어야 함.
                sharkRow = location.row; //상어 위치 옮겨주고
                sharkCol = location.col;
                totalTime += time[location.row][location.col];
                //그런데 만약 먹은 개수와 상어의 사이즈가 같다면?
                if(eat == sharkSize) {
                    sharkSize++;
                    eat = 0;
                }
            }
            else {
                //bfs결과 먹을 수 있는 물고기를 찾지 못하였다면?
                break;
            }
        }

        System.out.println(totalTime);

    }
    public static Point bfs(int row, int col) { // Point 클래스 타입의 객체 반환
        //BFS탐색을 통해 먹을 수 있는 먹이 중 문제의 조건에 맞게
        //가장 우선순위가 높은 물고기를 찾아서 그 위치를 반환해주어야 함.
        int findRow = -1; //먹이 후보 물고기의 위치를 저장할 변수들
        int findCol = -1;
        int minTime = Integer.MAX_VALUE; //후보군 물고기를 먹는데 걸리는 시간을 저장할 변수

        Queue<Point> q = new LinkedList<>();
        time = new int[N][N];
        q.add(new Point(row, col)); //현재 상어 위치를 큐에 넣어준다.

        while(!q.isEmpty()) {
            Point v = q.remove();
            for(int k=0; k<4; k++) {
                int nr = v.row + mv[k][0];
                int nc = v.col + mv[k][1];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N) { //바다 속인지 일단 확인
                    if(time[nr][nc] == 0 && sea[nr][nc] <= sharkSize) { // 방문하지 않은 지역으로 가야함!
                        //현재 바다속이 그냥 갈 수 있는 지역이거나, 혹은 물고기가 있다면 현재 아기 상어의 크기보다 작거나 같아야함
                        time[nr][nc] = time[v.row][v.col] + 1; //시간 +1 을 해준다.
                        if(minTime < time[nr][nc]) {
                            //만약 후보 물고기를 먹는데 걸리는 시간보다
                            //현재 갈 수 있는 지역으로 가는 시간이 더 길다면?
                            //이미 후보 물고기를 찾았으므로, 더이상 갈 이유가 없다.
                            continue;

                        }
                        q.add(new Point(nr, nc)); //이동할 수 있는 지역은 큐에 넣어준다!
                        if(sea[nr][nc] != 0 && sea[nr][nc] < sharkSize) {
                            //만약 먹을 수 있는 물고기가 있다?
                            //만약 후보 물고기를 먹는데 걸리는 시간보다 작다면 update해줌
                            //처음 물고기를 찾았을 경우, minTime = Max 상태이므로 무조건 물고기를
                            //후보군에 넣고 minTime을 update하게된다!
                            if(minTime > time[nr][nc]) {
                                findRow = nr;
                                findCol = nc;
                                minTime = time[nr][nc];
                            }
                            else if(minTime == time[nr][nc]) { //만약, 둘 다 걸리는 시간이 같다면?
                                //위쪽에 있거나, 혹은 둘 다 위에 있다면 더 왼쪽에 있는 것 선택!
                                if(findRow > nr || ( (findRow == nr && findCol > nc) ) ) {
                                    findRow = nr;
                                    findCol = nc;
                                    minTime = time[nr][nc];
                                }
                            }
                        }
                    }

                }
            }

        }
        return new Point(findRow, findCol);
    }
}
