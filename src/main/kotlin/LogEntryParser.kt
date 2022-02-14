import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object LogEntryParser {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:H:mm:ss Z")

    fun parse(log: String): LogEntry {
        val parts = log.split(" ").map { it.trim() }
        val ip = parts[0]
        val time = parts[3].replace("[", "")
        val timezone = parts[4].replace("]", "")
        val path = parts[6]
        val zonedDateTime = ZonedDateTime.parse("$time $timezone", dateTimeFormatter)

        return LogEntry(ip, path, zonedDateTime)
    }
}