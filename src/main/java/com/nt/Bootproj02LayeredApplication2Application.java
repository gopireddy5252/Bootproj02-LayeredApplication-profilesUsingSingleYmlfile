package com.nt;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nt.controller.EmployeeOperationController;
import com.nt.model.Employee;

@SpringBootApplication
public class Bootproj02LayeredApplication2Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Bootproj02LayeredApplication2Application.class, args);
        EmployeeOperationController controller = ctx.getBean("empController", EmployeeOperationController.class);
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println("DataSource class: " + dataSource.getClass().getName());

//        
        //read input from the end user
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter desg1 (or type 'exit' to quit): ");
            String desg1 = sc.next();
            if (desg1.equalsIgnoreCase("exit")) {
                break; // exit the loop
            }
            System.out.println("Enter desg2: ");
            String desg2 = sc.next();
            System.out.println("Enter desg3: ");
            String desg3 = sc.next();
            
            try {
                List<Employee> list = controller.processEmployeeByDesg(desg1, desg2, desg3);
                list.forEach(emp -> {
                    System.out.println(emp);
                });
            } catch (Exception e) {
                e.printStackTrace();
                //System.out.println("Internal process problem");
            }
            
            //insert into values

        

        try {
            System.out.println("Enter eno:");
            int eno = sc.nextInt();  // Read employee number
            
            sc.nextLine();  // Consume newline left by nextInt()

            System.out.println("Enter ename:");
            String sname = sc.nextLine();  // Read employee name

            System.out.println("Enter job:");
            String job = sc.nextLine();  // Read job title

            System.out.println("Enter sal:");
            double sal = sc.nextDouble();  // Read salary

            System.out.println("Enter deptno:");
            int deptno = sc.nextInt();  // Read department number

            // Create Employee object and set the data
            Employee emp = new Employee();
            emp.setEno(eno);
            emp.setSname(sname);
            emp.setJob(job);
            emp.setSal(sal);
            emp.setDeptno(deptno);

            String resultMSg=controller.registerEmployee(emp);
            System.out.println(resultMSg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();  // Close scanner to avoid resource leaks
        }
        System.out.println("___________________________");
        String[] beanids=ctx.getBeanDefinitionNames();
        System.out.println("all bean ids: "+Arrays.toString(beanids));
        
    }
        
        // No need to close the context here, let Spring Boot manage it
        // ((ConfigurableApplicationContext) ctx).close();
    }
}
