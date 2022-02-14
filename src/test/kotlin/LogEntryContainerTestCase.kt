import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.test.assertEquals

internal class LogEntryContainerTestCase {
    @Test
    fun `should be able to calculate unique ip address`() {
        val entries = listOf(
            LogEntry("123.123.123.123", "/path", ZonedDateTime.now()),
            LogEntry("123.123.123.123", "/path", ZonedDateTime.now()),
            LogEntry("123.123.123.123", "/path", ZonedDateTime.now()),
            LogEntry("111.111.111.111", "path", ZonedDateTime.now()),
        )

        val logEntryContainer = LogEntryContainer(entries)

        assertEquals(2, logEntryContainer.countOfUniqueIp())
    }

    @Test
    fun `should be able to calculate most visited url`() {
        val entries = listOf(
            LogEntry("123.123.123.123", "/path-a", ZonedDateTime.now()),
            LogEntry("123.123.123.123", "/path-a", ZonedDateTime.now()),
            LogEntry("123.123.123.123", "/path-b", ZonedDateTime.now()),
            LogEntry("123.123.123.123", "/path-b", ZonedDateTime.now()),
            LogEntry("111.111.111.111", "/path-b", ZonedDateTime.now()),
        )

        val logEntryContainer = LogEntryContainer(entries)

        assertEquals("/path-b", logEntryContainer.mostVisitedUrl())
    }

    @Test
    fun `should be able to calculate most active unique ips in the same timezone`() {
        val entries = listOf(
            LogEntry("123.123.123.1", "/path-a", ZonedDateTime.now().minusHours(3)),
            LogEntry("123.123.123.2", "/path-a", ZonedDateTime.now().minusHours(2)),
            LogEntry("123.123.123.3", "/path-a", ZonedDateTime.now().minusHours(1)),
            LogEntry("123.123.123.4", "/path-b", ZonedDateTime.now().minusMinutes(20)),
            LogEntry("123.123.123.4", "/path-b", ZonedDateTime.now().minusMinutes(10)),
            LogEntry("123.123.123.5", "/path-b", ZonedDateTime.now().minusSeconds(5)),
        )

        val logEntryContainer = LogEntryContainer(entries)

        assertEquals(listOf("123.123.123.5", "123.123.123.4", "123.123.123.3"), logEntryContainer.mostActiveIps())
    }

    @Test
    fun `should be able to calculate most active ips considering timezone`() {
        val entries = listOf(
            LogEntry("123.123.123.1", "/path-a", ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+0900"))),
            LogEntry("123.123.123.2", "/path-a", ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+0500"))),
            LogEntry("123.123.123.3", "/path-a", ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+0300"))),
            LogEntry("123.123.123.4", "/path-a", ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+0700"))),
        )

        val logEntryContainer = LogEntryContainer(entries)
        assertEquals(listOf("123.123.123.3", "123.123.123.2", "123.123.123.4"), logEntryContainer.mostActiveIps())
    }
}