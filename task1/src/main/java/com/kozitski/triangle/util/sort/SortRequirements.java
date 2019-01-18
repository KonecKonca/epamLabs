package com.kozitski.triangle.util.sort;

import com.kozitski.triangle.entity.Triangle;

import java.util.Comparator;
import java.util.function.Function;

public class SortRequirements {

    // expressions for sorting
    public static final Function<Triangle, Integer> ID_FUNCTION = (t) -> (int)(t.getTriangleId());
    public static final Function<Triangle, Integer> X_FIRST_POINT_FUNCTION = (t) -> (int)(t.getPoint(0).getCoordinateX());
    public static final Function<Triangle, Integer> Y_FIRST_POINT_FUNCTION = (t) -> (int)(t.getPoint(0).getCoordinateY());
    public static final Function<Triangle, Integer> X_SECOND_POINT_FUNCTION  = (t) -> (int)(t.getPoint(1).getCoordinateX());
    public static final Function<Triangle, Integer> Y_SECOND_POINT_FUNCTION  = (t) -> (int)(t.getPoint(1).getCoordinateY());
    public static final Function<Triangle, Integer> X_THIRD_POINT_FUNCTION  = (t) -> (int)(t.getPoint(2).getCoordinateX());
    public static final Function<Triangle, Integer> Y_THIRD_POINT_FUNCTION  = (t) -> (int)(t.getPoint(2).getCoordinateY());

}

