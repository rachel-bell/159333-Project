package com.example.myapplication_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getPetType();
        getBreedOrWeight();
        getPetFood();

    }



    public String petType;
    public String dogName;
    public String dogSex;
    public String dogSize;
    public double dogWeight;
    public String catSize;
    public double catWeight;

    //Get the pet type for user
    public void getPetType(){
        Button Dog = findViewById(R.id.dog);
        Dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petType = "dog";
            }
        });

        Button Cat = findViewById(R.id.cat);
        Cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petType = "cat";
            }
        });

    }



    public CheckBox breed;
    public CheckBox weight;
    //Ask the user which information they want to give (breed or weight)
    public void getBreedOrWeight(){
        breed = findViewById(R.id.breed);
        weight = findViewById(R.id.weight);

        //If 'breed' button is clicked, then ask the user give the detail of the different pet
        //If pet type is 'dog', then need to give the name, sex and the size of the dog
        //If pet type is 'cat', then only need to give the size of the cat
        //Finally the below 'calculateDog/CatWeight' function will calculate the weight of the pet by above information
        breed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (petType == "dog") {
                    if (breed.isChecked()) {
                        weight.setChecked(false);
                    }
                    getDogName();
                    getDogSex();
                    getDogSize();
                    calculateDogWeight();
                }
                if(petType =="cat"){
                    if (breed.isChecked()) {
                        weight.setChecked(false);
                    }
                    getCatSize();
                    calculateCatWeight();
                }
            }

        });

        //If 'weight' button is clicked, then the user only need to give the pet weight
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (petType == "dog") {
                    if (weight.isChecked()) {
                        breed.setChecked(false);
                    }
                    getDogWeight();
                }
                if(petType == "cat"){
                    if (weight.isChecked()) {
                        breed.setChecked(false);
                    }
                    getCatWeight();
                }
            }
        });
    }



    //Save the row count of the currently selected item to the index1 variable
    public int index1;
    public Spinner dogNameList;
    //Get the breed of user selecting, then store the index of the breed, which is easier for below weight calculation
    public void getDogName(){
        dogNameList = findViewById(R.id.dogNameList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getDogNameList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dogNameList.setAdapter(adapter);
        dogNameList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dogName = parent.getItemAtPosition(position).toString();
                index1 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    //Get the names of the dog breeds and enter into 'names' list from the excel
    private List<String> getDogNameList() {
        List<String> names = new ArrayList<>();

        try {

            InputStream inputStream = getAssets().open("C:/Users/panzh/OneDrive/Desktop/159333/Dog Breeds & Weights1.xls");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);


            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(0);
                names.add(cell.getStringCellValue());
            }


            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }



    //Get the sex of user selecting
    public void getDogSex() {
        Button Male = findViewById(R.id.male);
        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dogSex = "male";}
        });

        Button Female = findViewById(R.id.female);
        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dogSex = "female";}
        });
    }



    //Get the size of user selecting
    public void getDogSize() {
        Button Small = findViewById(R.id.dogSmall);
        Small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dogSize = "small";}
        });

        Button Median = findViewById(R.id.dogMedian);
        Median.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dogSize = "median";}
        });

        Button Large = findViewById(R.id.dogLarge);
        Large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dogSize = "large";}
        });
    }




    public int excelColumn;
    public String excelValue;
    //Calculate the weight of the dog
    public void calculateDogWeight(){
        //According to the given excel, we can know column 2 is male weight, column 3 is female weight
        if(dogSex=="male"){
            excelColumn=1;
        } else if(dogSex=="female"){
            excelColumn=2;
        }

        try {
            InputStream inputStream = getAssets().open("C:/Users/panzh/OneDrive/Desktop/159333/Dog Breeds & Weights1.xls");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFCell cell = sheet.getRow(index1).getCell(excelColumn);
            String cellValue = cell.getStringCellValue();
            excelValue=cellValue;

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Judge 'excelValue' is a range or just a value, if it is a range then need to calculate by the size of the dog
        if(excelValue.contains(" – ")){
            String[] parts = excelValue.split(" – ");
            if(dogSize.equals("small")){
                dogWeight = Double.parseDouble(parts[0]);
            }
            if(dogSize.equals("median")){
                dogWeight = (Double.parseDouble(parts[0]) + Double.parseDouble(parts[1])) / 2;
            }
            if(dogSize.equals("large")){
                dogWeight = Double.parseDouble(parts[1]);
            }
        }else{
            dogWeight=Double.parseDouble(excelValue);
        }


    }



    //Get the dog weight for the user
    public void getDogWeight(){
        EditText weightEditText = findViewById(R.id.dogWeightEditText);
        String weightString = weightEditText.getText().toString();
        dogWeight = Double.parseDouble(weightString);

    }



    //Get the cat size of the user
    public void getCatSize(){
        Button Small = findViewById(R.id.catSmall);
        Small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {catSize = "small";}
        });

        Button Median = findViewById(R.id.catMedian);
        Median.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {catSize = "median";}
        });

        Button Large = findViewById(R.id.catLarge);
        Large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {catSize = "large";}
        });
    }



    //Cat weight depend on the size, not breed
    public void calculateCatWeight(){
        if(catSize.equals("small")){
            catWeight=8;
        }
        if(catSize.equals("median")){
            catWeight=10;
        }
        if(catSize.equals("large")){
            catWeight=12;
        }
    }



    //Get the cat weight for the user
    public void getCatWeight(){
        EditText weightEditText = findViewById(R.id.catWeightEditText);
        String weightString = weightEditText.getText().toString();
        catWeight = Double.parseDouble(weightString);
    }



    public String foodName;
    public String foodType;
    public double protein;
    public double fat;
    public double ash;
    public double fibre;

    //Get the food which the pet eat
    public void getPetFood(){
        getFoodName();
        getFoodTypeProteinFatAshFibre();
    }



    //Save the row count of the currently selected item to the index1 variable
    public int index2;
    public Spinner foodNameList;
    public void getFoodName(){
        foodNameList = findViewById(R.id.foodNameList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getFoodNameList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodNameList.setAdapter(adapter);
        foodNameList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foodName = parent.getItemAtPosition(position).toString();
                index2 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    //Dog food have different food name to cat food
    //Get the name list from the excel which our group found
    private List<String> getFoodNameList() {
        List<String> names = new ArrayList<>();

        if(petType.equals("dog")) {
            try {

                InputStream inputStream = getAssets().open("C:/Users/panzh/OneDrive/Desktop/159333/Dog Food.xls");
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);


                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    XSSFRow row = sheet.getRow(i);
                    XSSFCell cell = row.getCell(0);
                    names.add(cell.getStringCellValue());
                }


                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(petType.equals("cat")) {
            try {

                InputStream inputStream = getAssets().open("C:/Users/panzh/OneDrive/Desktop/159333/Cat Food.xls");
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);


                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    XSSFRow row = sheet.getRow(i);
                    XSSFCell cell = row.getCell(0);
                    names.add(cell.getStringCellValue());
                }


                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //If the pet's food does not appear in the list, user can choose 'Other' option
        //And below will need user to provide food components for the food
        names.add("Other");

        return names;
    }




    //Dog food have different components to cat food,
    //therefore we need to judge the components by using different excel
    public void getFoodTypeProteinFatAshFibre(){
        if(!foodName.equals("Other")) {
            if(petType.equals("dog")) {
                try {
                    InputStream inputStream = getAssets().open("C:/Users/panzh/OneDrive/Desktop/159333/Dog Food.xls");
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    XSSFCell cell1 = sheet.getRow(index2).getCell(1);
                    String cellValue1 = cell1.getStringCellValue();
                    foodType = cellValue1;
                    XSSFCell cell2 = sheet.getRow(index2).getCell(2);
                    String cellValue2 = cell2.getStringCellValue();
                    protein = Double.parseDouble(cellValue2);
                    XSSFCell cell3 = sheet.getRow(index2).getCell(3);
                    String cellValue3 = cell3.getStringCellValue();
                    fat = Double.parseDouble(cellValue3);
                    XSSFCell cell4 = sheet.getRow(index2).getCell(4);
                    String cellValue4 = cell4.getStringCellValue();
                    ash=Double.parseDouble(cellValue4);
                    XSSFCell cell5 = sheet.getRow(index2).getCell(5);
                    String cellValue5 = cell5.getStringCellValue();
                    fibre=Double.parseDouble(cellValue5);

                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(petType.equals("cat")) {
                try {
                    InputStream inputStream = getAssets().open("C:/Users/panzh/OneDrive/Desktop/159333/Cat Food.xls");
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    XSSFCell cell = sheet.getRow(index2).getCell(1);
                    String cellValue = cell.getStringCellValue();
                    foodType = cellValue;
                    XSSFCell cell2 = sheet.getRow(index2).getCell(2);
                    String cellValue2 = cell2.getStringCellValue();
                    protein = Double.parseDouble(cellValue2);
                    XSSFCell cell3 = sheet.getRow(index2).getCell(3);
                    String cellValue3 = cell3.getStringCellValue();
                    fat = Double.parseDouble(cellValue3);
                    XSSFCell cell4 = sheet.getRow(index2).getCell(4);
                    String cellValue4 = cell4.getStringCellValue();
                    ash=Double.parseDouble(cellValue4);
                    XSSFCell cell5 = sheet.getRow(index2).getCell(5);
                    String cellValue5 = cell5.getStringCellValue();
                    fibre=Double.parseDouble(cellValue5);

                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        //If user choose 'Other' option, then need to give the type, protein, fat, ash and fibre of the food
        //If ash enter null, then make ash to 5
        //If fibre enter null, then make fibre to 0
        if(foodName.equals("Other")){
            Button Dry = findViewById(R.id.dry);
            Dry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodType = "dry";
                }
            });

            Button Wet = findViewById(R.id.wet);
            Wet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodType = "wet";
                }
            });

            EditText proteinEditText = findViewById(R.id.proteinEditText);
            String proteinString = proteinEditText.getText().toString();
            protein = Double.parseDouble(proteinString);

            EditText fatEditText = findViewById(R.id.fatEditText);
            String fatString = fatEditText.getText().toString();
            fat = Double.parseDouble(fatString);

            EditText ashEditText = findViewById(R.id.ashEditText);
            String ashString = ashEditText.getText().toString();
            ash = ashString.isEmpty() ? 5.0 : Double.parseDouble(ashString);

            EditText fibreEditText = findViewById(R.id.fibreEditText);
            String fibreString = fibreEditText.getText().toString();
            fibre = fibreString.isEmpty() ? 0.0 : Double.parseDouble(fibreString);
        }
    }



}