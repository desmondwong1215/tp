---
layout: default.md
title: "Yu Hang's Project Portfolio Page"
---

### Project: EduBase 

EduBase is a desktop address book application used for managing student attendance. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

---

* **New Features**

* **Added the course model functionality**
    * **What it does:** Base model class for Course. 
    * **Justification:** Course class will contain information like student list 
    * **Pull Requests:** [\#44](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/44)

* **Added `add_student` commands**
    * **What it does:** Enables users add students to a specified course.
    * **Justification:** Allows users to track which students belong to which course. 
    * **Highlights:** Wrote both command and parser classes while requiring integration between course and person classes.
    * **Pull Requests:** [\#74](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/74)[#81](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/81)

* **Added `view_course_details` commands**
    * **What it does:** Displays students in a specified course. 
    * **Justification:** Users can view which students are part of which class.
    * **Highlights:** Used defensive programming principles to prevent null parameters
    * **Pull Requests:** [\#89](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/89)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2526s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-09-19T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=gnahuy123&tabRepo=AY2526S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v.1.1` and `v.1.2` (2 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `edit_student` and `edit_course` [\#186](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/186)
  * Developer Guide:
    * Added UML diagram for `model` [\#94](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/94)
    
* **Community**
  * PRs reviewed (with non-trivial review comments): [\#83](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/83), [\#90](https://github.com/AY2526S1-CS2103T-T13-4/tp/pull/90)

* **Tools**:
  * Integrated CodeCov into team repo.
---