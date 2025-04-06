package zuo_course_01base.No_7;

import java.util.Arrays;
import java.util.Comparator;

//会议安排
public class BestArrange {

    //每一个会议的结构————开始、结束时间
    public static class Program{
        public int start;
        public int end;
        public Program(int start,int end){
            this.start=start;
            this.end=end;
        }
    }

    //让ProgramComparator实现比较接口
    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1,Program o2){
            return o1.end-o2.end;
        }
    }

    public static int bestArrange(Program[] programs,int timepoint){    //项目列表，开始安排的时间
        Arrays.sort(programs,new ProgramComparator());
        int result = 0;
        for (int i=0;i<programs.length;i++){
            /**
             *      从0项目开始遍历，如果小于当前时间就添加。起始时timepoint指定的是从"timepoint"开始可以安排会议。
             *  每安排一个会议之后，就需要调整timepoint为最新安排项目的结束时间。
             * */
            if (timepoint<=programs[i].end){        //现在的时间早于programs[i]的开始时间
                result++;
                timepoint = programs[i].end;        //更新时间点为programs[i]的结束时间，再以此结束时间来判断其他可安排的会议
            }
        }
        return result;
    }
}
