package appServer;

import java.time.LocalDate;

public class TripTicketRecord
{
    private String train;
    private LocalDate tripDate;
    private int ticketCount;

    TripTicketRecord(String train, LocalDate tripDate, int ticketCount)
    {
        this.train = train;
        this.tripDate = tripDate;
        this.ticketCount = ticketCount;
    }

    public String getTrain()
    {
        return train;
    }

    public LocalDate getTripDate()
    {
        return tripDate;
    }

    public int getTicketCount()
    {
        return ticketCount;
    }
}
