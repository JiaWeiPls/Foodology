package com.google.firebase.samples.apps.mlkit.common;
// A simple way to pass out the info from CloudImage Labelling Processor
// Will direct the label from CloudImageLabellingProcessor to StillImageActivity's toast
// Can be improved to fill in more functions
public class Testprint {


    private static String First_result;
    private static String Second_result;
    private static String Confidence_result1;
    private static String Confidence_result2;
    public Testprint(){
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
}
