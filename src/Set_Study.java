import java.util.*;

public class Set_Study {

	public void test() {

		/* Study which method is when called, depending on the type of Set */
	     Set<Name> names = new HashSet<>(); 	//does not maintain any order
		// Set<Name> names = new LinkedHashSet<>();	//maintains the order in which the elements are inserted
	   	//Set<Name> names = new TreeSet<>();  //ordered by the natural order of Name
	   // Set<Name> names = new TreeSet<>(new NameComparator());	//ordered by the logic of the Comparator
	   //Set<Name> names = new TreeSet<>(new NameComparator(false));
		 Name name1 = new Name("Anton");    System.out.println(" added: " + names.add(name1));
		 Name name2 = new Name("Beatrix");  System.out.println(" added: " + names.add(name2));
		 Name name3 = new Name("Cornelia"); System.out.println(" added: " + names.add(name3));
		 Name name4 = new Name("Boris");    System.out.println(" added: " + names.add(name4));
		 Name name5 = new Name("Barbara");  System.out.println(" added: " + names.add(name5));
		 Name name6 = new Name("Beatrix");  System.out.println(" added: " + names.add(name6));	//duplicate

		 System.out.println(names);		    // [Anton, Beatrix, Boris, Barbara, Cornelia] for HashSet                   
		                                    // [Anton, Beatrix, Cornelia, Boris, Barbara] for LinkedHashSet
		                                    // [Anton, Barbara, Beatrix, Boris, Cornelia] for TreeSet
		                                    // [Cornelia, Boris, Beatrix, Barbara, Anton] for NameComparator(false)
	}

    public static void main(String[] args) {
        new Set_Study().test();
    }

    /* ==================================================================================== */
    class Name  implements Comparable<Name> {

		private String name;

		public Name(String name) {
			this.name = name;
		}

		@Override // called first for HashSet or LinkedHashSet
		public int hashCode() {
//			int hc = name.hashCode();                    // usually used 
			int hc = name.toUpperCase().charAt(0) - 'A'; // used for study
			System.out.println("hashCode() called: " + hc + " - for " + name);
			return hc;
		}

		@Override // called for HashSet or LinkedHashSet one or more times if hash number yet exists
		public boolean equals(Object obj) {
			if (!(obj instanceof Name))  // also valid if obj = null
				return false;
			
			String oldName = ((Name)obj).name;
			boolean eq = name.equals(oldName);
			System.out.println("equals() called: " + name + " compared to " + oldName);
			return eq;
		}
		
		@Override  // called only for TreeSet (constructor without comparator)
		public int compareTo(Name oldName) {
			if (oldName == null) throw new NullPointerException();
			
			System.out.println("compareTo() called: " + name + " compared to " + oldName.name);
			return name.compareTo(oldName.name);
		}

		@Override
		public String toString() {
			return name;
		}
    }
    
    /* ==================================================================================== */
    class NameComparator implements Comparator<Name> {
    	
    	private boolean descending;
    	
    	public NameComparator() {
    		descending = true;
    	}
    	
		public NameComparator(boolean direction) {
			descending = direction;
		}
    	
    	@Override // called only for TreeSet (constructor with comparator)
        public int compare(Name n1, Name n2) {
    		System.out.println("compare called: " + n1 + " compared to " + n2.name);
            return (descending) ? n1.name.compareTo(n2.name)
            					: n2.name.compareTo(n1.name);
        }
    }
}
