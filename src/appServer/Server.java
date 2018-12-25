package appServer;

import data.DatabaseManager;
import data.IDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Server implements IServer
{
    private DatabaseManager dbm;
    private IDAO dao;

    Server() throws Exception
    {
        dbm = new DatabaseManager();
        dao = dbm.getDao();
    }

    @Override
    public String[] getTrains()
    {
        return dao.getTrains();
    }

    @Override
    public int getTicketCount(String trainRouteNumber)
    {
        return dao.getTicketCount(trainRouteNumber);
    }

    @Override
    public TripTicketRecord[] getTripTicketRecords()
    {
        List<TripTicketRecord> tripTicketRecordList = new ArrayList<>();
        List<Map<String, String>> records = dao.getTripTicketRecords();
        for (Map<String, String> record : records)
        {
            String train = record.get("train");
            LocalDate tripDate = LocalDate.parse(record.get("tripDate"));
            int ticketCount = Integer.parseInt(record.get("ticketCount"));
            tripTicketRecordList.add(new TripTicketRecord(train, tripDate, ticketCount));
        }
        return tripTicketRecordList.toArray(new TripTicketRecord[0]);
    }

    @Override
    public void close()
    {
        try
        {
            dbm.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
