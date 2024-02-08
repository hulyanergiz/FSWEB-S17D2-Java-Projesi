package com.workintech.s17g2.tax;

import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable {

    public DeveloperTax() {
    }

    @Override
    public Double getSimpleTaxRate() {
        return 0.1;
    }

    @Override
    public Double getMiddleTaxRate() {
        return 0.2;
    }

    @Override
    public Double getUpperTaxRate() {
        return 0.3;
    }
}
