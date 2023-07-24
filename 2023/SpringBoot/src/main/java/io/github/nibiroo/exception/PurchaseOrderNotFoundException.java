package io.github.nibiroo.exception;

public class PurchaseOrderNotFoundException extends RuntimeException {
    public PurchaseOrderNotFoundException() {
        super("Purchase order not found.");
    }
}
