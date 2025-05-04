package controlador;

/**
 * Interfície del patró d'esdeveniments.
 * Permet a la vista o altres components reaccionar a notificacions.
 */
public interface Notificar {
    void notificar(Notificacio notificacio);
}
