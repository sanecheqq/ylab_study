package task_lecture2.stats_accumulator;

public class StatsAccumulatorImpl implements StatsAccumulator {
    private int min;
    private int max;
    private int accumulator;
    private int counterOfAddedElements;

    public StatsAccumulatorImpl() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        accumulator = 0;
        counterOfAddedElements = 0;
    }

    @Override
    public void add(int value) {
        increaseAccumulator(value);
        increaseCounterOfAddedElements();
        setMinAndMaxIfNeeded(value);
    }

    private void increaseAccumulator(int value) {
        accumulator += value;
    }

    private void increaseCounterOfAddedElements() {
        counterOfAddedElements++;
    }

    private void setMinAndMaxIfNeeded(int value) {
        if (value > getMax()) {
            setMax(value);
        }
        if (value < getMin()) {
            setMin(value);
        }
    }
    @Override
    public Double getAvg() {
        return (double) getAccumulator() / getCount();
    }

    @Override
    public int getCount() {
        return counterOfAddedElements;
    }

    @Override
    public int getMin() {
        return min;
    }

    private void setMin(int min) {
        this.min = min;
    }

    @Override
    public int getMax() {
        return max;
    }

    private void setMax(int max) {
        this.max = max;
    }

    public int getAccumulator() {
        return accumulator;
    }
}
