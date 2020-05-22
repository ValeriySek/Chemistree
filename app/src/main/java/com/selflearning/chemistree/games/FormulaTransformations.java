package com.selflearning.chemistree.games;

import java.util.ArrayList;
import java.util.List;

public class FormulaTransformations {

    public static List<String> disassembleFormula(String acid){
        int length = acid.length();
        List<String> strings = new ArrayList<>();


        for(int i = 0; i < length; ){


            if(Character.isLetter(acid.charAt(i))){
                int iStart = i++;
                while(i < length && Character.isLowerCase(acid.charAt(i))) i++;
                String name = acid.substring(iStart, i);
                strings.add(name);
            } else {

                int iStart = i;


                if(Character.isDigit(acid.charAt(i))) {
                    while (i < length && Character.isDigit(acid.charAt(i))) i++;
                    String name = acid.substring(iStart, i);
                    strings.add(name);
                }


                if(i < length && (acid.charAt(i) == '(' || acid.charAt(i) == ')')){
                    String name = acid.substring(i, ++i);
                    strings.add(name);
                }
            }
        }
        return strings;
    }


}
