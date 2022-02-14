import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Month

internal class LogEntryParserTestCase {
    @Test
    fun `should be able to parse ip`() {
        val logEntry = LogEntryParser.parse("""177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] "GET /intranet-analytics/ HTTP/1.1" 200 3574""")

        assertEquals("177.71.128.21", logEntry.ip)
    }

    @Test
    fun `should be able to parse url`() {
        val logEntry = LogEntryParser.parse("""177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] "GET /intranet-analytics/ HTTP/1.1" 200 3574""")
        assertEquals("/intranet-analytics/", logEntry.path)
    }

    @Test
    fun `should be able to parse time with timezone`() {
        val logEntry = LogEntryParser.parse("""177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] "GET /intranet-analytics/ HTTP/1.1" 200 3574""")
        assertEquals(2018, logEntry.zonedDateTime.year)
        assertEquals(Month.JULY, logEntry.zonedDateTime.month)
        assertEquals(10, logEntry.zonedDateTime.dayOfMonth)
        assertEquals(22, logEntry.zonedDateTime.hour)
        assertEquals(21, logEntry.zonedDateTime.minute)
        assertEquals(28, logEntry.zonedDateTime.second)
        assertEquals("+02:00", logEntry.zonedDateTime.zone.toString())
    }
}