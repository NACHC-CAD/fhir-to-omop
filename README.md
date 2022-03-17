The fhir-to-omop Project
========================

The fhir-to-omop project started as an initial effort to map FHIR data to OMOP data.  This project grew into a series of tools that includes mapping FHIR to OMOP but also includes a suite of tools designed to address many of the common tasks associated with both FHIR and OMOP.  All of the tools here are publicly available under the Apache 2 license. Documentation for this project is available <a href="https://nachc-cad.github.io/fhir-to-omop/index.html">here</a>.  Our getting started guide is available <a href="https://nachc-cad.github.io/fhir-to-omop/pages/navbar/getting-started/start-here/StartHere.html">here</a>.  This page also includes the prerequisites required for the fhir-to-omop tools.  These tools were developed and tested in a Windows environment but should be runnable anywhere as they are all written in Java.  There are some convenience files that make running these tools easier on Windows (e.g. .bat files). These tools have been developed and tested using MS Sql Server.  

There are three target audiences for the fhir-to-omop tools
1. People who just want to use the tools.  
2. People who want to integrate the fhir-to-omop tools into their Java project
3. People who want to edit and add to the fhir-to-omop tools.

1.) Using fhir-to-omop tools directly
=================================
These users can simply download the zip file and start using the tools from the cmd line.  Release 0.0.10 (2022-03-16) can be downloaded from <a href="https://github.com/NACHC-CAD/fhir-to-omop/releases/download/0.0.10/fhir-to-omop.zip">here</a>.  After the zip file has been downloaded, navigate to the fhir-to-omop page and run the fhir-to-omop.bat file.  You should then be presented with a list of the different tools you can run from the cmd line.  
```
# ----------------------------------------
#
# running fhir to omop
#
# ----------------------------------------

Working Directory: C:\Users\gresh\Downloads\fhir-to-omop\fhir-to-omop
Welcome to fhir-to-omop


# --------------------------
# WELCOME TO fhir-on-omop
# --------------------------

You've successfully launched the fhir-to-omop application!
However, we need at least one correct parameter, the name of the module you would like to run.
The following options are valid:

        say-hello (h)
                Echo the string "Hello world!" back to the console.
        params-example (p)
                Displays an example of what the parameters file should look like.
        my-params (m)
                Shows the parameters that the application has found for your request.
                In other words, this echos the parameters you have given the application
        instant-omop (i)
                Instant OMOP will build an instance of OMOP based on your app.params file.
                Your app.params file needs to be in the directory you are launching fhir-to-omop from.
        add-test-patients (a)
                Adds about 1,000 test patients from synthea to your OMOP instance

Also, make sure you have an app.properties file in your working dir (the dir your in now).

```
To run most of these command you will need to provide an app.properties file that contains information about the resources you want to connect to.  A sample app.properties file has been created that includeds everytthing required to create and populate an OMOP instance.  To run any of the above commands, simply type fhir-to-omop [option].  For example
```
fhir-to-omop say-hello
```
Or you can use the short cut version
```
fhir-to-omop h
```

2.) Integrate the fhir-to-omop tools into your Java project
=======================================================
The entire fhir-to-omop tool suite can be integrated into you Java project by adding the following dependency to your Java Project
```
<dependency>
	<groupId>org.nachc.cad.tools</groupId>
	<artifactId>fhir-to-omop</artifactId>
	<version>0.0.10</version>
</dependency>
```

3.) Edit and Test fhir-to-omop
==========================
The fhir-to-omop tool suite can be checked out so you can browse/edit the code and review and run the Junit tests using something like the following
```
git clone https://github.com/NACHC-CAD/fhir-to-omop 
cd fhir-to-omop
git checkout tags/0.0.10
```



