package implement;

import api.ITaTeTiTDA;

import java.util.Arrays;
import java.util.Random;

public class TaTeTi implements ITaTeTiTDA {
    private static final int pc = 1;
    private static final int player = -1;
    private static final int empty = 0;
    private final int[] board = new int[9];

    public int count = 0;

    public boolean prune = true;

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
        if(board[0] != 0 && board[0] == board[2] && board[1] == board[2]) {
//            System.out.println(1);
            return board[0];
        } else if (board[3] != 0 && board[3] == board[4] && board[4] == board[5]) {
//            System.out.println(2);
            return board[3];
        } else if (board[6] != 0 && board[6] == board[7] && board[6] == board[8]) {
//            System.out.println(3);
            return board[6];
        } else if(board[0] != 0 && board[0] == board[3] && board[0] == board[6]) {
//            System.out.println(4);
            return board[0];
        } else if (board[1] != 0 && board[1] == board[4] && board[1] == board[7]) {
//            System.out.println(5);
            return board[1];
        } else if (board[2] != 0 && board[2] == board[5] && board[2] == board[8]) {
//            System.out.println(6);
            return board[2];
        } else if (board[0] != 0 && board[0] == board[4] && board[0] == board[8]) {
//            System.out.println(7);
            return board[0];
        } else if (board[2] != 0 && board[2] == board[4] && board[2] == board[6]) {
//            System.out.println(8);
            return board[2];
        } else {
//            System.out.println(0);
            return 0;
        }
    }

    public void JugarPc() {
        if(!boardCompleted()) {
            int value = Integer.MIN_VALUE, aux, pos = 0;
            int alfa = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if(board[i] == empty) {
                    board[i] = pc;
                    aux = minmax(player, alfa, beta);
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

    private int minmax(int turn, int alfa, int beta) {
        count++;
        if(boardCompleted() || getResult() != 0) {
            return getResult();
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
                aux = minmax(nextTurn, alfa, beta);
                if (turn == pc && aux > value) {
                    value = aux;
                    alfa = Math.max(value, alfa);
                }
                else if (turn == player && aux < value) {
                    value = aux;
                    beta = Math.min(value, beta);
                }
                board[i] = empty;
                if (prune && alfa > beta)
                    break;
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
