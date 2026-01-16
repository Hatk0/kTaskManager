fun main() {
    val viewModel = TaskViewModel()

    while (true) {
        println("""
            =======================
            1. Add task
            2. Remove task
            3. Mark task done
            4. Search tasks
            5. Show all tasks
            0. Exit
            =======================
        """.trimIndent())

        when (readlnOrNull()) {
            "1" -> {
                print("Title: ")
                val title = readlnOrNull() ?: ""
                print("Description: ")
                val desc = readlnOrNull() ?: ""
                val task = viewModel.addTask(title, desc)
                println("Task added: ${formatTask(task)}")
            }
            "2" -> {
                print("Task ID to remove: ")
                val id = readlnOrNull()?.toIntOrNull()
                if (id != null) {
                    if (viewModel.removeTask(id)) println("Task removed!")
                    else println("Task not found!")
                }
            }
            "3" -> {
                print("Task ID to mark done: ")
                val id = readlnOrNull()?.toIntOrNull()
                if (id != null) {
                    if (viewModel.markTaskDone(id)) println("Task marked as done!")
                    else println("Task not found!")
                }
            }
            "4" -> {
                print("Search keyword: ")
                val keyword = readlnOrNull() ?: ""
                val results = viewModel.searchTasks(keyword)
                if (results.isEmpty()) println("No matching tasks found")
                results.forEach { println(formatTask(it)) }
            }
            "5" -> {
                val allTasks = viewModel.showAllTasks()
                if (allTasks.isEmpty()) println("No tasks found!")
                allTasks.forEach { println(formatTask(it)) }
            }
            "0" -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid option")
        }

        println("\nPress Enter to continue...")
        readlnOrNull()
    }
}

fun formatTask(task: Task) = "${task.id}. [${if (task.isDone) "x" else " "}] ${task.title} - ${task.description}"