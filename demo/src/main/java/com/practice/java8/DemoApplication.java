package com.practice.java8;

import java.util.*;
import java.util.stream.Collectors;

public class DemoApplication {
    public static void main(String[] args) {
        DemoApplication dm = new DemoApplication();
        dm.test1();
    }

    public void test1() {
        List<TempStudent> studentList = new ArrayList<TempStudent>();
        TempStudent s1 = new TempStudent("mano", 28, new Address("98789"), Arrays.asList(new MobileNumber("456"), new MobileNumber("98789")));
        TempStudent s2 = new TempStudent("anil", 30, new Address("36363"), Arrays.asList(new MobileNumber("123"), new MobileNumber("231")));
        TempStudent s3 = new TempStudent("hani", 1, new Address("969874"), Arrays.asList(new MobileNumber("4564"), new MobileNumber("98789")));
        TempStudent s4 = new TempStudent("king", 35, new Address("56985"), Arrays.asList(new MobileNumber("252"), new MobileNumber("698")));
        TempStudent s5 = new TempStudent("princess", 14, new Address("52478"), Arrays.asList(new MobileNumber("547"), new MobileNumber("8597")));
        studentList = Arrays.asList(s1, s2, s3, s4, s5);
        List<Student> stuList = new ArrayList<Student>();

        //Get student with exact match name
        Optional<TempStudent> ts = studentList.stream().filter(s -> s.getName().equals("mano")).findFirst();
        System.out.println(ts.isPresent() ? ts.get() : "no name");

        //Get student with matching address
        Optional<TempStudent> ts2 = studentList.stream().filter(s -> s.getAddress().getZipcode().equals("36363")).findFirst();
        System.out.println(ts2.isPresent() ? ts2.get().getAddress().getZipcode() : "no address");

        //Get all student having mobile numbers
        List<TempStudent> ts3 = studentList.stream().filter(s -> s.getMobileNumbers().stream().anyMatch(x -> Objects.equals(x.getNumber(), "98789"))).collect(Collectors.toList());
        ts3.stream().forEach(s -> System.out.println(s.getName()));

        //Get all student having 2 mobile number
        List<TempStudent> ts4 = studentList.stream().filter(m -> m.getMobileNumbers().stream().anyMatch(x -> Objects.equals(x.getNumber(), "698") || Objects.equals(x.getNumber(), "231"))).collect(Collectors.toList());
        ts4.forEach(s -> System.out.println(s.getName()));

        //Create a List<T> from the List<S>
        stuList = studentList.stream().map(s -> new Student(s.getName(), s.getAge(), s.getAddress(), s.getMobileNumbers())).collect(Collectors.toList());
        stuList.forEach(s -> System.out.println(s.name));

        //Convert List<T> to List<String> of  name
        List<String> strList = studentList.stream().map(m -> m.getName()).collect(Collectors.toList());
        strList.forEach(s -> System.out.println(s));

        //Convert List<students> to String
        studentList.stream().forEach(System.out::println);

        //Sort List<String>
        Collections.sort(strList);
        strList.forEach(s -> System.out.println(s));
    }
}

