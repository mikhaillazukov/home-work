package com.sbrf.reboot.generics;

public abstract class BankProduct {
    private final long clientId;

    public long getClientId() {
        return clientId;
    }

    public BankProduct(Long clientId) {
        this.clientId = clientId;
    }
}
