package com.DIPGroup3.foodology.common;
// A simple way to pass out the info from CloudImage Labelling Processor
// Will direct the label from CloudImageLabellingProcessor to StillImageActivity's toast
// Can be improved to fill in more functions

public class LabelReader {
    private final String TAG = "LabelReader";

    private static String First_result;
    private static String Second_result;
    private static String Confidence_result1;
    private static String Confidence_result2;
    public LabelReader(){
    }
    public static String First_result(){
        return First_result;
    }
    public static String Second_result(){
        return Second_result;
    }
    public static String Confidence_result1(){ return Confidence_result1; }
    public static String Confidence_result2(){
        return Confidence_result2;
    }

    public static void add_First_result(String string){
        First_result = string;
    }
    public static void add_Second_result(String string){
        Second_result = string;
    }
    public static void add_Confidence_result1(Float C1){
        Confidence_result1 = String.valueOf(C1);
    }
    public static void add_Confidence_result2(Float C2){
        Confidence_result2 = String.valueOf(C2);
    }



    public void LabelDetector() {



    }
    // Frame for label and recipe matching process (TBD)
    /*
    public void LabelDetector() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference first_ingredient_Ref = database.getReference("ingredient");

        first_ingredient_Ref.setValue(First_result);
        // [END write_message]

        // [START read_message]
        // Read from the database
        first_ingredient_Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                // Match First_result and recipe here

                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        // [END read_message]
    }

     */
}

