package zuo_course_02baseTiSheng.No_2;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind_FuXi {
    public static class Element<V>{
        public V value;
        public Element(V value){
            this.value = value;
        }
    }



    public static class UnionFindSet<V>{
        public HashMap<V,Element<V>> elementHashMap;
        public HashMap<Element<V>,Element<V>> fatherMap;
        public HashMap<Element<V>,Integer> sizeMap;
        public UnionFindSet(List<V> list){
            elementHashMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v:list){
                Element<V> element = new Element<>(v);
                elementHashMap.put(v,element);
                fatherMap.put(element,element);
                sizeMap.put(element,1);
            }
        }


        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> stack = new Stack<>();
            while(element!=fatherMap.get(element)){
                element = fatherMap.get(element);
                stack.push(element);
            }
            while(!stack.isEmpty()){
                fatherMap.put(stack.pop(),element);
            }
            return element;
        }


        public boolean isSameSet(V a,V b){
            if (elementHashMap.containsKey(a) && elementHashMap.containsKey(b)){
                return findHead(elementHashMap.get(a)) == findHead(elementHashMap.get(b));
            }
            return false;
        }

        public void union(V a,V b){
            if (elementHashMap.containsKey(a) && elementHashMap.containsKey(b)){
                if (findHead(elementHashMap.get(a)) != findHead(elementHashMap.get(b))){
                    Element<V> aF = findHead(elementHashMap.get(a));
                    Element<V> bF = findHead(elementHashMap.get(b));
                    Element<V> big = sizeMap.get(aF)>sizeMap.get(bF)?aF:bF;
                    Element<V> small = big==aF?bF:aF;
                    fatherMap.put(small,big);
                    sizeMap.put(big,sizeMap.get(aF)+sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }
    }



}
