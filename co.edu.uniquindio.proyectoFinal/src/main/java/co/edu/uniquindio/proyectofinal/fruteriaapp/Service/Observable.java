package co.edu.uniquindio.proyectofinal.fruteriaapp.Service;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
