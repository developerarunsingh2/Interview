package com.hnt.practice;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTes {

    public static void main(String[] args) {

        String str = "ab%%&&   123 ^^-";

        //find the list of special character, with regex

//        String regex = "[^a-z]";
        String regex = "[^a-z]";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if(m.find())
        {
            System.out.print(m.results());
        }
    }
}
