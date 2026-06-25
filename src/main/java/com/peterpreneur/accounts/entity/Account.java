package com.peterpreneur.accounts.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "account_number", nullable = false, unique = true, length = 25)
    private String accountNumber;

    @Column(name = "status_name", nullable = false, length = 50)
    private String statusName;

    @Column(name = "status_reason_name", length = 255)
    private String statusReasonName;

    @Column(name = "account_open_date", nullable = false)
    private LocalDate accountOpenDate;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    @Builder
    public Account(String accountNumber,
                   String statusName,
                   String statusReasonName,
                   LocalDate accountOpenDate) {
        this.accountNumber = accountNumber;
        this.statusName = statusName;
        this.statusReasonName = statusReasonName;
        this.accountOpenDate = accountOpenDate;
    }
}
