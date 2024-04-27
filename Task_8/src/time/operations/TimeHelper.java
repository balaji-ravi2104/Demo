package time.operations;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
//day light savings
// time zone   
public class TimeHelper {
 
	public LocalDateTime getCurrentTime() {   
		return LocalDateTime.now();
	}
	
	public long getCurrentTimeInmilliSeconds() throws DateTimeException{
		return System.currentTimeMillis(); 
	}

	public ZonedDateTime getCurrentTimeByZoneId(ZoneId id) throws DateTimeException{
		return ZonedDateTime.now(id); 
	}   

         
	public DayOfWeek getWeekday(long milliSeconds) throws DateTimeException {
		return getZonedDateTime(milliSeconds).getDayOfWeek(); 
	} 

	public Month getMonthByMilliSeconds(long milliSeconds) throws DateTimeException {
		return getZonedDateTime(milliSeconds).getMonth();    
	}

	public int getYearByMilliSeconds(long milliSeconds) throws DateTimeException {
		return getZonedDateTime(milliSeconds).getYear();
	}
 
	
	public Set<String> getZoneDetails() {
		return ZoneId.getAvailableZoneIds();
	}
	
	private ZoneId getDefaultZoneId() throws DateTimeException {
		return ZoneId.systemDefault();   
	}
	
	private ZonedDateTime getZonedDateTime(long milliSeconds) {
		// Convert milliseconds since the epoch to an Instant
		Instant instant = Instant.ofEpochMilli(milliSeconds);
		// Get the default time zone
		ZoneId id = getDefaultZoneId();
		// Convert the Instant to a ZonedDateTime using the default time zone
		ZonedDateTime zonedDateTime = instant.atZone(id);
		return zonedDateTime;
	}
}
