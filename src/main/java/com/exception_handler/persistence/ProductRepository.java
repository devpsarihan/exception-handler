package com.exception_handler.persistence;

import com.exception_handler.persistence.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByNameAndCategoryId(String name, int categoryId);

}
