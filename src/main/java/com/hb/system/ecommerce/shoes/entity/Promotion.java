package com.hb.system.ecommerce.shoes.entity;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @Column(name = "PROM_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PROM_id;

    @Column(name = "PROM_percentage")
    private int PROM_percentage;

    @Column(name = "PROM_start_date")
    private Date PROM_start_date;

    @Column(name = "PROM_end_date")
    private Date PROM_end_date;

    @Column(name = "PROM_description")
    private String PROM_description;

    @Column(name = "PROM_status")
    private boolean PROM_status;

    public Promotion() {
    }

    public Promotion(int PROM_id, int PROM_percentage, Date PROM_start_date, Date PROM_end_date, String PROM_description, boolean PROM_status) {
        this.PROM_id = PROM_id;
        this.PROM_percentage = PROM_percentage;
        this.PROM_start_date = PROM_start_date;
        this.PROM_end_date = PROM_end_date;
        this.PROM_description = PROM_description;
        this.PROM_status = PROM_status;
    }
    public Promotion(int PROM_percentage, Date PROM_start_date, Date PROM_end_date, String PROM_description, boolean PROM_status) {
        this.PROM_percentage = PROM_percentage;
        this.PROM_start_date = PROM_start_date;
        this.PROM_end_date = PROM_end_date;
        this.PROM_description = PROM_description;
        this.PROM_status = PROM_status;
    }
    public int getPROM_id() {
        return PROM_id;
    }

    public void setPROM_id(int PROM_id) {
        this.PROM_id = PROM_id;
    }

    public int getPROM_percentage() {
        return PROM_percentage;
    }

    public void setPROM_percentage(int PROM_percentage) {
        this.PROM_percentage = PROM_percentage;
    }

    public Date getPROM_start_date() {
        return PROM_start_date;
    }

    public void setPROM_start_date(Date PROM_start_date) {
        this.PROM_start_date = PROM_start_date;
    }

    public Date getPROM_end_date() {
        return PROM_end_date;
    }

    public void setPROM_end_date(Date PROM_end_date) {
        this.PROM_end_date = PROM_end_date;
    }

    public String getPROM_description() {
        return PROM_description;
    }

    public void setPROM_description(String PROM_description) {
        this.PROM_description = PROM_description;
    }

    public boolean isPROM_status() {
        return PROM_status;
    }

    public void setPROM_status(boolean PROM_status) {
        this.PROM_status = PROM_status;
    }
}
