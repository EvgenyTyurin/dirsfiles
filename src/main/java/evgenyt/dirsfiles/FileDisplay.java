package evgenyt.dirsfiles;

import lombok.Data;

@Data
public class FileDisplay implements Comparable<FileDisplay>{
    private String name;
    private String size;
    private boolean isDirectory;

    public FileDisplay(FileInfo fileInfo) {
        name = fileInfo.getName();
        isDirectory = fileInfo.isDirectory();
        if (fileInfo.isDirectory()) {
            size = "&#60;DIR&#62;";
        } else {
            size = DirsfilesApplication.strSize(fileInfo.getSize());
        }
    }

    @Override
    public int compareTo(FileDisplay otherFile) {
        int result = Boolean.compare(otherFile.isDirectory, isDirectory);
        if (result != 0)
            return result;
        return Utils.compareFileNames(name, otherFile.name);
    }

}
