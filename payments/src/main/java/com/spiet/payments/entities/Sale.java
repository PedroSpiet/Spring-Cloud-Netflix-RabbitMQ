package com.spiet.payments.entities;

import com.spiet.payments.VOs.SaleProductVO;
import com.spiet.payments.VOs.SaleVO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sale implements Serializable {
    private static final long serialVersionUID = 2018495467970178491L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = {CascadeType.REFRESH})
    private List<SaleProduct> products;

    @Column(name = "total_value", nullable = false, length = 10)
    private Double totalValue;


    public static Sale create(SaleVO sale) {
        return new ModelMapper().map(sale, Sale.class);
    }
}
