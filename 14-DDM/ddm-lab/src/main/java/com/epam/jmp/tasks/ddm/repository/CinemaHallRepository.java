package com.epam.jmp.tasks.ddm.repository;

import java.util.List;

import com.epam.jmp.tasks.ddm.domain.CinemaHall;

public class CinemaHallRepository extends AbstractRepository<CinemaHall> {

	@Override
	public void add(CinemaHall item) {
		CinemaHall i = new CinemaHall();
		i.setName(item.getName());
		doAdd(i);
		item.setId(i.getId());
	}

	@Override
	public void update(CinemaHall item) {
		CinemaHall i = doGetById(item.getId());
		i.setName(item.getName());
	}

	@Override
	public CinemaHall getById(Long id) {
		CinemaHall i = new CinemaHall();
		i.setId(id);
		i.setName(doGetById(id).getName());
		return i;
	}

	@Override
	public List<CinemaHall> getAllByCriteria(Criteria<CinemaHall> criteria) {
		List<CinemaHall> ret = doGetAllByCriteria(criteria);
		for(CinemaHall i:doGetAll()){
			CinemaHall ni = new CinemaHall();
			ni.setId(i.getId());
			ni.setName(i.getName());
			ret.add(ni);
		}
		return ret;
	}

}
