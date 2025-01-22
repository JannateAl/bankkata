package com.bankkata.view;

import com.bankkata.model.Transaction;

/**
 * Interface for formatting a transaction for display.
 */

public interface TransactionFormatter {
    String format(Transaction transaction, String clientZone);
}
