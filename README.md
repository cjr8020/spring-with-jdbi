# Example Spring Boot JDBI Project

This is an example Spring Boot JDBI project. For more information on how to use
it, see the [corresponding blog entry](http://tencentdeduction.oldpatricka.com/post/156379715585/using-jdbi-with-spring-boot).

## Calling Service

This project implements one endpoint at /, which creates a record, and then
prints any records with the name 'My Example' with IDs that are at least 2. You
can call it with curl like:

    ~ $ curl http://localhost:8080/
    Created example 'My Example' with id '1'
    Found ids of at least 2: ''
    ~ $ curl http://localhost:8080/
    Created example 'My Example' with id '2'
    Found ids of at least 2: '2'
    ~ $ curl http://localhost:8080/
    Created example 'My Example' with id '3'
    Found ids of at least 2: '2, 3'
    ~ $ curl http://localhost:8080/
    Created example 'My Example' with id '4'
    Found ids of at least 2: '2, 3, 4'
