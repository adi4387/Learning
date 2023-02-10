package com.adruy;

import java.util.Arrays;

import com.adruy.ds.SumFinder;

public class App {

    public static void main(String[] args) {
        var sf = new SumFinder();
        System.out.println(Arrays.toString(sf.twoSum(new int[]{0, 4, 6, 7, 8, 5}, 9)));
    }
}
