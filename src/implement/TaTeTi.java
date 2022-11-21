package implement;

import api.ITaTeTiTDA;

import java.util.Arrays;
import java.util.Random;

public class TaTeTi implements ITaTeTiTDA {
    private static final int pc = 1;
    private static final int player = -1;
    private static final int empty = 0;

    private final int[] board = new int[9];

    public boolean boardCompleted() {
        int i = 0;
        boolean completed = true;
        while (i < board.length && completed) {
            if(board[i] == empty) {
                completed = false;
            }
            i++;
        }
        return completed;
    }

    @Override
    public void Inicializar() {
        Arrays.fill(board, empty);
    }

    @Override
    public void Turno() throws Exception {
        this.Turno(GetRandomPosition());
    }
    
    public void Turno(int selectedPosition) throws Exception {
	        int pos = selectedPosition - 1;
	        if(board[pos] != empty) {
	            throw new Exception("Posicion erronea");
	        }
           board[pos] = pc;
           this.mostrarTablero();
    }
    
    private int GetRandomPosition() {
        Random random = new Random();
        return random.ints(0, 8)
                .findFirst()
                .getAsInt();

    }

    @Override
    public void Jugar(int selectedPosition) throws Exception {
        int pos = selectedPosition - 1;
        if(board[pos] != empty) {
            throw new Exception("Posicion erronea");
        }
        board[pos] = player;
        this.mostrarTablero();
    }

    public int getResult() {
    	//return 1 || -1  (si hay ganador) || 0 (empate)
        if(board[0] == board[2] && board[1] == board[2]) {
            return board[0];
        } else if (board[3] == board[4] && board[4] == board[5]) {
            return board[3];
        } else if (board[6] == board[7] && board[6] == board[8]) {
            return board[6];
        } else if(board[0] == board[3] && board[0] == board[6]) {
            return board[0];
        } else if (board[1] == board[4] && board[1] == board[7]) {
            return board[1];
        } else if (board[2] == board[5] && board[2] == board[8]) {
            return board[2];
        } else if (board[0] == board[4] && board[0] == board[8]) {
            return board[0];
        } else if (board[2] == board[4] && board[2] == board[6]) {
            return board[2];
        } else {
            return 0;
        }
    }

    public void JugarPc() {
        if(!boardCompleted()) {
            int value = Integer.MIN_VALUE, aux, pos = 0;
            for (int i = 0; i < board.length; i++) {
                if(board[i] == empty) {
                    board[i] = pc;
                    aux = minmax(player);
                    if(aux > value) {
                        value = aux;
                        pos = i;
                    }
                    board[i] = empty;
                }
            }
            board[pos] = pc;
        }
        this.mostrarTablero();
    }

    public int minmax(int turn) {
        if(boardCompleted() || getResult() != 0) {
            if(getResult() != 0) {
                return turn == pc ? player : pc;
            } else {
                return 0;
            }
        }

        int value, aux, nextTurn;
        if(turn == pc) {
            nextTurn = player;
            value = Integer.MIN_VALUE;
        } else {
            nextTurn = pc;
            value = Integer.MAX_VALUE;
        }

        for (int i = 0; i < board.length; i++) {
            if(board[i] == empty) {
                board[i] = turn;
                aux = minmax(nextTurn);
                if((turn == pc && aux > value) || (turn == player && aux < value))
                    value = aux;
                board[i] = empty;
            }
        }

        return value;
    }

    public void mostrarTablero() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String l;
                if (board[j + (i * 3)] == 1)
                    l = "X";
                else if (board[j + (i * 3)] == -1)
                    l = "O";
                else
                    l = "-";
                System.out.print(l);
            }
            System.out.println();
        }
    }
}
