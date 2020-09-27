package com.example.foodology.common;
// A simple way to pass out the info from CloudImage Labelling Processor
// Will direct the label from CloudImageLabellingProcessor to StillImageActivity's toast
// Can be improved to fill in more functions
public class Testprint {


    private static String Text_import;
    public Testprint(){
    }
    public static String Testprintout(){
        return Text_import;
    }
    public static void add(String string){
        Text_import = string;
    }
}
