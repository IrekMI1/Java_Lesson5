import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicate {
    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, ArrayList<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] files = path.split(" ");
            for (int i = 1; i < files.length; i++) {
                List<String> ContentAndPath = getContentAndPath(files[0], files[i]);
                String content = ContentAndPath.get(0);
                String dirPath = ContentAndPath.get(1);
                if (!map.containsKey(content)) {
                    map.put(content, new ArrayList<>(List.of(dirPath)));
                } else {
                    ArrayList<String> tempList = map.get(content);
                    tempList.add(dirPath);
                    map.put(content, tempList);
                }
            }
        }

        List<List<String>> duplicates = new ArrayList<>();

        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                duplicates.add(map.get(key));
            }
        }

        return duplicates;
    }

    private static List<String> getContentAndPath(String dir, String fileName) {
        StringBuilder sbContent = new StringBuilder();
        StringBuilder sbName = new StringBuilder();
        sbName.append(dir).append("/");
        int i = 0;

        while (fileName.charAt(i) != '(') {
            sbName.append(fileName.charAt(i));
            i++;
        }

        i++;

        while (fileName.charAt(i) != ')') {
            sbContent.append(fileName.charAt(i));
            i++;
        }

        return List.of(sbContent.toString(), sbName.toString());
    }

    public static void main(String[] args) {

        String[] paths = {
                "root/a 1.txt(abcd) 2.txt(efgh)",
                "root/c 3.txt(abcd)",
                "root/c/d 4.txt(efgh)",
                "root 4.txt(efgh)"
        };

        List<List<String>> output = findDuplicate(paths);
        System.out.println(output);
    }
}