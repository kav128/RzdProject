package appServer;

public final class ServerFactory
{
    private ServerFactory() {}

    public static IServer getServerInstance()
    {
        try
        {
            return new Server();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
