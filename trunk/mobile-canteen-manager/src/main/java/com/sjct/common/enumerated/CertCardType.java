package com.sjct.common.enumerated;

import java.util.Arrays;
import java.util.List;

import com.sjct.common.util.EnumUtils;

public enum CertCardType implements DescriptionID {
	
	ID_CARD     (0, "居民身份证"), 
	SOLDIER_NO  (1, "军人证"), 
	PASSPORT_NO (2, "护照");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private CertCardType(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public int getIndex() {
		return this.index;
	}

	public static List<CertCardType> getAll() {
		return Arrays.asList(CertCardType.class.getEnumConstants());
	}

	public static CertCardType findByIndex(Integer index) {
		return EnumUtils.getEnum(CertCardType.class, index);
	}

	public String toString() {
		return this.description;
	}
	
}
