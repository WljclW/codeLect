package zuo_course_01base.No_8_forceRecu;

import java.util.LinkedList;
import java.util.List;

/**
 * @author mini-zch
 * @date 2025/4/17 14:12
 */
public class AllSubsquence {
    public static void main(String[] args) {
        String str = "";
        AllSubsquence allSubsquence = new AllSubsquence();
        allSubsquence.process(str,0,new LinkedList<Character>());
    }

    public void process(String str, int index,List<Character> ls){
        if(index==str.length()){
            return;
        }
    }
}
