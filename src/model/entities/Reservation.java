package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

  private Integer rooNumber;
  private Date checkIn;
  private Date checkOut;
  
  private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

  public Reservation(Integer rooNumber, Date checkIn, Date checkOut) {
    super();
    this.rooNumber = rooNumber;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Integer getRooNumber() {
    return rooNumber;
  }

  public void setRooNumber(Integer rooNumber) {
    this.rooNumber = rooNumber;
  }

  public Date getCheckIn() {
    return checkIn;
  }

  public Date getCheckOut() {
    return checkOut;
  }
  
  public long duration() {
    long diff = checkOut.getTime() - checkIn.getTime();
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }
  
  public String updateDates(Date checkIn, Date checkOut) {
    Date now = new Date();
    if(checkIn.before(now) || checkOut.before(now)) {
      return "Error in reservation: Reservation dates for update must be future dates";
    }
    if(!checkOut.after(checkIn)) {
      return "Error in reservation: Check-out date must be after check-in date ";
    }
    
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    return null;
  }

  @Override
  public String toString() {
    return  "Room "
          + rooNumber 
          + ", Check-In: "
          + sdf.format(checkIn)
          + ", Check-Out: "
          + sdf.format(checkOut)
          + ", "
          + duration()
          + " nigths";
          
  }
}
