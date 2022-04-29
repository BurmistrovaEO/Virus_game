import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static final String UNIC_BINDING_NAME = "server.reverse";

    public static void main(String[] args) throws Exception
    {
        //создание реестра расшареных объетов
        final Registry registry = LocateRegistry.getRegistry(2099);

        //получаем объект (на самом деле это proxy-объект)
        Moves service = (Moves) registry.lookup(UNIC_BINDING_NAME);

        //Вызываем удаленный метод
        String result = service.reproduct("Home sweet home.");
        System.out.println(result);
    }
}
