import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task5 {

    public static void main(String[] args) {
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println(memeSum(1222, 30277));
        System.out.println(digitsCount(1289396387328L));
        System.out.println(totalPoints(Arrays.asList("dote", "dotes", "toes", "set", "dot", "dots", "sted"), "tossed"));
        System.out.println(longestRun(new int[]{3, 5, 7, 10, 15}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println(maxPossible(8732, 91255));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println(isNew(123));
    }

    //таск 1
    public static boolean sameLetterPattern(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) return false;
            } else {
                if (map.containsValue(c2)) return false;
                map.put(c1, c2);
            }
        }
        return true;
    }

    //таск 2
    public static int memeSum(int a, int b) {
        int result = 0, multiplier = 1;
        while (a > 0 || b > 0) {
            int sum = (a % 10) + (b % 10);
            result += sum * multiplier;
            multiplier *= (sum >= 10) ? 100 : 10;
            a /= 10;
            b /= 10;
        }
        return result;
    }

    //таск 3
    public static int digitsCount(long num) {
        if (num < 10) return 1;
        return 1 + digitsCount(num / 10);
    }

    //таск 4
    public static int totalPoints(List<String> guessedWords, String secretWord) {
        int totalScore = 0;
        Map<Character, Integer> secretWordMap = new HashMap<>();
        for (char c : secretWord.toCharArray()) {
            secretWordMap.put(c, secretWordMap.getOrDefault(c, 0) + 1);
        }
        for (String word : guessedWords) {
            if (word.length() < 3 || word.length() > 6) {
                continue;
            }
            Map<Character, Integer> wordMap = new HashMap<>();
            for (char c : word.toCharArray()) {
                wordMap.put(c, wordMap.getOrDefault(c, 0) + 1);
            }
            boolean isValid = true;
            for (Map.Entry<Character, Integer> entry : wordMap.entrySet()) {
                if (secretWordMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                int score = 0;
                switch (word.length()) {
                    case 3:
                        score = 1;
                        break;
                    case 4:
                        score = 2;
                        break;
                    case 5:
                        score = 3;
                        break;
                    case 6:
                        score = 4 + 50;
                        break;
                }
                totalScore += score;
            }
        }
        return totalScore;
    }

    //таск 5
    public static int longestRun(int[] nums) {
        int maxRun = 1, currentRun = 1;
        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) == 1) {
                currentRun++;
                maxRun = Math.max(maxRun, currentRun);
            } else {
                currentRun = 1;
            }
        }
        return maxRun;
    }

    //таск 6
    public static String takeDownAverage(String[] scores) {
        int sum = 0;
        for (String score : scores) {
            sum += Integer.parseInt(score.replace("%", ""));
        }
        int count = scores.length;
        double average = (double) sum / count;
        double newAverage = average - 5;
        double requiredScore = newAverage * (count + 1) - sum;
        int result = (int) Math.round(requiredScore);
        return result + "%";
    }

    //таск 7
    public static boolean canMove(String piece, String from, String to) {
        int x1 = from.charAt(0) - 'A', y1 = from.charAt(1) - '1';
        int x2 = to.charAt(0) - 'A', y2 = to.charAt(1) - '1';
        switch (piece.toLowerCase()) {
            case "rook":
                return x1 == x2 || y1 == y2;
            case "bishop":
                return Math.abs(x1 - x2) == Math.abs(y1 - y2);
            case "queen":
                return x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2);
            case "king":
                return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) == 1;
            case "knight":
                return Math.abs(x1 - x2) * Math.abs(y1 - y2) == 2;
            case "pawn":
                return (x1 == x2 && y2 - y1 == 1) || (y1 == 1 && x1 == x2 && y2 - y1 == 2);
        }
        return false;
    }

    //таск 8
    public static int maxPossible(int num1, int num2) {
        char[] num1Arr = String.valueOf(num1).toCharArray();
        char[] num2Arr = String.valueOf(num2).toCharArray();
        Arrays.sort(num2Arr);
        int index = num2Arr.length - 1;
        for (int i = 0; i < num1Arr.length && index >= 0; i++) {
            if (num1Arr[i] < num2Arr[index]) {
                num1Arr[i] = num2Arr[index--];
            }
        }
        return Integer.parseInt(new String(num1Arr));
    }

    //таск 9
    public static String timeDifference(String cityA, String timestamp, String cityB) {
        Map<String, Double> timeZones = new HashMap<>();
        timeZones.put("Los Angeles", -8.0);
        timeZones.put("New York", -5.0);
        timeZones.put("Caracas", -4.5);
        timeZones.put("Buenos Aires", -3.0);
        timeZones.put("London", 0.0);
        timeZones.put("Rome", 1.0);
        timeZones.put("Moscow", 3.0);
        timeZones.put("Tehran", 3.5);
        timeZones.put("New Delhi", 5.5);
        timeZones.put("Beijing", 8.0);
        timeZones.put("Canberra", 10.0);
        if (!timeZones.containsKey(cityA) || !timeZones.containsKey(cityB)) {
            throw new IllegalArgumentException("Invalid city name provided.");
        }
        double offsetA = timeZones.get(cityA);
        double offsetB = timeZones.get(cityB);
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-M-d HH:mm");
        try {
            Date date = inputFormat.parse(timestamp);
            double hourDifference = offsetB - offsetA;
            long millisecondDifference = (long) (hourDifference * 3600 * 1000);
            Date newDate = new Date(date.getTime() + millisecondDifference);
            return outputFormat.format(newDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + timestamp);
        }
    }

    //таск 10
    public static boolean isNew(int num) {
        String numStr = Integer.toString(num);
        char[] digits = numStr.toCharArray();
        Arrays.sort(digits);
        String sortedNum = new String(digits);
        for (int i = 0; i < num; i++) {
            String smallerNum = Integer.toString(i);
            char[] smallerDigits = smallerNum.toCharArray();
            Arrays.sort(smallerDigits);
            String sortedSmallerNum = new String(smallerDigits);
            if (sortedNum.equals(sortedSmallerNum)) {
                return false;
            }
        }
        return true;
    }
}
