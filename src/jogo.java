import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class jogo {
    class Preencher{
        
        static String[][] preenchendo(int jogador,String[][] velha, int[] x, int[] y, int lin, int co){
            String local_velha[][] = velha;
            for (int i=0; i<3; i++){
                for (int i2=0; i2<3; i2++){
                    boolean tx = contains(x, i);
                    boolean ty = contains(y, i2);
                    if (tx == true && ty==true){
                        if (jogador == 0){
                            local_velha[lin][co] = "O";
                        }
                        else{
                            local_velha[lin][co] = "X";
                        }
                    }

                    else if (local_velha[i][i2] == "*"){
                        local_velha[i][i2] = "*";
                    }
                }
            }

           return local_velha;
        }

        public static boolean contains(final int[] arr, final int key) {
            return Arrays.stream(arr).anyMatch(i -> i == key);
        }

        static void escrevendo(String[][] velha){
            System.out.print("\033[H\033[2J");
            for (int i=0; i<3; i++){
                for (int i2=0; i2<3; i2++){
                    System.out.printf("%s    ", velha[i][i2]);
                }
                System.out.println();
            }
        }

        public static byte ganhandor(String[] jogadas_2, String[] jogadas_1){
            
            byte ganhador = 0;
            String[] PH1 = {"00", "01", "02", null, null, null, null, null, null, null};
            String[] PH2 = {"10", "11", "12", null, null, null, null, null, null, null};
            String[] PH3 = {"20", "21", "22", null, null, null, null, null, null, null};

            String[] PV1 = {"00", "10", "20", null, null, null, null, null, null, null};
            String[] PV2 = {"01", "11", "21", null, null, null, null, null, null, null};
            String[] PV3 = {"02", "12", "22", null, null, null, null, null, null, null};
            String[] PV4 = {"00", "11", "01", "21", null, null, null, null, null, null};


            String[] PL1 = {"00", "11", "22", null, null, null, null, null, null, null};
            String[] PL2 = {"02", "11", "20", null, null, null, null, null, null, null};
            
            if (Arrays.equals(jogadas_1, PH1) || Arrays.equals(jogadas_1, PH2) ||
               Arrays.equals(jogadas_1, PH3) || Arrays.equals(jogadas_1, PV1) ||
               Arrays.equals(jogadas_1, PV2) || Arrays.equals(jogadas_1, PV3) ||
               Arrays.equals(jogadas_1, PL1) || Arrays.equals(jogadas_1, PL2) || 
               Arrays.equals(jogadas_1, PV4)){
                ganhador = 1;
                return ganhador;
            }

            else if(
            Arrays.equals(jogadas_2, PH1) || Arrays.equals(jogadas_2, PH2) ||
            Arrays.equals(jogadas_2, PH3) || Arrays.equals(jogadas_2, PV1) ||
            Arrays.equals(jogadas_2, PV2) || Arrays.equals(jogadas_2, PV3) ||
            Arrays.equals(jogadas_2, PL1) || Arrays.equals(jogadas_2, PL2) ||
            Arrays.equals(jogadas_1, PV4))
            {
                ganhador = 2;
                return ganhador;
            }

            return ganhador;
        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        byte contador=0;
        int[] px = new int[10];
        int[] py = new int[10];
        Random init = new Random();
        String[] jogadas_1 = new String[10];
        byte cj1=0;
        String[] jogadas_2 = new String[10];
        byte cj2=0;

        for (int i=0; i<10; i++){
            px[i] = -1;
            py[i] = -1;
        }

        Scanner in = new Scanner(System.in);
        
        String[][] velha = new String[3][3];

        for (int i=0; i<3; i++){
            for (int i2=0; i2<3; i2++){
    
                velha[i][i2] = "*";
            }
        }

        Preencher.escrevendo(velha);

        int jogador = init.nextInt(2);

        while (contador < 9){

            if (jogador == 0){
                for (String i : jogadas_1){
                    if (i != null){
                        System.out.printf("%s ", i);
                    }
                }
                System.out.println();
                System.out.println("-- Jogador 1 escolha uma posição --");
                System.out.print("linha: ");
                int lin = in.nextInt();
                System.out.print("coluna: ");
                int co = in.nextInt();

                lin = lin-1;
                co = co-1;
                if (velha[lin][co] == "*"){
                    py[contador] = lin;            
                    px[contador] = co;
                    velha = Preencher.preenchendo(jogador, velha, px, py, lin, co);
                    Preencher.escrevendo(velha);
                                            
                    String nl = Integer.toString(lin);
                    String nc = Integer.toString(co);

                    jogadas_1[cj1] = nl + nc;
                    cj1++;
                    contador++; 
                    }
                    else {
                        System.out.println("posição invalida!");
                    }
                    jogador = 1;
                }
                else {
                    for (String i : jogadas_2){
                        if (i != null){
                            System.out.printf("%s ", i);
                        }
                    }
                    System.out.println();
                    System.out.println("-- Jogador 2 escolha uma posição --");
                    System.out.print("linha: ");
                    int lin = in.nextInt();
                    System.out.print("coluna: ");
                    int co = in.nextInt();
                    lin = lin-1;
                    co = co-1;

                    if (velha[lin][co] == "*"){
                        py[contador] = lin;            
                        px[contador] = co;
                        velha = Preencher.preenchendo(jogador, velha, px, py, lin, co);
                        Preencher.escrevendo(velha);
                        
                        String nl = Integer.toString(lin);
                        String nc = Integer.toString(co);

                        jogadas_2[cj2] = nl + nc;
                        cj2++;
                        contador++;
                        
                    }
                    else {
                        System.out.println("posição invalida!");
                    }
                jogador = 0;
                }
            
            byte ganhador = Preencher.ganhandor(jogadas_1, jogadas_2);

            if (ganhador == 1){
                System.out.println("--- JOGADOR 1 GANHOU! ---");
                break;
            }
            else if (ganhador == 2){
                System.out.println("--- JOGADOR 2 GANHOU! ---");
                break;
            }
        }
        in.close();
    }
}
