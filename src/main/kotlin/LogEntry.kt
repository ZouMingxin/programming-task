import java.time.ZonedDateTime

data class LogEntry(
    val ip: String,
    val path: String,
    val zonedDateTime: ZonedDateTime,
)