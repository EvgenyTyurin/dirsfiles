package evgenyt.dirsfiles;

import lombok.Data;

import java.time.format.DateTimeFormatter;

/**
 * Directory display on web page
 */

@Data
public class DirDisplay {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private long id;
    private String dateTime;
    private String path;
    private long dirCount;
    private long fileCount;
    private String size;

    public DirDisplay(Dir dir) {
        id = dir.getId();
        dateTime = DATE_TIME_FORMATTER.format(dir.getDateTime());
        path = dir.getPath();
        long sizeLong = 0;
        for (FileInfo fileInfo : dir.getFiles()) {
            if (fileInfo.isDirectory()) {
                dirCount++;
            }
            else {
                fileCount++;
                sizeLong += fileInfo.getSize();
            }
        }
        size = Utils.strSize(sizeLong);
    }
}
