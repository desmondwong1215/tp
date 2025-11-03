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

[_AB3_](https://github.com/nus-cs2103-AY2526S1/tp).

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

**API** : [`Logic.java`](https://github.com/AY2526S1-CS2103T-T13-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

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
**API** : [`Model.java`](https://github.com/AY2526S1-CS2103T-T13-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the course book data i.e., all `Course` objects (which are contained in a `CourseList` object).
* stores the currently 'selected' `Person`, `Course` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>`, `ObservableList<Course>` respectively that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2526S1-CS2103T-T13-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save course book data, address book data, and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `CourseBookStorage`, `AddressBookStorage`, and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Proposed enhancements**

This section describes some noteworthy details on proposed future enhancements.

### \[Proposed\] Add tag to courses

### Proposed Feature — Tags for Courses

#### Motivation
Currently, each course can only store fixed information such as course name, code, and number of students enrolled. However, users may wish to add **custom remarks** — for example, the **name of the teacher-in-charge**, **difficulty level**, or **semester offered**.
A flexible **tagging system** allows users to attach personalized tags to courses without altering the base data model.

#### User Stories
| Version | As a ...                  | I want to ...             | So that I can ...                         |
|---------|---------------------------|---------------------------|-------------------------------------------|
| v1.3    | busy tuition teacher      | add tags to my classes    | remember the location of this lesson      |
| v1.3    | tuition teacher           | remove tags from a course | keep my course list tidy                  |
| v1.4    | organized tuition teacher | list all tags             | quickly find classes with certain remarks |
| v1.4    | tuition teacher           | edit a tag                | update outdated information               |

---

#### Proposed Implementation

The `Tag` feature introduces a `Tag` class that represents a single tag, and integrates it into the `Course` model as a `Set<Tag>`.

Each course stores its own independent tag list. Tags can be added or removed through dedicated commands like `addtag` and `removetag`.

##### Class Structure

The following class diagram shows the key relationships among `Course` and `Tag`:

<puml src="diagrams/CourseClassDiagram.puml" alt="CourseClassDiagram" />

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

---

#### Example

**Command:**

`addtag 2 t/Dr Tan teaches this course`

**Expected behavior:**
Adds a new tag to the course in position 2. The course now displays: CS2103 Software Engineering [Tags: Dr Tan teaches this course]

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

Target users are **Private Tuition Center Owners/Operators** who:

* **Run Their Center Solo or With Minimal Staff**:
    - Single owner-operator managing all administrative tasks
    - Recently scaled from 1-2 classes to 10+ classes
    - Managing 50-500 students across multiple subjects and levels

* **Transitioned From Manual/Spreadsheet Management**:
    - Previously used Excel/Google Sheets or paper records
    - Finding spreadsheets increasingly unwieldy with growth
    - Need faster access to student/class information during busy periods

* **Are Comfortable With Typing**:
    - Prefer keyboard-based workflows over mouse clicking
    - Can type commands faster than navigating GUI menus
    - Value efficiency and speed in administrative tasks

* **Need Quick Information Retrieval**:
    - Frequently need to look up parent contact numbers
    - Must quickly check class rosters before sessions
    - Handle enrollment inquiries on the spot
    - Need to verify which courses a student attends

**Value proposition**:

* **EduBase** is purpose-built for **solo tuition center operators who've scaled beyond spreadsheets** but don't need (or can't afford) expensive center management software.

* **Faster Than Spreadsheets**:
    - No more scrolling through multiple tabs to find a student
    - Commands like `find_student_by_name Alice` retrieve information instantly
    - Enrolling students takes seconds: `add_student S00001 C0001`

* **Designed for Solo Operation**:
    - All essential center management in one lightweight app
    - No cloud subscriptions or per-student fees
    - Works offline—no internet dependency

* **Handles Growth Gracefully**:
    - Manage up to 10,000 students and 50 courses (far beyond typical tuition center needs)
    - Quick searches even with hundreds of students
    - Simple enough to use during parent phone calls or between classes

* **Lower Barrier Than Enterprise Solutions**:
    - Free and open-source vs. expensive subscription software
    - Lightweight installation vs. complex setups
    - Command-line efficiency vs. cluttered GUI interfaces
    - Perfect for tech-comfortable owners who value speed over visual polish


### User Stories

The following user stories are derived from the project's feature list, grouped by their level of operation (School Level being foundational, Course Level being management actions within a class).

Priorities: 
- `***` (High): Core features required for v1.0 release (basic CRUD operations)
- `**` (Medium): Usability features for v1.1 (search, help)
- `*` (Low): Nice-to-have features for v1.2+ (advanced queries, tags)

| Priority | As a …                       | I want to …                                      | So that I can…                                                   |
|----------|------------------------------|--------------------------------------------------|------------------------------------------------------------------|
| `***`    | tuition center owner         | create a new course                              | add a new class when I expand my tuition offerings               |
| `***`    | tuition center owner         | view a list of all existing courses              | see all my classes at a glance                                   |
| `***`    | tuition center owner         | view detailed information of a course            | check which students are enrolled before class starts            |
| `***`    | tuition center owner         | register a new student with their parent's phone | add them when parents enquire about enrollment                   |
| `***`    | tuition center owner         | find a student by name quickly                   | retrieve parent contact information during phone calls           |
| `***`    | tuition center owner         | add an existing student to a specific course     | enroll them when parents sign up for additional classes          |
| `***`    | tuition center owner         | remove a student from a specific course          | process withdrawals when students drop a class                   |
| `***`    | tuition center owner         | see student count per course                     | know if classes are full or have vacancies                       |
| `**`     | tuition center owner         | find courses by name                             | quickly locate a specific class among my many offerings          |
| `**`     | busy tuition center owner    | edit a student's details                         | update parent contact numbers when they change phones            |
| `**`     | tuition center owner         | deregister a student                             | remove them from my system when they leave the center entirely   |
| `**`     | tuition center owner         | use a help command                               | remind myself of command syntax when I forget                    |
| `**`     | solo tuition center operator | quickly access parent phone numbers              | contact them for urgent matters or payment reminders             |
| `**`     | tuition center owner         | search students by ID                            | look up details when parents reference their child's student ID  |
| `*`      | growing tuition center       | clear all existing data                          | reset the system at the start of a new academic year             |
| `*`      | tuition center owner         | view both students and courses together          | get a complete overview of my center's current state             |
| `*`      | growing tuition center owner | see which courses a specific student takes       | provide parents with accurate information about their enrollment |

### Use cases

A **use case** describes an interaction between a user (or 'actor') and the system for a specific functionality. It is a description of a set of action sequences that the system performs to provide an observable result of value to the actor. Use cases are used to capture the functional requirements of the system.

(For all use cases below, the **System** is `EduBase (EB)` and the **Actor** is the `Tuition Center Owner/Operator`, unless specified otherwise)

**Actor Profile**: A solo tuition center owner managing multiple classes and students, who needs quick access to enrollment and contact information during day-to-day center operations.

---

**Use case: UC01 - Create Course**

**MSS:**
1. Tuition center owner commands to create a new course with a course name (e.g., "Primary 5 Math") and a course ID.
2. EB creates the course with the indicated course name and ID.
3. EB displays the new course in the course list and indicates success.

   Use case ends.

**Extensions:**
* 2a. EB detects invalid data (e.g., course ID is already used by another class, course ID is in an invalid format, or a required parameter is missing).
    * 2a1. EB shows an error message explaining the issue.

      Use case ends.

---

**Use case: UC02 - View All Courses**

**MSS:**
1. Tuition center owner commands to view all existing courses.
2. EB retrieves and displays a formatted list of all classes, showing each course's ID, name, and number of enrolled students.

   Use case ends.

**Extensions:**
* 2a. EB detects that no courses have been created.
    * 2a1. EB shows an empty list or a "no courses found" message.

      Use case ends.

---

**Use case: UC03 - Delete Course**

**Preconditions:**
* The target course (identified by course ID) exists in the center's course list.

**MSS:**
1. Tuition center owner commands to delete a course using its course ID (e.g., to remove a discontinued class).
2. EB deletes the course from the system.
3. EB indicates success and updates the course list display.

   Use case ends.

**Extensions:**
* 1a. EB detects that the course ID is in an invalid format or does not exist.
    * 1a1. EB shows an error message.

      Use case ends.

---

**Use case: UC04 - Register New Student**

**MSS:**
1. Tuition center owner receives an enrollment inquiry from a parent and commands to register the new student with all required details (name, parent's phone number, gender).
2. EB registers the student, assigns a unique student ID, and adds the student to the center's database.
3. EB displays the new student ID and indicates success.

   Use case ends.

**Extensions:**
* 2a. EB detects invalid data (e.g., the name contains digits or special symbols, phone number is too short).
    * 2a1. EB shows an error message about the invalid data.

      Use case ends.

---

**Use case: UC05 - Deregister Student**

**Preconditions:**
* The target student (identified by student ID) exists in the center's database.
* The student is not currently enrolled in any courses.

**MSS:**
1. Tuition center owner commands to deregister a student from the center's database using a valid student ID (e.g., when a student permanently leaves the tuition center).
2. EB removes the student from the database.
3. EB displays a success message.

   Use case ends.

**Extensions:**
* 1a. EB detects an invalid format for the student ID or an ID that does not exist.
    * 1a1. EB shows an error message.

      Use case ends.

* 1b. EB detects the student is still enrolled in one or more courses.
    * 1b1. EB shows an error message prompting the owner to remove the student from all courses before deregistering.

      Use case ends.

---

**Use case: UC06 - Find Student by Student ID**

**Preconditions:**
* One or more students are registered in the center's database.

**MSS:**
1. Tuition center owner commands to find one or more students using their student IDs (e.g., when a parent calls and mentions their child's student ID).
2. EB finds the students matching the provided student IDs.
3. EB displays a filtered list of the found students with their details (name, phone, gender, enrolled courses).

   Use case ends.

**Extensions:**
* 1a. EB detects that no student ID was provided or that one or more IDs are in an invalid format.
    * 1a1. EB shows an error message.

      Use case ends.

* 2a. EB finds no students matching the provided IDs.
    * 2a1. EB displays an empty list or a "no students found" message.

      Use case ends.

---

**Use case: UC07 - Edit Course**

**Preconditions:**
* The target course (identified by its index in the displayed list) exists.

**MSS:**
1. Tuition center owner commands to edit a course (selected by its index in the current view) with a new course name and/or new course ID (e.g., to update "P5 Math" to "Primary 5 Mathematics 2025").
2. EB updates the details of the selected course.
3. EB shows a success message and displays the edited course in the course list.

   Use case ends.

**Extensions:**
* 1a. EB detects an invalid index (e.g., out of bounds) or that no new details were provided.
    * 1a1. EB shows an error message.

      Use case ends.

* 2a. EB detects that the new course ID is in an invalid format or is already used by another course.
    * 2a1. EB shows an error message.

      Use case ends.

* 2b. EB detects that the name is in an invalid format.
    * 2b1. EB shows an error message.

      Use case ends.

---

**Use case: UC08 - Add Student To Course**

**Preconditions:**
* The parent has confirmed enrollment in a specific class/course.
* The target student (identified by student ID) is registered in the center's database.
* The target course exists in the center's course list.

**MSS:**
1. Tuition center owner commands to add a student to a specific course using the student ID and course ID.
2. EB adds the student to the course roster and increments the class enrollment count.
3. EB displays a success message and shows the updated course details.

   Use case ends.

**Extensions:**
* 1a. EB detects an invalid format for the student ID or course ID.
    * 1a1. EB shows an error message.

      Use case ends.

* 1b. EB detects that the student ID does not exist in the center's database.
    * 1b1. EB shows an error message indicating the student must be registered first.

      Use case ends.

* 2a. EB detects that the student is already enrolled in the course.
    * 2a1. EB shows an error stating that the student is already in the course.

      Use case ends.

---

**Use case: UC09 - Remove Student From Course**

**Preconditions:**
* The target student (identified by student ID) is enrolled in the target course.

**MSS:**
1. Tuition center owner commands to remove a student from a course using the student ID and course ID (e.g., when a parent withdraws their child from a specific class).
2. EB removes the student from the course roster and decrements the class enrollment count.
3. EB displays a success message confirming the removal.

   Use case ends.

**Extensions:**
* 1a. EB detects an invalid format for the student ID or course ID.
    * 1a1. EB displays an error message.

      Use case ends.

* 2a. EB detects the student is not enrolled in this course (or the student ID does not exist).
    * 2a1. EB displays an error message.

      Use case ends.

---

**Use case: UC10 - Find Student By Name**

**Preconditions:**
* One or more students are registered in the center's database.

**MSS:**
1. Tuition center owner commands to find students using one or more name keywords (e.g., when a parent calls asking about "Alice" but the owner doesn't remember the full name or student ID).
2. EB finds the students whose names contain any of the provided keywords.
3. EB displays a filtered list of the found students with their details.

   Use case ends.

**Extensions:**
* 1a. EB detects no name keyword was provided.
    * 1a1. EB shows an error message.

      Use case ends.

* 2a. EB finds no students matching the provided keywords.
    * 2a1. EB displays an empty list or a "no students found" message.

      Use case ends.

---

**Use case: UC11 - Find Course By Name**

**Preconditions:**
* One or more courses exist in the center's course list.

**MSS:**
1. Tuition center owner commands to find courses using one or more name keywords (e.g., to quickly locate all "Math" classes or all "Primary 5" classes).
2. EB finds the courses whose names contain any of the provided keywords.
3. EB displays a filtered list of the found courses with enrollment counts.

   Use case ends.

**Extensions:**
* 1a. EB detects no name keyword was provided.
    * 1a1. EB shows an error message.

      Use case ends.

* 2a. EB finds no courses matching the provided keywords.
    * 2a1. EB displays an empty list or a "no courses found" message.

      Use case ends.

---

**Use case: UC12 - Edit Student**

**Preconditions:**
* The target student (identified by index) exists in the displayed list.

**MSS:**
1. Tuition center owner commands to edit a student (selected by index in the current view) with new details (e.g., updated phone number, corrected name spelling, updated gender).
2. EB updates the details of the selected student.
3. EB displays the edited student information and shows a success message.

   Use case ends.

**Extensions:**
* 1a. EB detects an invalid index or that no new field values were provided.
    * 1a1. EB shows an error message.

      Use case ends.

---

**Use case: UC13 - Clear the Storage**

**MSS:**
1. Tuition center owner commands to clear the storage (e.g., at the start of a new academic year to reset all data).
2. EB deletes all students and courses from the system.
3. EB displays empty lists of students and courses and shows a success message.

   Use case ends.

**Extensions:**

* 1a. EB detects the storage is already empty.
    * 1a1. EB shows a message indicating the storage is already empty.

      Use case ends.

---

### Non-Functional Requirements

🚀 **Performance**
1. All commands should execute and return results within **1 second** on a standard system with at least 8GB RAM and a 2.0 GHz processor, ensuring the owner can quickly respond to parent inquiries without delays.

2. The system should support viewing of up to **1000 students in a course** without exceeding **2 seconds** of response time (though typical tuition center classes have 10-50 students).

3. The system shall support up to **50 concurrent courses** and **10,000 registered students** without measurable degradation in performance, accommodating growth beyond typical tuition center needs.

⌨️ **Usability**
1. The system should be fully operable **using only keyboard input**, with no requirement for mouse or GUI interaction, allowing the owner to work efficiently during phone calls with parents.

2. The system should provide **clear and specific error messages** for invalid inputs, including:
    - What went wrong (e.g., "Invalid Student ID format")
    - Expected format (e.g., "Expected format: SXXXXX")
    - Example (e.g., "Example: S00001")

3. The system should be **learnable within 30 minutes** for a tuition center owner with basic command-line familiarity, with common commands becoming muscle memory after a week of regular use.

💾 **Reliability & Data Persistence**
1. The system shall persist all data updates (courses, students, enrollments) to storage **immediately after each command**, ensuring **no data loss** if the application closes unexpectedly during busy enrollment periods.

2. Data must persist across sessions (saved to storage on each update) so the owner can safely close and reopen the application between teaching sessions.

3. The system shall ensure that no two entities (courses, students) share the same ID by enforcing **unique identifier generation**, preventing confusion when parents reference student or course IDs.

4. The system shall maintain data integrity when enrolling/removing students from courses, ensuring enrollment counts remain accurate.

💻 **Portability**
1. The system shall run on **Windows, macOS, and Linux command-line environments** without requiring OS-specific changes to commands, as tuition center owners may use different operating systems.

2. The system shall be deployable as a **single JAR file** requiring only Java 17+, with no complex installation procedures or external dependencies.

🏫 **Tuition Center Operations**
1. The system shall be usable by a **single operator** without requiring technical support or IT staff, as tuition center owners typically manage all administrative tasks independently.

2. The system shall operate **fully offline**, as tuition centers may not have reliable internet connectivity during class hours or may prefer to keep student data local for privacy reasons.

3. The system shall support **quick lookups during phone calls** with parents—all search commands must return results in under **1 second** to maintain conversational flow.

4. The system shall **remain responsive** when accessed between back-to-back classes, with no startup delays if the app stays open all day (startup time < 5 seconds if reopened).

5. The system shall handle **typical tuition center scale**:
    - 5-50 courses (covering different subjects and levels)
    - 50-500 students (typical range for solo-operated centers)
    - Up to 50 students per class
    - Performance should remain optimal even at maximum scale

6. The system shall allow **rapid enrollment processing** during peak enrollment periods (e.g., start of term), with batch operations completable in under 30 seconds for a class of 20 students.

### Glossary

* **EB**: Shortform for EduBase.

* **CLI (Command Line Interface)**: A text-based system for interacting with a computer, where users type commands to execute programs or perform tasks.

* **Tuition Center Owner/Operator**: Target user for EduBase. A private tuition business owner who manages enrollment, classes, and student records, typically operating solo or with minimal administrative staff.

* **Student**: An individual enrolled in the tuition center. Contains details such as Student ID, name, gender, and parent's contact number.

* **Course**: Represents a tuition class (e.g., "Primary 5 Math", "Secondary 3 English"). Each course has a unique Course ID and can enroll multiple students.

* **Course ID**: Unique identifier automatically generated for each course (format: CXXXX, e.g., `C0001`, `C0152`).

* **Student ID**: Unique identifier automatically generated for each student (format: SXXXXX, e.g., `S00001`, `S00234`). Parents can reference this ID when communicating with the tuition center.

* **Register Student**: The action of adding a new student to the tuition center's central database, typically done when parents first enroll their child.

* **Deregister Student**: The action of permanently removing a student from the tuition center's database, typically done when a student leaves the center entirely.

* **Add Student to Course**: The action of enrolling an already-registered student into a specific course/class (e.g., when parents sign up their child for a new subject).

* **Remove Student from Course**: The action of unenrolling a student from a specific course/class (e.g., when parents withdraw their child from a subject).

* **Enrollment**: The relationship between a student and a course, indicating the student is attending that class.

* **Parent Contact Number**: The phone number stored under each student's profile, used for communication regarding enrollment, payment, class schedules, or urgent matters.

* **Solo Operator**: A tuition center owner who handles all administrative tasks (enrollment, class management, parent communication) without dedicated administrative staff.

* **Class Roster**: The list of students enrolled in a specific course, viewable via the `view_course_details` command.

* **Center Database**: EduBase's storage system containing all student registrations and course enrollments for the tuition center, stored locally in JSON format.

* **Index**: The position number of an item (student or course) in the currently displayed list, starting from 1. Used in commands like `edit_student` and `edit_course`.

* **Filter**: A temporary view showing only students or courses matching certain search criteria (e.g., after using `find_student_by_name`). The filter can be cleared by using the `list` command.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually from the perspective of a tuition center owner.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder designated for your tuition center data.

    2. Double-click the jar file. Expected: Shows the GUI with a set of sample students and courses representing a typical tuition center setup. The window size may not be optimum.

2. Saving window preferences

    1. Resize the window to an optimum size for your workspace. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.


### Registering and deregistering students

1. Registering a new student (simulating a parent enrollment)

    1. Prerequisites: The tuition center database is initialized (use `list` to view current students).

    2. Test case: `register n/Alice Tan p/91234567 g/Female`<br>
       Expected: A new student is added to the student list with an auto-generated Student ID (e.g., S00001). Success message shows the student's details and ID. Parent can be informed of this ID for future reference.

    3. Test case: `register n/Bob Lee p/98765432 g/Male`<br>
       Expected: Another student is added with a different Student ID (e.g., S00002).

    4. Test case: `register n/Charlie Tan p/12 g/Male`<br>
       Expected: No student is registered. Error message indicates that phone numbers must be between 3-20 digits.

2. Deregistering a student (simulating a student leaving the tuition center)

    1. Prerequisites: List all students using the `list` command. At least one student exists (e.g., S00001). 

    2. Test case: `deregister S00001`<br>
       Expected: Student S00001 is deleted from the database. Success message shows details of the deleted student.

    3. Test case: `deregister S00001` (repeating the same command)<br>
       Expected: No student is deleted. Error message indicates that the student ID does not exist.

    4. Test case: `deregister S99999` (non-existent ID)<br>
       Expected: No student is deleted. Error message indicates that the student ID does not exist.

    5. Test case: `deregister INVALID` (invalid format)<br>
       Expected: No student is deleted. Error message indicates invalid Student ID format.

### Creating and managing courses

1. Creating a course (simulating adding a new class to the tuition center)

    1. Prerequisites: EduBase is initialized. Use `view_courses` to check existing courses.

    2. Test case: `create_course n/Primary 5 Math id/C0001`<br>
       Expected: A new course is created and displayed in the course list. Success message shows the course name and ID.

    3. Test case: `create_course n/Secondary 3 English id/C0002`<br>
       Expected: Another course is created with a different Course ID.

    4. Test case: `create_course n/Primary 6 Science id/C0001` (duplicate ID)<br>
       Expected: No course is created. Error message indicates that the course ID already exists.

    5. Test case: `create_course n/Math@ id/C0003` (invalid name)<br>
       Expected: No course is created. Error message indicates that course names can only contain alphanumeric characters and spaces.

2. Viewing course details (simulating checking class roster before a lesson)

    1. Prerequisites: At least one course exists (e.g., C0001) with some students enrolled. Use `create_course` and `add_student` to set this up if needed.

    2. Test case: `view_course_details C0001`<br>
       Expected: The system displays the course name, ID, and a list of all enrolled students with their details.

    3. Test case: `view_course_details C9999` (non-existent course)<br>
       Expected: Error message indicates that the course does not exist.

3. Editing a course (simulating updating class information)

    1. Prerequisites: Use `view_courses` to see all courses and their indices. At least one course exists.

    2. Test case: `edit_course 1 n/Primary 5 Advanced Math`<br>
       Expected: The first course in the list is updated with the new name. Success message shows the updated course details.

    3. Test case: `edit_course 1 id/C0055`<br>
       Expected: The first course's ID is updated to C0055 (provided this ID is not already in use).

    4. Test case: `edit_course 999 n/Some Course` (invalid index)<br>
       Expected: No course is edited. Error message indicates invalid index.

4. Deleting a course (simulating removing a discontinued class)

    1. Prerequisites: At least one course exists with NO students enrolled. Use `view_courses` to find the Course ID.

    2. Test case: `delete_course C0001`<br>
       Expected: Course C0001 is deleted. Success message confirms deletion.

    3. Test case: `delete_course C0001` (repeating the command)<br>
       Expected: No course is deleted. Error message indicates the course does not exist.


### Enrolling and unenrolling students (enrollment management)

1. Adding a student to a course (simulating parent enrolling in a class)

    1. Prerequisites: At least one registered student (e.g., S00001) and one course (e.g., C0001) exist. Student is NOT already enrolled in this course. Use `list` and `view_courses` to verify.

    2. Test case: `add_student S00001 C0001`<br>
       Expected: Student S00001 is enrolled in course C0001. Success message confirms enrollment. A filter is applied showing students in C0001. The student count for C0001 increments.

    3. Test case: `add_student S00001 C0001` (repeating the command)<br>
       Expected: No enrollment occurs. Error message indicates the student is already enrolled in this course.

    4. Test case: `add_student S99999 C0001` (non-existent student)<br>
       Expected: No enrollment occurs. Error message indicates the student does not exist.

    5. Test case: `add_student S00001 C9999` (non-existent course)<br>
       Expected: No enrollment occurs. Error message indicates the course does not exist.

2. Removing a student from a course (simulating withdrawal from a class)

    1. Prerequisites: Student S00001 is enrolled in course C0001. Verify using `view_course_details C0001`.

    2. Test case: `remove_student S00001 C0001`<br>
       Expected: Student S00001 is unenrolled from course C0001. Success message confirms removal. A filter is applied showing remaining students in C0001. The student count for C0001 decrements.

    3. Test case: `remove_student S00001 C0001` (repeating the command)<br>
       Expected: No action occurs. Error message indicates the student is not enrolled in this course.

    4. Test case: `remove_student S00002 C0001` (student not in this course)<br>
       Expected: No action occurs. Error message indicates the student is not enrolled in this course.

### Searching for students and courses

1. Finding students by name (simulating parent phone call)

    1. Prerequisites: Multiple students are registered with various names (e.g., "Alice Tan", "Bob Lee", "Alice Wong").

    2. Test case: `find_student_by_name Alice`<br>
       Expected: All students with "Alice" in their name are displayed (e.g., "Alice Tan", "Alice Wong").

    3. Test case: `find_student_by_name Alice Bob`<br>
       Expected: All students with either "Alice" OR "Bob" in their names are displayed.

    4. Test case: `find_student_by_name xyz` (no matches)<br>
       Expected: An empty list is displayed with a "no students found" message.

    5. Test case: `find_student_by_name` (no keywords provided)<br>
       Expected: Error message indicates that at least one name keyword is required.

2. Finding students by ID (simulating parent providing student ID)

    1. Prerequisites: Students with IDs S00001, S00002, S00003 exist.

    2. Test case: `find_student_by_id S00001`<br>
       Expected: Student S00001's details are displayed.

    3. Test case: `find_student_by_id S00001 S00002`<br>
       Expected: Both students S00001 and S00002 are displayed.

   4. Test case: `find_student_by_id S99999` (non-existent ID)<br>
      Expected: An empty list is displayed with a "no students found" message.

   5. Test case: `find_student_by_id INVALID` (invalid format)<br>
      Expected: Error message indicates invalid Student ID format.

3. Finding courses by name (simulating quick class lookup)

    1. Prerequisites: Multiple courses exist with various names (e.g., "Primary 5 Math", "Primary 6 Math", "Secondary 3 English").

    2. Test case: `find_course_by_name Math`<br>
       Expected: All courses with "Math" in their name are displayed (e.g., "Primary 5 Math", "Primary 6 Math").

    3. Test case: `find_course_by_name Primary English`<br>
       Expected: All courses with either "Primary" OR "English" in their names are displayed.

    4. Test case: `find_course_by_name xyz` (no matches)<br>
       Expected: An empty list is displayed with a "no courses found" message.

    5. Test case: `find_course_by_name` (no keywords provided)<br>
       Expected: Error message indicates that at least one name keyword is required.

### Editing student and course information

1. Editing a student (simulating updating parent contact information)

    1. Prerequisites: Use `list` to view all students and their indices. At least one student exists.

    2. Test case: `edit_student 1 p/87654321`<br>
       Expected: The first student's phone number is updated to 87654321. Success message shows the updated student details.

    3. Test case: `edit_student 1 n/Alice Tan Mei Ling g/Female`<br>
       Expected: The first student's name and gender are updated. Success message shows the updated details.

    4. Test case: `edit_student 999 p/91234567` (invalid index)<br>
       Expected: No student is edited. Error message indicates invalid index.

    5. Test case: `edit_student 1` (no fields provided)<br>
       Expected: No student is edited. Error message indicates that at least one field must be provided.

2. Editing a course (already covered in "Creating and managing courses" section above)

### Clearing all data

1. Clearing the database (simulating new academic year reset)

    1. Prerequisites: The database contains students and courses.

    2. Test case: `clear`<br>
       Expected: All students and courses are removed from the system. Success message confirms the database has been cleared. Both student and course lists display as empty.

    3. Test case: `clear` (when database is already empty, it does not contain any student and course)<br>
       Expected: A message which shows both address book and course book are empty will be displayed.

### Viewing and listing data

1. Viewing all data (simulating getting an overview of the center)

    1. Prerequisites: The database contains multiple students and courses.

    2. Test case: `list`<br>
       Expected: All students and courses are displayed in their respective panels. Any previously applied filters are cleared.

    3. Test case: After using `find_student_by_name Alice`, then `list`<br>
       Expected: The filter is removed and all students are shown again along with all courses.

2. Viewing all courses only

    1. Prerequisites: Multiple courses exist in the database.

    2. Test case: `view_courses`<br>
       Expected: All courses are displayed with their IDs, names, and student counts. The student panel remains unchanged.

### Saving data

1. Dealing with missing/corrupted data files

    1. Prerequisites: Close the app if it is running.

    2. Navigate to the data folder (located at `data/addressbook.json` and `data/coursebook.json`).

    3. Test case: Delete `addressbook.json` file<br>
        - Relaunch the app<br>
        - Expected: The app starts with sample student data. No error message is shown.

    4. Test case: Delete `coursebook.json` file<br>
        - Relaunch the app<br>
        - Expected: The app starts with sample course data. No error message is shown.

    5. Test case: Corrupt `addressbook.json` by adding invalid JSON syntax (e.g., remove a closing brace)<br>
        - Relaunch the app<br>
        - Expected: The app starts with empty student data or sample data. The corrupted file is ignored.

    6. Test case: Corrupt `coursebook.json` by modifying a course ID to an invalid format (e.g., "X0001" instead of "C0001")<br>
        - Relaunch the app<br>
        - Expected: The app starts but the corrupted course entry is not loaded. Valid courses are still displayed.

2. Data persistence verification

    1. Test case: Create a new course, then immediately close the app<br>
        - Relaunch the app<br>
        - Expected: The newly created course is still present in the course list.

    2. Test case: Register a new student, add them to a course, then close the app<br>
        - Relaunch the app<br>
        - Expected: The new student appears in the student list and is enrolled in the course.

    3. Test case: Edit a student's phone number, then close the app without using any other commands<br>
        - Relaunch the app<br>
        - Expected: The phone number change persists.

### Edge cases and stress testing

1. Testing with maximum data (simulating a large tuition center)

    1. Prerequisites: Start with an empty database.

    2. Test case: Register 100 students (can use a script or manual entry)<br>
       Expected: All students are registered successfully. The `list` command displays all students without significant delay (< 2 seconds).

    3. Test case: Create 20 courses<br>
       Expected: All courses are created successfully. The `view_courses` command displays all courses without significant delay.

    4. Test case: Add 50 students to a single course<br>
       Expected: All students are enrolled successfully. The `view_course_details` command displays all 50 students without significant delay.

    5. Test case: Use `find_student_by_name` with a common keyword that matches 30+ students<br>
       Expected: All matching students are displayed within 1 second.

2. Testing boundary conditions for IDs

    1. Test case: Register students until the Student ID reaches S99998<br>
        - Register one more student<br>
        - Expected: Student S99999 is created successfully.

    2. Test case: Attempt to register another student after reaching S99999<br>
       Expected: An error message indicates that the maximum number of students has been reached.


3. Testing concurrent operations (simulating rapid data entry during busy periods)

    1. Test case: Rapidly execute multiple commands in succession<br>
        - `register n/Student 1 p/91111111 g/Male`<br>
        - `register n/Student 2 p/92222222 g/Female`<br>
        - `create_course n/Test Course id/C0099`<br>
        - `add_student S00001 C0099`<br>
        - `list`<br>
          Expected: All commands execute successfully. Data integrity is maintained (student counts are accurate, no duplicate IDs).

    2. Test case: Add multiple students to the same course in rapid succession<br>
       Expected: All students are enrolled correctly. The course enrollment count increments accurately.

4. Testing special characters and long inputs

    1. Test case: `register n/Jean-Pierre O'Connor p/91234567 g/Male` (name with hyphen and apostrophe)<br>
       Expected: Error message indicates names can only contain alphanumeric characters and spaces (unless the system supports these characters).

    2. Test case: `register n/VeryLongNameWithExactlyFiftyCharactersInTotal p/91234567 g/Male` (exactly 50 characters)<br>
       Expected: Student is registered successfully if 50 is within the limit, or error message if it exceeds.

    3. Test case: `register n/NameThatExceedsFiftyCharacterLimitByBeingTooLong p/91234567 g/Male` (more than 50 characters)<br>
       Expected: Error message indicates the name is too long (maximum 50 characters).

    4. Test case: `create_course n/CourseName id/c0001` (lowercase course ID)<br>
       Expected: Error message indicates invalid Course ID format (must be uppercase 'C' followed by 4 digits).

    5. Test case: `register n/Test Student p/12345678901234567890 g/Male` (20-digit phone number)<br>
       Expected: Student is registered successfully (20 digits is within the valid range).

    6. Test case: `register n/Test Student p/123456789012345678901 g/Male` (21-digit phone number)<br>
       Expected: Error message indicates phone number is too long (maximum 20 digits).

### Help and exit commands

1. Using the help command

    1. Test case: `help`<br>
       Expected: A help window or message is displayed showing how to access the user guide or command documentation.

    2. Test case: `help extra parameters`<br>
       Expected: The extraneous parameters are ignored. Help is displayed normally.

2. Exiting the application

    1. Test case: `exit`<br>
       Expected: The application closes gracefully. All data is saved.

    2. Test case: `exit with extra parameters`<br>
       Expected: The extraneous parameters are ignored. The application closes normally.

    3. Test case: Make several data changes (register students, create courses), then `exit`<br>
        - Relaunch the app<br>
          Expected: All changes are persisted and visible upon relaunch.
       
