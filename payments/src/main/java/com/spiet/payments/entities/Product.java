package com.spiet.payments.entities;

import com.spiet.payments.VOs.ProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable {
    private static final long serialVersionUID = 8250524453350932357L;

    @Id
    private Long id;

    @Column(name = "inventory", nullable = false, length = 10)
    private Integer inventory;

    public static Product create(ProductVO productVO) {
        return new ModelMapper().map(productVO, Product.class);
    }

}
