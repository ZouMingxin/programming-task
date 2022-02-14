import java.io.File


fun main() {
    val logEntries = File(object {}.javaClass.getResource("programming-task-example-data.log").path)
        .readLines()
        .map { LogEntryParser.parse(it) }

    val logEntryContainer = LogEntryContainer(logEntries)

    println("Count of unique ips: ${logEntryContainer.countOfUniqueIp()}")
    println("Most visited url: ${logEntryContainer.mostVisitedUrl()}")
    println("Most active ips: ${logEntryContainer.mostActiveIps()}")
}