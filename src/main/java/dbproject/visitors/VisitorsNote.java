package dbproject.visitors;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class VisitorsNote {
	private int vbcode;
	private String userid;
	private String vbtext;
	private Date vbdate;
	private String name;
	private String vbdateToStr;
	private Calendar tempCalendar;

	public int getVbcode() {
		return vbcode;
	}

	public void setVbcode(int vbcode) {
		this.vbcode = vbcode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getVbtext() {
		return vbtext;
	}

	public void setVbtext(String vbtext) {
		this.vbtext = vbtext;
	}

	public Date getVbdate() {
		return vbdate;
	}

	public String getVbDateToStr() {
		tempCalendar = Calendar.getInstance();
		tempCalendar.setTime(vbdate);
		if (tempCalendar.get(Calendar.MINUTE) < 10) {
			vbdateToStr = vbdate.toString() + " " + tempCalendar.get(Calendar.HOUR_OF_DAY) + ":0"
					+ tempCalendar.get(Calendar.MINUTE);
		} else {
			vbdateToStr = vbdate.toString() + " " + tempCalendar.get(Calendar.HOUR_OF_DAY) + ":"
					+ tempCalendar.get(Calendar.MINUTE);
		}
		return vbdateToStr;
	}

	public void setVbdate(Date vbdate) {
		this.vbdate = vbdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
