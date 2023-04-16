import java.util.*;

public class SetExamples {


    public void showExamples() {
        /* To test use HashSet, LinkedHashSet or TreeSet */
        //Set<String> set = new HashSet<>();
        Set<String> set = new LinkedHashSet<>();
        // Set<String> set = new TreeSet<>();

        set.add("one");
        set.add("two");
        set.add("three");

        System.out.println(set.size());          // 3
        System.out.println(set);      // [two, one, three] or
        // [one, two, three] for LinkedHashSet
        System.out.println(set.contains("two")); // true

        System.out.println(set.remove("one"));   // true - removes one
        System.out.println(set.add("three"));    // false - duplicates not alowed
        System.out.println(set);      // [two, three]

        System.out.println(set.isEmpty());       // false
        set.clear();
        System.out.println(set.isEmpty());       // true


        /* ============================================================== */
        /*                          Mengen Operationen                    */
        /* ============================================================== */
        // Elemente zufügen
        set.add("Z");
        set.add("X");
        set.add("Y");
        set.add("A");

        // Neue Menge: v2
        Set<String> v2 = new HashSet<>(Arrays.asList("E", "B", "C", "D", "A"));

        // Mengen Vereinigung: set U v2
        set.addAll(v2);
        System.out.println(set);  // [A, B, C, D, E, X, Y, Z]

        // Neue Menge: v3
        Set<String> v3 = new HashSet<>(Arrays.asList("B", "Z"));

        // v3 ist eine Untermenge von set
        System.out.println(set.containsAll(v3)); // true

        // Mengen Differenz: set - v3
        set.removeAll(v3);
        System.out.println(set);   // [A, C, D, E, X, Y]

        // Elemente löschen
        v2.remove("C");
        System.out.println(v2);    // [E, B, D, A]

        // Mengen Durchschnitt: set A v2
        set.retainAll(v2);
        System.out.println(set);   // [A, D, E]


        /* ======================= Iteration ========================== */
        System.out.println("*");
        for (String s : set) {
            System.out.print(s + " ");      // A D E

            // funktioniert nicht - man kann eine Collection nicht verändern während eine Schleife darüber läuft (ConcurrentModificationException)
            //set.remove(s);
        }

        System.out.println("\n-");
        String[] items = set.toArray(new String[0]);
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " "); // A D E
        }

        System.out.println("\n--");
        //verwendet man einen Iterator kann man Elemente aus der Collection entfernen während man darüber iteriert
        for (Iterator<String> it = set.iterator(); it.hasNext(); /**/) {
            String o2 = it.next();
            System.out.print(o2 + " ");       // A D E
            if (o2.equals("A"))
                it.remove();   // remove A
        }
        System.out.println("\n" + set);   // [D, E]

        set.add("A");
        set.add("B");
        set.add("C");

        if (set instanceof SortedSet<String>) {
            methodsSpecificToSortedSets((SortedSet<String>) set);
        }


        /* ================================================================ */
        /* List methods not valid for a Set: index methods and ListIterator */
        /* ================================================================ */
        //won't work on a set
//       set.add(1, "X");
//        set.addAll(2, v3);
//        set.remove(1);
//        Object o = set.set(1, "Y");
//        System.out.println(set.get(1));
//        System.out.println(set.indexOf("two"));
//
//        for (ListIterator it = set.listIterator(set.size()); it.hasPrevious(); /**/) {
//            System.out.print(it.previous() + " ");
//        }

    }


    public void methodsSpecificToSortedSets(SortedSet<String> set) {

        /* ================================================================ */
        /* Methods valid only for TreeSet (SortedSet)                       */
        /* ================================================================ */
        System.out.println(set.first()); // A
        System.out.println(set.last());  // E

        SortedSet<String> headPart = set.headSet("D");
        System.out.println(headPart);    // [A, B, C]

        SortedSet<String> tailPart = set.tailSet("B");
        System.out.println(tailPart);    // [B, C, D, E]

        SortedSet<String> subPart = set.subSet("B", "D");
        System.out.println(subPart);    // [B, C]
    }


    public static void main(String[] args) {
        new SetExamples().showExamples();


    }
}

