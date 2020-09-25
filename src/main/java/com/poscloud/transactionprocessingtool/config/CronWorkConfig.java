package com.poscloud.transactionprocessingtool.config;


import com.poscloud.transactionprocessingtool.service.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



/**
 * Service Implementation for the Cron jobs to run background task at specified times
 *
 *
 * @author Samuel Gwokuda <samuel@poscloud.co.zw>
 * @author Takaedza Hakunavanhu <takaedza@poscloud.co.zw>
 * @version v1.0
 * @since v1.0
 */
@Component
public class CronWorkConfig {
    private final TransactionServiceImpl nmbrec;
   private final Logger log = LoggerFactory.getLogger(CronWorkConfig.class);

    public CronWorkConfig(TransactionServiceImpl nmbrec) {
        this.nmbrec = nmbrec;
    }

    @Scheduled(cron = "0 30 22 * * *")//Run task everyday at 1am
    public void copyTransactions()  {
        log.info("----------------Starting Cron job ");
        try {
            nmbrec.reconcile();
        }catch(Exception ex){
            log.error("----------------Error "+ex.getMessage());
        }
    }
}
