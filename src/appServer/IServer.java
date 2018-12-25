package appServer;

public interface IServer extends AutoCloseable
{
    String[] getTrains();
    int getTicketCount(String trainRouteNumber);
    TripTicketRecord[] getTripTicketRecords();
}
