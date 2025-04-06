package zuo_course_01base.No_3;

public class HeapSort {
    /**
     * 1. 堆结构中最重要的两个操作：heapfy和heapInsert,其他操作都是由这两个操作变来的。
     * */

    public static void heapInsert(int[] arr,int index){
        /**
         *  do:某个数现在在index位置，是否需要向上移动。
         *
         * 具体思路：
         *      只要index比它父节点的数字大，就不断的将index和父节点交换(交换后就相当于index节点上移了)，每一交换后
         *      原来index节点所在的索引就变了，变成了"(index-1)/2"，因此需要更新index的值。
         * */
        int parent = (index-1)/2;
        while (arr[index] > arr[parent]) {  //这一步包含了索引为0的位置。-1/2=0，当index=0时，arr[parent]=arr[(0-1)/2]=arr[0]
            swap(arr,index,parent);
            index = parent;
            parent = (index-1)/2;
        }
    }
//  heapInsert的简洁版本
//    public static void heapInsert01(int[] arr,int index){
//        while(arr[index]>arr[(index-1)/2]){
//            swap(arr,index,(index-1)/2);
//            index = (index-1)/2;
//        }
//    }


    public static void heapfy(int[] arr,int index,int heapSize){
        /**
         * do:某个数在index位置，是否需要向下移动.
         *
         * 当前位置：index；堆中元素数量：heapSize
         *
         *      是否需要向下移动就是问它的孩子节点有比它大的吗？？
         * */
        int left = 2*index +1;          //当前位置的左孩子节点。有左孩子不一定有右孩子，有右孩子一定有左孩子
        while(left<heapSize){           //说明有孩子，则还可能有右孩子。
            /*
            * left+1<heapSize && arr[left]<arr[left+1]是一个技术语句。
            *       首先判断left+1是否越界了，如果越界了后面的的arr[left]<arr[left+1]就不会执行因此不会异常终止；
            * */
            int largest = left+1<heapSize && arr[left]<arr[left+1] ? left+1:left;       //返回左右孩子最大的那个节点的索引
            largest = arr[largest]>arr[index]?largest:index;            //返回index节点和左右孩子最大的相比最大的，对应的索引
            if(largest==index){                                 //如果index节点比左右两个孩子节点都大，就不需要移动了。break;
                break;
            }
            swap(arr,largest,index);        //如果没有执行if说明，index比左右孩子的某个小，需要交换
            index = largest;                //交换后需要更新index值
            left = 2*index+1;               //根据更新后的index值更新left的值。
        }
    }

//    public static void heapfy01(int[] arr,int index,int heapSize){
//        int left = index*2+1;
//        while(left<heapSize){
//            int largest = left+1<heapSize && arr[left+1]>arr[left]?left+1:left;
//            if(arr[index]<arr[largest]){
//                swap(arr,index,largest);
//                index = largest;
//                left = 2*index+1;
//            }
//            else
//                break;;
//        }
//    }

    public static void heapSort(int[] arr){
        /**
         * 堆排序：等价于一个一个的给数，然后构建最大堆。也就是说插入一个一个的数，不断调整先前的最大堆。。
         *          最大堆建立完成后——每一次让index=0的位置出去，然后把堆的最后一个元素放到index=0的位置，然后调整堆为最大堆。
         *      重复上一步骤直到堆中没有元素，则出去的顺序就是由大到小的排序。
         * */
        //步骤1：特殊情况的考虑
        if(arr == null || arr.length==2)            //如果只有0、1个元素，直接返回就行
            return;
        //步骤2：构建最大根堆
//        for(int i=0;i<arr.length;i++){      //O(N)
//            heapInsert(arr,i);           //O(NlogN),将每一个索引的元素依次放在堆的index=0的位置，并调整最大堆。
//        }
        //下面是上面for循环的快速版本
        for(int i =arr.length-1;i>=0;i--){
            heapfy(arr,i,arr.length);
        }
        //步骤3：上面已经建立好了最大根堆，这里就是依次出堆，称为有序数列。
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while(heapSize>0){         // O(N)
            heapfy(arr,0,heapSize);             //O(logN),次数取决于堆的高度————logN。
            swap(arr,0,--heapSize);         //O(1)
        }
    }


    public static void swap(int[] arr,int i,int j){
        /**
         * 交换两元素的值
         * */
        int tmp = arr[i];
        arr[i]  = arr[j];
        arr[j] = tmp;
    }
}
