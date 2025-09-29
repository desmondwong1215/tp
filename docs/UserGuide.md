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

* `create_course English 101` : Creates a new course named "English 101".

* `register /n John Doe /g Male` : Registers a new student.

* `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create_course <COURSE_NAME>`, `COURSE_NAME` is a parameter which can be used as `create_course English 101`.

* Items in square brackets are optional.<br>
  *Note: None of the current EduBase commands have optional parameters.*

* Items with `…`​ after them can be used multiple times including zero times.<br>
  *Note: None of the current EduBase commands support multiple repeating parameters.*

* Parameters can be in any order for commands using flags (e.g., `/n`, `/g`, `/d`).<br>
  e.g. for `register /n NAME /g GENDER`, `register /g GENDER /n NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `view_all_courses`, `exit_course`, and `exit`) will be ignored.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### System-Wide Commands (School Level)

These commands are used for managing courses and the central student registry.

---

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

---

### Creating a Course: `create_course`

Allows teachers to create a new course. A unique **Course ID** (e.g., C0001) will be generated automatically. Duplicate course names are allowed.

Format: `create_course <COURSE_NAME>`

<box type="tip" seamless>
**Tip:** Course names can only contain letters, spaces, hyphens, and apostrophes.
</box>

Examples:
* `create_course English 101`
* `create_course Calculus II`

---

### Viewing All Courses: `view_all_courses`

Allows users to view a list of all existing courses with their Course IDs.

Format: `view_all_courses`

Example:
* `view_all_courses`

---

### Deleting a Course: `delete_course`

Allows teachers to delete an existing course using its unique **Course ID**.

Format: `delete_course <COURSE_ID>`

<box type="warning" seamless>
**Caution:** Ensure the Course ID is correct, as this action cannot be undone.
</box>

Examples:
* `delete_course C0001`

---

### Registering a New Student: `register`

Allows teachers to add a new student to the school-level address book. A unique **Student ID** (e.g., S00001) is automatically assigned.

Format: `register /n <NAME> /g <GENDER>`

<box type="tip" seamless>
**Tip:** Student names can only contain letters, spaces, hyphens, and apostrophes. Duplicate names are allowed but will receive a new unique Student ID.
</box>

Examples:
* `register /n John Doe /g Male`
* `register /g Female /n Jane Doe`

---

### Deregistering a Student: `deregister_student`

Allows teachers to permanently delete a student from the school-level address book using their unique **Student ID**.

Format: `deregister_student <STUDENT_ID>`

Examples:
* `deregister_student S00001`

---

### Entering a Course: `enter_course`

Allows teachers to enter the context of a specific course to perform course-level commands.

Format: `enter_course <COURSE_ID>`

Examples:
* `enter_course C0001`

---
--------------------------------------------------------------------------------------------------------------------

### Course-Level Commands

These commands can only be executed **after** successfully using the `enter_course` command.

---

### Adding Student to Course: `add_student`

Allows teachers to enroll an already-registered student into the currently entered course using their **Student ID**.

Format: `add_student <STUDENT_ID>`

<box type="warning" seamless>
**Caution:** A student cannot be added to a course if they are already enrolled.
</box>

Examples:
* `add_student S00001`

---

### Removing Student from Course: `remove_student`

Allows teachers to unenroll a student from the currently entered course using their **Student ID**.

Format: `remove_student <STUDENT_ID>`

Examples:
* `remove_student S00001`

---

### Creating a Session: `create_session`

Creates a new attendance record for **all** enrolled students in the current course for a specified date. Attendance for each student is initially set to **"unmarked"**.

Format: `create_session /d <DATE>`

<box type="tip" seamless>
**Tip:** The date must be in `YYYY-MM-DD` format and cannot be a future date. The system assigns a unique **Session ID** (an integer) upon success.
</box>

Examples:
* `create_session /d 2025-09-18`

---

### Marking/Unmarking Attendance: `mark` / `unmark`

Allows teachers to update a student's attendance status for a specific session.

Format:
* `mark /id <STUDENT_ID> /s <SESSION_ID>`
* `unmark /id <STUDENT_ID> /s <SESSION_ID>`

<box type="tip" seamless>
**Tip:** `mark` sets the student status to **Present**. `unmark` sets the student status to **Absent**.
</box>

Examples:
* `mark /id S00001 /s 1`
* `unmark /id S00001 /s 1`

---

### Viewing Attendance: `view_attendance`

Allows teachers to view the full class attendance for a specific session.

Format: `view_attendance /s <SESSION_ID>`

Examples:
* `view_attendance /s 1`

---

### Exiting a Course: `exit_course`

Allows teachers to exit the current course context and return to the School Level.

Format: `exit_course`

Examples:
* `exit_course`

---
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

Action | Format, Examples
---|---
**System-Wide Commands** |
**Create Course** | `create_course <COURSE_NAME>` <br> e.g., `create_course Chemistry I`
**View Courses** | `view_all_courses`
**Delete Course** | `delete_course <COURSE_ID>` <br> e.g., `delete_course C0003`
**Register Student** | `register /n <NAME> /g <GENDER>` <br> e.g., `register /n Alice /g Female`
**Deregister Student** | `deregister_student <STUDENT_ID>` <br> e.g., `deregister_student S00005`
**Enter Course** | `enter_course <COURSE_ID>` <br> e.g., `enter_course C0001`
**Course-Level Commands** |
**Add Student** | `add_student <STUDENT_ID>` <br> e.g., `add_student S00001`
**Remove Student** | `remove_student <STUDENT_ID>` <br> e.g., `remove_student S00001`
**Create Session** | `create_session /d <DATE>` <br> e.g., `create_session /d 2025-09-29`
**Mark Attendance** | `mark /id <STUDENT_ID> /s <SESSION_ID>` <br> e.g., `mark /id S00001 /s 1`
**Unmark Attendance** | `unmark /id <STUDENT_ID> /s <SESSION_ID>` <br> e.g., `unmark /id S00001 /s 1`
**View Attendance** | `view_attendance /s <SESSION_ID>` <br> e.g., `view_attendance /s 1`
**Exit Course** | `exit_course`
**Exit Program** | `exit`
