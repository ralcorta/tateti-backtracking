package api;

public interface ITaTeTiTDA {
    void Inicializar(); // Sin precondiciones

    // void Turno(boolean juegaMaquinaPrimero) throws Exception; // Permite indicar si la máquina jugará como primer(true) o segundo(false) jugador;
    void Turno(boolean juegaMaquinaPrimero);

    // Juego ya inicializado
    void Jugar(int posicion) throws Exception; // Recibe la posición en la que el jugador humano coloca la ficha
}
