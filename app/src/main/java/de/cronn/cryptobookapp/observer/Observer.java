package de.cronn.cryptobookapp.observer;


public interface Observer {
    void addObserved(Observable observable);
    void notifyObserved();
}
