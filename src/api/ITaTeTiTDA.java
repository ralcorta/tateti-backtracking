package api;

public interface ITaTeTiTDA {
    boolean validatePosition(int pos);
    boolean boardCompleted();
    void Inicializar();
    void Turno() throws Exception;
    void Turno(int pos) throws Exception;
    void Jugar(int posicion) throws Exception;
    int getResult();
    void JugarPc();
    void mostrarTablero(int p);

}
