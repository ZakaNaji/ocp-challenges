package com.znaji.generics;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * GENERICS + COLLECTIONS + LAMBDAS + STREAMS MASTERY LAB
 * <p>
 * Rules:
 * 1. Do NOT solve everything at once.
 * 2. Work challenge by challenge.
 * 3. For every TODO, first write a loop-based version in comments if useful,
 * then implement the stream/lambda/generic version.
 * 4. Prefer clear code over clever code.
 * 5. Do not modify the provided data model unless a TODO explicitly asks for it.
 * <p>
 * Suggested order:
 * - Part 1 -> warm-up
 * - Part 2 -> stream fundamentals
 * - Part 3 -> collectors mastery
 * - Part 4 -> generics and wildcards
 * - Part 5 -> real challenge mode
 */
public class FunctionalCollectionsMasteryLab {

    public static void main(String[] args) {
        Data data = SampleData.create();

        Part1Warmup.run(data);
        Part2StreamPipelines.run(data);
        Part3Collectors.run(data);
        Part4GenericsAndWildcards.run(data);
        Part5ChallengeMode.run(data);
    }

    // =========================================================
    // DOMAIN MODEL
    // =========================================================

    enum Category {
        BACKEND, FRONTEND, DEVOPS, DATABASE, CLOUD, SECURITY
    }

    enum Level {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

    static final class Course {
        private final String id;
        private final String title;
        private final Category category;
        private final Level level;
        private final int durationHours;
        private final double rating;
        private final List<String> tags;

        public Course(String id, String title, Category category, Level level,
                      int durationHours, double rating, List<String> tags) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.level = level;
            this.durationHours = durationHours;
            this.rating = rating;
            this.tags = List.copyOf(tags);
        }

        public String id() {
            return id;
        }

        public String title() {
            return title;
        }

        public Category category() {
            return category;
        }

        public Level level() {
            return level;
        }

        public int durationHours() {
            return durationHours;
        }

        public double rating() {
            return rating;
        }

        public List<String> tags() {
            return tags;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", category=" + category +
                    ", level=" + level +
                    ", durationHours=" + durationHours +
                    ", rating=" + rating +
                    ", tags=" + tags +
                    '}';
        }
    }

    static final class Student {
        private final String id;
        private final String name;
        private final int age;
        private final List<String> enrolledCourseIds;
        private final Set<String> completedCourseIds;
        private final List<Integer> scores;

        public Student(String id, String name, int age,
                       List<String> enrolledCourseIds,
                       Set<String> completedCourseIds,
                       List<Integer> scores) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.enrolledCourseIds = List.copyOf(enrolledCourseIds);
            this.completedCourseIds = Set.copyOf(completedCourseIds);
            this.scores = List.copyOf(scores);
        }

        public String id() {
            return id;
        }

        public String name() {
            return name;
        }

        public int age() {
            return age;
        }

        public List<String> enrolledCourseIds() {
            return enrolledCourseIds;
        }

        public Set<String> completedCourseIds() {
            return completedCourseIds;
        }

        public List<Integer> scores() {
            return scores;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", enrolledCourseIds=" + enrolledCourseIds +
                    ", completedCourseIds=" + completedCourseIds +
                    ", scores=" + scores +
                    '}';
        }
    }

    static final class Instructor {
        private final String id;
        private final String name;
        private final List<Category> specialties;
        private final List<String> teachingCourseIds;

        public Instructor(String id, String name, List<Category> specialties, List<String> teachingCourseIds) {
            this.id = id;
            this.name = name;
            this.specialties = List.copyOf(specialties);
            this.teachingCourseIds = List.copyOf(teachingCourseIds);
        }

        public String id() {
            return id;
        }

        public String name() {
            return name;
        }

        public List<Category> specialties() {
            return specialties;
        }

        public List<String> teachingCourseIds() {
            return teachingCourseIds;
        }

        @Override
        public String toString() {
            return "Instructor{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", specialties=" + specialties +
                    ", teachingCourseIds=" + teachingCourseIds +
                    '}';
        }
    }

    static final class Data {
        private final List<Course> courses;
        private final List<Student> students;
        private final List<Instructor> instructors;

        public Data(List<Course> courses, List<Student> students, List<Instructor> instructors) {
            this.courses = List.copyOf(courses);
            this.students = List.copyOf(students);
            this.instructors = List.copyOf(instructors);
        }

        public List<Course> courses() {
            return courses;
        }

        public List<Student> students() {
            return students;
        }

        public List<Instructor> instructors() {
            return instructors;
        }
    }

    // =========================================================
    // SAMPLE DATA
    // =========================================================

    static final class SampleData {
        static Data create() {
            List<Course> courses = List.of(
                    new Course("C001", "Java Generics Deep Dive", Category.BACKEND, Level.ADVANCED, 18, 4.8, List.of("java", "generics", "collections")),
                    new Course("C002", "Spring Fundamentals", Category.BACKEND, Level.INTERMEDIATE, 22, 4.7, List.of("spring", "backend", "java")),
                    new Course("C003", "Docker Essentials", Category.DEVOPS, Level.BEGINNER, 12, 4.5, List.of("docker", "containers", "devops")),
                    new Course("C004", "SQL Performance Tuning", Category.DATABASE, Level.ADVANCED, 16, 4.9, List.of("sql", "database", "performance")),
                    new Course("C005", "React State Mastery", Category.FRONTEND, Level.INTERMEDIATE, 14, 4.4, List.of("react", "frontend", "state")),
                    new Course("C006", "AWS for Developers", Category.CLOUD, Level.INTERMEDIATE, 20, 4.6, List.of("aws", "cloud", "backend")),
                    new Course("C007", "Secure Coding in Java", Category.SECURITY, Level.ADVANCED, 10, 4.3, List.of("security", "java", "owasp")),
                    new Course("C008", "Linux CLI Bootcamp", Category.DEVOPS, Level.BEGINNER, 9, 4.2, List.of("linux", "cli", "shell")),
                    new Course("C009", "Stream API by Practice", Category.BACKEND, Level.INTERMEDIATE, 11, 4.9, List.of("java", "streams", "lambdas")),
                    new Course("C010", "Kubernetes Basics", Category.CLOUD, Level.BEGINNER, 15, 4.1, List.of("kubernetes", "cloud", "devops"))
            );

            List<Student> students = List.of(
                    new Student("S001", "Alice", 24, List.of("C001", "C002", "C009"), Set.of("C009"), List.of(90, 85, 88)),
                    new Student("S002", "Bob", 29, List.of("C003", "C008", "C010"), Set.of("C003", "C008"), List.of(70, 65, 74)),
                    new Student("S003", "Charlie", 31, List.of("C004", "C007"), Set.of("C004"), List.of(95, 91)),
                    new Student("S004", "Dina", 22, List.of("C005", "C009"), Set.of(), List.of(82, 79, 81)),
                    new Student("S005", "Ethan", 27, List.of("C002", "C006", "C009"), Set.of("C002"), List.of(88, 84, 86)),
                    new Student("S006", "Fatima", 26, List.of("C001", "C004", "C007"), Set.of("C001", "C004"), List.of(99, 96, 98)),
                    new Student("S007", "George", 35, List.of("C003", "C006"), Set.of("C003"), List.of(60, 67, 72)),
                    new Student("S008", "Hana", 23, List.of("C005", "C008", "C010"), Set.of("C008"), List.of(77, 80, 75))
            );

            List<Instructor> instructors = List.of(
                    new Instructor("I001", "Mustapha", List.of(Category.BACKEND, Category.SECURITY), List.of("C001", "C002", "C007", "C009")),
                    new Instructor("I002", "Nora", List.of(Category.DEVOPS, Category.CLOUD), List.of("C003", "C006", "C008", "C010")),
                    new Instructor("I003", "Yassine", List.of(Category.DATABASE), List.of("C004")),
                    new Instructor("I004", "Salma", List.of(Category.FRONTEND), List.of("C005"))
            );

            return new Data(courses, students, instructors);
        }
    }

    // =========================================================
    // PART 1 — WARM-UP
    // =========================================================

    static final class Part1Warmup {
        static void run(Data data) {
            System.out.println("\n=== PART 1: WARM-UP ===");

            // TODO 1:
            // Return a List<String> containing only course titles.
            // Use stream + map + toList.
            List<String> courseTiles = data.courses.stream()
                    .map(Course::title)
                    .toList();

            // TODO 2:
            // Return a List<Course> of only ADVANCED courses.
            List<Course> advancedCourses = data.courses.stream()
                    .filter(course -> course.level() == Level.ADVANCED)
                    .toList();

            // TODO 3:
            // Return a List<String> of student names sorted alphabetically.
            List<String> sortedListOfStudentsNames = data.students.stream()
                    .map(Student::name)
                    .sorted(Comparator.naturalOrder())
                    .toList();

            // TODO 4:
            // Return a List<String> of course titles sorted by rating descending.

            List<String> courseTitleSortedByRating = data.courses.stream()
                    .sorted(Comparator.comparingDouble(Course::rating)
                            .reversed()
                            .thenComparing(Course::title))
                    .map(Course::title)
                    .toList();

            // TODO 5:
            // Count how many courses belong to BACKEND.
            long countBackEndCourses = data.courses.stream()
                    .filter(course -> course.category == Category.BACKEND)
                    .count();

            // TODO 6:
            // Check whether any student has age < 23.

            boolean anyStudentUnder23 = data.students.stream()
                    .anyMatch(student -> student.age() < 23);

            // TODO 7:
            // Check whether all students have at least one enrolled course.

            boolean allStudentAreEnrolled = data.students.stream()
                    .noneMatch(student -> student.enrolledCourseIds().isEmpty());

            // TODO 8:
            // Find the first course with rating >= 4.9.
            // Return Optional<Course>.

            Optional<Course> firstGreatCourse = data.courses.stream()
                    .filter(course -> course.rating >= 4.9)
                    .findFirst();

            // TODO 9:
            // Find the longest course title using reduce.
            Optional<String> longestCourseTitle = data.courses.stream()
                    .map(Course::title)
                    .reduce((c1, c2) -> c1.length() > c2.length() ? c1 : c2);

            // TODO 10:
            // Build a single comma-separated String of all instructor names.
            // First do it with collect(joining), then do it again with reduce.

            String joinedInstructorsName = data.instructors.stream()
                    .map(Instructor::name)
                    .collect(Collectors.joining(", "));

            Optional<String> reducedInstructorNames = data.instructors.stream()
                    .map(Instructor::name)
                    .reduce((n1, n2) -> n1 + ", " + n2);

        }
    }

    // =========================================================
    // PART 2 — STREAM PIPELINES
    // =========================================================

    static final class Part2StreamPipelines {
        static void run(Data data) {
            System.out.println("\n=== PART 2: STREAM PIPELINES ===");

            // TODO 11:
            // Get all tags from all courses as a single List<String>.
            // This is your first flatMap challenge.
            List<String> allCoursesTags = data.courses().stream()
                    .map(Course::tags)
                    .flatMap(List::stream)
                    .toList();

            System.out.println("all courses tags: " + allCoursesTags);
            // TODO 12:
            // Get all distinct tags, sorted alphabetically.

            List<String> distinctSortedCoursesTags = data.courses().stream()
                    .flatMap(course -> course.tags().stream())
                    .distinct()
                    .sorted(Comparator.naturalOrder())
                    .toList();

            System.out.println("distinct and sorted alpha courses tags: " + distinctSortedCoursesTags);
            // TODO 13:
            // Get all enrolled course ids from all students as one flat stream.
            // Then count how many total enrollments exist.
            long totalEnrollments = data.students().stream()
                    .flatMap(student -> student.enrolledCourseIds.stream())
                    .count();

            System.out.println("total enrollments: " + totalEnrollments);

            // TODO 14:
            // Get all distinct course ids that at least one student is enrolled in.
            List<String> distinctEnrolledInCourses = data.students.stream()
                    .flatMap(student -> student.enrolledCourseIds.stream())
                    .distinct()
                    .toList();

            System.out.println("distinct enrolled in courses: " + distinctEnrolledInCourses);
            // TODO 15:
            // Find all course titles for courses that any student has completed.
            // Output should be distinct and sorted.

            Set<String> completedCourses = data.students().stream()
                    .flatMap(student -> student.completedCourseIds().stream())
                    .collect(Collectors.toSet());

            List<String> listOfCompletedCourses = data.courses().stream()
                    .filter(course -> completedCourses.contains(course.id()))
                    .map(Course::title)
                    .distinct()
                    .sorted()
                    .toList();

            System.out.println("List of course completed by a student at least: " + listOfCompletedCourses);
            // TODO 16:
            // Find students enrolled in at least one ADVANCED course.
            // Return their names.
            Set<String> advancedCoursesIds = data.courses().stream()
                    .filter(course -> course.level() == Level.ADVANCED)
                    .map(Course::id)
                    .collect(Collectors.toSet());

            List<String> studentEnrolledInAdvancedCourses = data.students().stream()
                    .filter(student -> student.enrolledCourseIds()
                            .stream()
                            .anyMatch(advancedCoursesIds::contains))
                    .map(Student::name)
                    .toList();

            System.out.println("Students enrolled in at least one advanced course: " + studentEnrolledInAdvancedCourses);

            // TODO 17:
            // Find course titles that have at least one tag containing "java".

            List<String> coursesWithJavaTag = data.courses().stream()
                    .filter(course -> course.tags().contains("java"))
                    .map(Course::title)
                    .toList();

            System.out.println("courses with java tag: " + coursesWithJavaTag);
            // TODO 18:
            // Produce a List<String> where each entry looks like:
            // "Alice -> Java Generics Deep Dive"
            // for every enrollment of every student.
            // This is a good flatMap + lookup challenge.

            Map<String, String> courseIdsTitlesMap = data.courses().stream()
                    .collect(Collectors.toMap(
                            Course::id,
                            Course::title
                    ));

            List<String> ListOfStudentAndEnrolledCourseTitle1 = data.students().stream()
                    .flatMap(student -> student.enrolledCourseIds()
                            .stream()
                            .map(enrolledCourseId -> student.name() + " -> " + courseIdsTitlesMap.get(enrolledCourseId))
                    ).toList();

            System.out.println("Students and there enrolled courses titles: " + ListOfStudentAndEnrolledCourseTitle1);

            // TODO 19:
            // From all scores of all students, produce:
            // - a List<Integer> of scores >= 90
            // - sorted descending
            // - with duplicates preserved

            List<Integer> sortedStudentsScores = data.students().stream()
                    .flatMap(student -> student.scores().stream())
                    .filter(score -> score >= 90)
                    .sorted(Comparator.reverseOrder())
                    .toList();

            System.out.println("students scores above 90, sorted desc: " + sortedStudentsScores);

            // TODO 20:
            // Find the average of all scores across all students.
            // Return OptionalDouble or double, your choice.

            OptionalDouble studentsScoresAverage = data.students().stream()
                    .flatMap(student -> student.scores().stream())
                    .mapToDouble(Integer::doubleValue)
                    .average();

            System.out.println(String.format("students average score %.2f", studentsScoresAverage.orElse(0.0)));
        }
    }

    // =========================================================
    // PART 3 — COLLECTORS MASTERY
    // =========================================================

    static final class Part3Collectors {
        static void run(Data data) {
            System.out.println("\n=== PART 3: COLLECTORS MASTERY ===");

            // TODO 21:
            // Group courses by category.
            // Result type: Map<Category, List<Course>>
            Map<Category, List<Course>> coursesByCat = data.courses().stream()
                    .collect(Collectors.groupingBy(Course::category));

            System.out.println("courses by category: " + coursesByCat);
            // TODO 22:
            // Group courses by level.
            // Result type: Map<Level, List<String>>
            // Store only course titles, not Course objects.
            /*version1 - using Collectors.toMap :
            Map<Level, List<String>> coursesTitlesByLevel = data.courses().stream()
                    .collect(Collectors.toMap(
                            Course::level,
                            course -> new ArrayList<>(List.of(course.title())),
                            (l1, li2) -> {
                                l1.addAll(li2);
                                return l1;
                            }
                    ));*/
            Map<Level, List<String>> coursesTitlesByLevel = data.courses().stream()
                    .collect(Collectors.groupingBy(
                            Course::level,
                            Collectors.mapping(
                                    Course::title,
                                    Collectors.toList()
                            )
                    ));
            System.out.println("courses titles grouped by level: " + coursesTitlesByLevel);

            // TODO 23:
            // Count how many courses exist in each category.
            // Result type: Map<Category, Long>
            Map<Category, Long> countOfCoursesPerCategory = data.courses().stream()
                    .collect(Collectors.groupingBy(
                            Course::category,
                            Collectors.counting()
                    ));

            System.out.println("count of courses per cat: " + countOfCoursesPerCategory);

            // TODO 24:
            // Find the highest-rated course in each category.
            // Result type: Map<Category, Optional<Course>>
            Map<Category, Optional<Course>> highestRatedCoursePerCat = data.courses().stream()
                    .collect(Collectors.groupingBy(
                            Course::category,
                            Collectors.maxBy(Comparator.comparingDouble(Course::rating))
                    ));
            System.out.println("Highest rated course per cat: " + highestRatedCoursePerCat);

            // TODO 25:
            // Same as TODO 24, but unwrap the Optional safely so the final map is:
            // Map<Category, Course>

            Map<Category, Course> highestRatedCoursePerCat1 = data.courses().stream()
                    .collect(Collectors.groupingBy(
                            Course::category,
                            Collectors.collectingAndThen(
                                    Collectors.maxBy(
                                            Comparator.comparingDouble(Course::rating)
                                    ),
                                    oMax -> oMax.orElse(null)
                            )
                    ));

            System.out.println("highest rated course per cat: " + highestRatedCoursePerCat1);
            // TODO 26:
            // Partition students into:
            // - age >= 26
            // - age < 26
            // Result type: Map<Boolean, List<Student>>
            Map<Boolean, List<Student>> grownUpStudents = data.students().stream()
                    .collect(Collectors.groupingBy(
                            student -> student.age >= 26

                    ));

            System.out.println("grown up students: " + grownUpStudents.get(true));
            System.out.println("still young students: " + grownUpStudents.get(false));

            // TODO 27:
            // Group students by the number of enrolled courses.
            // Result type: Map<Integer, List<String>>
            // Store student names.

            Map<Integer, List<Student>> groupedStudentsByCountOfEnrolledCOurses = data.students().stream()
                    .collect(Collectors.groupingBy(
                            student -> student.enrolledCourseIds().size()
                    ));

            System.out.println("grouped students by num of enrolled courses: " + groupedStudentsByCountOfEnrolledCOurses);
            // TODO 28:
            // Create a Map<String, Course> indexed by course id.
            // Then think:
            // what would happen if course ids were not unique?

            // TODO 29:
            // Create a Map<Category, Double> of average rating per category.

            // TODO 30:
            // Create a Map<Category, String> where each value is a single joined string
            // of course titles in that category, separated by " | ".

            // TODO 31:
            // For each student, compute average score.
            // Result type: Map<String, Double>
            // key = student name

            // TODO 32:
            // Create a nested grouping:
            // Map<Category, Map<Level, List<String>>>
            // containing course titles.

            // TODO 33:
            // Collect all tags into a frequency map.
            // Result type: Map<String, Long>
            // Example: "java" -> how many times it appears across all courses

            // TODO 34:
            // Build a Set<String> of all categories taught by all instructors.
            // Store enum names as strings.
        }
    }

    // =========================================================
    // PART 4 — GENERICS AND WILDCARDS
    // =========================================================

    static final class Part4GenericsAndWildcards {
        static void run(Data data) {
            System.out.println("\n=== PART 4: GENERICS AND WILDCARDS ===");

            // TODO 35:
            // Implement a generic method:
            // <T> List<T> filterList(List<T> input, Predicate<? super T> predicate)
            // Return a NEW list.

            // TODO 36:
            // Implement a generic method:
            // <T, R> List<R> mapList(List<T> input, Function<? super T, ? extends R> mapper)

            // TODO 37:
            // Implement a generic method:
            // <T> Optional<T> findFirstMatching(List<T> input, Predicate<? super T> predicate)

            // TODO 38:
            // Implement a generic method:
            // <T> Map<T, Long> frequencyMap(List<T> input)

            // TODO 39:
            // Implement a generic method:
            // <T> List<T> mergeLists(List<? extends T> left, List<? extends T> right)

            // TODO 40:
            // Implement a generic method:
            // <T> void copyAll(List<? extends T> source, List<? super T> target)
            // This is a PECS exercise.

            // TODO 41:
            // Implement a generic method:
            // <T> List<T> distinctPreservingOrder(List<T> input)

            // TODO 42:
            // Implement a generic method:
            // <T> List<T> sortWithComparator(List<T> input, Comparator<? super T> comparator)
            // Return a NEW list.

            // TODO 43:
            // Implement a generic max method:
            // <T> Optional<T> maxBy(Collection<T> items, Comparator<? super T> comparator)

            // TODO 44:
            // Implement a generic grouping method:
            // <T, K> Map<K, List<T>> groupBy(List<T> input, Function<? super T, ? extends K> classifier)

            // TODO 45:
            // Implement a generic partition method:
            // <T> Map<Boolean, List<T>> partitionBy(List<T> input, Predicate<? super T> predicate)
        }

        // TODO 35 implementation here
        // static <T> List<T> filterList(...) { ... }

        // TODO 36 implementation here
        // static <T, R> List<R> mapList(...) { ... }

        // TODO 37 implementation here
        // static <T> Optional<T> findFirstMatching(...) { ... }

        // TODO 38 implementation here
        // static <T> Map<T, Long> frequencyMap(...) { ... }

        // TODO 39 implementation here
        // static <T> List<T> mergeLists(...) { ... }

        // TODO 40 implementation here
        // static <T> void copyAll(...) { ... }

        // TODO 41 implementation here
        // static <T> List<T> distinctPreservingOrder(...) { ... }

        // TODO 42 implementation here
        // static <T> List<T> sortWithComparator(...) { ... }

        // TODO 43 implementation here
        // static <T> Optional<T> maxBy(...) { ... }

        // TODO 44 implementation here
        // static <T, K> Map<K, List<T>> groupBy(...) { ... }

        // TODO 45 implementation here
        // static <T> Map<Boolean, List<T>> partitionBy(...) { ... }
    }

    // =========================================================
    // PART 5 — CHALLENGE MODE
    // =========================================================

    static final class Part5ChallengeMode {
        static void run(Data data) {
            System.out.println("\n=== PART 5: CHALLENGE MODE ===");

            // TODO 46:
            // Find the "most versatile" instructor.
            // Definition:
            // - highest number of specialties
            // tie-breaker: highest number of taught courses
            // tie-breaker: lexicographically smaller name
            // Return Optional<Instructor>.

            // TODO 47:
            // Build a leaderboard of students by average score descending.
            // Output:
            // List<String> like "Fatima -> 97.67"
            // Tie-breaker: name ascending.

            // TODO 48:
            // Find all pairs:
            // student name + course title
            // where the student is enrolled in a course whose category is BACKEND.
            // Output as List<String>, sorted first by student name then by course title.

            // TODO 49:
            // Build a Map<Category, List<String>> where each list contains names of students
            // enrolled in at least one course of that category.
            // Student names in each list must be distinct and sorted.

            // TODO 50:
            // Find the top 3 most common tags across all courses.
            // Output type: List<Map.Entry<String, Long>> or your own DTO if you prefer.

            // TODO 51:
            // For each instructor, compute the average rating of courses they teach.
            // Result type: Map<String, Double>
            // key = instructor name

            // TODO 52:
            // Find "inactive" courses:
            // courses that no student is currently enrolled in.
            // Return List<Course> sorted by title.

            // TODO 53:
            // Build a recommendation list for each student:
            // recommend courses in categories the student already studies,
            // but exclude courses they are already enrolled in.
            // Result type:
            // Map<String, List<String>>
            // key = student name, value = recommended course titles sorted alphabetically

            // TODO 54:
            // Find students whose completed courses are a subset of their enrolled courses.
            // Return List<String> of names.
            // Then think: should this always be true in a good domain model?

            // TODO 55:
            // Compute completion ratio for each student:
            // completed / enrolled
            // Return Map<String, Double>

            // TODO 56:
            // Find the category with the highest average course rating.
            // Return Optional<Category>.

            // TODO 57:
            // Build a reverse index:
            // Map<String, Set<String>>
            // key = tag
            // value = set of course titles having that tag

            // TODO 58:
            // Find all students who are enrolled only in BEGINNER courses.

            // TODO 59:
            // Build a summary object for each category.
            // Create a DTO CategorySummary with:
            // - category
            // - courseCount
            // - averageRating
            // - totalDurationHours
            // - highestRatedCourseTitle
            //
            // Then build:
            // Map<Category, CategorySummary>

            // TODO 60:
            // Hard challenge:
            // detect students whose average score is above the global average score
            // AND who are enrolled in at least one ADVANCED course
            // AND who have completed at least one course.
            // Output sorted by average score descending.

            // TODO 61:
            // Hard challenge:
            // produce a flat report row per instructor-course pair.
            // DTO suggestion: TeachingRow(instructorName, courseTitle, category, level, rating)
            // Then sort by:
            // category -> rating desc -> instructorName

            // TODO 62:
            // Hard challenge:
            // Build a Map<Level, List<String>> of student names where a student appears in a level
            // if they are enrolled in at least one course of that level.
            // Each list should be distinct and sorted.

            // TODO 63:
            // Hard challenge:
            // For each student, compute the set of tags they are exposed to via enrolled courses.
            // Result type: Map<String, Set<String>>

            // TODO 64:
            // Hard challenge:
            // Find the "broadest learner":
            // the student exposed to the highest number of distinct tags across enrolled courses.
            // Return Optional<Student>

            // TODO 65:
            // Final boss:
            // write one method of your own that combines:
            // - generics
            // - a functional interface parameter
            // - stream processing
            // - collecting
            //
            // Example direction:
            // a reusable generic indexing/grouping/report helper.
        }
    }

    // =========================================================
    // OPTIONAL DTOS FOR ADVANCED CHALLENGES
    // =========================================================

    // TODO:
    // Create record CategorySummary(...)
    // for challenge 59.

    // TODO:
    // Create record TeachingRow(...)
    // for challenge 61.
}