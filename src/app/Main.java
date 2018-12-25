package app;

import appServer.IServer;
import appServer.ServerFactory;
import appServer.TripTicketRecord;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        String[] trains = serverInstance.getTrains();
        XYChart.Series<String, Number> ticketDataSeries = new DataSeriesBuilder<String, Number>()
                .withName("Количество билетов")
                .withXAxisValues(trains)
                .withXYFunction(trainRouteNumber -> serverInstance.getTicketCount(trainRouteNumber))
                .build();

        XYChart<String, Number> chart = new ChartBuilder()
                .withXAxis("Маршрут поезда")
                .withYAxis("Количество билетов")
                .withTitle("Сводка по количеству купленных билетов на разные поезда")
                .withDataSeries(ticketDataSeries)
                .build();

        ObservableList<TripTicketRecord> tickets = FXCollections.observableArrayList();
        tickets.addAll(serverInstance.getTripTicketRecords());
        TableView<TripTicketRecord> ticketTable = new TableView<>(tickets);

        TableColumn<TripTicketRecord, String> trainColumn = new TableColumn<>("Маршрут");
        trainColumn.setCellValueFactory(new PropertyValueFactory<>("train"));
        TableColumn<TripTicketRecord, LocalDate> tripDateColumn = new TableColumn<>("Дата рейса");
        tripDateColumn.setCellValueFactory(new PropertyValueFactory<>("tripDate"));
        TableColumn<TripTicketRecord, Integer> ticketCountColumn = new TableColumn<>("Количество билетов");
        ticketCountColumn.setCellValueFactory(new PropertyValueFactory<>("ticketCount"));

        ticketTable.getColumns().add(trainColumn);
        ticketTable.getColumns().add(tripDateColumn);
        ticketTable.getColumns().add(ticketCountColumn);

        primaryStage.setTitle("Сводка по купленным билетам");
        primaryStage.setScene(new Scene(chart, 1280, 720));
        primaryStage.show();

        Stage secondStage = new Stage();
        secondStage.setTitle("Сводная таблица по купленным билетам");
        secondStage.setScene(new Scene(ticketTable, 350, 520));
        secondStage.show();
    }

    private static IServer serverInstance;

    public static void main(String[] args)
    {
        try (IServer server = ServerFactory.getServerInstance())
        {
            serverInstance = server;
            launch(args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
