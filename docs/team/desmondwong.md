---
  layout: default.md
  title: "Desmond Wong's Project Portfolio Page"
---

### Project: EduBase

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to create a course.
    * **What it does**: allows the teacher to create a course by specifying the COURSE_ID and course name.
    * **Justification**: This is a core feature of the course, as teacher needs to add students to the course.

* **New Feature**: Find student by STUDENT_ID
  * **What it does**: allows the teacher to easily find the students by entering their STUDENT_IDs.
  * **Justification**: It provides a convenient and efficient ways for teacher to find a student, instead of browsing through the whole list of students.

* **New Feature**: Edit course.
  * **What it does**: allows the teacher to edit the course details, such as COURSE_ID and course name.
  * **Justification**: TInstead of deleting the existing course and creating a new course, teachers can easily edit the course details with this command.

* **Improved Feature**: Clear the storage.
  * **What it does**: allows the teacher to delete all students and courses with one command.

* **Code contributed**: 
  * [create_course](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/60)
  * [find_student_by_id](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/73)
  * [edit_course](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/90), [test case for edit_course](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/99)
  * [clear](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/141)

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage(Pull requests [test cases for edit_course](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/99)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `create_course`, `find_student_by_id` and `edit_course`. [\#153](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/153)
        * Did cosmetic tweaks to existing documentation of features `clear`, `edit_student`, `find_student_by_name`: [\#153](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/153)
    * Developer Guide:
        * Added implementation details of the `create_course`, `find_student_by_id` and `edit_course` feature. [\#136](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/136)
        * Added more uses cases. [\#172](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/172)
        * Updated relevant UML diagrams. [\#104](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/104)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#82](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/82), [\#83](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/83), [\#101](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/101), [\#107](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/107), [\#114](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/114), [\#130](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/130), [\#148](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/148)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2526S1/forum/issues/207#issuecomment-3307279071))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/146), [2](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/145), [3](http://github.com/AY2526S1-CS2103T-T13-4/tp/issues/139), [4](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/137), [5](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/134), [6](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/149))
