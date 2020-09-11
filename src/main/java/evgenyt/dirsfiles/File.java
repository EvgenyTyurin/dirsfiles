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

    @ManyToOne
    @JoinColumn(name="dir_id", nullable=false)
    private Dir dir;

}
