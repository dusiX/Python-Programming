import java.util.ArrayList;
import java.util.Arrays;

class Main {

    public class Kata
    {
        public static String reverseWords(final String original)
        {
            char reversed[] = new char[original.length()];
            int count = 0, k = 0, start = 0;
            char originalchars[] = original.toCharArray();

            for(int i = 0; i < originalchars.length; i++){
                if(originalchars[i] == 32 && i + 1 != originalchars.length){
                    if(start == 0){
                        for(int j = count - 1; j >= start; j--){
                            reversed[k++] = originalchars[j];
                        }
                        if(k + 1 != originalchars.length) {
                            reversed[k++] = ' ';
                        }
                        start = i;
                        count++;
                    }else{
                        for(int j = count - 1; j > start; j--){
                            reversed[k++] = originalchars[j];
                        }
                        if(k + 1 != originalchars.length) {
                            reversed[k++] = ' ';
                        }
                        start = i;
                        count++;
                    }
                }else if(i + 1 == originalchars.length){
                    if (start == 0){
                        for(int j = count; j >= start; j--){
                            reversed[k++] = originalchars[j];
                        }
                    }else{
                        for(int j = count; j > start; j--){
                            reversed[k++] = originalchars[j];
                        }
                    }

                }else{
                    count++;
                }
            }
            String x = new String(reversed);
            return x;
        }
    }

    public static void main (String[] args) {
        //test
        //System.out.println(Kata.reverseWords("The quick brown fox jumps over the lazy dog."));  //"ehT kciuq nworb xof spmuj revo eht yzal .god"
        //System.out.println(Kata.reverseWords("apple"));                                         //"elppa"
        //System.out.println(Kata.reverseWords("a b c d"));                                       //"d c b a"
        //System.out.println(Kata.reverseWords("double  spaced  words"));                         //"elbuod  decaps  sdrow"
        System.out.println(Kata.reverseWords("     "));

    }

}