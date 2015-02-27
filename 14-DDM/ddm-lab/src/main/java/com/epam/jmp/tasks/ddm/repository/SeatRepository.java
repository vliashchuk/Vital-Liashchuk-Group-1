package com.epam.jmp.tasks.ddm.repository;

import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.tasks.ddm.domain.CinemaHall;
import com.epam.jmp.tasks.ddm.domain.Seat;

public class SeatRepository extends AbstractRepository<Seat>{

	private CinemaHallRepository cinemaHallRepository;
	
	public SeatRepository(CinemaHallRepository cinemaHallRepository){
		this.cinemaHallRepository = cinemaHallRepository;
	}
	
	@Override
	public void add(Seat item) {
		CinemaHall ch;
		if(item.getCinemaHall() != null){
			ch = cinemaHallRepository.getById(item.getCinemaHall().getId());
		} else {
			throw new IllegalArgumentException("CinemaHall of Seat an not be null!");
		}
		
		Seat i = new Seat(ch, item.getRawNumber(), item.getPalceNumber());
		doAdd(i);
		item.setId(i.getId());
	}

	@Override
	public void update(Seat item) {
		throw new UnsupportedOperationException("Seat can not be changed after creation.");
	}

	public Seat getById(Long id) {
		Seat s = doGetById(id);
		Seat i = new Seat(
				cinemaHallRepository.getById(s.getCinemaHall().getId()),
				s.getRawNumber(),
				s.getPalceNumber());
		i.setId(s.getId());
		return i;
	}
	
	public List<Seat> getAllByCriteria(Criteria<Seat> criteria) {
		List<Seat> ss = doGetAllByCriteria(criteria);
		List<Seat> ret = new ArrayList<Seat>();
		for(Seat s:ss){
			Seat i = new Seat(
					cinemaHallRepository.getById(s.getCinemaHall().getId()),
					s.getRawNumber(),
					s.getPalceNumber());
			i.setId(s.getId());
			ret.add(i);
		}
		return ret;
	}

}
