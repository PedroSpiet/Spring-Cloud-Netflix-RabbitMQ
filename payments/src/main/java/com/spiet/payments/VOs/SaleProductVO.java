package com.spiet.payments.VOs;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spiet.payments.entities.Sale;
import com.spiet.payments.entities.SaleProduct;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@JsonPropertyOrder({"id", "idProduct", "quantity", "sale"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleProductVO extends RepresentationModel<SaleProductVO> implements Serializable {
    private static final long serialVersionUID = 8825523004400479204L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduct")
    private Long idProduct;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("sale")
    private Sale sale;

    public static SaleProductVO create(SaleProduct sp) {
        return new ModelMapper().map(sp, SaleProductVO.class);
    }
}
