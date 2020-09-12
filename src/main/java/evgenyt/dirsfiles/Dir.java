package evgenyt.dirsfiles;

import lombok.Data;
import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@Entity
public class Dir {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String path;
    private LocalDateTime dateTime;
    @OneToMany(mappedBy="dir")
    private List<FileInfo> files = new ArrayList<>();

    public void setFilesInfo() {
        File dirFile = new File(path);
        for (File file : dirFile.listFiles()) {
            files.add(new FileInfo(file.getName(), file.length(), file.isDirectory(),this));
        }
        System.out.println("Found files " + files.size() + " for dir " + path);
    }

}
