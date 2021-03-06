package by.gsu.study.sales.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "sales")
@EqualsAndHashCode(exclude = "sales")

@Entity
@Table(name = "product")
public class Product implements IEntity{

    public Product() {
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Sale> sales;
}
