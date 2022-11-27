package principal;

import implement.TaTeTi;

import java.io.BufferedReader;
import java.io.IOException;
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

	public static String GetMessageResponse(String message) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\n");
		System.out.print(message);
		return reader.readLine();
	}

	public static int GetPosition() throws IOException {
		int pos;
		do {
			String m = GetMessageResponse("Elige una posicion disponible entre el 1 y el 9: ");
			pos = Integer.parseInt(m);
		} while (!validPosition(pos));
		return pos;
	}

	public static boolean GetSimpleResponse(String message) throws IOException {
		String baseMessage = String.format("%s (Y/n) ", message);
		String m = GetMessageResponse(baseMessage);
		return !m.equals("n");
	}
	
    public static void main(String[] args) throws Exception {
        System.out.println("Inicializando juego...");
        TaTeTi game = new TaTeTi();
        game.Inicializar();
		if(!GetSimpleResponse("Deseas jugar con poda?")) {
			game.prune = false;
		}
        if(GetSimpleResponse("Deseas jugar primero?")) {
			int position = GetPosition();
			game.Jugar(position);
			game.JugarPc();
        } else {
            if(GetSimpleResponse("Deseas elegir donde comienza la PC?")) {
                game.Turno(GetPosition());
            } else {
                System.out.println("Juega la PC.");
                game.Turno();
            }
        }
        while(game.getResult() == 0 && !game.boardCompleted()) {
    		game.Jugar(GetPosition());
			if(game.boardCompleted() || game.getResult() != 0)
				break;
            game.JugarPc();
        }

		System.out.println(String.format("El ganador es: %s", game.GetWinner()));
		System.out.println(String.format("Cantidad de jugadas evaluadas: %s", Integer.toString(game.count)));
    }
}