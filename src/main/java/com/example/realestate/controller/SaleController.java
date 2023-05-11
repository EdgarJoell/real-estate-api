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

    /**
     * Calls on getSales() from Sale Service
     * @return a list of sales
     */
    @GetMapping(path = "/sales/")
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    /**
     * Calls on getSaleById() from Sale Service
     * @param saleId sale id we are searching for
     * @return a sale by sale id
     */
    @GetMapping(path = "/sales/{saleId}/")
    public Optional<Sale> getSaleById(@PathVariable Long saleId) {
        return saleService.getSaleById(saleId);
    }

    /**
     * Calls on getSaleById() from Sale Service
     * @param sale sale we are creating
     * @return the sale we created
     */
    @PostMapping(path = "/properties/{propertyId}/sales/")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Sale> createSale(@PathVariable Long propertyId, @RequestBody Sale sale) {
        return saleService.createSale(propertyId, sale);
    }

}
