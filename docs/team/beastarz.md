---
layout: default.md
title: "Jin Yu's Project Portfolio Page"
---

## Project: EduBase

EduBase is a desktop address book application used for managing student attendance. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### New Feature: Added the core Course feature

* **What it does:** Allows for the creation of courses and provides the underlying structure to store a list of students within each course.

* **Justification:** This feature is fundamental for a student attendance application, as all students and attendance records must be associated with a specific course or module.

* **Highlights:** This was a significant structural change that required creating new Course models and modifying the Storage and Model components to handle course data. This work laid the foundation for other features like adding/removing students from courses (e.g., PRs #44, #81).

* **Credits:** {mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}

### New Feature: Added the remove student command

* (based on add remove student command commit).

* **Code contributed:** [RepoSense link](https://github.com/AY2526S1-CS2103T-T13-4/tp)

### Project management

* Acted as a primary integrator for the project, responsible for reviewing and merging a majority of the pull requests (over 25 merged PRs visible in history).

* Managed the master branch, including resolving merge conflicts ([merge conflicts from upstream](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)) and keeping it in sync.

### Enhancements to existing features

* Improved user feedback for `add_student` and `remove_student` commands by enhancing their success messages.

* Fixed a critical bug where student IDs were not incrementing correctly after a student was removed ([Pull request #62, fix student id incrementation bug](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/62)).

* Fixed a bug that prevented users from adding students with duplicate names ([fix to allow duplicate names](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)).

* Fixed `deregister student` command (likely an earlier name for the remove student command).

* Refactored parts of the codebase for improved OOP ([improve OOP commit](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)).

### Documentation

* **User Guide:**

  * Initialized the first version of the EduBase user guide by migrating it from the AB3 template ([migrate user guide from ab3 to EduBase](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)).

  * Made subsequent content updates to the User Guide ([update ug commit](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)).

* **Developer Guide:**

  * Added the Course class diagram to the Developer Guide ([add course class diagram commit](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)).

  * Updated the "About Us" page with personal details and photos ([initialize jin yu's details on AboutUs.md](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main), [add jin yu photo](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main), [update photo](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)).

### Community

* PRs reviewed (with non-trivial review comments): [#8](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/8), [#29](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/29), [#31](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/31), [#41](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/41), [#44](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/44), [#62](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/62), [#70](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/70), [#73](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/73), [#81](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/81), [#103](https->://github.com/AY2526S1-CS2103T-T13-4/tp/pull/103), [#104](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/104), [#113](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/113), [#114](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/114), [#136](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/136), [#138](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/138), [#140](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/140), [#141](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/141), [#147](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/147), [#153](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/153), [#164](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/164), [#167](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/167), [#168](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/168), [#170](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/170)

* Reported bugs and suggestions for teams in the class (examples: [1](https://github.com/AY2526S1-CS2103T-T13-4/tp/issues?q=is%3Aissue%20state%3Aclosed%20assignee%3ABeastarz))

* Some parts of the history feature I added was adopted by several other class mates ([1](https://github.com/AY2526S1-CS2103T-T13-4/tp), [2](https://github.com/AY2526S1-CS2103T-T13-4/tp))

### Tools

* Set up and configured Markbind for the project's documentation website ([configure Markbind documentation files](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main), PRs [#2](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/2), [#4](httpss://github.com/AY2526S1-CS2103T-T13-4/tp/pull/4), [#5](httpss://github.com/AY2526S1-CS2103T-T13-4/tp/pull/5)).

* Continuously maintained code quality and ensured CI/CD pipeline integrity by frequently fixing Checkstyle violations (multiple [fix checkstyle commits](https://github.com/AY2526S1-CS2103T-T13-4/tp/commits/main)).
