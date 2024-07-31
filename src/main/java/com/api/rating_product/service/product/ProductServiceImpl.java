package com.api.rating_product.service.product;

import com.api.rating_product.DTOS.product.ProductDTO;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product findProductById(Long id) {
        return this.productRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product newProduct = new Product(productDTO);
        newProduct.setName(newProduct.getName());
        newProduct.setDescription(newProduct.getDescription());
        this.saveProduct(newProduct);
        return newProduct;

    }

    public void saveProduct(Product product){

        this.productRepository.save(product);

    }

}
