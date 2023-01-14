public class presubmit {

    public static void main(String[] args) {
        String str1 = new String("a");
        String str2 = new String("b");
        String str3 = new String("c");
        String str4 = new String("d");
        String str5 = new String("1");
        String str6 = new String("2");
        String str7 = new String("3");
        String str8 = new String("4");
        String str9 = new String("5");
        String str10 = new String("aa");
        String str11 = new String("ae");
        String str12 = new String("aeee");
        String str15 = new String("agf");
        String str16 = new String("a12");
        String str17 = new String("0a");
        String str18 = new String("a0000");
        String str19 = new String("alok");
        String str20 = new String("deeeeee");
        OpenHashSet set = new OpenHashSet(0.7f,0.1f);
        System.out.println(set.capacity());
        System.out.println(set.size());
        System.out.println();
        set.add(str1);
        set.add(str2);
        set.add(str3);
        System.out.println(set.capacity());
        System.out.println(set.size());
        System.out.println();
        set.add(str4);
        System.out.println(set.capacity());
        System.out.println(set.size());
        System.out.println();
        set.add(str5);
        set.add(str6);
        set.add(str7);
        set.add(str8);
        set.add(str9);
        set.add(str10);
        set.add(str20);
        System.out.println(set.capacity());
        System.out.println(set.size());
        System.out.println();
        set.add(str11);
        System.out.println(set.capacity());
        System.out.println(set.size());
        System.out.println();
        set.add(str1);
        System.out.println(set.capacity());
        System.out.println(set.size());
        System.out.println();
        set.add(str15);
        set.add(str16);
        set.add(str17);
        set.add(str18);
        set.add(str19);
        set.add(str12);
        System.out.println(set.capacity());
        System.out.println(set.size());
        System.out.println();
    }

}
