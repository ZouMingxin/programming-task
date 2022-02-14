class LogEntryContainer(private val logEntries: List<LogEntry>) {
    fun countOfUniqueIp(): Int {
        return logEntries.distinctBy { it.ip }.size
    }

    fun mostVisitedUrl(): String? {
        return logEntries.groupBy { it.path }.maxByOrNull { it.value.size }?.key
    }

    fun mostActiveIps(entriesToTake: Int = 3): List<String> {
        return logEntries.sortedByDescending { it.zonedDateTime }.distinctBy { it.ip }.take(entriesToTake).map { it.ip }
    }
}