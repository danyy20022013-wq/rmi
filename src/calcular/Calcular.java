package calcular;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calcular extends Remote {
    <T> T ejecutarTarea(Tarea<T> t) throws RemoteException;
}