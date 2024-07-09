package com.ey;

import java.util.*;
import java.util.stream.Stream;

public class StringGroupSorting {

    public static Map<Character,List<String>> getMap(List<String> listString) {
//        List<String> list = new ArrayList<>();

        Map<Character, List<String>> map = new HashMap<>();

        for (String s : listString) {
            char c = s.charAt(0);

            if (map.get(c) == null) {
                List<String> innerList = new ArrayList<>();
                innerList.add(s);
                map.put(c, innerList);

            } else {
                List<String> internalList = map.get(c);
                internalList.add(s);
                map.put(c, internalList);

            }
        }

        return map;
    }
    public static void main(String[] args) {



        List<String> listString = new ArrayList<>();
        listString.add("Aryan");
        listString.add("AA");
        listString.add("AB");
        listString.add("CA");
        listString.add("CB");

        Map<Character, List<String>> listMap = new HashMap<>();
        listMap = getMap(listString);

        Set<Map.Entry<Character, List<String>>> entrySet = listMap.entrySet();

        Set<Character> keySet =  listMap.keySet();
        List<Character> list = new ArrayList<>();
        for(char c: keySet)
        {
            list.add(c);
        }

        Collections.sort(list);

  for(char c: list)
  {
      System.out.println(listMap.get(c));
  }
    }
}
