import java.util.*;

class TrieNode {
    String content = null;
    Map<String, TrieNode> children = new TreeMap<>();
}

class FileSystem {
    TrieNode root = new TrieNode();

    public List<String> ls(String path) {
        TrieNode curNode = root;

        if (!path.equals("/")) {
            // Find the directory
            String[] list = path.split("/");
            String curString;

            for (int i = 1; i < list.length; i++) {
                curString = list[i];
                curNode = curNode.children.get(curString);
            }

        }

        List<String> children = new LinkedList<>(curNode.children.keySet());

        return children;

    }

    public void mkdir(String path) {
        TrieNode curNode = root;
        String[] arr = path.split("/");
        for (int i = 1; i < arr.length; i++) {
            String curString = arr[i];
            if (!curNode.children.containsKey(curString)) {
                curNode.children.put(curString, new TrieNode());
            }
            curNode = curNode.children.get(curString);
        }
    }

    public void addContentToFile(String filePath, String content) {
        TrieNode curNode = root;
        String[] arr = filePath.split("/");
        for (int i = 1; i < arr.length; i++) {
            String curString = arr[i];
            if (!curNode.children.containsKey(curString)) {
                curNode.children.put(curString, new TrieNode());
            }
            curNode = curNode.children.get(curString);
        }
        if (curNode.content != null) {
            curNode.content += content;
        } else {
            curNode.content = content;
        }
    }

    public String readContentFromFile(String filePath) {
        TrieNode curNode = root;
        String[] arr = filePath.split("/");
        for (int i = 1; i < arr.length; i++) {
            String curString = arr[i];
            if (!curNode.children.containsKey(curString)) {
                curNode.children.put(curString, new TrieNode());
            }
            curNode = curNode.children.get(curString);
        }
        return curNode.content;
    }

    public static void main(String args[]) {
        FileSystem fs = new FileSystem();
        //   System.out.println("====Ls_First_Time* ====" + fs.ls(""));
        System.out.println("====Ls_First_Time ====" + fs.ls("/"));
        fs.addContentToFile("/a/b/c/d", "ishan");
        fs.mkdir("/a/b/c/e");
        fs.mkdir("/a/b/c/f");
        fs.mkdir("/a/b/c/g");
        fs.mkdir("/a/b/c/h");
        System.out.println("===== Ls_Second_Time_**** ====" + fs.ls("/a/b/c"));
        System.out.println("===== Ls_Second_Time_Adding_Content ====" + fs.ls("/a/b/c/f"));
        System.out.println("=====Read_Content_From_File_fs ====" + fs.readContentFromFile("/a/b/c/d"));
    }
}

