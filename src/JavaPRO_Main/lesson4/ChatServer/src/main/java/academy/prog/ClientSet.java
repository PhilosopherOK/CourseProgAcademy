package academy.prog;

import java.util.HashSet;
import java.util.Set;

public final class ClientSet {
    private volatile static Set<String> clientSet = new HashSet<>();

    //add or check if the client is in the message list
    public synchronized static void addClient(String client) {
        if(checkForClientExist(client)){
            clientSet.add(client);
        }
    }
    public static boolean checkForClientExist(String client){
        return !clientSet.contains(client);
    }

    public synchronized static String getClientSetLikeAStr() {
        StringBuilder sb = new StringBuilder("");
        for (String client : clientSet) {
            sb.append(client + "; ");
        }
        return sb.toString();
    }
}
