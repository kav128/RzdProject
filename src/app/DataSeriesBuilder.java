package app;

import javafx.scene.chart.XYChart;
import java.util.function.Function;

class DataSeriesBuilder<T, S> implements Builder<XYChart.Series<T, S>>
{
    private String name = "Data Series";
    private T[] xAxisValues;
    private Function<T, S> yAxisValueGetter;

    DataSeriesBuilder<T, S> withName(String name)
    {
        this.name = name;
        return this;
    }

    DataSeriesBuilder<T, S> withXAxisValues(T[] values)
    {
        xAxisValues = values;
        return this;
    }

    DataSeriesBuilder<T, S> withXYFunction(Function<T, S> function)
    {
        yAxisValueGetter = function;
        return this;
    }

    @Override
    public XYChart.Series<T, S> build()
    {
        XYChart.Series<T, S> series = new XYChart.Series<>();
        series.setName(name);
        for (T xValue : xAxisValues)
        {
            S yValue = yAxisValueGetter.apply(xValue);
            series.getData().add(new XYChart.Data<>(xValue, yValue));
        }
        return series;
    }
}
