package com.xebia.shop.v2.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    private UUID uuid;
    private Date datePaid;
    private double total;
    private String cardId;
    private String description;
    private UUID orderUuid;

    public Payment(UUID uuid, Date datePaid, double total, String description, String cardId, UUID orderUuid) {
        this.uuid = uuid;
        this.datePaid = datePaid;
        this.total = total;
        this.cardId = cardId;
        this.description = description;
        this.orderUuid = orderUuid;
    }

    public Payment() {
        // Empty constructor required by framework
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public UUID getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        return uuid.equals(payment.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "uuid=" + uuid +
                ", datePaid=" + datePaid +
                ", total=" + total +
                ", cardId='" + cardId + '\'' +
                ", description='" + description + '\'' +
                ", order=" + orderUuid +
                '}';
    }
}
