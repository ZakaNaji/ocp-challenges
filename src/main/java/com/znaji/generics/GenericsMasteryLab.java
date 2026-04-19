package com.znaji.generics;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * GenericsMasteryLab
 *
 * Rules for this lab:
 * 1. Do NOT jump directly to Google or docs.
 * 2. For each TODO:
 *    - first predict what should compile and what should not
 *    - then implement
 *    - then test in main()
 * 3. When a TODO asks for an explanation, write it as a comment above your code.
 * 4. Do not skip the "why" questions.
 *
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
        var x =  first("Hello", 10); // works i guess infered type is Object or a common interface btw Integer and String like Constable or Serializable
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
        System.out.println("Stack after pop:" + dogStack );
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