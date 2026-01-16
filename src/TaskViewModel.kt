class TaskViewModel {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    fun addTask(title: String, description: String): Task {
        val task = Task(
            id = nextId++,
            title = title,
            description = description
        )
        tasks.add(task)
        return task
    }

    fun removeTask(id: Int): Boolean {
        val task = tasks.find { it.id == id } ?: return false
        tasks.remove(task)
        return true
    }

    fun markTaskDone(id: Int): Boolean {
        val index = tasks.indexOfFirst { it.id == id }
        if (index == -1) return false
        tasks[index] = tasks[index].copy(isDone = true)
        return true
    }

    fun showAllTasks(): List<Task> = tasks.sortedBy { it.isDone }

    fun searchTasks(keyword: String): List<Task> =
        tasks.filter { it.title.contains(keyword, ignoreCase = true)
                || it.description.contains(keyword, ignoreCase = true) }
}
