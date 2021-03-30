package com.spiet.payments.VOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spiet.payments.entities.Sale;
import com.spiet.payments.entities.SaleProduct;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@JsonPropertyOrder({"id", "date", "products", "totalValue"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleVO extends RepresentationModel<SaleVO> implements Serializable {
    private static final long serialVersionUID = -2039118431967862301L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("products")
    private List<SaleProductVO> products;

    @JsonProperty("totalValue")
    private Double totalValue;

    public static SaleVO create(Sale sale) {
        return new ModelMapper().map(sale, SaleVO.class);
    }
}
