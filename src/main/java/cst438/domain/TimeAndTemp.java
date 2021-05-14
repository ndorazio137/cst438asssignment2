package cst438.domain;

import java.text.DecimalFormat;

// Helper class.
public class TimeAndTemp {
   public double temp;
   public long time;
   public int timezone;
   
   public TimeAndTemp(double temp, long time, int timezone) {
      this.temp = temp;
      this.time = time;
      this.timezone = timezone;
   }
   
   public double getTempInFahrenheit() {
      double fahrenheit = (temp - 273.15) * 9.0/5.0 + 32.0;
      DecimalFormat format = new DecimalFormat("#.00");
      return new Double(format.format(fahrenheit));
   }
   
   public String getFahrenheitSymbol() {
      // \u00B0 is the degree(°) symbol
      return "\u00B0" + "F";
   }
   
   public String getCelciusSymbol() {
      // \u00B0 is the degree(°) symbol
      return "\u00B0" + "C";
   }
   
   public String getKelvinSymbol() {
      return "K";
   }

   public double getTemp() {
      return temp;
   }
   
   public long getTime() {
      return time;
   }

   public int getTimezone() {
      return timezone;
   }

   @Override
   public String toString() {
      return "TimeAndTemp [temp=" + temp + ", time=" + time + ", timezone="
            + timezone + "]";
   }
}
