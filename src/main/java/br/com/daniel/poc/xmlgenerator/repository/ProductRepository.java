package br.com.daniel.poc.xmlgenerator.repository;

import br.com.daniel.poc.xmlgenerator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
