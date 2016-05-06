//Jeremy Sanders
//Insy 4305-001

public class Time
{
	private int hours;
	private int minutes;

	public Time()
	{
		this(-1,-1);
	}

	public Time(int hours, int minutes)
	{
		setHours(hours);
		setMinutes(minutes);
	}

	public void setHours(int hours)
	{
		this.hours=hours;
	}

	public void setMinutes(int minutes)
	{
		this.minutes=minutes;
	}

	public int getHours()
	{
		return hours;
	}

	public int getMinutes()
	{
		return minutes;
	}

	public String toString()
	{
		return String.format("%02d:%02d",getHours(),getMinutes());
	}
}