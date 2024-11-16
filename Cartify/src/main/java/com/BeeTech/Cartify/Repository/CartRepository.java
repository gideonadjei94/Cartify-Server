package com.BeeTech.Cartify.Repository;

import com.BeeTech.Cartify.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
