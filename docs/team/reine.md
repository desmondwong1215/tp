---
layout: default.md
title: "Reine Ong's Project Portfolio Page"
---

### Project: Edubase 

Beastarz is a course and student management application that helps administrators manage registrations and view relationships between courses and students efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, with around 10 kLoC of code.

Given below are my contributions to the project.

---

#### **New Features**

* **Added the `view_courses`commands**
    * **What it does:** Displays list of courses.
    * **Justification:** Allows administrators to easily check all courses.
    * **Highlights:** Required integration between the command layer, model layer, and UI components. Involved linking the course logic to the split-screen UI and updating JavaFX bindings dynamically.
    * **Pull Requests:** [#68](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/68), [#91](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/91)

---

#### **Enhancements to Existing Features**

* Refactored `add` and `delete` commands into `register` and `deregister` for clarity and consistency ([#59](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/59))
* Improved UI responsiveness and updated the color scheme for better readability ([#132](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/132))
* Updated UI to correctly display linked fields and student/course details ([#101](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/101))
* Fixed inconsistent error messages (e.g., “No course found” → user-friendly message) and improved display text for success/failure cases ([#166](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/166))

---

#### **Testing**

* Added `ViewCourseCommandTest.java` to test the new course viewing feature ([#91](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/91))
* Fixed failing tests after refactors and enhanced overall assertion checks
* Updated and extended GUI tests to ensure correct course and student display.

---

#### **Documentation**

* Updated `UiClassDiagram` and Developer Guide to include new course UI elements ([#103](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/103))
* Contributed to the About Us page and team documentation ([#24](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/24))
* Reviewed and updated the User Guide and Developer Guide ([#168](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/168))

---

#### **Community**

* Reviewed and merged multiple pull requests (e.g. [#91](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/91), [#80](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/80))
* Collaborated with teammates to resolve integration conflicts between branches (`view-students`, `update-ui`)
* Provided feedback on UI consistency and command naming conventions

---

#### **Project Management**

* Managed several feature PRs and coordinated merge sequences for UI-related components
* Maintained a consistent code style across multiple modules through Checkstyle fixes and formatting commits

---

#### **Code Contributed**

* [View on RepoSense Dashboard](https://nus-cs2103-ay2526s1.github.io/tp-dashboard/#/widget/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-09-19T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=reineoeq&tabRepo=AY2526S1-CS2103T-T13-4%2Ftp%5Bmaster%5D)

---

