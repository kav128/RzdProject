package app;

import javafx.scene.chart.*;
import java.util.ArrayList;
import java.util.List;

class ChartBuilder implements Builder<XYChart<String, Number>>
{
    private String xAxisLabel = "xAxis";
    private String yAxisLabel = "yAxis";
    private List<XYChart.Series<String, Number>> seriesList;
    private String title = "Chart title";

    ChartBuilder()
    {
        seriesList = new ArrayList<>();
    }

    ChartBuilder withXAxis(String xAxisLabel)
    {
        this.xAxisLabel = xAxisLabel;
        return this;
    }

    ChartBuilder withYAxis(String yAxisLabel)
    {
        this.yAxisLabel = yAxisLabel;
        return this;
    }

    ChartBuilder withDataSeries(XYChart.Series<String, Number> series)
    {
        seriesList.add(series);
        return this;
    }

    ChartBuilder withTitle(String title)
    {
        this.title = title;
        return this;
    }

    @Override
    public XYChart<String, Number> build()
    {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisLabel);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisLabel);

        XYChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        for (XYChart.Series<String, Number> series : seriesList)
            chart.getData().add(series);
        chart.setTitle(title);

        return chart;
    }
}
