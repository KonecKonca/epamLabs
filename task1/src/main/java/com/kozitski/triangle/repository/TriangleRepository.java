package com.kozitski.triangle.repository;

import com.kozitski.triangle.entity.Triangle;
import com.kozitski.triangle.util.annotations.NotRealisable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

public class TriangleRepository implements Repository<Triangle> {
    private static TriangleRepository repository;
    private List<Triangle> triangleList;
    private static final Logger LOGGER = LogManager.getLogger(TriangleRepository.class);

    private TriangleRepository(){
        triangleList = new ArrayList<>();
    }
    public static TriangleRepository getTriangleRepository(){
        if(repository != null){
            return repository;
        }
        repository = new TriangleRepository();
        LOGGER.info("is created");
        return repository;
    }

    public void sortTriangles(Function<Triangle, Integer> comparator){
        if(comparator != null){
            triangleList.sort(Comparator.comparing(comparator));
        }
    }
    public void sortTriangles(Function<Triangle, Integer> comparator, Function<Triangle, Integer> additionalComparator){
        if(comparator != null && additionalComparator != null){
            triangleList.sort(Comparator.comparing(comparator).thenComparing(additionalComparator));
        }
        else {
            LOGGER.error("Was received invalid comparators for compare");
        }
    }
    public void sortTriangles(Function<Triangle, Integer> comparator, Function<Triangle, Integer> additionalComparator1,  Function<Triangle, Integer> additionalComparator2){
        if(comparator != null && additionalComparator1 != null && additionalComparator2 != null){
            triangleList.sort(Comparator.comparing(comparator).thenComparing(additionalComparator1).thenComparing(additionalComparator2));
        }
        else {
            LOGGER.error("Was received invalid comparators for compare");
        }
    }
    @NotRealisable("That method doesn't work now. Interchangeable with overloads methods higher")
    public void sortTriangles(Function<Triangle, Integer> comparator, Function<Triangle, Integer> ... additionalComparators){
        if(comparator != null && additionalComparators != null){
            Comparator<Triangle> compare = Comparator.comparing(comparator);

            for (Function<Triangle, Integer> additionalComparator : additionalComparators) {
                compare.thenComparing(additionalComparator);
            }

            triangleList.sort(compare);
        }
        else {
            LOGGER.error("Was received invalid comparators for compare");
        }
    }

    @Override
    public void add(Triangle triangle) {
        triangleList.add(triangle);
    }
    @Override
    public void remove(Triangle triangle) {
        triangleList.remove(triangle);
    }
    @Override
    public void removeAll()  {
        triangleList = new ArrayList<>();
    }
    @Override
    public Triangle get(int index) {
        return triangleList.get(index);
    }
    public ArrayList<Triangle> getAllTriangles(){
        ArrayList<Triangle> list = new ArrayList<>();
        list.addAll(triangleList);
        return list;
    }
    public int size() {
        return triangleList.size();
    }

    @Override
    public List<Triangle> query(Specification<Triangle> specification) {
        List<Triangle> result = new ArrayList<>();
        for(Triangle triangle : triangleList){
            if(specification.specify(triangle)){
                result.add(triangle);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("TriangleRepository:" + "\n");
        for(Triangle triangle : triangleList){
            stringBuilder.append("\t" + triangle + "\n");
        }
        return stringBuilder.toString();
    }

}
