# Manni's PostgreSQL SHA Types

## Project Status: Playground / Abandoned

Just exploring some ideas here. Figured I'd share.

## Summary

PostgreSQL has extensible types, which is sweet, but it's no
good for the application programmer if those types can't be
extracted to similar types in the application language.

I wanted to learn how this would work in Java.

I custom-compiled PostgreSQL with Alexey Klyukin's sha 1.1.0
PostgreSQL data types, and tried to make a corresponding Java
type. Here are my findings.

## Gradle

The gradle files are configured to support the tasks you would expect.
As a general rule, you would do 

```
gradle clean build
```

and everything would just work.

When you are ready to deploy your work, you would do

```
gradle distZip
```

and a correctly named and versioned zip file will be built in ./build/distributions.

To run the application:

```
gradle run
```

To just compile:

```
gradle compileJava
```

To just test:

```
gradle test
```

