---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# EduBase User Guide

EduBase is a **desktop app for teachers to manage student attendance, optimized for use via a Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, EduBase can get your student management tasks done faster than traditional GUI apps.

<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
   Some example commands you can try:

* `list` : (Example from original template - replace with EduBase equivalent)

* `create_course n/ English 101 id/ C0001` : Creates a new course named "English 101" with course ID 'C0001'.

* `register n/ John Doe g/ Male p/98765432` : Registers a new student.

* `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create_course n/ <COURSE_NAME> id/ <COURSE_ID>`, `COURSE_NAME` and `COURSE_ID` are parameters which can be used as `create_course n/ English 101 id/ C0001`.

* Items in square brackets are optional.<br>
  *Note: None of the current EduBase commands have optional parameters.*

* Items with `…`​ after them can be used multiple times including zero times.<br>
  *Note: None of the current EduBase commands support multiple repeating parameters.*

* Parameters can be in any order for commands using flags (e.g., `n/`, `g/`, `d/`).<br>
  e.g. for `register n/ NAME g/ GENDER p/ PHONE`, `register g/ GENDER n/ NAME p/ PHONE` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `view_course`, `exit`, and `list`) will be ignored.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### System-Wide Commands (School Level)

These commands are used for managing courses and the central student registry.

---

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

**Format:** `help`

---

### Creating a Course: `create_course`

Allows teachers to create a new course. Duplicate course names are allowed, but duplicate course ids are not allowed.

**Format:** `create_course n/<COURSE_NAME> id/<COURSE_ID>`

**Requirements:**
* COURSE_ID need to be in valid format, which is `C` followed by 4 digits (e.g., CXXXX).
* COURSE_ID cannot be used by other courses in the course list.

<box type="tip" seamless>

**Tip:** Course names can only contain letters, spaces, hyphens, and apostrophes.

</box>

**Examples:**
* `create_course n/English 101 id/C0002`
* `create_course n/Calculus II id/C1231`

---

### Viewing All Courses: `view_course`

Allows users to view a list of all existing courses with their Course IDs.

**Format:** `view_course`

**Example:**
* `view_course`

---

### Edit Course: `edit_course`

Allows users to edit course id and course name by selecting its index in view list.

**Requirements:**
* INDEX need to be the same as the index used in the displayed course list.
* COURSE_ID need to be in valid format, which is `C` followed by 4 digits (e.g., CXXXX).
* New COURSE_ID cannot be same with any other courses in the course list.
* At least one field (course name or course id) needs to be provided.

**Format:** `edit_course INDEX n/<COURSE_NAME> id/<COURSE_ID>`

**Example:**
* `edit_course 1 n/English 101 id/C0001`
---

### Find Course By Name: `find_course_by_name`

Allows users to find course by entering the course name.

**Requirements:**
* At least one name need to be provided, and not blank.

**Format:** `find_course_by_name <COURSE_NAME>`

**Example:**
`find_course_by_name English Math`

---

### Deleting a Course: `delete_course`

Allows teachers to delete an existing course using its unique **Course ID**.

**Format:** `delete_course <COURSE_ID>`

<box type="warning" seamless>

**Caution:** Ensure the Course ID is correct, as this action cannot be undone.

</box>

**Examples:**
* `delete_course C0001`

---

### Registering a New Student: `register`

Allows teachers to add a new student to the EB database. A unique **Student ID** (e.g., S00001) is automatically assigned.

**Format:** `register n/<NAME> p/<PHONE> g/<GENDER>`

**Requirements:**
* Names can only include alphanumeric characters and spaces.
* Phone numbers can only include numbers and more than 3 digits.
* Genders can only accept values of `Male`, `Female`, and `Other`.

<box type="tip" seamless>

**Tips:**<br>
* Student names can only contain alphanumeric characters and spaces.<br>
* Duplicate names are allowed but will receive a new unique Student ID.<br>
* Names are case-sensitive.<br>
* Genders are case-insensitive.

</box>

**Examples:**
* `register n/John Doe p/12345678 g/Male`
* `register n/Jane Doe p/12345678 g/Female`

---

### Viewing All Students and Courses: `list`

Allows users to view a list of all existing courses and student.

**Format:** `list`

**Example:**
* `list`

--- 

### Find Student By ID: `find_student_by_id`

Allows users to find student by entering the student id.

**Requirements:**
* At least one id need to be provided, and not blank.

**Format:** `find_student_by_id <STUDENT_ID>`

**Example:**
`find_student_by_id S00001 S00002`

---

### Find Student By Name: `find_student_by_name`

Allows users to find student by entering the student name.

**Requirements:**
* At least one name need to be provided, and not blank.

**Format:** `find_student_by_name <STUDENT_NAME>`

**Example:**
`find_student_by_name Alice Bob`

---

### Deregistering a Student: `deregister`

Allows teachers to permanently delete a student from the EB database using their unique **Student ID**.

**Format:** `deregister <STUDENT_ID>`

**Requirements:**
* `Student Id` must follow the correct format which is ‘S’ followed by 5 numeric digits. (e.g. `S00001`)
* The target student exists in the EB database.
* The target student is not enrolled in either of the courses.

**Examples:**
* `deregister_student S00001`

---

### Viewing Course Details: `view_course_details`

Allows teachers to view all courses details and all of the students in that class.

**Format:** `view_course_details <COURSE_ID>`

**Examples:** `view_course_details C0001`

---

### Adding Student to Course: `add_student`

Allows teachers to enroll an already-registered student into a course using **Student ID** and **Course ID**.

**Format:** `add_student <STUDENT_ID> <COURSE_ID>`

**Requirements:**
* `Student ID` must follow the correct format which is ‘S’ followed by 5 numeric digits. (e.g. `S00001`)
* `Course ID` must follow the correct format which is ‘C’ followed by 4 numeric digits. (e.g. `C0001`)
* The target student exists in the EB database.
* The target course exists in the EB database.
* The target student is not enrolled in the target course.

<box type="warning" seamless>

**Caution:** A student cannot be added to a course if they are already inside the course.

</box>

**Examples:**
* `add_student S00001 C0001`

---

### Removing Student from Course: `remove_student`

Allows teachers to unenroll a student from the currently entered course using **Student ID** and **Course ID**.

**Format:** `remove_student <STUDENT_ID> <COURSE_ID>`

**Requirements:**
* `Student ID` must follow the correct format which is ‘S’ followed by 5 numeric digits. (e.g. `S00001`)
* `Course ID` must follow the correct format which is ‘C’ followed by 4 numeric digits. (e.g. `C0001`)
* The target student exists in the EB database.
* The target course exists in the EB database.
* The target student is currently enrolled in the target course.

**Examples:**
* `remove_student S00001 C0001`

---

### Exiting the System: `exit`

Allows teachers to exit from the system using the command box.

**Format:** `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: I am getting an "Invalid name" error when registering a student.<br>
**A**: **Names can only contain letters, spaces, hyphens, and apostrophes.** Ensure you are not using any numbers or special symbols.

**Q**: How do I know the ID of the course I just created?<br>
**A**: The **Course ID** is displayed immediately upon successful creation (e.g., `Course "English 101" (ID:C0001) is created successfully`). You can also use `view_all_courses` to see a list of all IDs.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| **Action**               | **Format,** **Examples**                                                                                    |
|--------------------------|-------------------------------------------------------------------------------------------------------------|
| **Create Course**        | `create_course n/<COURSE_NAME> id/<COURSE_ID>` <br> e.g., `create_course n/Calculus II id/C1231`            |
| **View Courses**         | `view_course`                                                                                               |
| **Edit Course**          | `edit_course INDEX n/<COURSE_NAME> id/<COURSE_ID>` <br> e.g., `edit_course 1 n/Calculus II id/C1231`        |
| **Find Course By Name**  | `find_course_by_name` <br> e.g., `find_course_by_name English`                                              |
| **Delete Course**        | `delete_course <COURSE_ID>` <br> e.g., `delete_course C0003`                                                |
| **Register Student**     | `register n/<NAME> p/<PHONE> g/<GENDER>` <br> e.g., `register n/John Doe p/12345678 g/Male`                 |
| **Edit Student**         | `edit_student INDEX n/<NAME> p/<PHONE> g/<GENDER>` <br> e.g., `edit_student 1 n/John Doe p/12345678 g/Male` |
| **Find Student By ID**   | `find_student_by_id` <br> e.g., `find_student_by_id S00001 S00002`                                          |
| **Find Student By Name** | `find_student_by_name` <br> e.g., `find_student_by_name Alice Bob`                                          |
| **Deregister**           | `deregister <STUDENT_ID>` <br> e.g., `deregister S00005`                                                    |
| **Add Student**          | `add_student <STUDENT_ID> <COURSE_ID>` <br> e.g., `add_student S00001 C0001`                                |
| **View Course Details**  | `view_course_details <COURSE_ID>` <br> e.g., `view_course_details C0001`                                    |
| **Remove Student**       | `remove_student <STUDENT_ID> <COURSE_ID>` <br> e.g., `remove_student S00001 C0001`                          |
| **Exit Program**         | `exit`                                                                                                      |
