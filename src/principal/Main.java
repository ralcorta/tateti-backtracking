package principal;

import implement.TaTeTi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Inicializando juego...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TaTeTi game = new TaTeTi();
        game.Inicializar();
        System.out.print("Deseas jugar primero? (Y/n)");
        String firstMove = reader.readLine();
        if(firstMove != "n") {
            System.out.print("\nElige una posicion disponible entre el 1 y el 9: ");
            String pos = reader.readLine();
            game.Jugar(Integer.parseInt(pos));
        } else {
            System.out.print("Deseas elegir donde comienza la PC? (Y/n)");
            String selectPos = reader.readLine();
            if(selectPos  != "n") {
                System.out.print("\nElige una posicion disponible entre el 1 y el 9: ");
                String pos = reader.readLine();

            } else {
                System.out.println("Juega la PC.");
                game.Turno(Integer.parseInt(pos));
            }
        }
        while(game.getResult() == 0 && !game.boardCompleted()) {
            System.out.print("Elige una posicion disponible entre el 1 y el 9: ");
            String pos = reader.readLine();
            game.Jugar(Integer.parseInt(pos));
            game.JugarPc();
        }

    }
}