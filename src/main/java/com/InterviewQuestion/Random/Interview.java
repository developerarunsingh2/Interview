package com.InterviewQuestion.Random;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public  class Interview {
    public static void main(String[] args) {
        System.out.println("Ram");
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"John esrst",85.5,"Banaras"));
        students.add(new Student(2,"Jhon 123",55,"Banaras"));
        students.add(new Student(3,"Ram 123",34.5,"Coventry"));
        students.add(new Student(4, "123 Park",65,"Banaras"));
        students.add(new Student(5, "123 Park",65,"Banaras"));

        List<Student> distinctionStudents = students.stream().filter(s ->s.getPercentage()>25 && s.getCity()
                .equals("Banaras")).sorted(Comparator.comparing(Student::getPercentage).reversed()).collect(Collectors.toList());

        out.println(distinctionStudents);
        //75%>  belong to city . sorted, sorted based on percentage.

        // getting the unique records where duplicate records are present except unique ids

        String s = " Hello Java 11 ";
        System.out.println(s.isBlank()); // false
        System.out.println(" \n ".isBlank()); // true
        System.out.println(s.strip()); // "Hello Java 11"
        System.out.println(s.stripLeading()); // "Hello Java 11 "
        System.out.println(s.stripTrailing()); // " Hello Java 11"
        s.lines().forEach(System.out::println); // Splits lines

    }

    int a =10;

    public int add(){

        Integer b = new Integer(11);

        b=20; // autoboxing

        Integer c = Integer.valueOf(b);

        int d = c; // unboxing

        return 10;
    }
//
////jwt, config web security, configuratoin class(web security adapter class, urls, roles, user alot,
//    user , admin, which type of authentication,
//
//    authentication(public token, login, username, password, token key, content header, public token is same
//    and matches),
//
//        authorize -> acess levels
//    */
//
//    node , react, python,
//    /
//
//    logging framework:
//    splunk logs, (prod vs non-prod), timestamp, on the based of microservices,
//
//    cloud uses, (pcf, harness deployed),
//
//    hosted in private space, planning to move to azure,
//
//    production issue to troubleshout:
//    stacktrace, with object and logger data, slam and sluthe,
//
//    edge cases,
//
//    tools, github, jira, jenkins, test cases,
//
//    Mockito annotations:
//    @SpringRunner,
//    @Test,
//    @Mock
//    @InjectMock,
//    Mockito.when, then, beforeEach,
//    initialized to same set of data,
//    test coverage,
//    75%
//
//    testing, unit testing for rest api,
//    swagger use case, api documentation part,
//    urls,
//    POC part,
//    Jenkins pipeline, (build and deployment, ),
//    Jekins -> feature branch name, deployment, branch, build, path parameters,
//    sonarqube, code quality,
//    docker usecase?
//    is deployment is manual driven, or CI/CD..(after commit as well, trigger test cases),
//    splunk for log monitoring, pcf, GRAPHANA, kibana,
//
//    team mentoring and lead role,
//
//    code review: object, null pointers, unused methods, best practices, code quality, query optimization,
//    solid principles followed, certification: 2019 java 8 (associate),
//
//    put vs post api..
//
//
//    best practices for rest api:
//    1. url patterns, common paths across all paths, rest controller, method name alignment with actual
//    functionality, unnecessary autowiring,
//
//    format of data, in the future new requirement :
//    some additional element,
//
//    exception handling in projects, throws keywords, Rest controller advice and then creating methods and certain
//    excepitions,
//
//    controller for
//
//    api/getEmployeeDetails/
//            noun vs
//
//    public unused getBest() {
//        return best;
//    }/update
//    /delete
//    /
//
//AWS, RDS, scaling, ec2, s3, elastic bean stalk,
//    // sapient pool, different type of projects, cloud platforms,
//


//    }

   /*
   *
   * dummy student list*/


}