package com.example.realestate.controller;

import com.example.realestate.model.Sale;
import com.example.realestate.repository.SaleRepository;
import com.example.realestate.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/")
public class SaleController {

    private SaleService saleService;

    @Autowired
    public void setSaleService(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(path = "/sales/")
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    @GetMapping(path = "/sales/{saleId}/")
    public Optional<Sale> getSaleById(@PathVariable Long saleId) {
        return saleService.getSaleById(saleId);
    }

}
