package academy.prog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFiles {
    private static volatile Map<String , Map<String, String>> filesForClient = new HashMap<>();

    public static void putFilesInMap(String to, String fileName, String path){
        if(filesForClient.containsKey(to)){
            filesForClient.get(to).put(fileName,path);
        }else{
            filesForClient.put(to, new HashMap<>());
            filesForClient.get(to).put(fileName, path);
        }
    }

    public static Map<String, Map<String, String>> getFilesForClient() {
        return new HashMap<>(filesForClient);
    }
    public static List<String> getFilePathForSomeTo(String to){
        List<String > paths = new ArrayList<>();
        if(filesForClient.containsKey(to)){
            for(String innerKey: filesForClient.get(to).keySet()){
                paths.add(innerKey);
                paths.add(filesForClient.get(to).get(innerKey));
            }
        }else{
            return null;
        }
        return paths;
    }

}
