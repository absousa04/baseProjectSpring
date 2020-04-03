package br.com.vr.divulgacao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.vr.divulgacao.entity.Canal;

public interface CanalRepository extends PagingAndSortingRepository<Canal, String>  {
	
	List<Canal> findAll();
	
	Canal findByIdCanal(Long idCanal);

	boolean existsByIdCanal(Long idCanal);

}
