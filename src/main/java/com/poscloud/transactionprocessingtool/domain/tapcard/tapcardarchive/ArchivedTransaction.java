package com.poscloud.transactionprocessingtool.domain.tapcard.tapcardarchive;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="transaction")
public class ArchivedTransaction {
    @Id
    private Long id;
    private LocalDate date;
    private String status;
    private Double debit;
    private Double credit;
    private Double amount;
    private Double fees;
    private Double commission;
    private String billid;
    private String channel;
    private String description;
    private Integer biller_id;
    private Integer product_id;
    private Integer agent_id;
    private Integer client_id;
    private Integer device_id;
    private Integer employee_id;
    private String source;
    private String destination;
    private Integer accounts_id;
    private Integer users_id;
    private Integer transaction_types_id;
    private Integer counter_transaction_id;
    private Double balance;
    private Integer payment_channels_id;
    private Integer card_id;
    private Integer status_counter;
    private String api_data;
    private String terminal_id;
    private String capture_method;
    private String currency;
    private String acquirer_response_code;
    private String acquirer_response_description;
    private String agent_name;
    private String agent_account;
    private String association;
    private String provider;
    private String app_version;
    private String pos_type;
    private Double tax;
    @Column(name="acquire_remote_reference")
    private String rrn;
    private String operator_session;
    private String origin;
    private String other_description;
    private String reference;
    private LocalDateTime transaction_date;
}
