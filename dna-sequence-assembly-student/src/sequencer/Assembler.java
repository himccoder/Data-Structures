/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {
	ArrayList<Fragment> L;

	/**
	 * Creates a new Assembler containing a list of fragments.
	 * 
	 * The list is copied into this assembler so that the original list will not be
	 * modified by the actions of this assembler.
	 * 
	 * @param fragments
	 */
	public Assembler(List<Fragment> fragments) {
		this.L = new ArrayList<Fragment>(fragments);
	}

	/**
	 * Returns the current list of fragments this assembler contains.
	 * 
	 * @return the current list of fragments
	 */
	public List<Fragment> getFragments() {
		return this.L;
	}

	/**
	 * Attempts to perform a single assembly, returning true iff an assembly was
	 * performed.
	 * 
	 * This method chooses the best assembly possible, that is, it merges the two
	 * fragments with the largest overlap, breaking ties between merged fragments by
	 * choosing the shorter merged fragment.
	 * 
	 * Merges must have an overlap of at least 1.
	 * 
	 * After merging two fragments into a new fragment, the new fragment is inserted
	 * into the list of fragments in this assembler, and the two original fragments
	 * are removed from the list.
	 * 
	 * @return true iff an assembly was performed
	 */
	public boolean assembleOnce() {
		int maxOverlapLength = 0;
		Fragment chosenF1 = null;
		Fragment chosenF2 = null;
		for (int i = 0; i < L.size(); i++) { // = sign?
			for (int j = 0; j < L.size(); j++) { //
				if (j != i) {
					Fragment F1 = L.get(i);
					Fragment F2 = L.get(j);
					int currentOverlap = F1.calculateOverlap(F2);
					if (currentOverlap >= maxOverlapLength) {
						maxOverlapLength = currentOverlap;
						chosenF1 = F1;
						chosenF2 = F2;

					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		}
		if (maxOverlapLength < 1) {
			return false;
		} else {
			Fragment mergedFragment = chosenF1.mergedWith(chosenF2);
			L.remove(chosenF1);
			L.remove(chosenF2);
			L.add(mergedFragment);
			return true;
		}

	}

	/**
	 * Repeatedly assembles fragments until no more assembly can occur.
	 */
	public void assembleAll() {

		while (L.size() > 1) {
			assembleOnce();
		}

	}
}
