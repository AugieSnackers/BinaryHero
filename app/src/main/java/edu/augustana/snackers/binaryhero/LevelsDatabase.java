package edu.augustana.snackers.binaryhero;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;

/**
 * Created by Nelly on 4/11/2016.
 */
public class LevelsDatabase {
    public final static int HIGHEST_LEVEL = 6;
    public final static int RADIUS[] = { 50,50,50,50,70,70,70};
    public final static int NUM_BALLS[] = { 4,8,16,16,32,32,32};
    public final static int BINARY_LEN[] = { 4,4,4,4,5,5,5};
    public final static int THRESHOLD[] = { 3,3,5,6,7,10,15};

    public final static int COLOR[] ={Color.BLACK,Color.RED};
    public final static int TEXT_COLOR = Color.parseColor( "#a32120");
    public final static DisplayMetrics METRICS = Resources.getSystem().getDisplayMetrics();
    public final static int SCREEN_WIDTH = METRICS.widthPixels;
    public final static int SCREEN_HEIGHT = METRICS.heightPixels;

    //TODO: customize the text to explain the hint
    public final static String passwordMeaning[] = {"Binary numbers use just two symbols - 0 and 1 - to represent numeric values.",
    "Base-2 is, essentially, another way to say binary. Numbers written in base-2 often have a subscript \'2\' following them.",
    "A bit is the smallest unit of data stored in a computer. Bits have values of either 0 or 1. This is where base-2 really comes in handy!",
    "A byte is 8 bits long. A common use of bytes is to store characters, such as letters and numbers.",
    "Decimal, also known as base-10, is the number system you would ordinarily use to count. It uses digits 0 through 9.",
    "Hexadecimal is also known as base-16. Not only does it use the digits 0 to 9, after 9 comes the letter A, all the way to F! Who mixed letters with my numbers??",
    "Compile: to translate written source code into machine code executable by the computer"};
    public final static String passwords[] = {"binary", "base2","bit","byte","decimal","hexadecimal","compile"};

    public static long[] hiScores = {99999,99999,99999,99999,99999,99999};


    /**
     * calculates the score the user earned during their round
     * @param level integer that shows what level the user is on
     * @param finishTime long integer value that shows how long it took user to finish game
     */
        public static void updateScore(int level, long finishTime){
            if(hiScores[level] > finishTime){
                hiScores[level] = finishTime;
            }

        }

    /**
     * getter method that returns the score the user earned
     * @param level integer that shows level
     * @return array hiScores at the level parameter
     */
        public static long getLevelScore(int level){
            return hiScores[level];
        }

//    public static int[] getPassword() {
//    }

        }

    //not neccessary
   // public static int[] getPassword(){
       // return passwords;
    //}
    //public static int passwordsBase10[] = {378,234,721,346,498,352};

    //}

