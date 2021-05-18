package com.spring.hateos.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.hateos.models.ProdutoModel;
import com.spring.hateos.repositorys.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public ResponseEntity<List<ProdutoModel>> getAllProdutos() {
		List<ProdutoModel> produtoList = produtoRepository.findAll();
		if (produtoList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (ProdutoModel produto : produtoList) {
				long id = produto.getIdProduto();
				produto.add(linkTo(methodOn(ProdutoService.class).getOneProduto(id)).withSelfRel());
			}
			return new ResponseEntity<List<ProdutoModel>>(produtoList, HttpStatus.OK);
		}
	}

	public ResponseEntity<ProdutoModel> getOneProduto(@PathVariable(value = "id") long id) {
		Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);
		if (!produtoOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			produtoOptional.get()
					.add(linkTo(methodOn(ProdutoService.class).getAllProdutos()).withRel("List of Products"));
			return new ResponseEntity<ProdutoModel>(produtoOptional.get(), HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Validated ProdutoModel produtoModel){
		return new ResponseEntity<ProdutoModel>(produtoRepository.save(produtoModel), HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteProduto(@PathVariable(value = "id") long id){
		 ProdutoModel produtoModel = produtoRepository.getOne(id);
		produtoRepository.delete(produtoModel);
		return new ResponseEntity<>("Product "+ id + " deleted", HttpStatus.OK);
	}
	
}
