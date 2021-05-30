import java.util.Scanner;

public class TipToe {
    private static char[][] chars = new char[3][3];

    private static boolean checkHorizontal(char sign) {
        boolean result = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i][0] == sign && chars[i][0] == chars[i][1] && chars[i][1] == chars[i][2]) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static boolean checkVertical(char sign) {

        boolean result = false;
        for (int i = 0; i < chars.length; i++) {
            if ((chars[0][i] == sign && chars[0][i] == chars[1][i] && chars[2][i] == chars[1][i])) {
                result = true;
                break;
            }
        }

        return result;
    }

    private static boolean checkFirstDiagonal(char sign) {
        boolean result = false;

        if ( chars[0][0] ==sign&&chars[0][0] == chars[1][1] && chars[1][1] == chars[2][2]) {

            result = true;
        }

        return result;
    }

    private static boolean checkSecondDiagonal(char sign) {
        boolean result = false;

        if (chars[0][2]==sign&&chars[0][2] == chars[1][1] && chars[1][1] == chars[2][0]) {

            result = true;
        }

        return result;
    }


    private static int checkSign(char sign) {
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {

                if (chars[i][j] == sign) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Deprecated
     * @return
     */
    private static boolean checkImpossible() {
        boolean result = false;

        if (Math.abs(checkSign('X') - checkSign('O')) >= 2) {
            result = true;
        } else if (checkHorizontal('X') && checkHorizontal('O')) {
            result = true;
        } else if (checkVertical('X') && checkVertical('O')) {
            result = true;
        }

        return result;


    }

    //Проверить все возможные варианты//Check all  possible variates

    public static boolean verifyEnd() {
        boolean result = false;

        if (checkHorizontal('X') || checkVertical('X') || checkFirstDiagonal('X') || checkSecondDiagonal('X')) {
            System.out.println("X wins");
            result = true;
        } else if (checkHorizontal('O') || checkVertical('O') || checkFirstDiagonal('O') || checkSecondDiagonal('O')) {
            System.out.println("O wins");
            result = true;
        } else if (checkSign(' ') == 0) {
            result = true;
            System.out.println("Draw");
        }

        return result;
    }


    /**
     * Вывести матрицу
     */
    public static void print() {

        System.out.println("---------");
        for (int i = 0; i < chars.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < chars[i].length; j++) {
                System.out.print(chars[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.println("---------");
    }

    public static boolean isCellEmpty(int first, int second) {
        boolean result = true;

        if (chars[first][second] != ' ' && chars[first][second] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            result = false;
        }

        return result;


    }


    public static int[] validateNumbers(String[] input) {
        int[] numbers = new int[2];
        numbers[0] = -1;
        numbers[1] = -1;

        for (int i = 0; i < input.length; i++) {
            try {
                numbers[i] = Integer.parseInt(input[i]) - 1;
                if (numbers[i] < 0 || numbers[i] > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    numbers[i] = -1;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                break;
            }

        }

        return numbers;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------");
        for (int i = 0; i < chars.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < chars.length; j++) {
                chars[i][j] = ' ';
                System.out.print(chars[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");

        int[] numbers;
        String[] input;
        int count = 0;
        while (true) {

            input = scanner.nextLine().split(" ");

            System.out.println("Enter the coordinates: " + (input.length > 1 ? input[0] + " " + input[1] : input[0]));

            numbers = validateNumbers(input);

            if (numbers[0] != -1 && numbers[1] != -1) {
                if (isCellEmpty(numbers[0], numbers[1])) {
                    count++;
                    if (count % 2 != 0) {
                        chars[numbers[0]][numbers[1]] = 'X';
                    } else {
                        chars[numbers[0]][numbers[1]] = 'O';
                    }

                    print();

                    if (verifyEnd()) {
                        break;
                    }

                }
            }


        }


    }
}