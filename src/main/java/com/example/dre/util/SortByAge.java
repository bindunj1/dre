package com.example.dre.util;

import java.util.Comparator;

import com.example.dre.dto.MatchingUser;

public class SortByAge implements Comparator<MatchingUser> {

	@Override
	public int compare(MatchingUser o1, MatchingUser o2) {

		if(o1.getAgeDifference()<o2.getAgeDifference()) {
			return -1;
		}
		else if(o1.getAgeDifference()>o2.getAgeDifference()) {
			return 1;
		}
		else if(o1.getAgeDifference() == o2.getAgeDifference()) {
			return -1;
		}

		return 0;
	}
}
