package com.example.petproject.ui;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

public class Test {


    public static ArrayList<Double> Calculate_CHO(String food_type, double protein_rate, double fat_rate, double ash_rate, double fibre_rate, int weight, String pet_type) {
        ArrayList<Double> strings = new ArrayList<>();
        Log.d("protein_rate", "protein_rate:"+protein_rate);
        if (TextUtils.equals(food_type, "dry")) {
            protein_rate = protein_rate / 0.9;
            fat_rate = fat_rate / 0.9;
            ash_rate = ash_rate / 0.9;
            fibre_rate = fibre_rate / 0.9;
        } else {
            protein_rate = protein_rate / 0.25;
            fat_rate = fat_rate / 0.25;
            ash_rate = ash_rate / 0.25;
            fibre_rate = fibre_rate / 0.25;
        }
        double v = 100 - protein_rate - fat_rate - ash_rate - fibre_rate;

        strings.add(v);


        double v1 = Pet_need_per_day(weight, pet_type);
        strings.add(v1);

        double v2 = Offer_per_100g(protein_rate, fat_rate, v);
        Log.d("protein_rate", "protein_rate:"+protein_rate);

        strings.add(v2);

        double v3 = Food_need_per_day(weight, pet_type, protein_rate, fat_rate, v, food_type);
        strings.add(v3);
        return strings;
    }


    public static double Pet_need_per_day(float weight, String pet_type) {
        if (TextUtils.equals(pet_type, "dog")) {
            return Math.pow(weight, 0.75) * 130;
        } else {
            return Math.pow(weight, 67) * 130;
        }
    }


    public static double Offer_per_100g(double protein_rate, double fat_rate, double CHO_rate) {
        double energy_protein = protein_rate * 3.5;
        double energy_fat = fat_rate * 8.5;
        double energy_CHO = CHO_rate * 3.5;
        double v = energy_protein + energy_fat + energy_CHO;
        return v;
    }


    public static double Food_need_per_day(float weight, String pet_type, double protein_rate, double fat_rate, double CHO_rate, String food_type) {
        double pet_need_per_day = Pet_need_per_day(weight, pet_type);
        double offer_per_g = Offer_per_100g(protein_rate, fat_rate, CHO_rate) / 100;
        double DM_food = pet_need_per_day / offer_per_g;
        if (TextUtils.equals(food_type, "dry"))
            return DM_food * 100 / 90;
        else
            return DM_food * 100 / 25;
    }
}
