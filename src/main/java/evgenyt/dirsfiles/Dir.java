package evgenyt.dirsfiles;

import lombok.Data;
import javax.persistence.*;
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
    private List<File> files = new ArrayList<>();

    public void setFilesInfo() {
        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.map(Objects::toString)
                .forEach(fileName -> files.add(new File(fileName, this)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Found files " + files.size() + " for dir " + path);
    }

}
