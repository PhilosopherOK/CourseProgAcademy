package academy.prog.inheritance;

import javax.persistence.*;

// C -> VipC

/*

    | id | name || card_num   || type
       1    cdg        ???        2
       2    sdfd    34334435      2
       3    ddfsd                 1
 */

@Entity
@Table(name = "clients4")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // default
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Regular")
public class Client5 {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Client5() {}

    public Client5(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
