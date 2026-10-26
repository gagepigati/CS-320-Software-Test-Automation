# CS 320: Software Test Automation and Quality Assurance

This portfolio contains selected work from CS 320 at Southern New Hampshire University. The projects focus on writing Java classes that follow customer requirements, using JUnit unit tests to verify expected behavior, and evaluating software testing techniques.

## Included projects

**Project One: Contact Service**

- `Contact.java`: A contact object with a permanent contact ID and validated fields.
- `ContactService.java`: In-memory contact storage, with add, delete, retrieval, and update operations.
- `ContactTest.java`: JUnit tests of the contact object's required fields and their limits.
- `ContactServiceTest.java`: JUnit tests of unique IDs, deletion, updates, and invalid operations.

**Project Two: Summary and Reflections**

- `CS320_Project_Two_Summary_and_Reflections.pdf`: A report discussing testing against requirements, the strengths and limitations of the unit tests, and possible additional testing methods.

## How can I ensure that my code, program, or software is functional and secure?

I start by turning the written requirements into specific test cases so each important feature has a clear expected result. I test valid inputs as well as invalid inputs and boundary values. In the Contact Service project, this meant checking limits on names and IDs, enforcing ten numeric digits in phone numbers, and making sure duplicate contacts could not overwrite existing records. I also tested updates that should fail, because the program must protect existing information even when someone enters incorrect data. Unit tests help me catch these issues early, although a complete application would also need integration testing, security reviews, and other checks before release.

## How do I interpret user needs and incorporate them into a program?

I first identify the specific actions the customer expects the program to perform and the rules it must enforce. Then I translate those needs into manageable classes, methods, and test cases. For this project, the customer needed to add, delete, and edit contact records without changing their unique IDs. That requirement shaped both the design of the Contact class and the tests for the service. When a requirement leaves room for interpretation, I try to clarify it instead of adding behavior the customer did not request.

## How do I approach designing software?

I break a larger problem into smaller responsibilities so the code is easier to maintain and test. The Contact class holds and validates contact data, while ContactService manages the stored contacts. I also consider possible failures before implementing a method. For example, validating every field before applying a multi-field update helps avoid a partially updated record. After implementation, I use test results to identify improvements and revisit the design when a method is difficult to test or understand.
