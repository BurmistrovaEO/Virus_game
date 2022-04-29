import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

class Server_interaction implements Moves
{
    private int moves;
    public static int count = 0;
    private static final long serialVersionUID = 1L;

    @Override
    public synchronized String reproduct(String str) throws RemoteException {
        return null;
    }

    @Override
    public synchronized String kill_rival(String str) throws RemoteException {
        return null;
    }

    @Override
    public synchronized int check_clients(String str) throws RemoteException{
        int ret = count;
        count++;
        return ret;
    }

    private boolean switch_active_client(){ //to give move to other player
        //check if someone won and then either shut off or continue
        return true;
    }
    //public static final String UNIC_BINDING_NAME = "server.reverse";

//    public static void main(String[] args) throws Exception
//    {
//        //создание объекта для удаленного доступа
//        final Server_interaction service = new Server_interaction();
//
//        //создание реестра расшареных объектов
//        final Registry registry = LocateRegistry.createRegistry(2099);
//        //создание "заглушки" – приемника удаленных вызовов
//        Remote stub = UnicastRemoteObject.exportObject(service,0);
//        //регистрация "заглушки" в реесте
//        registry.bind(UNIC_BINDING_NAME, stub);
//
//        //усыпляем главный поток, иначе программа завершится
//        Thread.sleep(Integer.MAX_VALUE);
//    }
        public static void main(final String[] args) throws Exception, IOException, AlreadyBoundException {
            Server_interaction server = new Server_interaction();
            Registry registry = LocateRegistry.createRegistry(2099);
            Remote stub = UnicastRemoteObject.exportObject(server,0);
            //registry.bind(UNIC_BINDING_NAME, stub);

            registry.bind(Moves.NAME, stub);

            Thread.sleep(Integer.MAX_VALUE);
            System.out.println("Counter-Server runs");
        }


}
