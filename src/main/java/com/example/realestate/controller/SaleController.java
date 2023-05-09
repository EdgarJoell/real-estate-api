package com.example.realestate.controller;

import com.example.realestate.model.Sale;
import com.example.realestate.repository.SaleRepository;
import com.example.realestate.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/sales/")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Sale> createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

}
