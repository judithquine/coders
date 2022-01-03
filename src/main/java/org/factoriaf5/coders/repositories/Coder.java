package org.factoriaf5.coders.repositories;

import org.aspectj.apache.bcel.classfile.Code;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "coders")
public class Coder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surnames;
    private Calendar birthday;
    private String country;
    private String studies;
    private String address;
    private String promotion;

    public Coder() {

    }

    public Coder(String name, String surnames, Calendar birthday, String country, String studies, String address, String promotion) {
        this.name = name;
        this.surnames = surnames;
        this.birthday = birthday;
        this.country = country;
        this.studies = studies;
        this.address = address;
        this.promotion = promotion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }



    @Override
    public String toString() {
        return "Coder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", birthday='" + birthday + '\'' +
                ", country='" + country + '\'' +
                ", studies='" + studies + '\'' +
                ", address='" + address + '\'' +
                ", promotion='" + promotion + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coder coder = (Coder) o;
        return Objects.equals(id, coder.id) && Objects.equals(name, coder.name) && Objects.equals(surnames, coder.surnames) && Objects.equals(birthday, coder.birthday) && Objects.equals(country, coder.country) && Objects.equals(studies, coder.studies) && Objects.equals(address, coder.address) && Objects.equals(promotion, coder.promotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surnames, birthday,country, studies, address, promotion);
    }
}
