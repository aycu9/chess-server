package com.github.aycu9.data;

/**
 * Created by Libra on 2018-05-26.
 */
public class UserState {

    public UserSelection selectedSquare;
    public UserSelection destinationSquare;
    public String promotionResult;

    @Override
    public String toString() {
        return "UserState{" +
                "selectedSquare=" + selectedSquare +
                ", destinationSquare=" + destinationSquare +
                ", promotionResult='" + promotionResult + '\'' +
                '}';
    }

    public static class UserSelection {
        public int column;
        public int row;

        @Override
        public String toString() {
            return "UserSelection{" +
                    "column=" + column +
                    ", row=" + row +
                    '}';
        }
    }
}
