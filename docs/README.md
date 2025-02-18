# Luna User Guide

Luna is a chatbot application designed to assist with task management.
<br>
With Luna, you can easily add, track, and organize your tasks, ensuring you stay on top of your daily activities. 
<br>
Whether you need to remember important deadlines, events, or simple to-dos, Luna is here to assist you in keeping your life organized.

- [Quick Start](#quick-start)
- [Available Commands](#available-commands)
- [Example Usage](#example-usage)
- [Image](#image)

---

### Quick Start
1. Ensure you have Java 17 installed .
2. Download the JAR file from the release page.
3. Run the JAR file using `java -jar luna.jar` or simply double click on the JAR file.
4. You should see Luna greeting you.
5. You may begin interacting with Luna.

### Available Commands
- **bye**: Exits the chatbot.
- **help**: Displays a list of available commands.
- **list**: Lists all tasks currently in the task list.
- **todo**: Adds a todo task to the task list.
```
todo [description]
```
- **deadline**: Adds a deadline task to the task list. <br>
  <code>date</code> format must be in <code>yyyy-MM-dd</code>.
```
deadline [description] /by [date]
```

- **event**: Adds an event task to the task list. <br>
  <code>date</code> format must be in <code>yyyy-MM-dd</code>.
```
event [description] /from [start_date] /to [end_date]
```

- **mark**: Marks a task as done.
```
mark [number]
```
- **unmark**: Marks a task as not done.
```
unmark [number]
```
- **delete**: Deletes a task from the task list.
```
delete [number]
``` 
- **find**: Finds tasks by description.
```
find [description]
```

### Example Usage
Here are some example commands and their respective outputs.
Example deadline command:
```
deadline Finish Assignment /by 2025-02-21
```
Luna Output:
```
Got it. I've added this task:
[D][ ] Finish Assignment (by: Feb 21 2025)
Now you have 1 tasks in the list.
```

Example find command:
``` 
find Assignment
```
Luna Output:
```
Here are the matching tasks in your list:
1.[D][ ] Finish Assigment (by: Feb 21 2025)
```

Example mark command:
```
mark 1
```
Luna Output:
```
Nice! I've marked this task as done:
[D][X] Finish Assignment (by: Feb 21 2025)
```

### Image
![Ui](https://noahkoh.github.io/ip/docs/Ui.png)
