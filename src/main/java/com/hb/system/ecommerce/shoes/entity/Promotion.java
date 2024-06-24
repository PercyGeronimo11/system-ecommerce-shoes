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
    @Column(name = "PROMT_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PROMT_id;

    @Column(name = "PROMT_percentage")
    private int PROMT_percentage;

    @Column(name = "PROMT_start_date")
    private Date PROMT_start_date;

    @Column(name = "PROMT_end_date")
    private Date PROMT_end_date;

    @Column(name = "PROMT_description")
    private String PROMT_description;

    @Column(name = "PROMT_status")
    private boolean PROMT_status;

    public Promotion() {
    }

    public Promotion(int PROMT_id, int PROMT_percentage, Date PROMT_start_date, Date PROMT_end_date, String PROMT_description, boolean PROMT_status) {
        this.PROMT_id = PROMT_id;
        this.PROMT_percentage = PROMT_percentage;
        this.PROMT_start_date = PROMT_start_date;
        this.PROMT_end_date = PROMT_end_date;
        this.PROMT_description = PROMT_description;
        this.PROMT_status = PROMT_status;
    }
    public Promotion(int PROMT_percentage, Date PROMT_start_date, Date PROMT_end_date, String PROMT_description, boolean PROMT_status) {
        this.PROMT_percentage = PROMT_percentage;
        this.PROMT_start_date = PROMT_start_date;
        this.PROMT_end_date = PROMT_end_date;
        this.PROMT_description = PROMT_description;
        this.PROMT_status = PROMT_status;
    }
    public int getPROMT_id() {
        return PROMT_id;
    }

    public void setPROMT_id(int PROMT_id) {
        this.PROMT_id = PROMT_id;
    }

    public int getPROMT_percentage() {
        return PROMT_percentage;
    }

    public void setPROMT_percentage(int PROMT_percentage) {
        this.PROMT_percentage = PROMT_percentage;
    }

    public Date getPROMT_start_date() {
        return PROMT_start_date;
    }

    public void setPROMT_start_date(Date PROMT_start_date) {
        this.PROMT_start_date = PROMT_start_date;
    }

    public Date getPROMT_end_date() {
        return PROMT_end_date;
    }

    public void setPROMT_end_date(Date PROMT_end_date) {
        this.PROMT_end_date = PROMT_end_date;
    }

    public String getPROMT_description() {
        return PROMT_description;
    }

    public void setPROMT_description(String PROMT_description) {
        this.PROMT_description = PROMT_description;
    }

    public boolean isPROMT_status() {
        return PROMT_status;
    }

    public void setPROMT_status(boolean PROMT_status) {
        this.PROMT_status = PROMT_status;
    }
}
