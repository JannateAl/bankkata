package com.bankkata.view;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.bankkata.model.Transaction;

public class SimpleTransactionFormatter implements TransactionFormatter{

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(Transaction transaction, String clientZone) {
        
        ZoneId zoneId = ZoneId.of(clientZone); 
        ZonedDateTime clientTimeZone = transaction.getDate().withZoneSameInstant(zoneId);

        return String.format("%s | %.2f EUR | %.2f EUR | %s",
            transaction.getOperation(),
            transaction.getAmount(),
            transaction.getBalanceAfterOper(),
            clientTimeZone.format(DATE_FORMATTER)
        );
    }
}