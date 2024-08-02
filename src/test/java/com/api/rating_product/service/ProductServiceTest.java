package com.api.rating_product.service;

import com.api.rating_product.DTOS.product.ProductDTO;
import com.api.rating_product.common.ProductConstant;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.repositories.ProductRepository;
import com.api.rating_product.service.product.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void createProduct_withValidDate_ReturnProduct(){

        ProductDTO productDTO = ProductConstant.PRODUCT_DTO;
        Product newProduct = new Product(productDTO);

        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(newProduct);

        Product sut = productService.createProduct(productDTO);
        assertThat(sut).isEqualTo(newProduct);

    }

    @Test
    public void createProduct_withInvalidData_ThrowsException(){

        ProductDTO productDTO = ProductConstant.INVALID_PRODUCT;
        Product product = new Product(productDTO);

        when(productRepository.save(product)).thenThrow(RuntimeException.class);

        assertThatThrownBy(()->productService.createProduct(productDTO)).isInstanceOf(RuntimeException.class);

    }

}
