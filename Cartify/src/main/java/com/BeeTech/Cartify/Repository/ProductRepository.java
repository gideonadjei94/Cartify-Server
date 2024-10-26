package com.BeeTech.Cartify.Repository;

import com.BeeTech.Cartify.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
