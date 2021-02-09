/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.ws.client.app;

import com.eltonb.ws.client.model.CourseData;
import com.eltonb.ws.client.model.StudentData;
import com.eltonb.ws.client.model.StudentRegistry;
import com.eltonb.ws.client.model.StudentRegistryService;
import com.eltonb.ws.client.model.StudentRequest;
import com.eltonb.ws.client.model.StudentResponse;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author elton.ballhysa
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            StudentRegistryService service = new StudentRegistryService(new URL("http://localhost:8080/ws-textbook-ex03-studentRegistrySoap/StudentRegistryService?WSDL"));
            StudentRegistry port = service.getStudentRegistryPort();
            StudentRequest request = new StudentRequest();
            request.setStudentId(2);
            StudentResponse response = port.readStudent(request);
            display(response);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    private static void display(StudentResponse response) {
        StudentData student = response.getStudent();
        System.out.println("student id: " + student.getId());
        System.out.println("name      : " + student.getName());
        System.out.println("surname   : " + student.getSurname());
        System.out.println("gpa       : " + student.getGpa());
        System.out.println("credits   : " + student.getTotalCredits());
        System.out.println("---------------------");
        student.getCourses().forEach(MainApp::displayCourse);
    }
    
    private static void displayCourse(CourseData course) {
        System.out.println(course.getCode() + " - " + course.getTitle() + ": " + course.getGrade());
    }
    
}
