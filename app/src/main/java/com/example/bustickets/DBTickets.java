package com.example.bustickets;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DBTickets {
    public static Map<Ticket, Integer> tickets;

    static {
        tickets = new HashMap<>();
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.KIEV, new GregorianCalendar(2021, Calendar.JULY, 11, 10, 15), SeatClass.COMFORT, 500,true), 3);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.KIEV, new GregorianCalendar(2021, Calendar.JULY, 11, 22, 30), SeatClass.ECONOMY, 400,false), 1);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.KIEV, new GregorianCalendar(2021, Calendar.JULY, 13, 10, 15), SeatClass.COMFORT, 500,true), 2);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.KIEV, new GregorianCalendar(2021, Calendar.JULY, 15, 10, 15), SeatClass.COMFORT, 500,true), 3);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.KIEV, new GregorianCalendar(2021, Calendar.JULY, 15, 22, 30), SeatClass.ECONOMY, 400,false), 1);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.KIEV, new GregorianCalendar(2021, Calendar.JULY, 17, 10, 15), SeatClass.COMFORT, 500,true), 2);

        tickets.put(new Ticket(City.KIEV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 10, 22, 0), SeatClass.COMFORT, 500,true), 3);
        tickets.put(new Ticket(City.KIEV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 12, 9, 20), SeatClass.ECONOMY, 400,false), 1);
        tickets.put(new Ticket(City.KIEV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 12, 22, 0), SeatClass.COMFORT, 500,true), 1);
        tickets.put(new Ticket(City.KIEV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 14, 22, 0), SeatClass.COMFORT, 500,true), 1);
        tickets.put(new Ticket(City.KIEV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 16, 9, 20), SeatClass.ECONOMY, 400,false), 2);
        tickets.put(new Ticket(City.KIEV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 16, 22, 0), SeatClass.COMFORT, 500,true), 3);

        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.LVIV, new GregorianCalendar(2021, Calendar.JULY, 11, 10, 15), SeatClass.COMFORT, 400,false), 3);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.LVIV, new GregorianCalendar(2021, Calendar.JULY, 11, 22, 30), SeatClass.COMFORT, 500,true), 2);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.LVIV, new GregorianCalendar(2021, Calendar.JULY, 13, 10, 15), SeatClass.COMFORT, 400,false), 1);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.LVIV, new GregorianCalendar(2021, Calendar.JULY, 15, 10, 15), SeatClass.COMFORT, 400,false), 1);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.LVIV, new GregorianCalendar(2021, Calendar.JULY, 15, 22, 30), SeatClass.COMFORT, 500,true), 3);
        tickets.put(new Ticket(City.ZAPORIZHZHIA, City.LVIV, new GregorianCalendar(2021, Calendar.JULY, 17, 10, 15), SeatClass.COMFORT, 400,false), 1);

        tickets.put(new Ticket(City.LVIV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 10, 22, 0), SeatClass.COMFORT, 500,true), 3);
        tickets.put(new Ticket(City.LVIV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 12, 9, 20), SeatClass.ECONOMY, 400,false), 1);
        tickets.put(new Ticket(City.LVIV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 12, 22, 0), SeatClass.COMFORT, 500,true), 1);
        tickets.put(new Ticket(City.LVIV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 14, 22, 0), SeatClass.COMFORT, 500,true), 1);
        tickets.put(new Ticket(City.LVIV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 16, 9, 20), SeatClass.ECONOMY, 400,false), 2);
        tickets.put(new Ticket(City.LVIV, City.ZAPORIZHZHIA, new GregorianCalendar(2021, Calendar.JULY, 16, 22, 0), SeatClass.COMFORT, 500,true), 3);
    }
}
