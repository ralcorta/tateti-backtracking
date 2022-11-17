package implement;

import api.ITaTeTiTDA;

import java.util.Arrays;

public class TaTeTi implements ITaTeTiTDA {
    private static final int boardSize = 9;
    private static final int pc = 1;
    private static final int player = -1;
    private static final int empty = 0;

    private final int[] board = new int[boardSize];

    public boolean boardCompleted(int[] board) {
        int i = 0;
        boolean found = false;
        while (i < boardSize && !found) {
            if(board[i] == empty) {
                found = true;
            }
            i++;
        }
        return found;
    }

    @Override
    public void Inicializar() {
        for (int i = 0; i < boardSize; i++) {
            board[i] = empty;
        }
    }

    @Override
    public void Turno(boolean juegaMaquinaPrimero, int selectedPosition) throws Exception {
        int pos = selectedPosition - 1;
        if(juegaMaquinaPrimero && (pos < 0 || pos > 8 || board[pos] != empty)) {
            board[pos] = pc;
        };
    }

    @Override
    public void Jugar(int selectedPosition) throws Exception {
        int pos = selectedPosition - 1;
        if(pos < 0 || pos > 8 || board[pos] != empty) {
            throw new Exception("Posicion erronea");
        }
        board[pos] = player;
        JugarPc();
    }

    public int result(int[] board) {
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

    public  int JugarPc() {
        return JugarPc(board, pc);
    }

    public int JugarPc(int[] board, int turno) {
        if(boardCompleted(board)) {
            return result(board);
        }
        int[] copiedBoard = Arrays.copyOf(board, boardSize);
        int value;

        if(turno == pc) {
            value = Integer.MIN_VALUE;
        } else {
            value = Integer.MAX_VALUE;
        }

        for (int i = 0; i < boardSize; i++) {
            if(copiedBoard[i] == empty) {
                copiedBoard[i] = turno;
                if(turno == pc) {
                    value = Math.max(value, JugarPc(copiedBoard, player));
                } else {
                    value = Math.min(value, JugarPc(copiedBoard, pc));
                }
            }
        }

        return value;
    }
}
