import java.util.ArrayList;
import java.util.Arrays;

public class NormalForm {
    char[] Vn = {'S', 'A', 'B', 'C', 'D', 'E'};
    char[] Vt = {'a', 'b'};

    String[] Production = new String[]{"S->aB", "S->AC", "A->a", "A->ASC", "A->BC", "A->aD", "B->b", "B->bS",
            "C->ε", "C->BA", "E->aB", "D->abC"};

    public ArrayList<String> noEpsilon(){
        ArrayList<String> noEpsilon = new ArrayList<>(); // Stores new productions after eliminating epsilon

        // Iterate through each production and check for epsilon
        for (String prod : Production) {
            String[] vt = prod.split(">");
            if (!vt[1].equals("ε")) {
                noEpsilon.add(prod);

            }
        }
        return noEpsilon;
    }

    public ArrayList<String> noRenaming(ArrayList<String> Prod){
        ArrayList<String> noRenaming = new ArrayList<>(); // Stores new productions after eliminating epsilon
        for (String prod : Prod) {
            String[] vt = prod.split(">");

            if (!(vt[1].length() == 1 && vt[1].toCharArray()[0] >= 'A' && vt[1].toCharArray()[0] <= 'Z')) {
                noRenaming.add(prod);
            }
        }
        return noRenaming;
    }

    public ArrayList<String> noInaccessible(ArrayList<String> Prod){
        ArrayList<String> noInaccessible = new ArrayList<>();
        ArrayList<String> vn = new ArrayList<>();
        for(String prod: Prod){
            String[] vt = prod.split(">");
            String[] sym = vt[1].split("(?!^)");
            for (String s : sym) {
                if (s.toCharArray()[0] >= 'A' && s.toCharArray()[0] <= 'Z' && !vn.contains(s)) {
                    vn.add(s);
                }
            }

        }
        for(String prod: Prod){
            String[] vt = prod.split(">");
            String[] ac = vt[0].split("(?!^)");
            if(vn.contains(ac[0])){
                noInaccessible.add(prod);
            }
        }

        return noInaccessible;
    }

    public ArrayList<String> noNonproductive(ArrayList<String> Prod){
        ArrayList<String> noNonproductive = new ArrayList<>();

        for(String prod: Prod){
            String[] vt = prod.split(">");
            String[] sym = vt[1].split("(?!^)");
            String[] ac = vt[0].split("(?!^)");
            int up = 0;
            String upper = "";
            for(String s : sym){
                if (s.toCharArray()[0] >= 'A' && s.toCharArray()[0] <= 'Z' ) {
                    up++;
                    upper = upper + s;
                }
            }
            if(up >= 2 || (up == 1 && !upper.equals(ac[0])) || up == 0){
                noNonproductive.add(prod);
            }
        }

        return noNonproductive;
    }

    public ArrayList<String> normalForm(ArrayList<String> Prod){
        ArrayList<String> normalForm = new ArrayList<>();
        String[] sub = {"X1", "X2", "X3", "X4", "X5", "X6", "X7", "X8", "X9"};
        int i = 0;
        for(String prod: Prod) {
            String[] vt = prod.split(">");
            String[] sym = vt[1].split("(?!^)");
            String[] ac = vt[0].split("(?!^)");
            int up = 0;
            String upper = "";
            int lo = 0;
            String lower = "";
            for (String s : sym){
                if (s.toCharArray()[0] >= 'A' && s.toCharArray()[0] <= 'Z' ) {
                    up++;
                    if(up <= 2){
                        upper = upper + s;
                    }
                }
                else {
                    lo++;
                    if(lo <= 2){
                        lower = lower + s;
                    }
                }
            }
            if (sym.length == 1 || (up == 2 && sym.length == 2)){
                normalForm.add(prod);
            }
            else if(upper.length() == 2 && sym.length>2){
                normalForm.add(sub[i] + "->" + upper);
                normalForm.add(ac[0] + "->" + sub[i] + sym[sym.length-1]);
                i++;
            }
            else if(lower.length()==2 && sym.length>2){
                normalForm.add(sub[i] + "->" + lower);
                normalForm.add(ac[0] + "->" + sub[i] + sym[sym.length-1]);
                i++;
            }
            else if(lo == 1 && up == 1 && sym.length == 2){
                normalForm.add(sub[i] + "->" + lower);
                normalForm.add(ac[0] + "->" + sub[i] + upper);
                i++;
            }

        }
        return normalForm;

    }
}
