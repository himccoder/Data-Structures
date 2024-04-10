/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

public class Fragment {
	private String frag_str;

	/**
	 * Creates a new Fragment based upon a String representing a sequence of
	 * nucleotides, containing only the uppercase characters G, C, A and T.
	 * 
	 * @param nucleotides
	 * @throws IllegalArgumentException if invalid characters are in the sequence of
	 *                                  nucleotides
	 */
	public Fragment(String nucleotides) throws IllegalArgumentException {
		for (int i = 0; i < nucleotides.length(); i++) {
			char c = nucleotides.charAt(i);
			if (c != 'G' && c != 'C' && c != 'A' && c != 'T') {
				throw new IllegalArgumentException();
			}
		}

		this.frag_str = nucleotides;
	}

	/**
	 * Returns the length of this fragment.
	 * 
	 * @return the length of this fragment
	 */
	public int length() {
		return this.frag_str.length();
	}

	/**
	 * Returns a String representation of this fragment, exactly as was passed to
	 * the constructor.
	 * 
	 * @return a String representation of this fragment
	 */
	@Override
	public String toString() {
		return this.frag_str;
	}

	/**
	 * Return true if and only if this fragment contains the same sequence of
	 * nucleotides as another sequence.
	 */
	public String getFrag() {
		return frag_str;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Fragment)) {
			return false;
		}

		Fragment f = (Fragment) o;
		for (int i = 0; i < f.length(); i++) {
			if (f.getFrag().charAt(i) != this.getFrag().charAt(i)) {
				return false; // If any corresponding characters differ, the sequences are not the same
			}
		}

		// Don't unconditionally return false; check that
		// the relevant instances variables in this and f
		// are semantically equal
		return true;
	}

	/**
	 * Returns the number of nucleotides of overlap between the end of this fragment
	 * and the start of another fragment, f.
	 * 
	 * The largest overlap is found, for example, CAA and AAG have an overlap of 2,
	 * not 1.
	 * 
	 * @param f the other fragment
	 * @return the number of nucleotides of overlap
	 */
	public int calculateOverlap(Fragment f) {
		int maxOverlap = 0;
		int overlapLength;
		for (int i = 1; i <= Math.min(this.length(), f.length()); i++) { // setting limit of the shorter fragment
			if (this.getFrag().substring(this.getFrag().length() - i).equals(f.getFrag().substring(0, i))) {
				overlapLength = i;
				if (overlapLength > maxOverlap) {
					maxOverlap = overlapLength;
				}
			}
		}
		return maxOverlap;

	}

	/**
	 * Returns a new fragment based upon merging this fragment with another fragment
	 * f.
	 * 
	 * This fragment will be on the left, the other fragment will be on the right;
	 * the fragments will be overlapped as much as possible during the merge.
	 * 
	 * @param f the other fragment
	 * @return a new fragment based upon merging this fragment with another fragment
	 */
	public Fragment mergedWith(Fragment f) {
		int overlapLength = calculateOverlap(f);
		String mergedSequence = "";
		if (overlapLength == 0) {
			mergedSequence = this.toString() + f.toString();
		} else {
			mergedSequence = this.toString() + f.toString().substring(overlapLength);
		}
		Fragment f_merged = new Fragment(mergedSequence);
		return f_merged;

	}
}
