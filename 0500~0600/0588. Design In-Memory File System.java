/* 
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.
 

Example 1:


Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 

Constraints:

1 <= path.length, filePath.length <= 100
path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
1 <= content.length <= 50
At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */

class FileSystem {
    abstract class AbstractFile {
        String name;
        AbstractFile(String name) {
            this.name = name;
        }
    }
    
    class Directory extends AbstractFile{
        Map<String, AbstractFile> subdirectory; // Polymorphism 
        
        public Directory(String name) {
            super(name);
            this.subdirectory = new HashMap<>();
        }
    }
    
    class File extends AbstractFile{
        String content;
        
        public File(String name) {
            super(name);
            this.content = "";
        }
    }

    private final Directory root;
    
    public FileSystem() {
        root = new Directory("/");
    }
    
    public List<String> ls(String path) {
        String[] strs = getDirectories(path);
        Directory folder = root;
        AbstractFile sub = null; 
        for (int i = 1; i < strs.length; ++i) {
            sub = folder.subdirectory.get(strs[i]);
            if (sub instanceof Directory) {
                folder = (Directory) sub;
            }
        }
        // if the last filename represents a file 
        if (sub instanceof File) {
            return Arrays.asList(((File) sub).name);
        }
        // Otherwise it is a combination of files and directories.
        List<String> fileList = new ArrayList<>();
        for (String name: folder.subdirectory.keySet()) {
            fileList.add(name);
        }
        Collections.sort(fileList);
        return fileList;
    }
    
    public void mkdir(String path) {
        String[] strs = getDirectories(path);
        Directory folder = root;
        AbstractFile sub = null;
        for (int i = 1; i < strs.length; ++i) {
            folder.subdirectory.putIfAbsent(strs[i], new Directory(strs[i]));
            sub = folder.subdirectory.get(strs[i]);
            if (sub instanceof Directory) {
                folder = (Directory) sub;
            }
        }
    }
    
    
    public void addContentToFile(String filePath, String content) {
        String[] strs = getDirectories(filePath);
        Directory folder = root;
        AbstractFile sub = null;
        for (int i = 1; i < strs.length - 1; ++i) {
            sub = folder.subdirectory.get(strs[i]);
            if (sub instanceof Directory) {
                folder = (Directory) sub;
            }
        }
        String name = strs[strs.length - 1];
        // create a new file if it does not exist
        folder.subdirectory.putIfAbsent(name, new File(name));
        File targetFile = (File) folder.subdirectory.get(name);
        targetFile.content += content;
    }
    
    public String readContentFromFile(String filePath) {
        String[] strs = getDirectories(filePath);
        Directory folder = root;
        AbstractFile sub = null;
        // operations will be passed valid parameters
        for (int i = 1; i < strs.length - 1; ++i) {
            sub = folder.subdirectory.get(strs[i]);
            if (sub instanceof Directory) {
                folder = (Directory) sub;
            }
        }
        String name = strs[strs.length - 1];
        File targetFile = (File) folder.subdirectory.get(name);
        return targetFile.content;
    }
    
    /*
     * Get the directories by parsing the given path.
     * if the path is just "/", return an Array with just an empty String
     */
    private String[] getDirectories(String path) {
        return path.equals("/") ? new String[] {""} : path.split("/");
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */