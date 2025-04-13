package zuo_course_01base.No_5.ershua;

import zuo_course_01base.No_5.ershua.Node;

public class digui_bianli {
    public void xianxu(Node head){
        if(head==null) return;
        System.out.println(head.value);
        xianxu(head.left);
        xianxu(head.right);
    }

    public void zhongxu(Node head){
        if(head==null) return;
        zhongxu(head.left);
        System.out.println(head.value);
        zhongxu(head.right);
    }

    public void houxu(Node head){
        if(head==null) return;
        houxu(head.left);
        houxu(head.right);
        System.out.println(head.value);
    }

}
