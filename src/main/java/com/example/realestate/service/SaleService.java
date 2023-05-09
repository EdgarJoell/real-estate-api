package com.example.realestate.service;

import com.example.realestate.model.Sale;
import com.example.realestate.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    /**
     * Gets a list of Sales
     * @return a list of sales
     */
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    /**
     * Gets sale by id
     * @param saleId we are searching for
     * @return sale based on id
     */
    public Optional<Sale> getSaleById(Long saleId) {
        return saleRepository.findById(saleId);
    }

    /**
     * Creates sale
     * @param sale we are adding
     * @return sale added
     */
    public Optional<Sale> createSale(@RequestBody Sale sale) {
        return Optional.of(saleRepository.save(sale));
    }


}
