package cn.tx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StuMain {


    static void main() throws IOException {
        System.out.println("---Welcome to Student Management System---");
        Student student = new Student();

        Scanner sc = new Scanner(System.in);

        System.out.println("Please input student id");
        int id = sc.nextInt();
        sc.nextLine();
        student.id = id;

        System.out.println("Please input student name");
        String name = sc.nextLine();
        student.username = name;

        System.out.println("Please input student gender");
        String gender = sc.next();
        student.gender = gender;

        System.out.println("Please input student math score");
        int math = sc.nextInt();
        student.math = math;

        System.out.println("Please input student physics score");
        int physics = sc.nextInt();
        student.physics = physics;

        System.out.println("Please input student english score");
        int english = sc.nextInt();
        student.english = english;

        writeStudent(student);
    }

    public static void writeStudent(Student student) throws IOException {
        File file = new File("stu.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("id\tname\tgender\tmath\tphysics\tenglish");
        bw.newLine();
        bw.write(student.id + "\t");
        bw.write(student.username + "\t");
        bw.write(student.gender + "\t");
        bw.write(student.math + "\t");
        bw.write(student.physics + "\t");
        bw.write(student.english + "\t");
        bw.newLine();

        bw.flush();
        if(bw != null){
            bw.close();
        }
    }
}
