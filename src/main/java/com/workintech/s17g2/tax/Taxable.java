package com.workintech.s17g2.tax;

public interface Taxable {
    Double getSimpleTaxRate();
    Double getMiddleTaxRate();
    Double getUpperTaxRate();
}
