public class StringSearcher {
    private String toFind;
    private char[] stock;
    private boolean isFirstBuffer;

    public StringSearcher(String toFind) {
        this.toFind = toFind;
        stock = new char[this.toFind.length() - 1];
        isFirstBuffer = true;
    }

    public boolean find(char[] buffer) {
        if (!isFirstBuffer) {
            return contains(addAll(stock, buffer));
        } else {
            return contains(buffer);
        }
    }

    private boolean contains(char[] buffer) {
        String b = String.copyValueOf(buffer);
        for (int i = 0; i < toFind.length() - 1; i++) {
            stock[stock.length - 1 - i] = buffer[buffer.length - 1 - i];
        }
        isFirstBuffer = false;
        return b.contains(toFind);
    }

    private char[] addAll(char[] array1, char[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        char[] joinedArray = new char[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    private char[] clone(char[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }
}

