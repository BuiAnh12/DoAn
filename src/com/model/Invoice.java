package com.model;
import java.util.Date;
import java.time.LocalDateTime;
public class Invoice {
    private int invoiceId;
    private int customerId;
    private int staffId;
    private Date purchaseDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Invoice(int invoiceId, int customerId, int staffId, Date purchaseDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.purchaseDate = purchaseDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
}