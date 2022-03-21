package com.calculator;

import java.util.ArrayList;

public class StringCalculator {

    public String getDelimiter(String value){
        String delimiter=" ";
        int contador =0;
        if((""+value.charAt(0)).equals("#")){
            return delimiter="#";
        }
        for (int i=1;i<value.length();i++){
            if((""+value.charAt(i)).equals("]")){
                break;
            }
            contador+=1;
        }
        if(contador==2){
            return delimiter = "[##]";
        }
        return delimiter= "[###]";
    };

    public String[] getLista(String value, String delimiter){
        String[] list = null;
        if(delimiter != null) {
            if(delimiter.equals("#")){
                return list = value.substring(1).split(delimiter);
            }else if(delimiter.equals("[##]")){
                return list = value.substring(4).split("\\[##]");
            }
            return list = value.substring(5).split("\\[###]");
        }

        return list = value.split("[,|\n]");
    };

    public int add(String values) throws Exception {
        if(values.length() > 0) {
            int temp;
            String delimiter = null;
            try {
                temp = Integer.parseInt("" + values.charAt(0));
            } catch(Exception e) {
                if(("" + values.charAt(0)) == "-") {
                    delimiter = null;
                } else {
                    delimiter = getDelimiter(values);
                }
            }

            String[] splittedList = getLista(values,delimiter);

            ArrayList<Integer> numberList = new ArrayList<Integer>();
            int accumulator = 0;
            for(String element: splittedList) {
                int tempValue = Integer.parseInt(element);
                if(tempValue < 0) {
                    throw new Exception("NegativeNumberException");
                }
                if(tempValue > 1000) {
                    continue;
                }
                numberList.add(tempValue);
            }
            for(Integer number: numberList) {
                accumulator += number;
            }
            return accumulator;
        }
        return 0;
    }
}
