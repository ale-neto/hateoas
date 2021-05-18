package com.spring.hateos.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.hateos.models.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
	
}
