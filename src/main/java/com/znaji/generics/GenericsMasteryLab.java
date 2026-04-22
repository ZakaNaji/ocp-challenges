package com.znaji.generics;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * GenericsMasteryLab
 * <p>
 * Rules for this lab:
 * 1. Do NOT jump directly to Google or docs.
 * 2. For each TODO:
 * - first predict what should compile and what should not
 * - then implement
 * - then test in main()
 * 3. When a TODO asks for an explanation, write it as a comment above your code.
 * 4. Do not skip the "why" questions.
 * <p>
 * Goal:
 * By the end of this file, you should deeply understand:
 * - why generics exist
 * - invariance
 * - wildcard behavior
 * - extends vs super
 * - type parameter bounds
 * - generic API design
 * - how to read nested generic signatures
 */
public class GenericsMasteryLab {

    public static void main(String[] args) {
        System.out.println("=== Generics Mastery Lab ===");

        // TODO MAIN-1:
        // Create and test your solutions progressively here.
        // For every implemented exercise, add a tiny demo call and print the result.

        // Suggested workflow:
        // - Start with Section 1
        // - Only move on when your tests convince you the section is clear
        // - Keep adding examples here

        // TODO S1-1:
        //todoS1_1();

        // TODO S1-2:
        // LegacyBox demonstrates the pain of losing type information.
        // When we put a String in LegacyBox, it only knows it's an Object.
        // This forces us to cast it back to String when we get it out.
        // If we accidentally put an Integer in another LegacyBox and try to cast it to String, we get a ClassCastException at runtime.

        // TODO S1-3:
        //todoS1_3();

        // TODO S2-2:
        //todoS2_2();

        //Todo S2-3:
        //todoS2_3();

        // TODO S2-4:
        //todoS2_4();

        // TODO S3-1:
        //todoS3_1();

        // TODO S3-2:
        //todoS3_2();

        // TODO S3-3:
        //todoS3_3();

        //TODO S4-1:
        //todoS4_1();

        // TODO S4-2:
        //todoS4_2();

        // TODO S4-3:
        //todoS4_3();

        // TODO S4-4:
        //todoS4_4();

        // TODO S4-5:
        //todoS4_5();

        // TODO S5-1:
        //todoS5_1();

        // TODO S5-2:
        //todoS5_2();

        // TODO S5-3:
        //todoS5_3();

        // TODO S5-4:
        //todoS5_4();

        // TODO S6-1:
        //todoS6_1();

        // TODO S7-1:
        //todoS7_1();

        // TODO S8-2:
        //todoS8_2();

        // Todo S9-1:
        //todoS9_1();

        // Todo S9-2:
        //todoS9_2();

        // Todo S10-1:
        //todoS10_1();

        // Todo S10-2:
        //todoS10_2();

        // Todo S10-3:
        //todoS10_3();

        //Todo S10-4:
        //todoS10_4();

        // Todo S11-1:
        //todoS11_1();

        // Todo S11-2:
        //todoS11_2();

        // Todo S11-3:
        //todoS11_3();

        // Todo S11-4:
        //todoS11_4();
    }

    private static void todoS11_4() {
        List<String> strings = List.of("apple", "banana", "avocado", "grape", "apple");
        Map<String, Integer> freqs = frequencies(strings);
        System.out.println("Frequencies: " + freqs);

        List<Dog> dogs = List.of(new Dog("Rex"), new Dog("Max"), new Dog("Rex"));
        // Note: for this to work, we did override equals and hashCode in Dog to consider two Dogs equal if they have the same name.
        Map<Dog, Integer> dogFreqs = frequencies(dogs);
        System.out.println("Dog Frequencies: " + dogFreqs);
    }

    // Todo S11-4:
    static <T> Map<T, Integer> frequencies(List<T> input) {
        return input.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        e -> 1,
                        Integer::sum
                ));
    }

    private static void todoS11_3() {
        List<String> strings = List.of("apple", "banana", "avocado", "grape");
        Optional<String> firstWithA = findFirst(strings, s -> s.startsWith("a"));
        System.out.println("First string starting with 'a': " + firstWithA.orElse("None found"));

        List<Dog> dogs = List.of(new Dog("Rex"), new Dog("Max"), new Dog("Buddy"));
        Optional<Dog> firstDogWithNameStartingWithM = findFirst(dogs, d -> d.name().startsWith("M"));
        System.out.println("First dog with name starting with 'M': " + firstDogWithNameStartingWithM.map(Dog::name).orElse("None found"));
    }

    // Todo S11-3:
    static <T> Optional<T> findFirst(List<T> input, Predicate<T> predicate) {
        return input.stream()
                .filter(predicate)
                .findFirst();
    }

    private static void todoS11_2() {
        List<String> strings = List.of("apple", "banana", "avocado", "grape");
        List<Integer> lengths = transform(strings, String::length);
        System.out.println("Lengths: " + lengths);

        List<Dog> dogs = List.of(new Dog("Rex"), new Dog("Max"));
        List<String> dogNames = transform(dogs, Dog::name);
        System.out.println("Dog names: " + dogNames);
    }

    // Todo S11-2:
    static <T, R> List<R> transform(List<T> input, Function<T, R> mapper) {
        return input.stream()
                .map(mapper)
                .toList();
    }

    private static void todoS11_1() {
        List<String> strings = List.of("apple", "banana", "avocado", "grape");
        List<String> filtered = filter(strings, s -> s.startsWith("a"));
        System.out.println("Filtered strings: " + filtered);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = filter(numbers, n -> n % 2 == 0);
        System.out.println("Filtered even numbers: " + evenNumbers);

        List<Dog> dogs = List.of(new Dog("Rex"), new Dog("Max"), new Dog("Buddy"));
        List<Dog> dogsWithNameStartingWithM = filter(dogs, d -> d.name().startsWith("M"));
        System.out.println("Filtered dogs: " + dogsWithNameStartingWithM);
    }

    // Todo S11-1:
    static <T> List<T> filter(List<T> input, Predicate<T> predicate) {
        return input.stream()
                .filter(predicate)
                .toList();
    }

    // Todo S10-4:
    static List<String> flattenKeys(List<Map<String, Integer>> maps) {
        return maps.stream()
                .flatMap(map -> map.keySet().stream())
                .toList();
    }

    private static void todoS10_4() {
        List<Map<String, Integer>> maps = List.of(
                Map.of("A", 1, "B", 2),
                Map.of("C", 3, "D", 4),
                Map.of("E", 5)
        );

        List<String> keys = flattenKeys(maps);
        System.out.println("Flattened keys: " + keys);
    }


    //Todo S10-3:
    static int totalNested(Map<String, List<Integer>> data) {
        return data.values().stream()
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void todoS10_3() {
        Map<String, List<Integer>> data = Map.of(
                "A", List.of(1, 2, 3),
                "B", List.of(4, 5),
                "C", List.of(6, 7, 8, 9)
        );
        int total = totalNested(data);
        System.out.println("Total sum of nested integers: " + total);
    }



    private static void todoS10_2() {
        List<Map<String, Integer>>  scoresList = List.of(
                Map.of("Math", 90, "Science", 88, "Literature", 95),
                Map.of("Math", 85, "Science", 91, "Literature", 89),
                Map.of("Math", 92, "Science", 79, "Literature", 94)
        );

        scoresList.forEach(map -> {
            int mathScore = map.get("Math");
            int scienceScore = map.get("Science");
            int literatureScore = map.get("Literature");
            System.out.println("Math: " + mathScore + ", Science: " + scienceScore + ", Literature: " + literatureScore);
        });
    }

    private static void todoS10_1() {
        Map<String, List<Integer>> scoresByCategory = new LinkedHashMap<>(); // just to have a predictable order for printing
        scoresByCategory.put("Math", List.of(90, 85, 92));
        scoresByCategory.put("Science", List.of(88, 91, 79));
        scoresByCategory.put("Literature", List.of(95, 89, 94));

        //print sum by category:
        scoresByCategory.forEach((k, v) -> {
            int sum = v.stream().mapToInt(Integer::intValue).sum();
            System.out.println(k + " total score: " + sum);
        });
    }


    // Todo S9-4:
    static <T> T firstOf(List<? extends T> list) {
        // null check and all that is omitted for brevity
        //list.add(new Object()); this not allowed because we don't know the exact type of the list, it could be List<Dog>, List<Cat>, etc. which would not accept Object
        return list.get(0); // This is fine, we know it's at least a T
    }

    // TODO S9-2:
    static <T> void addAll(List<? super T> destination, List<? extends T> source) {
        destination.addAll(source);
    }

    private static void todoS9_2() {
        List<Animal> animals = new ArrayList<>();
        List<Dog> dogs = List.of(new Dog("Rex"), new Dog("Max"));
        addAll(animals, dogs);
        System.out.println("Animals after addAll: " + animals);

        List<Object> objects = new ArrayList<>();
        addAll(objects, dogs);
        System.out.println("Objects after addAll: " + objects);

        List<Vehicle> vehicles = List.of(new Vehicle("Car"), new Vehicle("Bike"));
        //addAll(animals, vehicles); // will not compile.

        List<Poodle> poodles = List.of(new Poodle("Fluffy"), new Poodle("Fifi"));
        addAll(animals, poodles); // This is fine because List<? extends Animal> can accept List<Poodle>
        addAll(dogs, poodles); // This is also fine because List<? extends Dog> can accept List<Poodle>
    }


    //Todo S9-1:
    static <T> void copy(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    private static void todoS9_1() {
        List<Dog> dogs = List.of(new Dog("Rex"), new Dog("Max"));
        List<Animal> animals = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        copy(dogs, animals);
        copy(dogs, objects);

        List<Integer> integers = List.of(1, 2, 3);
        List<Number> numbers = new ArrayList<>();
        copyIntegers(integers, numbers);

        System.out.println("Animals after copy: " + animals);
        System.out.println("Objects after copy: " + objects);
        System.out.println("Numbers after copy: " + numbers);

        //wrong combination that should not compile:
        List<Vehicle> vehicles = List.of(new Vehicle("Car"), new Vehicle("Bike"));
        //copy(vehicles, animals); // This should NOT compile because List<Vehicle> is not a subtype of List<? extends Animal>

        copy(vehicles, objects); // This is fine because List<Object> can accept any type, including Vehicle
    }


    private static void todoS8_2() {
        List<Animal> animalList = new ArrayList<>();
        addThreeDogs(animalList); // This is fine because List<Animal> can accept Dogs

        List<Dog> dogList = new ArrayList<>();
        addThreeDogs(dogList); // This is also fine because List<Dog> can accept Dogs

        List<Object> objectList = new ArrayList<>();
        addThreeDogs(objectList);// this is fine because List<Object> can accept Dogs
    }

    //Todo S8-4:
    static void copyIntegers(List<Integer> source, List<? super Integer> destination) {
        destination.addAll(source); // This is fine because we know destination can accept Integers
    }

    // Todo S8-1:
    static void addThreeDogs(List<? super Dog> dogs) {
        dogs.add(new Dog("Rex")); // Todo 8-3: This is fine because we know the list can accept Dogs
        dogs.add(new Dog("Max"));
        dogs.add(new Poodle("Fluffy")); // This is fine because Poodle is a subtype of Dog

        // Todo S8-3: reading as a Dog is not allowed because we don't know the exact type of the list. It could be List<Animal> or List<Object>, which would not guarantee that the elements are Dogs.
        //Dog firstDog = dogs.get(0); // Not allowed, we don't know if it's a Dog, it could be an Animal or Object
        //Object firstObject = dogs.get(0); // This is fine, we can read as Object since all types are Objects
    }

    // TODO S7-4:
    static Animal firstAnimal(List<? extends Animal> animals) {
        return animals.get(0); // This is fine, we know it's at least an Animal
    }

    // TODO S7-3:

    static double sum(List<? extends Number> numbers) {
        // we can read as Number
        return numbers.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    // Todo S7-1:
    static void printAnimals(List<? extends Animal> animals) {
        animals.forEach(System.out::println);

        //Todo S7-2:
        // We can read as Animal:
        //Animal firstAnimal = animals.get(0); // This is fine, we know it's at least an Animal
        // We can also read as Object:
        //Object firstObject = animals.get(0); // This is also fine, since all Animals are Objects

        // However, we cannot add a Dog or Cat because we don't know the exact type of the list:
        // animals.add(new Dog("Rex")); // Not allowed, we don't know if it's a List<Dog>, List<Cat>, or List<Animal>
    }

    private static void todoS7_1() {
        List<Animal> animals = List.of(new Dog("Rex"), new Cat("Whiskers"));
        printAnimals(animals);
        List<Dog> dogs = List.of(new Dog("Max"), new Dog("Buddy"));
        printAnimals(dogs);
        List<Cat> cats = List.of(new Cat("Mittens"), new Cat("Shadow"));
        printAnimals(cats);

        List<Vehicle> vehicles = List.of(new Vehicle("Car"), new Vehicle("Bike"));
        // The following line does NOT compile because List<Vehicle> is not a subtype of List<? extends Animal>:
        //printAnimals(vehicles);
    }


    //Todo S6-1:
    public static void printUnknownList(List<?> list) {
        // Todo S6-2:
        // We cannot add any specific type to List<?> because we don't know what type it holds. It could be a List<String>, List<Integer>, or any other type. Adding an element would violate type safety since the list might not accept that type.
        // list.add("hello"); // Not allowed
        // list.add(123); // Not allowed

        //Todo S6-3:
        // We can safely read elements from List<?> as Object because all types in Java inherit from Object. However, we cannot assume any more specific type than Object since we don't know the actual type of the list's elements.
        Object element = list.get(0); // This is fine, but we only know it's an Object
        String strElement = (String) element; // This would require a cast and could fail at runtime if the actual type is not String
        list.forEach(System.out::println);
    }

    private static void todoS6_1() {
        List<String> stringList = List.of("Hello", "World");
        List<Integer> intList = List.of(1, 2, 3);
        List<Dog> dogList = List.of(new Dog("Rex"), new Dog("Max"));
        System.out.println("Printing String list:");
        printUnknownList(stringList);
        System.out.println("Printing Integer list:");
        printUnknownList(intList);
        System.out.println("Printing Dog list:");
        printUnknownList(dogList);
    }

    //Todo S5-4:
    public static <T extends Comparable<T>> T maxOfTwo(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    private static void todoS5_4() {
        var maxInt = maxOfTwo(10, 20);
        System.out.println("Max of 10 and 20: " + maxInt);

        var maxString = maxOfTwo("apple", "banana");
        System.out.println("Max of 'apple' and 'banana': " + maxString);

        NumericBox<Integer> intBox1 = new NumericBox<>(10);

        NumericBox<Integer> intBox2 = new NumericBox<>(20);
        var maxBox = maxOfTwo(intBox1, intBox2);
        System.out.println("Max of " + intBox1 + " and " + intBox2 + " is: " + maxBox);
    }


    // TODO S5-3:
    public static <T extends Number> double sumNumbers(List<T> numbers) {
        return numbers.stream().
                mapToDouble(Number::doubleValue)
                .sum();
    }

    private static void todoS5_3() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        double sum = sumNumbers(numbers);
        System.out.println("Sum of numbers: " + sum);

        List<Double> doubleNumbers = List.of(1.5, 2.5, 3.5);
        double doubleSum = sumNumbers(doubleNumbers);
        System.out.println("Sum of double numbers: " + doubleSum);
    }


    // TODO S5-2:
    public static <T extends Number> double asDouble(T value) {
        return value.doubleValue() * 2;
    }

    private static void todoS5_2() {
        var result = asDouble(10); // Inferred type T is Integer, which extends Number
        System.out.println("Result: " + result);

        // The following line does NOT compile because String does not extend Number:
        //var badResult = asDouble("Hello"); // This would fail because String does not extend Number
    }


    private static void todoS5_1() {
        NumericBox<Integer> intBox = new NumericBox<>(42);
        System.out.println("Integer in NumericBox: " + intBox.get());
        System.out.println("Integer as double: " + intBox.asDouble());

        NumericBox<Double> doubleBox = new NumericBox<>(3.14);
        System.out.println("Double in NumericBox: " + doubleBox.get());
        System.out.println("Double as double: " + doubleBox.asDouble());

        // The following line does NOT compile because String is not a subtype of Number:
        //NumericBox<String> stringBox = new NumericBox<>("Hello"); // This would fail because String does not extend Number
    }

    // TODO S4-5:
    public static <T, R> List<R> map(List<T> source, Function<T, R> mapper) {
        //assert source != null : "Source list cannot be null";
        if (source == null) {
            throw new IllegalArgumentException("Source list cannot be null");
        }
        return source.stream()
                .map(mapper)
                .toList();
    }

    private static void todoS4_5() {
        List<String> strings = List.of("Hello", "World", "Generics");
        List<Integer> lengths = map(strings, String::length);
        System.out.println("Lengths: " + lengths);

        List<Dog> dogs = List.of(new Dog("Rex"), new Dog("Max"));
        List<String> dogNames = map(dogs, Dog::name);
        System.out.println("Dog names: " + dogNames);

        List<Integer> numbers = List.of(1, 2, 3);
        List<String> numberLabels = map(numbers, n -> "Number " + n);
        System.out.println("Number labels: " + numberLabels);
    }

    // TODO S4-4:
    private static <T> List<T> repeat(T value, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(value);
        }
        return result;
    }

    private static void todoS4_4() {
        List<String> repeated = repeat("A", 5);
        System.out.println("Repeated strings: " + repeated);

        List<Integer> repeatedInts = repeat(42, 3);
        System.out.println("Repeated integers: " + repeatedInts);
    }


    private static void todoS4_3() {
        List<String> stringList = new ArrayList<>(List.of("A", "B", "C"));
        System.out.println("Before swap: " + stringList);
        swap(stringList, 0, 2);
        System.out.println("After swap: " + stringList);

        List<Integer> intList = new ArrayList<>(List.of(1, 2, 3, 4));
        System.out.println("Before swap: " + intList);
        swap(intList, 1, 3);
        System.out.println("After swap: " + intList);
    }

    // TODO S4-3:
    private static <T> void swap(List<T> list, int i, int j) {
        if (i < 0 || i >= list.size() || j < 0 || j >= list.size()) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        if (i == j) {
            return; // No need to swap if indices are the same
        }
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private static void todoS4_2() {
        List<String> stringList = List.of("A", "B", "C");
        String lastString = last(stringList);
        System.out.println("Last string: " + lastString);
    }

    // TODO S4-2:
    private static <T> T last(List<T> list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return list.get(list.size() - 1);
    }

    private static void todoS4_1() {
        String s = first("Hello", "World");
        Integer i = first(10, 20);
        // The following line does NOT compile because the types are incompatible:
        var x = first("Hello", 10); // works i guess infered type is Object or a common interface btw Integer and String like Constable or Serializable
        System.out.println(x);

        String s1 = (String) first("Hello", 10); // need to explicitly cast to String
    }

    //TODO S4-1:
    private static <T> T first(T a, T b) {
        return a;
    }

    private static void todoS3_3() {
        // Crate<Dog> is not Crate<Animal> because of invariance.
        Crate<Dog> dogCrate = new Crate<>();
        Crate<Animal> animalCrate = new Crate<>();
        // The following line does NOT compile because of invariance:
        // animalCrate = dogCrate; // This would allow putting any Animal into a Crate<Dog>, which would break type safety when we try to read from it as a Dog
    }

    private static void todoS3_2() {
        Box<String> stringBox = new Box<>();
        Box<Object> objectBox = new Box<>();
        // The following lines do NOT compile because of invariance:
        // Box<Object> objBox = stringBox; // This would allow putting any Object into a Box<String>
        // stringBox = objectBox; // stringBox.get() would return Object, not String, which would break type safety
    }

    private static void todoS3_1() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Rex"));
        // The following line does NOT compile because List<Dog> is not a subtype of List<Animal> due to invariance:
        // List<Animal> animals = dogs;
        // If Java allowed this, we could add a Cat to the animals list, which would corrupt the dog list and cause a ClassCastException when we try to read from it.
        // For example:
        // animals.add(new Cat("Whiskers")); // This would add a Cat to the dogs list
    }

    private static void todoS2_4() {
        StackX<String> stringStack = new StackX<>();
        stringStack.push("Hello");
        stringStack.push("World");
        System.out.println("String Stack peek: " + stringStack.peek());
        System.out.println("String Stack pop: " + stringStack.pop());
        StackX<Integer> intStack = new StackX<>();
        intStack.push(1);
        intStack.push(2);
        System.out.println("Integer Stack peek: " + intStack.peek());
        System.out.println("Integer Stack pop: " + intStack.pop());
        StackX<Dog> dogStack = new StackX<>();
        dogStack.push(new Dog("Rex"));
        dogStack.push(new Dog("Max"));
        System.out.println("Dog Stack peek: " + dogStack.peek());
        System.out.println("Dog Stack pop: " + dogStack.pop());
        System.out.println("Stack after pop:" + dogStack);
    }

    private static void todoS2_3() {
        Triple<String, Integer, Dog> triple1 = new Triple<>("Hello", 42, new Dog("Rex"));
        System.out.println(triple1);
        Triple<List<String>, Set<Integer>, Map<String, Dog>> triple2 = new Triple<>(
                List.of("A", "B"),
                Set.of(1, 2, 3),
                Map.of("Rex", new Dog("Rex"), "Max", new Dog("Max"))
        );
        System.out.println(triple2);
    }

    private static void todoS2_2() {
        Holder<String> stringHolder = new Holder<>("Hello");
        System.out.println(stringHolder);
        Holder<Dog> dogHolder = new Holder<>(new Dog("Rex"));
        System.out.println(dogHolder);
        Holder<List<Integer>> listHolder = new Holder<>(List.of(1, 2, 3));
        System.out.println(listHolder);
    }

    private static void todoS1_3() {
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello, Generics!");
        String stringValue = stringBox.get();
        System.out.println("Box with String: " + stringValue);
        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        Integer intValue = intBox.get();
        System.out.println("Box with Integer: " + intValue);
        // The following lines do NOT compile because of type safety:
        //Box rawBox = stringBox; // This would allow unsafe operations
        //rawBox.set(123); // This would put an Integer in a Box<String>
        //Box<Object> objectBox = stringBox; // This is not allowed because Box<String> is not a subtype of Box<Object>
        //objectBox.set(123); // This would put an Integer in a Box<String>
    }

    private static void todoS1_1() {
        LegacyBox legacyBox1 = new LegacyBox();
        legacyBox1.set("Hello, Generics!");
        String value1 = (String) legacyBox1.get();
        System.out.println("LegacyBox with String: " + value1);
        LegacyBox legacyBox2 = new LegacyBox();
        legacyBox2.set(42);
        try {
            String value2 = (String) legacyBox2.get(); // This will cause ClassCastException
            System.out.println("LegacyBox with Integer: " + value2);
        } catch (ClassCastException e) {
            System.out.println("Caught ClassCastException as expected: " + e.getMessage());
        }
    }

    // =========================================================
    // SECTION 0 — DOMAIN MODEL FOR EXERCISES
    // =========================================================

    static class Animal {
        private final String name;

        Animal(String name) {
            this.name = name;
        }

        public String name() {
            return name;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" + name + ")";
        }
    }

    static class Dog extends Animal {
        Dog(String name) {
            super(name);
        }

        public void bark() {
            System.out.println(name() + " says woof");
        }

        @Override
        public int hashCode() {
            return name().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Dog other = (Dog) obj;
            return name().equals(other.name());
        }
    }

    static class Cat extends Animal {
        Cat(String name) {
            super(name);
        }

        public void meow() {
            System.out.println(name() + " says meow");
        }
    }

    static class Poodle extends Dog {
        Poodle(String name) {
            super(name);
        }
    }

    static class Vehicle {
        private final String model;

        Vehicle(String model) {
            this.model = model;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" + model + ")";
        }
    }

    static class Car extends Vehicle {
        Car(String model) {
            super(model);
        }
    }

    static class Truck extends Vehicle {
        Truck(String model) {
            super(model);
        }
    }

    static class Cage<T> {
        private T occupant;

        public void put(T occupant) {
            this.occupant = occupant;
        }

        public T get() {
            return occupant;
        }

        @Override
        public String toString() {
            return "Cage[" + occupant + "]";
        }
    }

    static class Pair<L, R> {
        private final L left;
        private final R right;

        Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L left() {
            return left;
        }

        public R right() {
            return right;
        }

        @Override
        public String toString() {
            return "Pair[" + left + ", " + right + "]";
        }
    }

    static class Box<T> {
        private T value;

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }

    // =========================================================
    // SECTION 1 — WHY GENERICS EXIST
    // =========================================================

    static class LegacyBox {
        private Object value;

        public void set(Object value) {
            this.value = value;
        }

        public Object get() {
            return value;
        }
    }

    // TODO S1-1:
    // In main(), create a LegacyBox and put a String in it.
    // Retrieve it safely.
    // Then create another LegacyBox, put an Integer in it,
    // and intentionally create a ClassCastException scenario.
    // Do not remove the bad code — keep it as a demonstration.

    // TODO S1-2:
    // Above your demo, write a short comment:
    // What exact pain does LegacyBox demonstrate?
    // Mention:
    // - loss of type information
    // - repeated casts
    // - runtime failure

    // TODO S1-3:
    // In main(), create a Box<String> and Box<Integer>.
    // Demonstrate how the compiler now protects you.
    // Add commented-out lines that do NOT compile, and explain why.

    // =========================================================
    // SECTION 2 — GENERIC CLASS BASICS
    // =========================================================

    // TODO S2-1:
    // Create a generic class Holder<T> with:
    // - private field value
    // - constructor
    // - getter
    // - setter
    // - nice toString()
    static class Holder<T> {
        private T value;

        public Holder(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public void set(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Holder[" + value + "]";
        }
    }

    // TODO S2-2:
    // Create examples in main() using:
    // - Holder<String>
    // - Holder<Dog>
    // - Holder<List<Integer>>

    // TODO S2-3:
    // Create a generic class Triple<A, B, C>.
    // Add fields, constructor, getters, toString().
    // Test it with at least 3 different combinations of types.
    static class Triple<A, B, C> {
        private final A first;
        private final B second;
        private final C third;

        Triple(A first, B second, C third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

        public C getThird() {
            return third;
        }

        @Override
        public String toString() {
            return "Triple[" + first + ", " + second + ", " + third + "]";
        }
    }

    // TODO S2-4:
    // Create a generic class StackX<T> backed by ArrayList<T>.
    // Add:
    // - push(T)
    // - pop()
    // - peek()
    // - isEmpty()
    //
    // Then test it with:
    // - String
    // - Integer
    // - Dog

    static class StackX<T> {
        private final List<T> elements = new ArrayList<>();

        public void push(T element) {
            elements.add(element);
        }

        public T pop() {
            if (elements.isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            return elements.removeFirst();
        }

        public T peek() {
            if (elements.isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            return elements.getFirst();
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }

        @Override
        // For debugging purposes, let's add a comprehensive toString() method
        public String toString() {
            return "StackX" + elements;
        }
    }

    // TODO S2-5:
    // Write a comment:
    // Why is one StackX<T> class better than creating
    // StringStack, IntegerStack, DogStack, etc.?
    // Answer:
    // A single StackX<T> class is better because it promotes code reuse and reduces duplication. Instead of writing separate classes for each type, we can use the same generic class with different type parameters. This leads to cleaner, more maintainable code and allows us to easily create stacks for any type without needing to write new classes.

    // =========================================================
    // SECTION 3 — INVARIANCE
    // =========================================================

    // TODO S3-1:
    // In main(), create:
    // - List<Dog>
    // - List<Animal>
    //
    // Add some Dogs to the dog list.
    //
    // Then add commented-out code showing that:
    // List<Animal> animals = dogs;
    // does NOT compile.
    //
    // Write a short explanation:
    // what exact corruption would become possible if Java allowed it?

    // TODO S3-2:
    // Repeat the same idea with:
    // - Box<String>
    // - Box<Object>
    //
    // Show with commented-out code that assignment in either direction is illegal.

    // TODO S3-3:
    // Create your own generic class Crate<T>.
    // Then demonstrate that:
    // Crate<Dog> is not Crate<Animal>
    // even though Dog extends Animal.

    static class Crate<T> {
        private T item;

        public void put(T item) {
            this.item = item;
        }

        public T get() {
            return item;
        }
    }

    // TODO S3-4:
    // Write a helper method in comments only:
    // "What if Java had allowed List<Dog> -> List<Animal>?"
    // Write 4–6 lines of pseudocode showing the disaster.

    /**
     * If Java allowed List<Dog> to be assigned to List<Animal>, we could do the following:
     * List<Dog> dogs = new ArrayList<>();
     * List<Animal> animals = dogs; // This would be allowed
     * animals.add(new Cat()); // Now the dogs list contains a Cat, which is incorrect
     * Dog dog = dogs.get(0); // This would throw a ClassCastException at runtime because the first element is actually a Cat, not a Dog
     *
     */

    // =========================================================
    // SECTION 4 — GENERIC METHODS
    // =========================================================

    // TODO S4-1:
    // Implement:
    // static <T> T first(T a, T b)
    //
    // Return the first argument.
    // Then test it with:
    // - String/String
    // - Integer/Integer
    // - String/Integer
    //
    // For each case, observe inferred type.

    // TODO S4-2:
    // Implement:
    // static <T> T last(List<T> list)
    //
    // Return the last element.
    // Decide how you want to handle empty lists.

    // TODO S4-3:
    // Implement:
    // static <T> void swap(List<T> list, int i, int j)
    //
    // Then test it on:
    // - List<String>
    // - List<Integer>

    // TODO S4-4:
    // Implement:
    // static <T> List<T> repeat(T value, int count)
    //
    // Example idea:
    // repeat("A", 3) -> ["A", "A", "A"]

    // TODO S4-5:
    // Implement:
    // static <T, R> List<R> map(List<T> source, Function<T, R> mapper)
    //
    // Test with:
    // - List<String> -> lengths
    // - List<Dog> -> names
    // - List<Integer> -> String labels

    // TODO S4-6:
    // Write a short comment:
    // What is the difference between:
    // - generic class
    // - generic method

    // Answer:
    // A generic class is a class that has one or more type parameters, allowing it to operate on different types while maintaining type safety. For example, Box<T> is a generic class where T can be any type. A generic method, on the other hand, is a method that has its own type parameters, which can be used independently of the class's type parameters. This allows the method to be flexible and work with different types even if the class itself is not generic. For example, static <T> T first(T a, T b) is a generic method that can be used in any class, regardless

    // =========================================================
    // SECTION 5 — BOUNDED TYPE PARAMETERS
    // =========================================================

    // TODO S5-1:
    // Create a class NumericBox<T extends Number>.
    // Add:
    // - field value
    // - constructor
    // - getter
    // - method asDouble()
    //
    // Test with:
    // - Integer
    // - Double
    //
    // Add commented-out code with String and explain why it fails.

    static class NumericBox<T extends Number & Comparable<T>> implements Comparable<NumericBox<T>> {
        @Override
        public int compareTo(NumericBox<T> o) {
            return this.value.compareTo(o.value);
        }

        private final T value;

        public NumericBox(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public double asDouble() {
            return value.doubleValue();
        }

        @Override
        public String toString() {
            return "NumericBox{" +
                    "value=" + value +
                    '}';
        }
    }

    // TODO S5-2:
    // Implement:
    // static <T extends Number> double doubleValue(T value)
    //
    // Return value.doubleValue() * 2

    // TODO S5-3:
    // Implement:
    // static <T extends Number> double sumNumbers(List<T> numbers)
    //
    // Sum via doubleValue()

    // TODO S5-4:
    // Implement:
    // static <T extends Comparable<T>> T maxOfTwo(T a, T b)
    //
    // Return the larger value.
    // Test with:
    // - Integer
    // - String
    //
    // Optional:
    // Create your own comparable domain object and test it.

    // TODO S5-5:
    // Create a class or method that uses multiple bounds:
    // <T extends Number & Comparable<T>>
    //
    // Use it in some meaningful way.

    // TODO S5-6:
    // Write a comment:
    // Difference between:
    // - <T extends Number>
    // - ? extends Number

    // Answer:
    // <T extends Number> is a bounded type parameter used in generic class or method definitions. It means that T can be any type that is a subclass of Number, and it allows us to use T as a specific type within the class or method. For example, we can create a class NumericBox<T extends Number> that can hold any type of Number, and we can call methods specific to Number on T.
    // ? extends Number is a wildcard used in generic type declarations. It means that the type can be any subclass of Number, but we don't know exactly which one. This is useful for reading from a collection of unknown specific type, but we cannot add elements to it because we don't know the exact type.

    // =========================================================
    // SECTION 6 — UNBOUNDED WILDCARDS
    // =========================================================

    // TODO S6-1:
    // Implement:
    // static void printUnknownList(List<?> list)
    //
    // Print each element.
    //
    // Test with:
    // - List<String>
    // - List<Integer>
    // - List<Dog>

    // TODO S6-2:
    // Inside printUnknownList(), try commented-out code that adds:
    // - "hello"
    // - 123
    // - new Dog("Rex")
    //
    // Explain why none are allowed.

    // TODO S6-3:
    // Write a comment:
    // What can you safely read from List<?> and as what type?

    // =========================================================
    // SECTION 7 — ? EXTENDS
    // =========================================================

    // TODO S7-1:
    // Implement:
    // static void printAnimals(List<? extends Animal> animals)
    //
    // Print them.
    //
    // Test with:
    // - List<Animal>
    // - List<Dog>
    // - List<Cat>
    //
    // Also try to call it with List<Vehicle> and confirm failure.

    // TODO S7-2:
    // Inside printAnimals(), demonstrate:
    // - reading as Animal is fine
    // - reading as Object is fine
    // - adding Dog/Cat is not allowed
    //
    // Keep bad lines commented out.

    // TODO S7-3:
    // Implement:
    // static double sum(List<? extends Number> numbers)
    //
    // Read numbers and sum via doubleValue()

    // TODO S7-4:
    // Implement:
    // static Animal firstAnimal(List<? extends Animal> animals)
    //
    // Return the first animal.

    // TODO S7-5:
    // Write a short comment:
    // Why is ? extends usually described as “producer”?

    // Answer:
    // ? extends is often described as a "producer" because it allows you to read (produce) items of a certain type from a collection, but you cannot add (consume) items to the collection since you don't know the exact subtype. It is designed for situations where you want to work with a collection that produces items of a certain type, but you don't care about the specific subtype, allowing for greater flexibility in the types of collections you can use.

    // =========================================================
    // SECTION 8 — ? SUPER
    // =========================================================

    // TODO S8-1:
    // Implement:
    // static void addThreeDogs(List<? super Dog> dogs)
    //
    // Add 3 Dog objects.

    // TODO S8-2:
    // Test addThreeDogs() with:
    // - List<Dog>
    // - List<Animal>
    // - List<Object>
    //
    // Observe that all should work.

    // TODO S8-3:
    // Inside addThreeDogs(), demonstrate:
    // - writing Dog is allowed
    // - reading as Object is allowed
    // - reading as Dog is not allowed
    //
    // Keep bad lines commented out.

    // TODO S8-4:
    // Implement:
    // static void copyIntegers(List<Integer> source, List<? super Integer> destination)
    //
    // Copy all elements from source to destination.

    // TODO S8-5:
    // Write a short comment:
    // Why is ? super usually described as “consumer”?

    // Answer:
    // ? super is often described as a "consumer" because it allows you to add (consume) items of a certain type to a collection, but you cannot read (produce) items from the collection as a specific type since you don't know the exact supertype. It is designed for situations where you want to work with a collection that consumes items of a certain type, but you don't care about the specific supertype, allowing for greater flexibility in the types of collections you can use.

    // =========================================================
    // SECTION 9 — PECS IN REAL CODE
    // =========================================================

    // TODO S9-1:
    // Implement a generic copy method:
    // static <T> void copy(List<? extends T> source, List<? super T> destination)
    //
    // This is one of the most important generics exercises in the whole file.
    // Test it with combinations like:
    // - List<Dog> -> List<Animal>
    // - List<Dog> -> List<Object>
    // - List<Integer> -> List<Number>
    //
    // Also test combinations that should NOT compile, as commented-out code.

    // TODO S9-2:
    // Write a comment explaining the method signature:
    // Why extends on source?
    // Why super on destination?

    // Answer:
    // extends on source, first to be able to raad items as T, and second to allow any subtype of T to be used as source, providing flexibility in the types of sources we can copy from. super on destination allows us to write items as T, and also allows any supertype of T to be used as destination, providing flexibility in the types of destinations we can copy to. This combination ensures type safety while maximizing flexibility in the types of collections we can work with.
    // TODO S9-3:
    // Implement:
    // static <T> void addAll(List<? super T> destination, List<? extends T> source)
    //
    // Same idea, but parameter order reversed.
    //
    // Compare readability with the previous method.

    // TODO S9-4:
    // Implement:
    // static <T> T firstOf(List<? extends T> list)
    //
    // Explain why the return type is T and not wildcard.

    // =========================================================
    // SECTION 10 — NESTED GENERICS
    // =========================================================

    // TODO S10-1:
    // Create and populate:
    // Map<String, List<Integer>> scoresByCategory
    //
    // Example categories:
    // - "math"
    // - "science"
    // - "history"
    //
    // Then write code to print:
    // category -> total score

    // TODO S10-2:
    // Create:
    // List<Map<String, Integer>>
    //
    // Populate it with 2 or 3 maps.
    // Write code that prints all entries.

    // TODO S10-3:
    // Implement:
    // static int totalNested(Map<String, List<Integer>> data)
    //
    // Sum all integers across all lists.

    // TODO S10-4:
    // Implement:
    // static List<String> flattenKeys(List<Map<String, Integer>> maps)
    //
    // Return all keys in a single list.

    // TODO S10-5:
    // Write comments explaining in English what each means:
    // - Map<String, List<Integer>>
    // - List<Map<String, Integer>>
    // - Map<String, Map<String, List<Double>>>
    //
    // This is reading practice, not coding difficulty.

    // Answers:
    // Map<String, List<Integer>>: A map where each key is a String and the value is a List of Integers. For example, it could represent categories (String) and their associated scores (List<Integer>).
    // List<Map<String, Integer>>: A list where each element is a Map that maps Strings to Integers. For example, it could represent a list of score sheets, where each score sheet is a map of category (String) to score (Integer).
    // Map<String, Map<String, List<Double>>>: A map where each key is a String and the value is another Map. The inner Map has String keys and List<Double> values. For example, it could represent a map of students (String) to their subjects (String) and the grades they received in those subjects (List<Double>).

    // =========================================================
    // SECTION 11 — GENERIC API DESIGN
    // =========================================================

    // TODO S11-1:
    // Implement:
    // static <T> List<T> filter(List<T> input, Predicate<T> predicate)
    //
    // Test with:
    // - String
    // - Integer
    // - Dog

    // TODO S11-2:
    // Implement:
    // static <T, R> List<R> transform(List<T> input, Function<T, R> mapper)
    //
    // Yes, similar to earlier map().
    // This repetition is intentional.
    // The goal is to make generic API writing feel natural.

    // TODO S11-3:
    // Implement:
    // static <T> Optional<T> findFirst(List<T> input, Predicate<T> predicate)
    //
    // Return first match.

    // TODO S11-4:
    // Implement:
    // static <T> Map<T, Integer> frequencies(List<T> input)
    //
    // Count how many times each element appears.

    // TODO S11-5:
    // Implement:
    // static <K, V> void printMap(Map<K, V> map)
    //
    // Print all entries.

    // TODO S11-6:
    // Write a short comment:
    // When should you use:
    // - exact type parameter T
    // - ? extends T
    // - ? super T
    //
    // Keep it simple and practical.

    // =========================================================
    // SECTION 12 — TYPE INFERENCE PRACTICE
    // =========================================================

    // TODO S12-1:
    // Create a bunch of examples with var and explicit types using your generic methods.
    // For each, predict the inferred type before hovering in IDE.
    //
    // Suggested examples:
    // - var x = first("a", "b");
    // - var y = first("a", 10);
    // - var z = repeat(5, 3);
    // - var dogs = List.of(new Dog("Rex"), new Dog("Max"));

    // TODO S12-2:
    // Write 10 examples as comments:
    // "What is T here?"
    // Then verify each in code.

    // TODO S12-3:
    // Create examples where inference becomes tricky:
    // - mixing Integer and Double
    // - mixing Dog and Animal
    // - nested collection arguments
    //
    // Observe what common type Java chooses.

    // =========================================================
    // SECTION 13 — READING JDK-STYLE SIGNATURES
    // =========================================================

    // TODO S13-1:
    // Write a comment explaining this signature:
    // static <T> void copy(List<? super T> dest, List<? extends T> src)
    //
    // Explain it in plain English.

    // TODO S13-2:
    // Write a comment explaining this signature:
    // static <T> void sort(List<T> list, Comparator<? super T> c)
    //
    // Why does Comparator use super?

    // TODO S13-3:
    // Write a comment explaining this signature:
    // static <T, R> List<R> map(List<T> input, Function<? super T, ? extends R> mapper)
    //
    // Why super on T side and extends on R side?
    //
    // Then optionally implement this exact version.

    // TODO S13-4:
    // Create 5 of your own scary-looking generic signatures.
    // For each one:
    // - write it
    // - then translate it into plain English

    // =========================================================
    // SECTION 14 — DESIGN EXERCISES
    // =========================================================

    // TODO S14-1:
    // Design a generic Repository<T, ID> interface with methods like:
    // - save
    // - findById
    // - findAll
    // - deleteById
    //
    // Then create one fake in-memory implementation for a sample domain object.

    // TODO S14-2:
    // Design a generic Result<T> type representing:
    // - success(value)
    // - failure(errorMessage)
    //
    // Add methods to inspect state.

    // TODO S14-3:
    // Design a generic Mapper<S, T> interface.
    // Then implement:
    // - String -> Integer
    // - Dog -> String
    // - Integer -> String

    // TODO S14-4:
    // Design a generic Range<T extends Comparable<T>> class.
    // Add:
    // - start
    // - end
    // - contains(T value)
    //
    // Test with:
    // - Integer
    // - String
    //
    // Optional:
    // test with LocalDate if you want

    // =========================================================
    // SECTION 15 — ERROR PREDICTION DRILLS
    // =========================================================

    // TODO S15-1:
    // Create a separate comment block with at least 20 tiny code snippets.
    // For each snippet:
    // - predict COMPILES or DOES NOT COMPILE
    // - then verify
    //
    // Include cases about:
    // - invariance
    // - extends
    // - super
    // - bounds
    // - nested generics
    // - type inference

    // TODO S15-2:
    // Include at least 5 snippets that look like they should compile,
    // but do not.
    //
    // Include at least 5 snippets that look suspicious,
    // but actually compile.

    // TODO S15-3:
    // For every wrong prediction, write:
    // "Why my brain expected X, but Java does Y."

    // =========================================================
    // SECTION 16 — FINAL BOSS EXERCISES
    // =========================================================

    // TODO S16-1:
    // Implement a generic method:
    // static <T, R> List<R> flatMap(
    //     List<T> source,
    //     Function<? super T, ? extends List<? extends R>> mapper)
    //
    // Build it manually with loops.
    //
    // This is intentionally hard.
    // Do not skip the signature analysis.

    // TODO S16-2:
    // Implement:
    // static <T extends Comparable<? super T>> T max(List<? extends T> list)
    //
    // Do not use Collections.max().
    //
    // Test with:
    // - Integer
    // - String
    // - your own comparable type if possible

    // TODO S16-3:
    // Implement:
    // static <T> void reverse(List<T> list)
    //
    // Then create a second version with a helper method if needed.
    // Reflect on whether generics made this easier or not.

    // TODO S16-4:
    // Build a mini generic utility class MyCollections with 5–8 methods.
    // Include a mix of:
    // - exact generic methods
    // - bounded type parameters
    // - extends wildcard
    // - super wildcard
    //
    // Make it feel like your own tiny JDK utility class.

    // TODO S16-5:
    // Write a final summary comment in your own words:
    //
    // 1. Why generics exist
    // 2. What invariance means
    // 3. Difference between:
    //    - T
    //    - ?
    //    - ? extends T
    //    - ? super T
    // 4. Difference between:
    //    - <T extends X>
    //    - ? extends X
    // 5. What PECS means
    // 6. The 3 most common mistakes you used to make
    // 7. The 3 mental models that helped the most

    // =========================================================
    // SECTION 17 — OPTIONAL EXTREME MODE
    // =========================================================

    // TODO S17-1:
    // Research and experiment with:
    // - raw types
    // - generic varargs warnings
    // - @SafeVarargs
    // - why new T() is illegal
    // - why generic array creation is illegal
    //
    // Then summarize each in your own words.

    // TODO S17-2:
    // Create a "Generics Mistakes Museum":
    // a comment block containing 10 mistakes and their corrected versions.

    // TODO S17-3:
    // Build a generic event bus or message dispatcher with:
    // - event type
    // - handler type
    // - registration
    // - dispatch
    //
    // This is more design-oriented and great for real mastery.

}