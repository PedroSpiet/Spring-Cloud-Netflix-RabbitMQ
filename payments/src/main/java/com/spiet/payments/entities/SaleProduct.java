package com.spiet.payments.entities;

import com.spiet.payments.VOs.SaleProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sale_product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SaleProduct implements Serializable {

    private static final long serialVersionUID = 3279855097655387959L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_product", nullable = false, length = 10)
    private Long idProduct;

    @Column(name = "quantity", nullable = false, length = 10)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sale")
    private Sale sale;

    public static SaleProduct create(SaleProductVO spv) {
        return new ModelMapper().map(spv, SaleProduct.class);
    }
}
