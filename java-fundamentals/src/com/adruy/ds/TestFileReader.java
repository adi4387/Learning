package com.adruy.ds;

import java.util.List;

public class TestFileReader {

    public static void main(String[] args) {
        var logs = List.of("192.168.1.1 : GET", "192.168.1.2 : POST", "192.168.1.1 : GET");

        System.out.println(findIpAddressWithMaxRequest(logs));
    }

    public static String findIpAddressWithMaxRequest(List<String> logs) {
        return "";
    }
}
