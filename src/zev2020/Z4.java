package zev2020;

import java.util.ArrayList;
import java.util.Iterator;

public class Z4 {


    public static void main(String[] args) {

//        0|1|0
//        0|0|1
//        1|1|1
//         выиграл RED=1


        Piece[][] board = new Piece[3][3];
        board[0][0] = Piece.Empty;
        board[0][1] = Piece.Red;
        board[0][2] = Piece.Blue;
        board[1][0] = Piece.Blue;
        board[1][1] = Piece.Red;
        board[1][2] = Piece.Red;
        board[2][0] = Piece.Red;
        board[2][1] = Piece.Red;
        board[2][2] = Piece.Red;

        Z4 z4 = new Z4();

        System.out.println( "converctBoardToInt " + z4.converctBoardToInt( board ) );

        System.out.println( "1. вариант  3x3 hasWon " + z4.hasWon( board ) );

        System.out.println( "2. вариант  nхn hasWont " + z4.haswonIt( board ) );

    }


    //*разработать алгоритм , провверяющий результат игры в крестики-нолики
    //для доски N*N

    public int converctBoardToInt(Piece[][] board) {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                /*  с каждым значением связвается целовек число
                 */
                int value = board[i][j].ordinal();
                sum = sum * 3 + value;
            }
        }

        return sum;
    }

    //    Piece hasWon(Piece[][] board, int row, int column)
//    {
//        if (board.length!=board[0].length) return Piece.Empty;
//
//                Piece pieces=board[row][column];
//                if (pieces==Piece.Empty) return Piece.Empty;
//
//           if (hasWonRow(board,row)||hasWonColumn(board,column)) return pieces;
//
//           if (row==column&&hasWonDiagonal(board,1)) return pieces;
//
//           if(row==(board.length-column-1)&&hasWonDiagonal( board,-1 )) return pieces;
//
//
//        return Piece.Empty;
//    }
//
//
//    boolean hasWonRow(Piece[][] board,int row)
//    {
//        /*  в строке один элемент отличается - значит не выиграл по строке*/
//        for (int c = 0; c <board[row].length ; c++) {
//            if (board[row][c]!= board[row][0]){return false;}
//
//        }
//
//        return true;
//    }
//
//
//    boolean hasWonColumn(Piece[][] board,int сolumn)
//    {
//        /*  в колонке один элемент отличается - значит не выиграл по строке*/
//        for (int c = 0; c <board.length ; c++) {
//            if (board[c][сolumn]!= board[0][сolumn]){return false;}
//
//        }
//
//        return true;
//    }
//
//
//    boolean hasWonDiagonal(Piece[][] board,int direction)
//    {
//        int row=0;
//        int column=direction==1 ? 0:board.length-1;
//
//        Piece first=board[0][column];
//        for (int i = 0; i < board.length; i++) {
//            if (board[row][column] != first) return false;
//        }
//        row+=1;
//        column+=direction;
//
//        return true;
//    }
//
    //более короче код для NxN
    Piece hasWon(Piece[][] board) {
        int size = board.length;
        if (board[0].length != size) return Piece.Empty;
        Piece first;

        /* проверка строк*/
        for (int i = 0; i < size; i++) {
            first = board[i][0];
            if (first == Piece.Empty) continue;

            for (int j = 0; j < size; j++) {
                if (board[i][j] != first) break;
                else if (j == size - 1)// последений
                    return first;


            }
        }

        /* проверка столбцов*/
        for (int i = 0; i < size; i++) {
            first = board[0][i];
            if (first == Piece.Empty) continue;

            for (int j = 0; j < size; j++) {
                if (board[j][i] != first) break;
                else if (j == size - 1)// последений
                    return first;


            }
        }

        /* проверка диагоналей*/
        first = board[0][0];
        if (first != Piece.Empty) {
            for (int i = 1; i < size; i++) {

                if (board[i][i] != first) break;
                else if (i == size - 1)// последений
                    return first;
            }
        }


        first = board[0][size - 1];
        if (first != Piece.Empty) {
            for (int i = 0; i < size; i++) {
                if (board[i][size - i - 1] != first) break;
                else if (i == size - 1) return first; //послдений элемент
            }


        }

        return Piece.Empty;
    }

//* если последний ход известен*//

    Piece haswonIt(Piece[][] board) {
        if (board.length != board[0].length) return Piece.Empty;

        int size = board.length;

        ArrayList<PositionIterator> instructions = new ArrayList<PositionIterator>();
        for (int i = 0; i < board.length; i++) {
            instructions.add( new PositionIterator( 1, 0, size, new Position( 0, i ) ) );
            instructions.add( new PositionIterator( 0, 1, size, new Position( i, 0 ) ) );
        }

        instructions.add( new PositionIterator( 1, 1, size, new Position( 0, 0 ) ) );
        instructions.add( new PositionIterator( 1, -1, size, new Position( 0, size - 1 ) ) );


        for (PositionIterator iterator : instructions) {
            Piece winner = haswonIt( board, iterator );
            if (winner != Piece.Empty) return winner;
        }

        return Piece.Empty;
    }

//********используя итератор*********//

    /* проверка победителя по итератору*/
    Piece haswonIt(Piece[][] board, PositionIterator iterator) {
        Position firstPosition = iterator.next();
        Piece first = board[firstPosition.row][firstPosition.column];
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (board[position.row][position.column] != first)
                return Piece.Empty;
        }
        return first;
    }


    //если 3*3  vi i=0..8- некое значение- =0-пусто, =1-нолик , =2-крестик
    // 3^0+v0+3^1*v1+...+3^8*v8
    //
    enum Piece {
        //red =  нолик
        //blue = крестик
        Empty, Red, Blue
    }

    /* класс позиция*/
    static class Position {
        public int row, column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    /*класс итератор по позициям*/
    static class PositionIterator implements Iterator<Position> {
        private int rowIncrement, colIncrement, size;
        private Position current;

        public PositionIterator(int rowIncrement, int colIncrement, int size, Position position) {
            this.rowIncrement = rowIncrement;
            this.colIncrement = colIncrement;
            this.size = size;
            this.current = new Position( position.row - rowIncrement, position.column - colIncrement );
        }

        @Override
        public boolean hasNext() {
            return current.row + rowIncrement < size &&
                    current.column + colIncrement < size;
        }

        @Override
        public Position next() {
            current = new Position( current.row + rowIncrement, current.column + colIncrement );

            return current;
        }
    }
}
