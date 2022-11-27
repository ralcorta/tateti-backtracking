package api;

public interface ITaTeTiTDA {
    void Inicializar();
    void Turno() throws Exception;
    void Turno(int pos) throws Exception;
    void Jugar(int posicion) throws Exception;
}
