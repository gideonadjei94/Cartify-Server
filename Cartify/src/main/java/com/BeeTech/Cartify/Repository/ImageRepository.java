package com.BeeTech.Cartify.Repository;

import com.BeeTech.Cartify.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
