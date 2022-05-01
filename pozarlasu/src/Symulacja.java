import java.util.Random;
import java.util.Scanner;

public class Symulacja {

    private static int size;
    private static double forestation;

    public static void data(){
        Scanner skaner = new Scanner(System.in);
        System.out.println("Podaj rozmiar mapy:");
        size = skaner.nextInt();
        System.out.println("Podaj wartosc prawdopodobienstwa zalesienia (przedzial [0;1])");
        forestation = skaner.nextDouble();
    }

    public static boolean skan(char[][] mapa) {
        boolean skaner = false;
        for (int k = 0; k < mapa.length; k++) {
            for (int l = 0; l < mapa[k].length; l++) {
                if (mapa[k][l] == 'B') {
                    for (int i = 0; i < mapa.length; i++) {
                        for (int j = 0; j < mapa[i].length; j++) {
                            if (mapa[i][j] == 'T' && odleglosc(k, i, l, j) < 2) {
                                skaner = true;
                                return skaner;
                            }
                        }
                    }
                }
            }
        }
        return skaner;
    }
    public static double odleglosc(int x1, int x2, int y1, int y2) {
        double od;
        double x;
        double y;
        x = (x1 - x2) * (x1 - x2);
        y = (y1 - y2) * (y1 - y2);
        od = Math.sqrt(x + y);
        return od;
    }
    public static char[][] initmap(int L, double forestation){
        forestation*=100;
        char[][] map = new char[L][L];
        for(int i=0;i<L;i++){
            for(int j=0;j<L;j++){
                int los = new Random().nextInt(101);
                if(los<forestation) map[i][j]='T';
                else map[i][j]='X';
            }
        }
        for(int i=0;i<L;i++){
            for(int j=0;j<L;j++){
                System.out.printf("%2c",map[i][j]);
            }
            System.out.printf("\n");
        }
        return map;
    }
    public static void initpozar(char[][] mapa){
        for(int i=0;i<mapa[0].length;i++){
            if(mapa[0][i]=='T') mapa[0][i]='B';
        }
        for(int i=0;i< mapa.length;i++){
            for(int j=0;j<mapa[i].length;j++){
                System.out.printf("%2c",mapa[i][j]);
            }
            System.out.printf("\n");
        }
    }
    public static void podpalanie(char[][] mapa){
        do{
            for (int k = 0; k < mapa.length; k++) {
                for (int l = 0; l < mapa[k].length; l++) {
                    if (mapa[k][l] == 'B') {
                        for (int i = 0; i <mapa.length; i++){
                            for (int j = 0; j < mapa[i].length; j++) {
                                if (mapa[i][j] == 'T') {
                                    if (odleglosc(k, i, l, j) < 2) {
                                        mapa[i][j] = 'B';
                                    }
                                }
                            }
                        }
                    }

                }}
            for (int i = 0; i < mapa.length; i++) {
                for (int j = 0; j < mapa[i].length; j++) {
                    System.out.printf("%2c", mapa[i][j]);
                }
                System.out.printf("\n");
            }
        }while(skan(mapa));
    }
    public static double stosunek(char[][] map, int drzewo){
        float podpalone = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]=='B') podpalone++;
            }
        }
        double stosunek;
        if(drzewo==0) {
            return 0;
        }
        else{
            stosunek = podpalone/drzewo;
            return stosunek;
        }
    }
    public static void main(String[] args) {
        data();
        int drzewo = 0;
        char[][] mapka = initmap(size, forestation);
        System.out.println("\n");
        for (int i = 0; i < mapka.length; i++) {
            for (int j = 0; j < mapka[i].length; j++) {
                if (mapka[i][j] == 'T') drzewo++;
            }
            System.out.printf("\n");
        }
        initpozar(mapka);
        System.out.println("\n");
        podpalanie(mapka);
        System.out.printf("\n");
        System.out.println("Stosunek: "+stosunek(mapka, drzewo));

    }
}