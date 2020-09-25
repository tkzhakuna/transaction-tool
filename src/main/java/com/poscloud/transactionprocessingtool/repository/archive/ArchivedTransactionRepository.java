package com.poscloud.transactionprocessingtool.repository.archive;

import com.poscloud.transactionprocessingtool.domain.tapcard.tapcardarchive.ArchivedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArchivedTransactionRepository extends JpaRepository<ArchivedTransaction,Long> {

   ArchivedTransaction findByAmountAndRrn(Double amount, String rrn);
}
