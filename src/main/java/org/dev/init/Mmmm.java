package org.dev.init;

public class Mmmm {
    public static void main(String[] args) {

        String x = "Ola";
        String x2 =null;
        String x3 = "";
        String x4 = "Mundo";

        String[] s = checkString(x,x2,x3,x4);

        System.out.println(x);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);
        System.out.println();
        for (String ss :
                s) {
            System.out.println(ss);
        }
        }

        public static String[] checkString(String ...s){
            for (int i = 0; i < s.length; i++) {
                if(s[i]==null){
                    s[i]="novaString";
                }
            }
            return s;
        }
//        for (MenuItem sub :
//                subMenus) {
//            System.out.println(sub.getText() + " - " + sub.getId());
//        }
//
//        List<MenuItem> LISTA = subMenus.stream().toList();
//
//        for (MenuItem sub: LISTA){
//            System.out.println("Lista: "+ sub.getId());
//        }
}
