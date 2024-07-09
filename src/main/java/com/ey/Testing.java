package com.ey;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testing {

    public static int countSubArray(int arr[],int n)
    {
      int count = 0;
      int sum = 0;
      int targetSum;

      Map<Integer,Integer> map = new HashMap<>();
      for(int i=0;i<arr.length;i++)
      {
          sum+=arr[i];
          targetSum = sum-n*(i+1);

          if(map.containsKey(targetSum))
          {
              count+=map.get(targetSum);
          }

          map.put(sum-n*(i+1),map.getOrDefault(sum-n*(i+1),0)+1);
      }

      return count;
    }

    public static void apiCall() throws IOException, InterruptedException {
        var url = "https://api.github.com/users/LearnCodeWithDurgesh";
        //httprequest
       var request =  HttpRequest
               .newBuilder()
               .GET().
        uri(URI.create(url))
               .build();

        var client = HttpClient.newBuilder().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        System.out.println(response.statusCode());
    }
    public static void main(String args[]) throws IOException, InterruptedException {
//
//        int arr[]={1,2,3,4,2,3,1,5};
//        int n=3;
//        System.out.println(countSubArray(arr,n));

        List<Integer> list = new ArrayList<>();
        apiCall();
    }
}
