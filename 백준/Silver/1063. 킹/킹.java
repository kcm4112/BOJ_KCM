import java.util.*;
import java.io.*;

//'H'에서 해당 문자 뺀 훠 +1하면 ex) H - A + 1 = 8. 이후 8에서 값을 빼주면 해당 열의 index가 나온다.

public class Main {
    public static void main(String[] args) throws IOException{
        int [][] answer = new int [8][8];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫번째줄 String배열로 저장.
        String s = br.readLine();
        String [] list = s.split(" ");

        //킹의 위치 초기화
        int king_col = 8 - ('H' - list[0].charAt(0) + 1);
        int king_row = 8 - Integer.parseInt(list[0].charAt(1) + "");
//        System.out.printf("%d %d\n", king_col, king_row);
        //돌의 위치 초기화
        int rock_col = 8 - ('H' - list[1].charAt(0) + 1);
        int rock_row = 8 - Integer.parseInt(list[1].charAt(1) + "");
//        System.out.printf("%d %d\n", rock_col, rock_row);
        //움직이는 횟수
        int N = Integer.parseInt(list[2]);
//        System.out.println(N);

        //돌 위치는 -1로 초기화하기!
        answer[rock_row][rock_col] = -1;

        //이동 진행
        for(int i=0;i<N;i++){
            String move = br.readLine();
            if(move.equals("B")){
                int nkr = king_row +1;
                int nkc = king_col;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row +1;
                    int nrc = rock_col;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }
            }
            else if(move.equals("L")){
                int nkr = king_row;
                int nkc = king_col-1;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row;
                    int nrc = rock_col-1;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }
            }
            else if(move.equals("R")){
                int nkr = king_row;
                int nkc = king_col + 1;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row;
                    int nrc = rock_col + 1;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }

            }
            else if(move.equals("T")){
                int nkr = king_row - 1;
                int nkc = king_col;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row - 1;
                    int nrc = rock_col;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }
            }
            else if(move.equals("RT")){
                int nkr = king_row-1;
                int nkc = king_col+1;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row-1;
                    int nrc = rock_col+1;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }
            }
            else if(move.equals("LT")){
                int nkr = king_row-1;
                int nkc = king_col-1;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row-1;
                    int nrc = rock_col-1;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }
            }
            else if(move.equals("RB")){
                int nkr = king_row+1;
                int nkc = king_col+1;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row+1;
                    int nrc = rock_col+1;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }
            }
            else if(move.equals("LB")){
                int nkr = king_row+1;
                int nkc = king_col-1;
                if(nkr > 7 || nkr < 0 || nkc < 0 || nkc > 7){
                    continue;
                }
                if(answer[nkr][nkc]==-1){//그 자리에 돌이 있을 경우
                    int nrr = rock_row+1;
                    int nrc = rock_col-1;
                    if(nrr > 7 || nrr < 0 || nrc < 0 || nrc > 7){
                        continue;
                    }
                    else{
                        answer[nkr][nkc] = 0;
                        answer[nrr][nrc] = -1;
                        king_row = nkr;
                        king_col = nkc;
                        rock_row = nrr;
                        rock_col = nrc;
                    }
                }
                else{
                    king_row = nkr;
                    king_col = nkc;
                }
            }
        }

        int final_king = 'A' + king_col ;
        int final_rock = 'A' + rock_col ;
        System.out.printf("%c%d\n%c%d", final_king, 8-king_row, final_rock, 8-rock_row);



    }
}
