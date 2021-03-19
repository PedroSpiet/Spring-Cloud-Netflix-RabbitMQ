package com.spiet.payments.config;

import com.spiet.payments.VOs.ProductVO;
import com.spiet.payments.entities.Product;
import com.spiet.payments.repositories.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductReceiveMessage {

    private final ProductRepository produtoRepository;

    @Autowired
    public ProductReceiveMessage(ProductRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive (@Payload ProductVO produtoVO){
        produtoRepository.save(Product.create(produtoVO));
    }
}
