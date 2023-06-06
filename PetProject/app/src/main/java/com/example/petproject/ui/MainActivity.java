package com.example.petproject.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.example.petproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mmkv.MMKV;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected AppCompatAutoCompleteTextView variety;
    protected AppCompatAutoCompleteTextView food;

    private String varietyArr[] = new String[]{"Affenpinschers", "Afghan Hounds", "Airedale Terriers", "Akitas", "Alaskan Malamutes", "American English Coonhounds", "American Eskimo Dogs", "American Foxhounds", "American Hairless Terriers", "American Staffordshire Terriers", "Anatolian Shepherd Dogs", "Australian Cattle Dog", "Australian Shepherds", "Australian Terriers", "Basenjis", "Basset Hounds", "Beagles", "Bearded Collies", "Beaucerons", "Bedlington Terriers", "Belgian Malinois", "Belgian Sheepdogs", "Belgian Tervuren", "Bergamasco", "Berger Picards", "Bernese Mountain Dogs", "Bichons Frises", "Black and Tan Coonhounds", "Black Russian Terriers", "Bloodhounds", "Bluetick Coonhounds", "Boerboels", "Border Collies", "Border Terriers", "Borzois", "Boston Terriers", "Bouviers des Flandres", "Boxers", "Briards", "Brittanys", "Brussels Griffons", "Bull mastiff", "Bull Terriers", "Bulldog- British", "Bulldog- French", "Bulldogs", "Bullmastiffs", "Cairn Terriers", "Canaan Dogs", "Cardigan Welsh Corgis", "Cavalier King Charles Spaniel", "Cesky Terriers", "Chihuahua", "Chinese Crested", "Chinese Shar-Pei", "Chinooks", "Chow Chows", "Cirnechi dell’Etna", "Cocker Spaniel", "Collies", "Coton de Tulear", "Dalmatians", "Dandie Dinmont Terriers", "Daschund-miniature", "Daschund-standard", "Doberman Pinscher", "Doberman Pinschers", "Dogues de Bordeaux", "English Foxhounds", "English Springer Spaniel", "English Toy Spaniels", "Entlebucher Mountain Dogs", "Finnish Lapphunds", "Finnish Spitz", "Fox Terriers (Smooth)", "Fox Terriers (Wire)", "French Bulldogs", "German Pinschers", "German Shepherd Dogs", "German Shorthaired Pointer", "Giant Schnauzers", "Glen of Imaal Terriers", "Golden Retriever", "Great Dane", "Great Danes", "Great Pyrenees", "Greater Swiss Mountain Dogs", "Greyhounds", "Harriers", "Havanese", "Ibizan Hounds", "Icelandic Sheepdogs", "Irish Terriers", "Irish Wolfhounds", "Italian Greyhounds", "Jack Russel Terrier", "Japanese Chin", "Keeshonden", "Kelpie", "Kerry Blue Terriers", "Komondorok", "Kuvaszok", "Labrador Retriever", "Lagotti Romagnoli", "Lakeland Terriers", "Leonbergers", "Lhasa Apso", "Lhasa Apsos", "Lowchen", "Maltese", "Maltese Terrier", "Mastiffs", "Miniature American Shepherds", "Miniature Bull Terriers", "Miniature Pinschers", "Miniature Schnauzers", "Neapolitan Mastiffs", "Newfoundlands", "Norfolk Terrier", "Norwegian Buhunds", "Norwegian Elkhounds", "Norwegian Lundehunds", "Norwich Terriers", "Old English Sheepdogs", "Papillons", "Parson Russell Terriers", "Pekingese", "Pembroke Welsh Corgis", "Petits Bassets Griffons Vendeens", "Pharaoh Hounds", "Plotts", "Pointers", "Pointers (German Shorthaired)", "Pointers (German Wirehaired)", "Polish Lowland Sheepdogs", "Pomeranians", "Poodle- miniature", "Poodle- standard", "Poodle- toy", "Portuguese Podengo Pequenos", "Portuguese Water Dogs", "Pug", "Pulik", "Pumik", "Pyrenean Shepherds", "Rat Terriers", "Redbone Coonhounds", "Retrievers (Chesapeake Bay)", "Retrievers (Curly-Coated)", "Retrievers (Flat-Coated)", "Retrievers (Golden)", "Retrievers (Labrador)", "Retrievers (Nova Scotia Duck Tolling)", "Rhodesian Ridgebacks", "Rottweilers", "Russell Terriers", "Salukis", "Samoyeds", "Schipperkes", "Schnauzer- miniature", "Schnauzer- Standard", "Scottish Deerhounds", "Scottish Terriers", "Sealyham Terriers", "Setters (English)", "Setters (Gordon)", "Setters (Irish Red and White)", "Setters (Irish)", "Shetland Sheepdog", "Shetland Sheepdogs", "Shiba Inu", "Shih Tzu", "Siberian Huskies", "Silky Terriers", "Skye Terriers", "Sloughis", "Soft Coated Wheaten Terriers", "Spaniels (American Water)", "Spaniels (Boykin)", "Spaniels (Clumber)", "Spaniels (English Cocker)", "Spaniels (English Springer)", "Spaniels (Field)", "Spaniels (Irish Water)", "Spaniels (Sussex)", "Spaniels (Welsh Springer)", "Spanish Water Dogs", "Spinoni Italiani", "St. Bernards", "Staffordshire Bull Terriers", "Standard Schnauzers", "Swedish Vallhunds", "Tibetan Mastiffs", "Tibetan Spaniel", "Tibetan Terriers", "Toy Fox Terriers", "Treeing Walker Coonhounds", "Vizslas", "Weimaraners", "Welsh Terriers", "West Highland White Terriers", "Whippets", "Wirehaired Pointing Griffons", "Wirehaired Vizslas", "Yorkshire Terriers"};


    private String[] foodName = new String[]{"Purina One +Plue Beef Flavour Dog Kibble - Pams", "Grainfree Nutrition - Natures Goodness", "Pedigree Dog Food", "Classic Loaf  - My Dog", "Grainfree+ Nutrition - Natures Goodness", "Chicken & Sweet Potato - Vitapet", "Joint Care - Vitapet", "With Gourmet Beef & Vegetables - MyDog", "PRO PLAN Puppy Healthy Growth and Development Large Breed Dry Dog Food", "PURINA ONE Small Dog Adult Active With Chicken Dry Dog Food", "TUX Puppy Bites Chicken & Beef Dry Dog Food", "AdVENTuROS ™ Wild Chew with Venison Large Dog Chews", "PRO PLAN Veterinary Diets Canine DRM Dermatosis Dry Formula", "BENEFUL Healthy Puppy Dry Dog Food", "PRO PLAN Adult Weight Loss Sterilised All Breed Sizes Dry Dog Food", "PURINA ONE Vibrant Maturity 7+ With Chicken Formula Adult Premium Dog Food", "TUX Puppy Mini Biscuit Dry Dog Food", "AdVENTuROS ™ Training Bites with Buffalo", "PRO PLAN Veterinary Diets Canine EN Gastrointestinal Dry Formula", "BENEFUL Adult Healthy Weight With Real Chicken Dry Dog Food", "PRO PLAN Adult Sensitive Skin and Stomach All Breed Sizes Dry Dog Food", "PURINA ONE Adult True Instinct With Real Turkey Venison Premium Dry Dog Food", "TUX Senior 7+ Small Biscuit Dry Dog Food", "AdVENTuROS ™ Training Bites with Turkey", "PRO PLAN Veterinary Diets Canine EN Gastrointestinal Wet Formula", "BENEFUL Adult Incredibites With Real Beef Dry Dog Food", "PRO PLAN Adult Essential Health Large Breed Dry Dog Food", "PURINA ONE Adult Large Breed Premium Dry Dog Food", "TUX Adult Original Biscuit Chicken Flavour Dry Dog Food", "PRO PLAN Puppy Chicken & Rice Entrée Wet Food", "PRO PLAN Veterinary Diets Canine OM Obesity Management Wet Formula", "DOG CHOW Adult Complete Dry Dog Food", "PRO PLAN Adult Essential Health Small and Mini Dry Dog Food", "TUX Adult Original Biscuit Meaty Recipe Dry Dog Food", "PRO PLAN Adult Sensitive Digestion Lamb & Rice Dry Dog Food", "PRO PLAN Adult Dog Lamb & Vegetable Entrée Wet Food", "PRO PLAN Veterinary Diets Canine UR Urinary Dry Formula", "DOG CHOW Puppy Chow Complete Dry Dog Food", "PRO PLAN Puppy Healthy Growth and Development Medium Breed Dry Dog Food", "TUX Adult Small Biscuit Beef Bacon Flavour Dry Dog Food", "PRO PLAN Puppy Sensitive Digestion Lamb & Rice Dry Dog Food", "PRO PLAN Focus Adult Sensitive Skin & Stomach Salmon & Rice Entrée Wet Dog Food", "PURINA DENTALIFE Large Daily Oral Care Dog Treats", "BEGGIN Strips Bacon Flavour Dog Treats", "TUX Adult Country Biscuits Dry Dog Food", "BENEFUL Adult Prepared Meals Roasted Chicken Recipe Wet Dog Food", "BENEFUL Adult Simple Goodness With Real Beef Dry Dog Food", "PURINA ONE Adult True Instinct Beef Salmon Tender Cuts in Gravy Wet Dog Food", "FANCY FEAST Kitten Inspirations Chicken Wet Cat Food 12pk", "FANCY FEAST Adult Gravy Lovers Chicken Feast in Grilled Chicken Flavour Gravy Wet Cat Food", "PRO PLAN Adult Chicken in Gravy Wet Cat Food", "CAT CHOW Adult Complete Dry Cat Food", "PRO PLAN Adult 7+ Salmon & Tuna Dry Cat Food", "PURINA ONE Healthy Adult Salmon & Tuna Dry Cat Food", "PRO PLAN Veterinary Diets Feline HP ST/OX Hepatic Dry Formula", "FANCY FEAST Adult Petite Delights Grilled Chicken Grilled Tuna Grilled Turkey Wet Cat Food Multipack", "PRO PLAN Savor Adult Chicken Rice Entree in Gravy Wet Cat Food", "CAT CHOW Adult Naturals Original Plus Vitamins Minerals Dry Cat Food", "PRO PLAN Adult Chicken Dry Cat Food", "PRO PLAN Kitten with Chicken Wet Cat Food", "PURINA ONE® Healthy Adult with Chicken in Gravy Wet Cat Food", "FRISKIES Prime Filets With Chicken in Gravy Cat Food", "PRO PLAN Kitten Starter Salmon & Tuna Dry Cat Food", "OSCAR As Good as it Looks KITTEN Menu Wet Cat Food", "PRO PLAN Veterinary Diets Feline UR ST/OX Urinary Wet Formula", "FANCY FEAST Adult Puree Kiss Tuna Puree With Tuna Flakes Wet Cat Treats", "FRISKIES Adult Party Mix Gravylicious Crunch Chicken Flavour Dry Cat Treats", "PRO PLAN Adult Oral Care Chicken Dry Cat Food", "PRO PLAN Veterinary Diets Feline OM ST/OX Obesity Management Dry Formula", "FANCY FEAST Puree Kiss Naturals with Natural Chicken in Chicken Jelly Wet Cat Treats", "PURINA ONE Mature Adult 7+ Chicken Dry Cat Food", "CAT CHOW Adult Complete Dry Cat Food", "FANCY FEAST Medleys Chicken Florentine with Spinach in a Light Broth Wet Cat Food"};

    private EditText fat, pro, fibres, ash, weight;
    private Button add;
    private EditText cho;
    private TextView day, energy, last;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        variety = findViewById(R.id.variety);
        food = findViewById(R.id.food_name);
        add = findViewById(R.id.add);
        fat = findViewById(R.id.fat);
        pro = findViewById(R.id.pro);
        fibres = findViewById(R.id.fibres);
        ash = findViewById(R.id.ash);
        cho = findViewById(R.id.cho);
        day = findViewById(R.id.day);
        weight = findViewById(R.id.weight);
        energy = findViewById(R.id.energy);
        last = findViewById(R.id.last);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = food.getText().toString();
                String s1 = pro.getText().toString();
                String s2 = fat.getText().toString();
                String s3 = ash.getText().toString();
                String s4 = fibres.getText().toString();

                if (TextUtils.isEmpty(s) || TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2) || TextUtils.isEmpty(s3) || TextUtils.isEmpty(s4)) {
                    Toast.makeText(MainActivity.this, "please input", Toast.LENGTH_LONG).show();
                    return;
                }

                double d1 = Double.parseDouble(pro.getText().toString());
                double d2 = Double.parseDouble(fat.getText().toString());
                double d3 = Double.parseDouble(ash.getText().toString());
                double d4 = Double.parseDouble(fibres.getText().toString());
                PetFood petFood = new PetFood(s, wet_mode.isChecked() ? "wet" : "dry", d1, d2, d3, d4);


                Type listType = new TypeToken<ArrayList<PetFood>>() {
                }.getType();
                ArrayList<PetFood> petList;
                String data = MMKV.defaultMMKV().decodeString("data");
                if (TextUtils.isEmpty(data)) {
                    petList = new ArrayList<>();
                } else {
                    petList = new Gson().fromJson(data, listType);
                }
                petList.add(petFood);

                String s5 = new Gson().toJson(petList);
                MMKV.defaultMMKV().encode("data", s5);

                Toast.makeText(MainActivity.this, "add success", Toast.LENGTH_LONG).show();
//                initFoodPetData();
//                initFoodName();
            }
        });

        initFoodPetData();
        initVariety();
        initFoodName();


        RadioGroup model_rg = findViewById(R.id.model_rg);
        RadioButton swich_mode = findViewById(R.id.swich_mode);
        RadioButton input_mode = findViewById(R.id.input_mode);
        model_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.swich_mode:
                        swich_mode.setChecked(true);

                        variety.setText("");
                        food.setText("");
                        fat.setEnabled(false);
                        pro.setEnabled(false);
                        fibres.setEnabled(false);
                        ash.setEnabled(false);

                        variety.setThreshold(1);
                        food.setThreshold(1);

                        add.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.input_mode:
                        input_mode.setChecked(true);
                        variety.setText("");
                        food.setText("");
                        fat.setText("");
                        pro.setText("");
                        fibres.setText("");
                        ash.setText("");


                        fat.setEnabled(true);
                        pro.setEnabled(true);
                        fibres.setEnabled(true);
                        ash.setEnabled(true);

                        variety.setThreshold(Integer.MAX_VALUE);
                        food.setThreshold(Integer.MAX_VALUE);

                        add.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });


        RadioGroup food_rg = findViewById(R.id.food_rg);
        wet_mode = findViewById(R.id.wet_mode);
        dry_mode = findViewById(R.id.dry_mode);
        food_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.wet_mode:
                        wet_mode.setChecked(true);
                        break;
                    case R.id.dry_mode:
                        dry_mode.setChecked(true);
                        break;
                }
            }
        });

        RadioGroup anima_rg = findViewById(R.id.animal_rg);
        cat_mode = findViewById(R.id.cat_mode);
        RadioButton dog_mode = findViewById(R.id.dog_mode);

        anima_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.cat_mode:
                        cat_mode.setChecked(true);
                        break;
                    case R.id.dog_mode:
                        dog_mode.setChecked(true);
                        break;
                }
            }
        });

        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = dry_mode.isChecked();

                String s1 = pro.getText().toString();
                String s2 = fat.getText().toString();
                String s3 = ash.getText().toString();
                String s4 = fibres.getText().toString();
                String s5 = weight.getText().toString();

                if (TextUtils.isEmpty(s5) || TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2) || TextUtils.isEmpty(s3) || TextUtils.isEmpty(s4)) {
                    Toast.makeText(MainActivity.this, "please input", Toast.LENGTH_LONG).show();
                    return;
                }

                double pro1 = Double.parseDouble(pro.getText().toString());
                double fat1 = Double.parseDouble(fat.getText().toString());
                double ash1 = Double.parseDouble(ash.getText().toString());
                double fireb1 = Double.parseDouble(fibres.getText().toString());
                ArrayList<Double> strings = Test.Calculate_CHO(checked ? "dry" : "wet", pro1, fat1, ash1, fireb1, Integer.parseInt(s5), cat_mode.isChecked() ? "cat" : "dog");

                DecimalFormat decimalFormat = new DecimalFormat("#.##");  // 创建DecimalFormat对象，并指定保留两位小数的格式

                DecimalFormat decimalFormat2 = new DecimalFormat("#.#");  // 创建DecimalFormat对象，并指定保留两位小数的格式

                double cho1 = Double.parseDouble(decimalFormat.format(strings.get(0)));  // 进行四舍五入并保留两位小数
                cho.setText(cho1 + "");

                day.setText("Energy needed per day:" + decimalFormat2.format(strings.get(1)) + "kal");


                energy.setText("Energy supplied per:" + decimalFormat2.format(strings.get(2)) + "kal");

                last.setText("Food needed per day:" + decimalFormat2.format(strings.get(3)) + "g");
            }
        });
    }

    private RadioButton cat_mode;

    private RadioButton dry_mode, wet_mode;

    private List<PetFood> petFoods;

    private void initFoodPetData() {

        Type listType = new TypeToken<ArrayList<PetFood>>() {
        }.getType();
        ArrayList<PetFood> petList;
        String data = MMKV.defaultMMKV().decodeString("data");
        if (TextUtils.isEmpty(data)) {
            petList = new ArrayList<>();
        } else {
            petList = new Gson().fromJson(data, listType);
        }

        petFoods = new ArrayList<>();
        petFoods.addAll(petList);
        petFoods.add(new PetFood("Purina One +Plue", "dry", 28, 13, 5, 0));
        petFoods.add(new PetFood("Beef Flavour Dog Kibble - Pams", "dry", 20, 10, 5, 0));
        petFoods.add(new PetFood("Grainfree Nutrition - Natures Goodness", "dry", 28, 14, 5, 5));
        petFoods.add(new PetFood("Pedigree Dog Food", "dry", 22, 10, 5, 0));
        petFoods.add(new PetFood("Classic Loaf - My Dog", "wet", 6, 5, 5, 0));
        petFoods.add(new PetFood("Grainfree+ Nutrition - Natures Goodness", "wet", 6.5, 3.5, 5, 0));
        petFoods.add(new PetFood("Chicken & Sweet Potato - Vitapet", "dry", 22, 0.5, 4.5, 3.5));
        petFoods.add(new PetFood("Joint Care - Vitapet", "dry", 52, 2, 5, 2.5));
        petFoods.add(new PetFood("With Gourmet Beef & Vegetables - MyDog", "dry", 24, 10, 5, 0));
        petFoods.add(new PetFood("PRO PLAN Puppy Healthy Growth and Development Large Breed Dry Dog Food", "dry", 28, 15, 5, 4));
        petFoods.add(new PetFood("PURINA ONE Small Dog Adult Active With Chicken Dry Dog Food", "dry", 28, 18, 8, 2));
        petFoods.add(new PetFood("TUX Puppy Bites Chicken & Beef Dry Dog Food", "dry", 26, 12, 5, 4));
        petFoods.add(new PetFood("AdVENTuROS ™ Wild Chew with Venison Large Dog Chews", "dry", 8, 2, 4.5, 2));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Canine DRM Dermatosis Dry Formula", "dry", 30, 18, 5, 2.5));
        petFoods.add(new PetFood("BENEFUL Healthy Puppy Dry Dog Food", "dry", 28, 14, 5, 4));
        petFoods.add(new PetFood("PRO PLAN Adult Weight Loss Sterilised All Breed Sizes Dry Dog Food", "dry", 27, 9, 5, 6));
        petFoods.add(new PetFood("PURINA ONE Vibrant Maturity 7+ With Chicken Formula Adult Premium Dog Food", "dry", 28, 12, 5, 4.5));
        petFoods.add(new PetFood("TUX Puppy Mini Biscuit Dry Dog Food", "dry", 21, 10, 5, 4));
        petFoods.add(new PetFood("AdVENTuROS ™ Training Bites with Buffalo", "dry", 18, 5, 4.5, 1));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Canine EN Gastrointestinal Dry Formula", "dry", 24, 10.5, 5, 2));
        petFoods.add(new PetFood("BENEFUL Adult Healthy Weight With Real Chicken Dry Dog Food", "dry", 25, 8, 5, 9));
        petFoods.add(new PetFood("PRO PLAN Adult Sensitive Skin and Stomach All Breed Sizes Dry Dog Food", "dry", 30, 16, 5, 5));
        petFoods.add(new PetFood("PURINA ONE Adult True Instinct With Real Turkey Venison Premium Dry Dog Food", "dry", 30, 17, 5, 3));
        petFoods.add(new PetFood("TUX Senior 7+ Small Biscuit Dry Dog Food", "dry", 18, 5, 5, 4));
        petFoods.add(new PetFood("AdVENTuROS ™ Training Bites with Turkey", "dry", 37, 5, 9.5, 1));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Canine EN Gastrointestinal Wet Formula", "wet", 8, 2.5, 5, 3.6));
        petFoods.add(new PetFood("BENEFUL Adult Incredibites With Real Beef Dry Dog Food", "dry", 27, 13.5, 5, 4));
        petFoods.add(new PetFood("PRO PLAN Adult Essential Health Large Breed Dry Dog Food", "dry", 26, 12, 5, 4));
        petFoods.add(new PetFood("PURINA ONE Adult Large Breed Premium Dry Dog Food", "dry", 26, 12, 5, 4.5));
        petFoods.add(new PetFood("TUX Adult Original Biscuit Chicken Flavour Dry Dog Food", "dry", 18, 7, 5, 4.5));
        petFoods.add(new PetFood("PRO PLAN Puppy Chicken & Rice Entrée Wet Food", "wet", 10, 7, 5, 1.5));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Canine OM Obesity Management Wet Formula", "wet", 7.5, 2.5, 5, 13.25));
        petFoods.add(new PetFood("DOG CHOW Adult Complete Dry Dog Food", "dry", 21, 10, 5, 4.5));
        petFoods.add(new PetFood("PRO PLAN Adult Essential Health Small and Mini Dry Dog Food", "dry", 28, 17, 5, 3));
        petFoods.add(new PetFood("TUX Adult Original Biscuit Meaty Recipe Dry Dog Food", "dry", 16, 6, 5, 4));
        petFoods.add(new PetFood("PRO PLAN Adult Sensitive Digestion Lamb & Rice Dry Dog Food", "dry", 25, 15, 8.5, 4));
        petFoods.add(new PetFood("PRO PLAN Adult Dog Lamb & Vegetable Entrée Wet Food", "wet", 9, 2.5, 5, 1.5));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Canine UR Urinary Dry Formula", "dry", 22, 15, 5, 1.5));
        petFoods.add(new PetFood("DOG CHOW Puppy Chow Complete Dry Dog Food", "dry", 27, 12, 5, 5));
        petFoods.add(new PetFood("PRO PLAN Puppy Healthy Growth and Development Medium Breed Dry Dog Food", "dry", 28, 17, 5, 3));
        petFoods.add(new PetFood("TUX Adult Small Biscuit Beef Bacon Flavour Dry Dog Food", "dry", 16, 6, 5, 4));
        petFoods.add(new PetFood("PRO PLAN Puppy Sensitive Digestion Lamb & Rice Dry Dog Food", "dry", 28, 17, 5, 0));
        petFoods.add(new PetFood("PRO PLAN Focus Adult Sensitive Skin & Stomach Salmon & Rice Entrée Wet Dog Food", "wet", 7, 5, 5, 1.5));
        petFoods.add(new PetFood("PURINA DENTALIFE Large Daily Oral Care Dog Treats", "dry", 6, 1.5, 5, 1.5));
        petFoods.add(new PetFood("BEGGIN Strips Bacon Flavour Dog Treats", "dry", 15, 3.5, 5, 3.5));
        petFoods.add(new PetFood("TUX Adult Country Biscuits Dry Dog Food", "dry", 17, 9, 5, 4));
        petFoods.add(new PetFood("BENEFUL Adult Prepared Meals Roasted Chicken Recipe Wet Dog Food", "wet", 11, 2, 5, 1.5));
        petFoods.add(new PetFood("BENEFUL Adult Simple Goodness With Real Beef Dry Dog Food", "dry", 18, 7, 5, 3));
        petFoods.add(new PetFood("PURINA ONE Adult True Instinct Beef Salmon Tender Cuts in Gravy Wet Dog Food", "wet", 11, 3.5, 5, 1.5));

        petFoods.add(new PetFood("FANCY FEAST Kitten Inspirations Chicken Wet Cat Food 12pk", "wet", 13, 3.2, 5, 0.7));
        petFoods.add(new PetFood("FANCY FEAST Adult Gravy Lovers Chicken Feast in Grilled Chicken Flavour Gravy Wet Cat Food", "wet", 9, 2, 3, 1.5));
        petFoods.add(new PetFood("PRO PLAN Adult Chicken in Gravy Wet Cat Food", "wet", 12.5, 3.1, 5, 1.4));
        petFoods.add(new PetFood("CAT CHOW Adult Complete Dry Cat Food", "dry", 32, 13, 5, 3));
        petFoods.add(new PetFood("PRO PLAN Adult 7+ Salmon & Tuna Dry Cat Food", "dry", 37, 18, 5, 3));
        petFoods.add(new PetFood("PURINA ONE Healthy Adult Salmon & Tuna Dry Cat Food", "dry", 34, 14, 5, 3));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Feline HP ST/OX Hepatic Dry Formula", "dry", 28, 22, 5, 2));
        petFoods.add(new PetFood("FANCY FEAST Adult Petite Delights Grilled Chicken Grilled Tuna Grilled Turkey Wet Cat Food Multipack", "wet", 10, 2.5, 2.6, 1.5));
        petFoods.add(new PetFood("PRO PLAN Savor Adult Chicken Rice Entree in Gravy Wet Cat Food", "wet", 11, 2, 2.7, 1.5));
        petFoods.add(new PetFood("CAT CHOW Adult Naturals Original Plus Vitamins Minerals Dry Cat Food", "dry", 38, 13, 5, 3));
        petFoods.add(new PetFood("PRO PLAN Adult Chicken Dry Cat Food", "dry", 36, 16, 5, 3));
        petFoods.add(new PetFood("PRO PLAN Kitten with Chicken Wet Cat Food", "wet", 14, 3.5, 5, 0.3));
        petFoods.add(new PetFood("PURINA ONE® Healthy Adult with Chicken in Gravy Wet Cat Food", "wet", 12.5, 3, 2.5, 0.55));
        petFoods.add(new PetFood("FRISKIES Prime Filets With Chicken in Gravy Cat Food", "wet", 11, 2, 2.5, 1));
        petFoods.add(new PetFood("PRO PLAN Kitten Starter Salmon & Tuna Dry Cat Food", "dry", 43, 23, 5, 2.5));
        petFoods.add(new PetFood("OSCAR As Good as it Looks KITTEN Menu Wet Cat Food", "wet", 13, 3.4, 5, 0));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Feline UR ST/OX Urinary Wet Formula", "wet", 35, 12, 5, 1.5));
        petFoods.add(new PetFood("FANCY FEAST Adult Puree Kiss Tuna Puree With Tuna Flakes Wet Cat Treats", "wet", 6, 0.5, 3, 1));
        petFoods.add(new PetFood("FRISKIES Adult Party Mix Gravylicious Crunch Chicken Flavour Dry Cat Treats", "dry", 30, 15, 5, 4));
        petFoods.add(new PetFood("PRO PLAN Adult Oral Care Chicken Dry Cat Food", "dry", 36, 16, 5, 3));
        petFoods.add(new PetFood("PRO PLAN Veterinary Diets Feline OM ST/OX Obesity Management Dry Formula", "dry", 48, 8, 5, 7.5));
        petFoods.add(new PetFood("FANCY FEAST Puree Kiss Naturals with Natural Chicken in Chicken Jelly Wet Cat Treats", "wet", 6, 0.05, 2, 1));
        petFoods.add(new PetFood("PURINA ONE Mature Adult 7+ Chicken Dry Cat Food", "dry", 37, 11, 5, 6));
        petFoods.add(new PetFood("CAT CHOW Adult Complete Dry Cat Food", "dry", 32, 13, 5, 3));
        petFoods.add(new PetFood("FANCY FEAST Medleys Chicken Florentine with Spinach in a Light Broth Wet Cat Food", "wet", 11, 1.5, 3, 1.5));

    }

    private void initFoodName() {
        String[] foodName = new String[petFoods.size()];
        for (int i = 0; i < petFoods.size(); i++) {
            foodName[i] = petFoods.get(i).getFoodName();
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, foodName);
        food.setAdapter(stringArrayAdapter);
        food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String foodName = (String) parent.getItemAtPosition(position);
                Log.d("jwl", "foodName:" + foodName);
                for (PetFood item : petFoods) {
                    if (TextUtils.equals(item.getFoodName(), foodName)) {

                        double fat1 = item.getFat();
                        double ash1 = item.getAsh();
                        double fibre = item.getFibre();
                        double protein = item.getProtein();
                        String foodType = item.getFoodType();
                        if (TextUtils.equals(foodType, "dry")) {
                            dry_mode.setChecked(true);
                        } else {
                            wet_mode.setChecked(true);
                        }

                        fat.setText(fat1 + "");
                        ash.setText(ash1 + "");
                        fibres.setText(fibre + "");
                        pro.setText(protein + "");
                    }
                }

            }
        });
    }

    private void initVariety() {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, varietyArr);
        variety.setAdapter(stringArrayAdapter);
        variety.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedVariety = (String) parent.getItemAtPosition(position);
                Log.d("jwl", "selectedVariety:" + selectedVariety);
            }
        });
    }


}
