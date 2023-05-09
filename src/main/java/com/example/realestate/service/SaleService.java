package com.example.realestate.service;

import com.example.realestate.model.Sale;
import com.example.realestate.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long saleId) {
        return saleRepository.findById(saleId);
    }

}
