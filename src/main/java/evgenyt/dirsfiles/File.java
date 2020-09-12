package evgenyt.dirsfiles;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class File {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="dir_id", nullable=false)
    private Dir dir;

    public File() {
    }

    public File(String name, Dir dir) {
        this.name = name;
        this.dir = dir;
    }
}
