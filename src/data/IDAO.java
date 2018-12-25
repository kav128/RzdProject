package data;

import java.util.List;
import java.util.Map;

public interface IDAO
{
    String[] getTrains();
    int getTicketCount(String trainRouteNumber);
    List<Map<String, String>> getTripTicketRecords();
}
