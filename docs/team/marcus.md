---
layout: default.md
title: "Marcus' Project Portfolio Page"
---

## Project: EduBase

EduBase is a desktop address book application used for managing student attendance. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### New Feature: Added the delete course functionality

* **What it does:** Allows users to delete courses from the coursebook by specifying the course index.

* **Justification:** This feature is essential for managing courses, enabling users to remove courses that are no longer needed or were created by mistake.

* **Highlights:** This feature required creating the `DeleteCourseCommand` and `DeleteCourseCommandParser` classes, along with proper error handling for invalid course indices.

### New Feature: Added the find course by name functionality

* **What it does:** Allows users to search for courses by name using keywords.

* **Justification:** This feature improves user experience by enabling quick filtering of courses, especially useful when managing multiple courses.

* **Highlights:** Implemented the `FindCourseCommand` and `CourseNameContainsKeywordsPredicate` to filter courses based on partial name matches. Added 477 lines of code across 11 files.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2526s1.github.io/tp-dashboard/?search=siuuuuuuuuuuuuuuuuuu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)

### Project management

* Set up personal details and photo on the project's "About Us" page ([Pull request #37](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/37), [Pull request #33](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/33), [Pull request #20](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/20))

* Managed issues and pull requests for assigned features ([Issues #52](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/52), [#79](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/79), [#97](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/97))

### Enhancements to existing features

* Added equals and hashcode methods for `DeleteCourseCommand` to improve code quality and testability ([Pull request #83](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/83)).

* Updated application name from AddressBook to EduBase across multiple components ([Pull request #108](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/108)).

* Updated UML diagrams to reflect EduBase architecture ([Pull request #114](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/114), [Pull request #107](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/107)).

### Documentation

* **User Guide:**

  * Added documentation for delete course and find course features.

* **Developer Guide:**

  * Updated UML diagrams to reflect the EduBase architecture and renamed `AddressBookParser` to `MainParser` ([Pull request #107](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/107)).

  * Updated class diagrams to show the relationship between Course and Student entities ([Pull request #114](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/114)).

### Community

* PRs reviewed: [#90](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/90), [#92](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/92), [#94](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/94), [#95](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/95), [#101](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/101), [#155](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/155)

* Reported bugs and suggestions for the team (examples: [#109](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/109), [#111](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/111), [#160](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/160), [#163](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/163), [#165](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/165))

### Bug Fixes

* Fixed the application window displaying wrong name ([Issue #109](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues/109), [Pull request #108](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/108)).

* Added message for calling `view_course` on empty coursebook ([Pull request #164](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/164)).

* Removed tag functionality from UI ([Pull request #167](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/167)).
