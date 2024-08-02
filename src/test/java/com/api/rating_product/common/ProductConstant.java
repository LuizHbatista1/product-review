package com.api.rating_product.common;

import com.api.rating_product.DTOS.product.ProductDTO;
import com.api.rating_product.domain.product.Product;

public class ProductConstant {

    public static final ProductDTO PRODUCT_DTO =  new ProductDTO("teste-product","teste-product");
    public static final Product PRODUCT = new Product(PRODUCT_DTO);
    public static final ProductDTO INVALID_PRODUCT = new ProductDTO("","");


}
