package com.smartcow.datacontroller;


import java.util.Calendar;
import java.util.Date;
 
public class AgeCalculator
{
   public static Age calculateAgeBefore(Date birthDate)
   {
      int years = 0;
      int months = 0;
      int days = 0;
      //create calendar object for birth day
      Calendar birthDay = Calendar.getInstance();
      birthDay.setTimeInMillis(birthDate.getTime());
      //create calendar object for current day
      long currentTime = System.currentTimeMillis();
      Calendar now = Calendar.getInstance();
      now.setTimeInMillis(currentTime);
      //Get difference between years
      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
      int currMonth = now.get(Calendar.MONTH) + 1;
      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
      //Get difference between months
      months = currMonth - birthMonth;
      //if month difference is in negative then reduce years by one and calculate the number of months.
      if (months < 0)
      {
         years--;
         months = 12 - birthMonth + currMonth;
         if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
            months--;
      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
      {
         years--;
         months = 11;
      }
      //Calculate the days
      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
         days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
      else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
      {
         int today = now.get(Calendar.DAY_OF_MONTH);
         now.add(Calendar.MONTH, -1);
         days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
      } else
      {
         days = 0;
         if (months == 12)
         {
            years++;
            months = 0;
         }
      }
      //Create new Age object 
      return new Age(days, months, years);
   }
 
   public static Age calculateAgeAfter(Date birthDate)
   {
      int years = 0;
      int months = 0;
      int days = 0;
      //create calendar object for birth day
      Calendar birthDay = Calendar.getInstance();
      birthDay.setTimeInMillis(birthDate.getTime());
      //create calendar object for current day
      long currentTime = System.currentTimeMillis();
      Calendar now = Calendar.getInstance();
      now.setTimeInMillis(currentTime);
      //Get difference between years
      years = birthDay.get(Calendar.YEAR) - now.get(Calendar.YEAR);
      int currMonth = now.get(Calendar.MONTH) + 1;
      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
      //Get difference between months
      months = birthMonth-currMonth;
      //if month difference is in negative then reduce years by one and calculate the number of months.
      if (months < 0)
      {
         years--;
         months =  12 +birthMonth - currMonth;
        /* if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
            months++;*/
      }
      else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
      {
        // years++;
         months = 0;
      }
      else if (months == 0 && now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
      {
         years--;
         months = 12 +birthMonth - currMonth;
      }
      //Calculate the days
      if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
         days =  birthDay.get(Calendar.DATE)-now.get(Calendar.DATE);
      else if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
      {
         int today = now.get(Calendar.DAY_OF_MONTH);
         
         months--;
         days = now.getActualMaximum(Calendar.DAY_OF_MONTH)+  birthDay.get(Calendar.DAY_OF_MONTH)-today  ;
         
      } else
      {
         days = 0;
         if (months == 12)
         {
            years++;
            months = 0;
         }
      }
      //Create new Age object 
      return new Age(days, months, years);
   }
  /* public static void main(String[] args) throws ParseException
   {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      Date birthDate = sdf.parse("1998/06/18");
      Date someDate = sdf.parse("2016/12/12");
      Age age = calculateAgeBefore(birthDate);
      Age age1 = calculateAgeAfter(someDate);
      //My age is
      System.out.println(age);
      System.out.println(age.getYears());
      System.out.println(age.getMonths());
      System.out.println(age.getDays());
      
    //future date
      System.out.println("future "+age1);
      System.out.println(age1.getYears());
      System.out.println(age1.getMonths());
      System.out.println(age1.getDays());
    

   
}
*/   
  }
