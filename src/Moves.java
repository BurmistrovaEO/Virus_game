import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ScheduledExecutorService;

public interface Moves extends Remote {
    final String NAME = "moves";
    public String reproduct(String str) throws RemoteException;
    public String kill_rival(String str) throws RemoteException;

    int check_clients(String str) throws RemoteException;
}
