package com.google.firebase.samples.apps.mlkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DIP_database.db";
    public static final String TABLE_UserDetails = "UserDetails";
    public static final String COL_UserID = "UserID";
    public static final String COL_UserName = "UserName";
    public static final String COL_UserEmail = "UserEmail";
    public static final String COL_UserContact = "UserContact";

    public static final String TABLE_Recipe = "Recipe";
    public static final String COL_RecipeID = "RecipeID";
    public static final String COL_RecipeName = "RecipeName";
    public static final String COL_RecipeSteps = "RecipeSteps";
    public static final String COL_RecipeIngredient = "RecipeIngredient";

    public static final String TABLE_IngredientDetail = "IngredientDetail";
    public static final String COL_IngredientID = "IngredientID";
    public static final String COL_IngredientName = "IngredientName";

    public static final String TABLE_LoginUser = "LoginUser";
    public static final String COL_LoginID = "LoginID";
    public static final String COL_LoginPassword = "LoginPassword";

    public static final String TABLE_Forum = "Forum";
    public static final String COL_ForumID = "ForumID";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 12);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvrecipe=new ContentValues();
        cvrecipe.put(COL_RecipeID, "R1");
        cvrecipe.put(COL_RecipeName, "Fried Rice");
        cvrecipe.put(COL_RecipeSteps, "Prepare and dice the ingredients\n" +
                "The first step to cooking is to pre-heat your pan \n" +
                "To ensure the temperature is high enough, place your hand above the center of the pan and feel the heat as shown in video, if it’s hot you may proceed to pour in your oil. \n" +
                "Let the oil sit for 30seconds.  \n" +
                "Proceed by putting in carrot and fried for 15 seconds,  \n" +
                "Next put in sausage and stir for 10seconds,  \n" +
                "Lastly put in the crabmeat for 10seconds.  \n" +
                "Shift the ingredients one side and pour in the egg.  \n" +
                "Mix the egg yolk and egg white and let them sit on the pan till the liquid dry, \n" +
                "Mix all ingredients and get ready to pour in the rice.  \n" +
                "Stir the rice and make sure it is evenly spread.  \n" +
                "Lastly, add in soya sauce for flavour.  \n" +
                "If u want the fried rice to be spicy, you can choose to input sambal,  \n" +
                "For saltiness u can add a pinch of salt.  \n" +
                "Stir till even and its ready to serve.");
        cvrecipe.put(COL_RecipeIngredient, "1 Egg\n" +
                "1 Small Bowl of Rice\n" +
                "3 Sticks of Crabmeat\n" +
                "1 Sausage\n" +
                "Half a Carrot\n" +
                "1 Table Spoon of Soya Sauce\n" +
                "1 Table Spoon of Oil\n");
        db.insert(TABLE_Recipe, COL_RecipeID, cvrecipe);

        cvrecipe.put(COL_RecipeID, "R2");
        cvrecipe.put(COL_RecipeName, "Fried Tomato With Egg");
        cvrecipe.put(COL_RecipeSteps, "Start by cutting tomatoes into small wedges and finely chop the scallion.\n" +
                "Crack 4 eggs into a bowl and season with ¼ teaspoon salt, ¼ teaspoon white pepper, ½ teaspoon sesame oil, and 1 teaspoon Shaoxing wine. Beat eggs for a minute.\n" +
                "Preheat the wok over medium heat until it just starts to smoke. Then add 2 tablespoons of oil and immediately add the eggs. Scramble the eggs and remove from the wok immediately. Set aside.\n" +
                "Add 1 more tablespoon oil to the wok, turn up the heat to high, and add the tomatoes and scallions. Stir-fry for 1 minute, and then add 2 teaspoons sugar, ½ teaspoon salt, and ¼ cup water (if your stove gets very hot and liquid tends to cook off very quickly in your wok, add a little more water). Add the cooked eggs.\n" +
                "Mix everything together, cover the wok, and cook for 1-2 minutes, until the tomatoes are completely softened.");
        cvrecipe.put(COL_RecipeIngredient, "4 Tomatoes\n" + "1 Scallion\n" + "4 Eggs\n" + "3/4 tsp Salt\n" + "1/4 tsp White Pepper\n" + "1/2 tsp Oil\n" + "1 tsp Shaoxing Wine\n" + "2 tsp Sugar\n" + "1/4-1/2 Cup Water");
        db.insert(TABLE_Recipe, COL_RecipeID, cvrecipe);


        ContentValues cvingredient=new ContentValues();
        cvingredient.put(COL_IngredientID, "I1");
        cvingredient.put(COL_IngredientName, "Egg");
        db.insert(TABLE_IngredientDetail, COL_IngredientID, cvingredient);

        cvingredient.put(COL_IngredientID, "I2");
        cvingredient.put(COL_IngredientName, "Rice");
        db.insert(TABLE_IngredientDetail, COL_IngredientID, cvingredient);

        cvingredient.put(COL_IngredientID, "I3");
        cvingredient.put(COL_IngredientName, "Tomato");
        db.insert(TABLE_IngredientDetail, COL_IngredientID, cvingredient);
        db.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_UserDetails +" ( UserID VARCHAR PRIMARY KEY, UserName TEXT, UserEmail TEXT, UserContact INTERGER)");
        db.execSQL("CREATE TABLE "+ TABLE_Recipe +" ( RecipeID VARCHAR PRIMARY KEY, RecipeName TEXT, RecipeSteps TEXT, RecipeIngredient TEXT)");
        db.execSQL("CREATE TABLE "+ TABLE_IngredientDetail +" ( IngredientID VARCHAR PRIMARY KEY, IngredientName TEXT)");
        db.execSQL("CREATE TABLE "+ TABLE_LoginUser +" ( LoginID VARCHAR PRIMARY KEY, LoginPassword INTEGER)");
        db.execSQL("CREATE TABLE "+ TABLE_Forum +" ( ForumID VARCHAR PRIMARY KEY)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_UserDetails);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Recipe);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_IngredientDetail);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LoginUser);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Forum);
        onCreate(db);

    }

    public boolean insertData(String loginid, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues loginValues = new ContentValues();
        loginValues.put(COL_LoginID,loginid);
        loginValues.put(COL_LoginPassword,password);
        long result = db.insert(TABLE_LoginUser,null, loginValues);
        if (result == -1)
            return false;
        else
            return true;
    }

}

