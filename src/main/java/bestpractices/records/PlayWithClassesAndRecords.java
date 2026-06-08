package bestpractices.records;

import java.io.Serializable;

class PlayWithClassesAndRecords {

    /**
     * RangeRecord
     * @param min
     * @param max
     */
    static record RangeRecord(int min, int max) implements Serializable {
        RangeRecord {
            if (min > max) {
                throw new IllegalArgumentException("min doit être inférieur ou égal à max");
            }
            System.out.println("Constructeur de RangeRecord appelé");
        }
    }

    /**
     * RangeClass
     */
    static class RangeClass implements Serializable {

        final private int min;
        final private int max;

        RangeClass(int min, int max) {
            if (min > max) {
                throw new IllegalArgumentException("min doit être inférieur ou égal à max");
            }
            this.min = min;
            this.max = max;
            System.out.println("Constructeur de RangeClass appelé");
        }

        @Override
        public String toString() {
            return "RangeClass{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }
}

