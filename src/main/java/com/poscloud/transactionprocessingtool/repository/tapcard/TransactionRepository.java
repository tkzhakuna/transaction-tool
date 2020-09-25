package com.poscloud.transactionprocessingtool.repository.tapcard;

import com.poscloud.transactionprocessingtool.domain.tapcard.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Spring Data JPA repository for the Transaction entity.
 *
 * @author Samuel Gwokuda <samuel@poscloud.co.zw>
 * @author Takaedza Hakunavanhu <takaedza@poscloud.co.zw>
 * @version v1.0
 * @since v1.0
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //Transaction getByAcquire_remote_reference(String rrn);
    @Query(value="FROM Transaction t WHERE t.id>:id AND t.transaction_types_id=:type")
    List<Transaction>findByTransaction_types_id(@Param("id")Long id, @Param("type")Integer type, Pageable pageable);
    Transaction findByAmountAndRrn(Double amount, String rrn);
}
