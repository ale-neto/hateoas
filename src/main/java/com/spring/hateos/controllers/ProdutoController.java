package com.spring.hateos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hateos.models.ProdutoModel;
import com.spring.hateos.services.ProdutoService;

@RestController
public class ProdutoController  {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/produtos")
	public ResponseEntity<List<ProdutoModel>> getAllProdutos() {
		return produtoService.getAllProdutos();
	}
	
	@GetMapping("/produtos/{id}")
	public ResponseEntity<ProdutoModel> getOneProduto(@PathVariable(value = "id") long id) {
		return produtoService.getOneProduto(id);
	}
	
	@PostMapping("produto")
	public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Validated ProdutoModel produtoModel){
		return produtoService.saveProduto(produtoModel);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteProduto(@PathVariable(value = "id") long id){
		return produtoService.deleteProduto(id);
	}
}
