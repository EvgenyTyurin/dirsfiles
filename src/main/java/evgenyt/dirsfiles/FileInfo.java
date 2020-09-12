package evgenyt.dirsfiles;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class FileInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private long size;
    private boolean isDirectory;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="dir_id", nullable=false)
    private Dir dir;

    public FileInfo() {
    }

    public FileInfo(String name, long size, boolean isDirectory, Dir dir) {
        this.name = name;
        this.size = size;
        this.isDirectory = isDirectory;
        this.dir = dir;
    }
}
