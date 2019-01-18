package com.kozitski.task3;

import com.kozitski.task3.entity.Wagon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WorkExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        ExecutorService service = Executors.newFixedThreadPool(7);
        ArrayList<Wagon> wagons = new ArrayList<>(Arrays.asList(new Wagon(), new Wagon(),new Wagon(),new Wagon(),new Wagon(),new Wagon(),new Wagon()));
        ArrayList<Future<Integer>> results = new ArrayList<>(Arrays.asList(service.submit(wagons.get(0)), service.submit(wagons.get(1)),service.submit(wagons.get(2)),
                service.submit(wagons.get(3)),service.submit(wagons.get(4)),service.submit(wagons.get(5)),service.submit(wagons.get(6))));


        boolean checker = true;
        while (checker){

            int counter = 0;
            for(Wagon wagon : wagons){
                if(wagon.getIsEnded()){
                    counter++;
                }
            }

            if(counter == results.size()){
                for(Future<Integer> result : results){
                    System.out.println("Were released  " + result.get());
                }
                checker = false;
            }


        }

        service.shutdown();

    }

}
