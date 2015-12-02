/**
 * Created by User on 17.11.2015.
 */
public class Service {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void addSubject(AbstractSubject[][] subjects, AbstractSubject subject) {
        int[] id = findSubject(subjects, subject.getName());
            if(id[0] > -1) {
            if (id[1] > -1 && id[1] < subjects[id[0]].length) {
                subjects[id[0]][id[1]] = subject;
            }
            else if (id[1] == subjects[id[0]].length - 1) {
                AbstractSubject[] temp = new AbstractSubject[2 * id[1]];
                for (int j = 0; j < id[1]; j++) {
                    temp[j] = subjects[id[0]][j];
                }
            }
            else {
                for (int i = 0; i < subjects[id[0]].length; i++) {
                    if (subjects[id[0]][i] == null) {
                        subjects[id[0]][i] = subject;
                        break;
                    }
                }
            }
        }
    }

    public static int deleteSubject(AbstractSubject[][] subjects, String name) {
        int flag = -1;
        int[] id = findSubject(subjects,name);
        int len = subjects[id[0]].length;
        if (id[0] > -1 && id[1]> -1) {
            if(id[1] > -1 && id[1]<len) {
                for (int i = id[1]; i < len-1; i++) {
                    subjects[id[0]][i] = subjects[id[0]][i + 1];
                }
            }
            subjects[id[0]][len-1] = null;
            flag = 1;
        }
        return flag;
    }

    public static int[] findSubject(AbstractSubject[][] dimSubject, String name) {
        int[] m = new int[2];
        m[0] = -1;
        m[1] = -1;
        if (name != null) {
            m[0] = ALPHABET.indexOf(Character.toUpperCase(name.charAt(0)));
        }
        if (dimSubject != null && m[0] > -1) {
                for (int i = 0; dimSubject[m[0]].length > i && dimSubject[m[0]][i] != null; i++) {
                    if (name == dimSubject[m[0]][i].getName()) {
                        m[1] = i;
                        break;
                    }
                }
        }
        return m;
    }
}
