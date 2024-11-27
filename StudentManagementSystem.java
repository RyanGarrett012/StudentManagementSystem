package com.mycompany.studentmanagementsystem;
import java.io.*;
import com.mycompany.StudentManagementSystem.Student;
import com.mycompany.StudentManagementSystem.Subject;
import java.util.*;
/**
 *
 * @author Ryan
 */
public class JavaProject {
    private static Map<Integer, Student> students = new HashMap<>();
    private static List<Subject> subjects = new ArrayList<>();
    private static ArrayList<String> fileDataArray = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args)throws IOException {
        //Creating Subjects :D!!!
        subjects.add(new Subject("Math"));
        subjects.add(new Subject("English"));
        subjects.add(new Subject("Science"));
        subjects.add(new Subject("History"));
        subjects.add(new Subject("Java Programming"));
        
        //Creating text file to use as database or reading existing file into memory
        String studentRecord = "Student_Record.txt";
        File file = new File(studentRecord);
        if (file.createNewFile()) {
          System.out.println("File created: " + file.getName());
        } else {
            System.out.println("Student record file found");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()){
                String fileData = myReader.nextLine();
                String[] fileDataSplit = fileData.split(",");
                for (int i = 0; i < fileDataSplit.length; i++){
                    fileDataArray.add(fileDataSplit[i]);
                }
                //If the student record modulo operation has any remainder then it cannot function in this program
                if (fileDataArray.size() % 7 > 0){
                    System.out.println("Error: Student record has been modified or is otherwise unreadable, recommend to delete Student_Record.txt");
                    System.exit(0);
                }
            }
            //Adds the data from fileDataArray to students HashMap and creates instances of the Student class for each student on record
            for (int i = 0; i < fileDataArray.size(); i++){
                if (i == 0 || i % 7 == 0){
                    int id = Integer.parseInt(fileDataArray.get(i));
                    Student student = new Student(fileDataArray.get(i+1), id);
                    for (int y = 0; y <5; y++){
                        student.enrollInSubject(subjects.get(y));
                        try {
                            student.addGrade(subjects.get(y), Integer.parseInt(fileDataArray.get(i + 2 + y)));
                        } catch (Exception d){
                            student.addGrade(subjects.get(y), 0);
                        }
                    }
                    students.put(Integer.parseInt(fileDataArray.get(i)), student);
                }
            }
        }
        boolean exit = false; //boolean for case 5 (Terminates program)
        while (!exit) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add or Update Grade");
            System.out.println("3. View Student Grades");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Select an option by entering a number: ");
            
            int option = integerValidation(scanner.nextLine());
            switch (option) {
                case 1 -> addStudent();
                case 2 -> addOrUpdateGradeForStudent();
                case 3 -> viewStudentGrades();
                case 4 -> generateReport();
                case 5 -> exit = exit();
                default -> System.out.println("ERROR: Input is invalid. Try again."); //input validation
            }
        }
        scanner.close();
    }
    /*Creates a new instance of Student class using the entered name and ID#,
    adds 7 elements to the end of fileDataArray and populates the corresponding 
    ID and name elements, and adds 5 subject class instances to the new student
    class*/
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String StudentName = commaRemover(scanner.nextLine());
        System.out.print("Enter student ID: ");
        int id = integerValidation(scanner.nextLine());
        String idString = Integer.toString(id);
        int arrayPreLength = fileDataArray.size();
        //Checking if Student ID number is already in use
        for (int i = 0; i < arrayPreLength; i++){
            if ((fileDataArray.get(i)).equals(idString)){
                System.out.println("Student ID number is in use, please enter another number");
                System.out.println("The last studen ID number on record is " + fileDataArray.get((arrayPreLength - 7)));
                id = integerValidation(scanner.nextLine());
                idString = Integer.toString(id);
            } else {
            }
        }
        Student student = new Student(StudentName, id);
        students.put(id, student);
        //Appending fileDataArray with 7 "empty" elements 
        for (int i = 0; i < 7; i++){
            fileDataArray.add("0");
        }
        fileDataArray.set((arrayPreLength), idString);
        fileDataArray.set((arrayPreLength + 1), StudentName);
        for (int i = 0; i < 5; i++){
            student.enrollInSubject(subjects.get(i));
        }
        System.out.println("Student added: " + StudentName);
    }
    /*Updates the corresponding subject in the studant's student class instance
    and updates the corresponding subject element in the fileDataArray*/
    private static void addOrUpdateGradeForStudent() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("Enrolled subjects:");
            for (int i = 0; i < 5; i++){
                int x = i + 1;
                System.out.println(x + ". " + subjects.get(i));
            }
            System.out.print("Select a subject to add or update a grade (by number): ");
            int subjectIndex = integerValidation(scanner.nextLine()) - 1;
            if (subjectIndex >= 0 && subjectIndex < subjects.size()) {
                System.out.print("Enter grade (0-100): ");
                int grade = integerValidation(scanner.nextLine());
                if (grade >= 0 && grade <= 100) {
                    student.addGrade(subjects.get(subjectIndex), grade);
                    for (int i = 0; i < fileDataArray.size(); i++){
                        if (i == 0 || i % 7 == 0){
                            int var = Integer.parseInt(fileDataArray.get(i));
                            if (var == student.getId()){
                                System.out.println("Student ID found");
                                String gradeString = Integer.toString(grade);
                                fileDataArray.set((i + subjectIndex + 2), gradeString);
                            }
                        }
                    }
                    System.out.println("Grade added/updated: " + grade);
                } else {
                    System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                }
            } else {
                System.out.println("Invalid subject choice.");
            }
        }
    }
    /*Pulls the 5 instances of subject class of unique student class instance
    and displaces the grades for each subject*/
    private static void viewStudentGrades() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("\nGrades for " + student.getStudentName() + ":");
            for (Map.Entry<Subject, Integer> entry : student.getGrades().entrySet()) {
                System.out.println(entry.getKey().getSubjectName() + ": " + (entry.getValue() != null ? entry.getValue() : "No grade assigned"));
            }
        }
    }
    //connects students to their assigned ID for easier access (database rules!!)
    //we love primary keys!!!!!
    private static Student getStudentById(){
        System.out.print("\nEnter student ID: ");
        int id = integerValidation(scanner.nextLine());
        Student student = students.get(id);
        if (student == null) {
            System.out.println("No student exists with the ID - " + id);//input validation
        }
        return student;
    }
    //Report selection
    private static void generateReport(){
        boolean exit = false;
        while (!exit){
            System.out.println("\nSelect what kind of report:");
            System.out.println("1. Average grade for a student");
            System.out.println("2. All student average for a subject");
            System.out.println("3. Highest and lowest grades for a specific subject");
            System.out.println("4. All students' grades sorted by subject");
            System.out.println("5. Return to previous menu");
            int option = integerValidation(scanner.nextLine());
            switch (option){
                case 1 -> System.out.println(averageGrades());
                case 2 -> System.out.println(gradesForSubject());
                case 3 -> System.out.println(highLow());
                case 4 -> System.out.println(allGradesSorted());
                case 5 -> exit = true;
                default -> System.out.println("ERROR: Input is invalid. Try again."); //input validation
            }
        } 
    }
    /*Averages the grades for selected student by averaging their corresponding
    grades in the fileDataArray*/
    private static String averageGrades(){
        System.out.println("Enter the student's ID");
        int id = integerValidation(scanner.nextLine());
        int gradeSum = 0;
        for (int i = 0; i < fileDataArray.size(); i++){
            if (i == 0 || i % 7 == 0){
                if (Integer.parseInt(fileDataArray.get(i)) == id){
                    for (int x = (i + 2); x <= (i + 6); x++){
                        gradeSum += Integer.parseInt(fileDataArray.get(x));
                    }
                }
            }
        }
        int gradeAverage = gradeSum / 5;
        String gradeAverageString = Integer.toString(gradeAverage);
        String idString = Integer.toString(id);
        return ("The student with the ID " + idString + " has an average grade of " + gradeAverageString);
    }
    /*Adds the totals for the subject corresponding to its element numbers in 
    fileDataArray, then divides by the number of students in fileDataArray*/
    private static String gradesForSubject(){
        System.out.println("Available subjects:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).getSubjectName());
        }
        System.out.print("Select a subject: ");
        int subjectIndex = integerValidation(scanner.nextLine());
        while (subjectIndex < 1 || subjectIndex > 5){
            System.out.println("Please select a subject from the list by entering the corresponding number");
            subjectIndex = integerValidation(scanner.nextLine());
        }
        subjectIndex += 1;
        int runningTotal = 0;
        for (int i = 0; i <= fileDataArray.size(); i++){
            if (i == subjectIndex || i % 7 == subjectIndex){
                runningTotal += Integer.parseInt(fileDataArray.get(i));
            }
        }
        int numberOfStudents = fileDataArray.size() / 7;
        int subjectAverage = runningTotal / numberOfStudents;
        String subjectAverageString = Integer.toString(subjectAverage);

        return "The average grade for " + subjects.get(subjectIndex - 2) + " is " + subjectAverageString;
    }
    /*Pulls every subject's element from fileDataArray and moves it into
    runningArrayList which is used to create runningIntArray, from which
    the highest and lowest grades are determined*/
    private static String highLow(){
        System.out.println("Available subjects:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).getSubjectName());
        }
        System.out.print("Select a subject: ");
        int subjectIndex = integerValidation(scanner.nextLine());
        while (subjectIndex < 1 || subjectIndex > 5){
            System.out.println("Please select a subject from the list by entering the corresponding number");
            subjectIndex = integerValidation(scanner.nextLine());
        }
        subjectIndex += 1;
        ArrayList<String> runningArrayList = new ArrayList<String>();
        for (int i = 0; i < fileDataArray.size(); i++){
            if ( i == subjectIndex || i % 7 == subjectIndex){
                runningArrayList.add(fileDataArray.get(i));
            }
        }
        int[] runningIntArray = new int[runningArrayList.size()];
        for (int i = 0; i < runningArrayList.size(); i++){
            runningIntArray[i] = Integer.parseInt(runningArrayList.get(i));
        }
        int highest = runningIntArray[0];
        for (int i = 1; i < runningIntArray.length; i++) {
            if (runningIntArray[i] > highest) {
                highest = runningIntArray[i];
            }
        }
        int lowest = runningIntArray[0];
        for (int i = 1; i < runningIntArray.length; i++) {
            if (runningIntArray[i] < lowest) {
                lowest = runningIntArray[i];
            }
        }
        return "The highest and lowest grades for " + subjects.get(subjectIndex -2) + " are " + highest + " and " + lowest;
    }
    /*Calculates every student's average grade from fileDataArray, then sorts
    students by average from high to low using a HashMap and ArrayList*/
    private static String allGradesSorted(){
        int numberOfStudents = fileDataArray.size() / 7;
        Integer[] averages = new Integer[numberOfStudents];
        int gradeSum = 0;
        for (int i = 0; i < fileDataArray.size(); i++){
            gradeSum = 0;
            if (i == 0 || i % 7 == 0){
                for (int x = (i + 2); x <= (i + 6); x++){
                    gradeSum += Integer.parseInt(fileDataArray.get(x));
                }
                if (i == 0){
                    averages[i] = gradeSum / 5;
                } else {
                    averages[i / 7] = gradeSum / 5;
                }
            }
        }
        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        for (int i = 0; i < averages.length; i++){
            if ( i ==0){
                hashMap.put(averages[i], fileDataArray.get(i + 1));
            } else {
                hashMap.put(averages[i], fileDataArray.get((i * 7) + 1));
            }
        }
        ArrayList<Integer> sortedKeys = new ArrayList<Integer>(hashMap.keySet());
        Collections.sort(sortedKeys, Collections.reverseOrder());
        System.out.println("\nAll student averages are:");
        for (Integer i: sortedKeys){
            System.out.println(hashMap.get(i) + ": " + i);
        }
    return "";
    }
    //try-catch block to ensure users enter an integer for scanner.nextInt() method
    private static int integerValidation(String input){
            boolean validation = false;
            int validInteger = 0;
            while (!validation){
                try{
                    validInteger = Integer.parseInt(input);
                    validation = true;
                }
                catch (Exception e) {
                    System.out.println("Please enter an integer value");
                    input = scanner.nextLine();
                }
            }
        return validInteger;
    }
    //Removes comma's from String type user inputs to prevent Student_Record.txt
    //from breaking
    private static String commaRemover(String input){
        String var = input.replace(",","");
        return var;
    }
    //Rewrites Student_Text.txt with new data added to fileDataArray
    private static boolean exit(){
        File myFile = new File ("Student_Record.txt");
        try {
            if (!myFile.createNewFile()){
                FileWriter myWriter = new FileWriter("Student_Record.txt");
                for (int i = 0; i < fileDataArray.size(); i++){
                    myWriter.write(fileDataArray.get(i) + ",");
                }
                myWriter.close();
                System.out.println("Saved");
            } else {
                System.out.println("Error: New data not saved");
            }
        } catch (IOException e){
            System.out.println("Error occured when saving file data");
        }
        return true;
    }
}
