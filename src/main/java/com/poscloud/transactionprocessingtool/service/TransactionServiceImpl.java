package com.poscloud.transactionprocessingtool.service;

import com.poscloud.transactionprocessingtool.domain.tapcard.Transaction;
import com.poscloud.transactionprocessingtool.domain.tapcard.tapcardarchive.ArchivedTransaction;
import com.poscloud.transactionprocessingtool.repository.archive.ArchivedTransactionRepository;
import com.poscloud.transactionprocessingtool.repository.tapcard.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Service Implementation for the NmbReconService
 *
 *
 *
 * @author Samuel Gwokuda <samuel@poscloud.co.zw>
 * @author Takaedza Hakunavanhu <takaedza@poscloud.co.zw>
 * @version v1.0
 * @since v1.0
 */

@Service
//@Transactional
//@Log4j2
public class TransactionServiceImpl {
    Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final ExecutorService executor = Executors.newWorkStealingPool();

    private final TransactionRepository transRepo;

    private final ArchivedTransactionRepository atr;


    public TransactionServiceImpl(
                               TransactionRepository transRepo,

                               ArchivedTransactionRepository atr) {


        this.transRepo = transRepo;

        this.atr=atr;
    }


    //@Async("reconAsyncExecutor")
    public void reconcile() {
        log.info("----------Request to transfer transactions------------- ");
        Resource resource = new ClassPathResource("archived_trnx.csv");

        try {

            postByLine(resource);
        } catch (Exception ex) {
            ex.printStackTrace();


        }finally{
            //file.delete();
        }
        log.info("-----------------------Transactions migrated successfully-------------------------- ");


    }



    /**
     * Method to post T24 entries line by line
     *
     *
     *
     * @throws Exception mostly likely throws IO Exception
     */

    //@Transactional
    void postByLine(Resource resource) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             ) {
            reader.lines().forEach(line->{
                int num = 1;
                try {

                    final String[] data = line.split(",");
                    Double amount= Double.valueOf(data[0]);
                    String rrn=data[1];

                    ArchivedTransaction at=atr.findByAmountAndRrn(amount,rrn) ;
                    if(at!=null) {
                        if(transRepo.findByAmountAndRrn(amount,rrn)==null) {
                            Transaction trans = new Transaction();
                            BeanUtils.copyProperties(at, trans);
                            log.info(rrn + "-----------------------Saving " + amount + "-------------------------- ");
                            trans.setDescription(trans.getDescription() + "-archive");
                            trans.setAmount(amount);
                            trans.setRrn(rrn);

                            at = null;
                            transRepo.save(trans);
                        }else{
                            log.info(rrn + "-----------------------Duplicate-------------------------- ");
                        }
                    }
                } catch (Exception ex) {
                    log.error("----------------Error "+ex.getMessage());

                }



            });


        }

    }


}