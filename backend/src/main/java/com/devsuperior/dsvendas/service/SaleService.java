package com.devsuperior.dsvendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.entities.Sale;
import com.devsuperior.dsvendas.repositories.SaleRepository;
import com.devsuperior.dsvendas.repositories.SellerRepository;



@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository; //para evitar interações repetidas. Válido para poucos "vendedores"
	
	@Transactional(readOnly = true) //@Transac.. - vai garantir que toda operação seja resolvida no service. E o readOnly é para não fazer locking de escrita no banco
	public Page<SaleDTO> findAll(Pageable pageable) {
		sellerRepository.findAll(); //para evitar interações repetidas. Válido para poucos "vendedores"
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleDTO(x));
	}	

}
