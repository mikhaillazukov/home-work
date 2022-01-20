package com.sbrf.reboot.atm.cassettes;

import java.util.ArrayList;
import java.util.List;

public class Cassette<T> {

    private List<T> banknotes = new ArrayList<>();

    public Cassette(List<T> banknotes) {
        this.banknotes.addAll(banknotes);
    }

    public int getCountBanknotes() {
        return banknotes.size();
    }
}
