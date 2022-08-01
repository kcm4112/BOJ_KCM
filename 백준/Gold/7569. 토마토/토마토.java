import java.nio.Buffer;
import java.util.*;
import java.io.*;

class Point{
    int h, r, c;
    Point(int high, int row, int col){
        h = high;
        r = row;
        c = col;
    }
}


public class Main {
    static int N, M, H;
    static int[][][] map;
    static int[][] mv = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}}; //상,하,좌,우,위,아래.
    static int allzero, total, res = 0;
    static Queue<Point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer s = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(s.nextToken());
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[i][j][k] == 1) {
                        q.add(new Point(i, j, k));
                    }
                }
            }
        }
        bfs(); //1이 있다면 거기서부터 최대한 만들 수 있는 만큼 주변의 토마토를 1로 만든다!
        int min = -999;
        //이제 전체 map 중에 안익은 것이 있는지 판단!
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    res = Math.max(map[i][j][k], res);
                    if (map[i][j][k] == 0) {
                        total = -1;
                    }
                }
            }
        }
        if (total == -1) {
            System.out.println(-1);
        } else if (allzero == 0) {
            System.out.println(0);
        } else {
            System.out.println(res - 1);
        }

    }

    static void bfs(){
        while (!q.isEmpty()) {
            Point v = q.remove();
            for (int a = 0; a < 6; a++) {
                int nh = v.h + mv[a][2];
                int nr = v.r + mv[a][0];
                int nc = v.c + mv[a][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H) {
                    if (map[nh][nr][nc] == 0) {
                        map[nh][nr][nc] = map[v.h][v.r][v.c] + 1;
                        q.add(new Point(nh, nr, nc));
                        allzero = 1;
                    }
                }
            }
        }
    }
}
