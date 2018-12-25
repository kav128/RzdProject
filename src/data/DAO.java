package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DAO implements IDAO
{
    private DatabaseManager dbm;

    DAO(DatabaseManager databaseManager)
    {
        dbm = databaseManager;
    }

    @Override
    public String[] getTrains()
    {
        String sql = "SELECT routeNumber FROM trains";
        try
        {
            ResultSet resultSet = dbm.executeQuery(sql);
            List<String> trains = new ArrayList<>();
            while (resultSet.next())
                trains.add(resultSet.getString(1));
            resultSet.close();
            String[] trainArray = new String[trains.size()];
            return trains.toArray(trainArray);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getTicketCount(String trainRouteNumber)
    {
        String sql = "SELECT COUNT(*) FROM ticketDetails WHERE train = '" + trainRouteNumber + "'";
        try
        {
            ResultSet resultSet = dbm.executeQuery(sql);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            return count;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Map<String, String>> getTripTicketRecords()
    {
        String sql = "SELECT train, tripDate, COUNT(*) as ticketCount\n" +
                "FROM TicketDetails\n" +
                "GROUP BY train, tripDate\n" +
                "ORDER BY train, tripDate";
        try
        {
            List<Map<String, String>> mapList = new ArrayList<>();
            ResultSet resultSet = dbm.executeQuery(sql);
            while (resultSet.next())
            {
                Map<String, String> curMap = new HashMap<>();
                curMap.put("train", resultSet.getString("train"));
                curMap.put("tripDate", resultSet.getString("tripDate"));
                curMap.put("ticketCount", resultSet.getString("ticketCount"));
                mapList.add(curMap);
            }
            return mapList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
