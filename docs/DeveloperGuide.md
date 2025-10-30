---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# EduBase Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deregister S00001`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `CourseListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete_course C0001")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete_course C0001` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCourseCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `MainParser` object which in turn creates a parser that matches the command (e.g., `DeleteCourseCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCourseCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `MainParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `RegisterCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `RegisterCommand`) which the `MainParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `RegisterCommandParser`, `DeleteCourseCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the course book data i.e., all `Course` objects (which are contained in a `CourseList` object).
* stores the currently 'selected' `Person`, `Course` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>`, `ObservableList<Course>` respectively that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save course book data, address book data, and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `CourseBookStorage`, `AddressBookStorage`, and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

### \[Proposed\] Add tag to courses

### Proposed Feature — Tags for Courses

#### Motivation
Currently, each course can only store fixed information such as course name, code, and number of students enrolled. However, users may wish to add **custom remarks** — for example, the **name of the teacher-in-charge**, **difficulty level**, or **semester offered**.  
A flexible **tagging system** allows users to attach personalized tags to courses without altering the base data model.

#### User Stories
| Version | As a ...          | I want to ... | So that I can ... |
|----------|-------------------|----------------|-------------------|
| v1.3 | busy teacher      | add tags to my courses | remember who teaches each module |
| v1.3 | teacher           | remove tags from a course | keep my course list tidy |
| v1.4 | organized teacher | list all tags | quickly find courses with certain remarks |
| v1.4 | advanced teacher  | edit a tag | update outdated information |

---

#### Proposed Implementation

The `Tag` feature introduces a `Tag` class that represents a single tag, and integrates it into the `Course` model as a `Set<Tag>`.

Each course stores its own independent tag list. Tags can be added or removed through dedicated commands like `addtag` and `removetag`.

##### Class Structure

The following class diagram shows the key relationships among `Course`, `Tag`, and `AddTagCommand`:

![](images/TagClassDiagram.png)

##### Sequence of Operations

When a user executes the `addtag` command — e.g.,

`addtag 1 t/Dr Tan teaches this course`

the following steps occur:

1. The `LogicManager` receives the command text and passes it to the `CourseBookParser`.
2. The parser creates an `AddTagCommand` object with the parsed `Index` and `Tag`.
3. The `AddTagCommand` retrieves the specified course from the model using its index.
4. The command creates a new `Course` object with the updated tag set.
5. The `Model` replaces the old course with the new course in its course list.
6. The UI updates to reflect the change.

The following sequence diagram illustrates this process:

![](images/AddTagSequenceDiagram.png)

---

#### Example

**Command:**

`addtag 2 t/Dr Tan teaches this course`

**Expected behavior:**  
Adds a new tag to the course in position 2. The course now displays: CS2103T Software Engineering [Tags: Dr Tan teaches this course]

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

Target users are **CLI Power Users** (Teachers) who:
* **Are Proficient Typists**: The application MUST prioritize keyboard speed and command-line efficiency.
* **Prefer CLI**: Usage must minimize or eliminate the need for mouse interaction, focusing on keyboard navigation and muscle memory.
* **Handle High Volume**: Require rapid tools for tasks like bulk student management and attendance logging.

**Value proposition**:

* **EduBase** maximizes administrative workflow speed for expert users by providing a **Typing-Optimized CLI**.
* **Typing-Optimized Efficiency**: Enables faster task completion (e.g., marking attendance) compared to navigating complex GUI forms.
* **Keyboard-Driven Workflow**: Full system functionality is achieved solely through commands, eliminating GUI dependency.
* **Direct Access**: Provides immediate access to all critical educational administration functions.


### User Stories

The following user stories are derived from the project's feature list, grouped by their level of operation (School Level being foundational, Course Level being management actions within a class).

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

### School Level Stories
| Priority | As a …​ | I want to …​                                      | So that I can…​                                |
|----------|---------|---------------------------------------------------|------------------------------------------------|
| `* * *`  | teacher | create a new course                               | manage a new subject or class offering.        |
| `* * *`  | user    | view a list of all existing courses               | see the school's full course catalog.          |
| `* * *`  | teacher | delete an existing course                         | remove outdated or cancelled offerings         |
| `* * *`  | teacher | register a new student with their name and gender | add them to the school's central address book. |
| `* * *`  | teacher | deregister an student                             | delete them from the school's address book.    |
| `* * *`  | teacher | enter a specifc course                            | perform course-level management commands.      |

### Course Level Stories
| Priority | As a …​ | I want to …​                                                                    | So that I can…​                                                   |
|----------|---------|---------------------------------------------------------------------------------|-------------------------------------------------------------------|
| `* * *`  | teacher | add an already-created student to a specified course                            | enroll them in my class roster.                                   |
| `* * *`  | teacher | remove a student from a specific course                                         | update my class roster.                                           |
| `* * *`  | teacher | create a session for a given date                                               | establish a new attendance record for all students in the course. |
| `* * *`  | teacher | mark or unmark a student's attendance for a session.                            | accurately record their presence or absence.                      |
| `* * *`  | teacher | view a student's attendance record or the full class attendance on a given date | check historical records.                                         |
| `* * *`  | teacher | exit the current course                                                         | return to school-level commands.                                  |

### Use cases

(For all use cases below, the **System** is the EduBase (EB) and the **Actor** is the Teacher, unless specified otherwise)

**Use case: UC01 - Create Course**

**MSS:**

1.  Teacher commands to create a new course with a course name and a course id.
2.  EB validates the course id.
3.  EB creates the course with indicated course name and course id.
4.  EB indicates success.

    Use case ends.

**Extensions**

* 2a. EB detects invalid data (e.g., course id is used, course id is in an invalid format or missing required parameter) and shows error message.

  Use case ends.

**Use case: UC02 - View All Courses**

**MSS:**

1.  Teacher commands to view all existing courses.
2.  EB retrieves the list of all created courses.
3.  EB displays a formatted list showing each course's id and name.

    Use case ends.

**Extensions**

* 2a. EB detects that no courses have been created yet.

    * 2a1. EB Shows empty list.
      Use case ends.

      Use case ends.

**Use case: UC03 - Delete Course**

**MSS:**

1.  Teacher commands to delete course with a course id.
2.  EB checks if the course has any enrolled students.
3.  EB deletes the course and indicates success.

    Use case ends.

**Extensions**

* 1a. EB detects that the course id is not found or is in an invalid format and then shows error.

  Use case ends.

* 2a. EB detects that the course has enrolled students.

    * 4a1. EB prompts teacher to remove all existing students from the course before deleting the course.

      Use case ends.

**Use case: UC04 - Register New Student**

**MSS:**

1.  Teacher commands to register a new student with all the student details.
2.  EB validates the student details.
3.  EB assigns a unique STUDENT_ID and adds the student to the address book and indicates success.

    Use case ends.

**Extensions**

* 2a. EB detects the name containing digits or special symbols and shows an error on invalid name.

  Use case ends.

**Use case: UC05 - Deregister Student**

**MSS:**

1.  Teacher commands to deregister a student from the address book with a valid student id.
2.  EB checks if the student is currently enrolled in any courses.
3.  EB removes the student from the address book database.
4.  EB displays a success message.

    Use case ends.

**Extensions**

* 1a. EB detects an invalid format for STUDENT_ID or an ID that does not exist and shows error.

  Use case ends.

* 2a.  EB detects that the student is still enrolled in one or more courses and shows error.

  Use case ends.

**Use case: UC06 - Find Student by Id**

**MSS:**

1.  Teacher commands to enter a find student with STUDENT_ID.
2.  EB retrieves the list of filtered students.
3.  EB displays a formatted list showing each student's details.

    Use case ends.

**Extensions**

* 1a.  EB detects any STUDENT_ID in an invalid format or missing arguments and shows error.

  Use case ends.

**Use case: UC07 - Edit Course**

**MSS:**

1.  Teacher commands to edit the course with new course name or new course id. Teacher need to select the course by indicating its index used in the full displayed course list.
2.  EB edits the details of the course selected.
3.  EB shows a success message and displays the edited course.

    Use case ends.

**Extensions**

* 1a.  EB detects invalid index, no new detail is provided, new course id is in an invalid format or new course id is used by other course and shows error.

  Use case ends.

**Use case: UC08 - Add Student To Course**

**MSS:**

1.  Teacher commands to add a student to the current course with a valid student ID.
2.  EB checks if the student is already in the course.
3.  EB adds the student to the course roster.
4.  EB displays a success message.

    Use case ends.

**Extensions**

* 2a. EB detects an invalid format for STUDENT_ID, STUDENT_ID does not exist in the address book and shows an error message.

  Use case ends.

* 3a. EB detects that the student is already enrolled in the course shows an error stating that the student is in the course

  Use case ends.

**Use case: UC09 -  Remove Student From Course**

**MSS:**

1.  Teacher commands to remove student from course command with a STUDENT_ID.
2.  EB checks the validity of the STUDENT_ID.
3.  EB checks if the student is currently enrolled in the course.
4.  EB removes the student from the course roster.
5.  EB displays a success message confirming the removal.

    Use case ends.

**Extensions**

* 2a. EB detects the invalid format of student id and displays the appropriate error message.

  Use case ends.

* 3a. EB detects the student is not enrolled in the course and displays an error message.

  Use case ends.

**Use case: UC10 -  Find Student By Name**

**MSS:**

1.  Teacher commands to find students with names.
2.  EB finds the students whose name contain any names provided.
3.  EB displays a list of filtered students and show appropriate message.

    Use case ends.

**Extensions**

* 2a. EB detects no name is provided and show error message.

  Use case ends.

**Use case: UC11 -  Find Students By STUDENT_ID**

**MSS:**

1.  Teacher commands to find students with STUDENT_IDs.
2.  EB checks the validity of all the STUDENT_IDs provided.
3.  EB finds the students with these STUDENT_IDs.
4.  EB displays a list of filtered students and appropriate message.

    Use case ends.

**Extensions**

* 2a. EB detects that no STUDENT_ID is provided or there exists invalid STUDENT_IDs and shows error message.
  
  Use case ends.

**Use case: UC12 -  Find Course By Name**

**MSS:**

1.  Teacher commands to find courses with names.
2.  EB finds the courses whose name contain any names provided.
3.  EB displays a list of filtered courses and show appropriate message.

    Use case ends.

**Extensions**

* 2a. EB detects no name is provided and show error message.

  Use case ends.

**Use case: UC13 -  Edit Course**

**MSS:**

1.  Teacher commands to find edit course.
2.  EB changes the detail of the course.
3.  EB displays the course and show appropriate message.

    Use case ends.

**Extensions**

* 1a. EB detects no field value is provided, invalid index is provided or this action will produce duplicate course and show error message.

  Use case ends.

**Use case: UC14 -  Edit Student**

**MSS:**

1.  Teacher commands to find edit student.
2.  EB changes the detail of the student.
3.  EB displays the student and show appropriate message.

    Use case ends.

**Extensions**

* 1a. EB detects no field value is provided, invalid index is provided or this action will produce duplicate student and show error message.

  Use case ends.

**Use case: UC15 -  Clear the storage**

**MSS:**

1.  Teacher commands to clear the storage.
2.  EB deletes all students and courses and displays empty list of student, course and appropriate success message.

    Use case ends.

**Extensions**

* 1a. EB detects the storage is empty and show appropriate message.

  Use case ends.

### Non-Functional Requirements

1.  All commands should execute and return results within 1 second on a standard system with at least 8GB RAM and a 2.0 GHz processor.
2.  The system should support viewing attendance records for up to **100 students in a course** without exceeding **2 seconds** of response time.
3.  The system should be fully operable **using only keyboard input**, with no requirement for mouse or GUI interaction.
4.  The system should provide **clear and specific error messages** for invalid inputs.
5.  The system shall persist all data updates (courses, students, sessions, attendance) to storage immediately, ensuring **no data loss** if the application closes unexpectedly.
6.  Data must persist across sessions (saved to storage on each update).
7.  The system shall support up to **50 concurrent courses** and **1000 registered students** without measurable degradation in performance.
8.  The system shall run on **Windows, macOS, and Linux command-line environments** without requiring OS-specific changes to commands.
9.  The system shall ensure that no two entities (courses, students, sessions) share the same ID by enforcing **unique identifier generation**.

### Glossary

* **EB**: shortform for EduBase.
* **CLI**: A text-based system for interacting with a computer, where users type commands to execute programs or perform tasks.
* **Teacher**: Target user for EduBase. Responsible for creating courses and sessions, and managing students.
* **Student**: Contains details such as student_ID, name, gender and parents contact number.
* **Course ID**: Unique identifier automatically generated for each course (e.g., `C0001`).
* **Student ID**: Unique identifier automatically generated for each student (e.g., `S00001`).
* **Session**: An attendance record created for a course on a given date.
* **Session ID**: Unique identifier assigned to each session within a course (e.g., `1`).
* **Attendance Record**: The record of whether a student is present or absent in a given session.
* **Register Student**: The action of adding a new student to the school database.
* **Deregister Student**: The action of permanently removing a student from the school database.
* **Add Student to Course**: The action of enrolling a student (who is already registered) into a specific course.
* **Remove Student from Course**: The action of unenrolling a student from a specific course.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
