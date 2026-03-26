package com.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "plat")
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas etre vide")
    @Size(min = 2, max = 100)
    private String nom;

    @Min(value = 0, message = "Les calories ne peuvent pas etre negatives")
    private int calories;

    @Positive(message = "Le prix doit etre positif")
    private double prix;

    private double coutIngredients;

    public Plat() {}

    public Plat(String nom, int calories, double prix, double cout) {
        this.nom = nom;
        this.calories = calories;
        this.prix = prix;
        this.coutIngredients = cout;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public int getCalories() { return calories; }
    public double getPrix() { return prix; }
    public double getCoutIngredients() { return coutIngredients; }

    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setCalories(int c) { this.calories = c; }
    public void setPrix(double p) { this.prix = p; }
    public void setCoutIngredients(double c) { this.coutIngredients = c; }
}