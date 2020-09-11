package evgenyt.dirsfiles;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Dir {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String path;
    private LocalDateTime dateTime;
    @OneToMany(mappedBy="dir")
    private List<File> files;

}
