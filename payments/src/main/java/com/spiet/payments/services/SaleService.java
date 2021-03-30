package com.spiet.payments.services;

import com.spiet.payments.VOs.ProductVO;
import com.spiet.payments.VOs.SaleProductVO;
import com.spiet.payments.VOs.SaleVO;
import com.spiet.payments.entities.Product;
import com.spiet.payments.entities.Sale;
import com.spiet.payments.entities.SaleProduct;
import com.spiet.payments.exceptions.ResourceNotFoundException;
import com.spiet.payments.repositories.ProductRepository;
import com.spiet.payments.repositories.SaleProductRepository;
import com.spiet.payments.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private SaleRepository repo;
    private SaleProductRepository repoProduct;

    @Autowired
    public SaleService(SaleRepository repo, SaleProductRepository repoProduct) {
        this.repo = repo;
        this.repoProduct = repoProduct;
    }

    public SaleVO create(SaleVO saleProductVO) {
        Sale sale = repo.save(Sale.create(saleProductVO));

        List<SaleProduct> salveProducts = new ArrayList<>();

        saleProductVO.getProducts().forEach(p -> {
            SaleProduct pv = SaleProduct.create(p);
            pv.setSale(sale);
            salveProducts.add(repoProduct.save(pv));
        });
        sale.setProducts(salveProducts);
        return SaleVO.create(sale);
    }

    public Page<SaleVO> findAll(Pageable pageable) {
        var page = repo.findAll(pageable);
        return page.map(this::convertToSaleVO);
    }

    public SaleVO findById(Long id) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado!"));
        return SaleVO.create(entity);
    }

    private SaleVO convertToSaleVO(Sale sale) {
        return SaleVO.create(sale);
    }
}
