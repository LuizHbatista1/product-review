package com.api.rating_product.service.product;

import com.api.rating_product.DTOS.product.ProductDTO;
import com.api.rating_product.domain.product.Product;

public interface ProductService {

    Product createProduct(ProductDTO productDTO);

    Product findProductById(Long id);

}
