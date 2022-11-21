package principal;

import implement.TaTeTi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static boolean validPosition(int pos) {
		int position = pos - 1;
		boolean valid = true;
		if (position < 0 || position > 8) {
			valid = false;
		}
		
		return valid;
	}
	
    public static void main(String[] args) throws Exception {
        System.out.println("Inicializando juego...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TaTeTi game = new TaTeTi();
        game.Inicializar();
        System.out.print("Deseas jugar primero? (Y/n)");
        String firstMove = reader.readLine();
        if(!firstMove.equals("n")) {
        	int position;
        	do {
        		System.out.print("\nElige una posicion disponible entre el 1 y el 9: ");
        		String pos = reader.readLine();
        		position = Integer.parseInt(pos);
        	} while (!validPosition(position));
        	
            game.Jugar(position);
        } else {
            System.out.print("Deseas elegir donde comienza la PC? (Y/n)");
            String selectPos = reader.readLine();
            if(!selectPos.equals("n")) {
            	int position;
            	do {
            		System.out.print("\nElige una posicion disponible entre el 1 y el 9: ");
            		String pos = reader.readLine();
            		position = Integer.parseInt(pos);
            	} while (!validPosition(position));

                game.Turno((position));
            } else {
                System.out.println("Juega la PC.");
                game.Turno();
            }
        }
        while(game.getResult() == 0 && !game.boardCompleted()) {
        	int position;
        	do {
        		System.out.print("Elige una posicion disponible entre el 1 y el 9: ");
        		String pos = reader.readLine();
        		position = Integer.parseInt(pos);
        	} while (!validPosition(position));
        	
    		game.Jugar(position);        
            game.JugarPc();
        }

    }
}