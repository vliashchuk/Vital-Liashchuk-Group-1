package com.epam.jmp.tasks.ddm.repository;

import com.epam.jmp.tasks.ddm.domain.Entity;

public interface Criteria<T extends Entity> {
	boolean match(T item);
}
