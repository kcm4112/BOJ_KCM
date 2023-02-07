import java.util.*;
import java.io.*;

class Point {
    int row = 0;
    int col = 0;
    Point(int r, int c) {
        row = r;
        col = c;
    }
}
public class Main {
    static int N, M;
    static Point red, blue;
    static boolean ans = false;
    static char [][] map;
    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //가로 X 세로 N과 M입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        red = new Point(0, 0);
        blue = new Point(0, 0);
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char c = input.charAt(j);
                if(c == 'R') {
                    red.row = i;
                    red.col = j;
                    map[i][j] = c;
                }
                else if(c == 'B') {
                    blue.row = i;
                    blue.col = j;
                    map[i][j] = c;
                }
                else {
                    map[i][j] = c;
                }
            }
        }

        //처음 dir로 -1을 넘겨주어 4방향에 대해 모두 탐색할 수 있도록 한다.
        if(solve(-1, 1, red, blue)) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }

    }
    public static boolean solve(int dir, int count, Point r, Point b) {
        if(count > 10) { //
            return false;
        }
        for(int d = 0; d<4; d++) {
            if(dir == d) { //이전에 움직였던 방향으로는 다시 안움직여도됨. 어차피 못감!
                continue;
            }
            //파라미터로 들어온 방향에 대하여 R, B를 이동시켜 주어야 함.
            Point nextRed = move(r, d);
            Point nextBlue = move(b, d);

            //만약 빨간 구슬만 빠진 경우라면?
            if(map[nextRed.row][nextRed.col] == 'O' && map[nextBlue.row][nextBlue.col] != 'O') {
                return true;
            }
            else if(map[nextBlue.row][nextBlue.col] == 'O') {
                continue; //다른 방향에 대해 다시 탐색!
            }
            else if(nextRed.row == nextBlue.row && nextRed.col == nextBlue.col) {
                //만약 move결과 둘이 같은 곳에 위치했다면?
                //겹치는 경우는 존재하지 않으므로, 누가 원래 앞에 있었냐에 따라 위치를 조정해주어야 함
                if(d == 0) { //이동방향 = 상, 상대적으로 아래쪽에 있던놈의 row를 +1해주어야함
                    if(r.row > b.row) {
                        nextRed.row = nextRed.row + 1;
                    }
                    else {
                        nextBlue.row = nextBlue.row + 1;
                    }
                }
                else if(d == 1) { //이동방향 = 하, 상대적으로 위쪽에 있던놈의 row를 -1해주어야함
                    if(r.row < b.row) {
                        nextRed.row = nextRed.row - 1;
                    }
                    else {
                        nextBlue.row = nextBlue.row - 1;
                    }
                }
                else if(d == 2) { //이동방향 = 좌, 상대적으로 오른쪽에 있던놈의 col를 +1해주어야함
                    if(r.col > b.col) {
                        nextRed.col = nextRed.col + 1;
                    }
                    else {
                        nextBlue.col = nextBlue.col + 1;
                    }
                }
                else if(d == 3) { //이동방향 = 우, 상대적으로 왼쪽에 있던놈의 col를 -1해주어야함
                    if(r.col < b.col) {
                        nextRed.col = nextRed.col - 1;
                    }
                    else {
                        nextBlue.col = nextBlue.col - 1;
                    }
                }
            }

            //아직 게임이 안끝났다면, 또 solve과정이 필요함!
            //만약 또 solve하는 과정에서 true가 발생했다면?
            //return true를 해주어 계속 빠져나와야 함.
            if(solve(d, count+1, nextRed, nextBlue)) {
                return true;
            }
        }
        return false;
    }

    public static Point move(Point ball, int d) { //d는 방향을 표시!
        int nr = ball.row;
        int nc = ball.col;
        while(true) {
            nr += mv[d][0];
            nc += mv[d][1];
            if(map[nr][nc] == '#') { //쭉~ 이동시키다가 벽을 만나면?
                //이전의 위치로 다시 돌려놔야 함.
                nr -= mv[d][0];
                nc -= mv[d][1];
                break;
            }
            else if(map[nr][nc] == 'O') { //만약 구멍을 만나면?
                break;
            }
        }

        return new Point(nr, nc);
    }
}
