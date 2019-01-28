package org.lpro.categorie.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import org.lpro.categorie.entity.Categorie;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Details {

    @Id
    private String id;
    private String type;
    private String count;
    private String locale;

    @OneToMany(targetEntity = Categorie.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Categorie> categories;

    Details() {
        // necessaire pour JPA !
    }

    public Details(String type, String count, String locale) {
        this.type = type;
        this.count = count;
        this.locale = locale;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<Categorie> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public void deleteCat(String id) {
        categories.removeIf(c -> c.getId().equals(id));
    }
}