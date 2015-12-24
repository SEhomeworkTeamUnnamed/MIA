package StringComp;

/**
 * Created by DI on 2015/12/8.
 */
public class StringComp {
    public static void main(String[] args) {
        StringComp stringComp = new StringComp();
        System.out.print("hello world!\n");
        System.out.println(stringComp.cmpStr("mathematicas" , "matlab" ));
        String[] str2 = {"mathematica","mathfunction","matlab","mavericas","massinf","mgjhga","materias"};
        String[] str3 = stringComp.Simrank("math",str2);
        for(int i = 0;i< str3.length;i++) {
            System.out.println(str3[i]);
            System.out.println("\n");
        }
    }

    int myindexOf(String s1, String s2, int i) {
        char a = s1.charAt(i);
        int output = 0;
        char b = a;
        if (b >= 'A' && b <= 'Z') {
            b += 32;
        } else {
            b -= 32;
        }
        if (s2.indexOf(a) >= 0 && s2.indexOf(b) >= 0) {
            output = ((s2.indexOf(a) < s2.indexOf(b)) ? s2.indexOf(a) : s2.indexOf(b));
        } else if (s2.indexOf(a) >= 0 && s2.indexOf(b) < 0) {
            output = s2.indexOf(a);
        } else if (s2.indexOf(a) < 0 && s2.indexOf(b) >= 0) {
            output = s2.indexOf(b);
        } else if (s2.indexOf(a) < 0 && s2.indexOf(b) < 0) {
            output = -1;
        }
        return output;
    }

    float cmpStr(String str1, String str2) {
        int len1 = str1.length();
        float a;
        if (len1 <= 0) {
            a = -1;
        } else {
            if (myindexOf(str1, str2, 0) < 0) {
                a = -1;
            } else {
                a = 0;
                for (int i = 0; i < len1; i++) {
                    int temp = myindexOf(str1, str2, i);//temp为中间变量
                    if (temp < 0) {
                        break;
                    } else {
                        a += (float)(1) / (temp + 1);
                        str2 = str2.substring(temp + 1);
                    }
                }
            }
        }
        return a;
    }

    String[] Simrank(String str1, String[] str2) {
        int len = str2.length;
        float[] a = new float[len];
        int Ok_len = 0;
        for (int i = 0;i < len;i++) {
            float b = cmpStr(str1, str2[i]);
            if (b >= 1) {
                a[Ok_len] = b;
                str2[Ok_len] = str2[i];
                Ok_len++;
            }
        }
        String[] b = new String[(Ok_len)];
        for (int i = 0; i < Ok_len;i++ )
        {
            b[i] = str2[i];
        }
        return quickSort(a,b);
    }



    public static String[] quickSort(float[] a,String[] b) {
        float tmp = 0;
        String temp;
            for (int i = 0; i < b.length; i++) {
                for (int j = b.length - 1; j > i; j--) {
                    if (a[j] > a[j - 1]) {
                        tmp = a[j];
                        temp = b[j];
                        a[j] = a[j - 1];
                        b[j] = b[j - 1];
                        a[j - 1] = tmp;
                        b[j - 1] = temp;
                    }
                }
            }
        return b;
        }
    }


