This is sample for Micronaut Data using Oracle Json Duality View.

It uses test container with docker image "gvenzl/oracle-free:latest-faststart" by default and then
datasource configuration is taken from the container before starting Micronaut application context.

When app is ready, to create initial data, you can use this:
```
curl -X POST http://localhost:8080/init
```
And to list all students, use this:
```
curl http://localhost:8080/students
```
Other supported methods are:
Find student by id:
```
curl http://localhost:8080/students/1
```
Find student by name:
```
curl http://localhost:8080/students/student/Peter
```
Create new student with classes
```
curl -d '{"student":"Peter", "averageGrade":9.8, "classes": ["Math"]}' -H "Content-Type: application/json" -X POST http://localhost:8080/students
```
To update student average grade
```
curl -X PUT http://localhost:8080/students/1/average_grade/9.8
```
Find max average grade:
```
curl http://localhost:8080/students/max_average_grade
```
In order to delete student by id
```
curl -X DELETE http://localhost:8080/students/1
```