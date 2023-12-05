import java.util.Scanner;

interface Game {
    void play();
}

class Field {

    private state[][] field;
    private int fieldSize;

    public Field(int fieldSize) {
        field = new state[fieldSize][fieldSize];
        this.fieldSize = fieldSize;
        // initialize
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = state.EMPTY;
            }
        }
    }

    enum state {
        EMPTY,
        X,
        O
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void playO(int xAxis, int yAxis) {
        field[xAxis][yAxis] = state.O;
    }

    public void playX(int xAxis, int yAxis) {
        field[xAxis][yAxis] = state.X;
    }

    @Override
    public String toString() {
        String bar = horizontalBar();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(bar);
        for (state[] states : field) {
            stringBuilder.append("| ");
            for (state s : states) {
                char endSpace = ' ';
                stringBuilder.append(stateToString(s));
                stringBuilder.append(endSpace);
            }
            stringBuilder.append('|');
            stringBuilder.append('\n');
        }
        stringBuilder.append(bar);

        return stringBuilder.toString();
    }

    private char stateToString(state s) {
        switch (s) {
            case O:
                return 'O';
            case X:
                return 'X';
            default:
                return '_';
        }
    }

    private String horizontalBar() {
        char[] barChar = new char[2 * fieldSize + 4];
        for (int i = 0; i < 2 * fieldSize + 3; i++) {
            barChar[i] = '-';
        }
        barChar[2 * fieldSize + 3] = '\n';
        return new String(barChar);
    }
}

class MainGame implements Game {

    private Field field;
    private int gameSize;

    MainGame(int size) {
        field = new Field(size);
        gameSize = field.getFieldSize();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        char[] inputs = scanner.next().toCharArray();
        int position = 0;
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                switch (inputs[position++]) {
                    case 'O':
                        field.playO(i, j);
                        break;
                    case 'X':
                        field.playX(i, j);
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.print(field);
    }

}

public class Main {
    public static void main(String[] args) {
        Game game = new MainGame(3);
        game.play();
    }
}
