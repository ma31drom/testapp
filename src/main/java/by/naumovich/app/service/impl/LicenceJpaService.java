package by.naumovich.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.naumovich.app.dao.jpa.LicenceRepo;
import by.naumovich.app.dao.model.DriverLicence;
import by.naumovich.app.dao.model.User;
import by.naumovich.app.dao.model.UserRole;
import by.naumovich.app.dao.validation.RepoHolder;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.service.LicenceService;

@Service
public class LicenceJpaService extends AbstractCrudService<DriverLicence> implements LicenceService {

	@Autowired
	private LicenceRepo repo;

	@Override
	public DriverLicence getForUser(Integer id) {
		return repo.findByUserIdAndId(CredsServiceImpl.getUserId(), id);
	}

	@Override
	public DriverLicence getAllForUser() {
		List<DriverLicence> findByUserId = repo.findByUserId(CredsServiceImpl.getUserId());
		return findByUserId.isEmpty() ? null : findByUserId.get(0);
	}

	@Override
	@Transactional
	public void deleteForUser(List<Integer> ids) {
		List<DriverLicence> findByUserId = repo.findByUserId(CredsServiceImpl.getUserId());
		Set<Integer> collect = findByUserId.stream().map(DriverLicence::getId).collect(Collectors.toSet());

		List<Integer> failed = new ArrayList<Integer>();
		ids.forEach(id -> {
			if (!collect.contains(id)) {
				failed.add(id);
			}
		});
		if (failed.size() > 0) {
			String t = failed.stream().map(String::valueOf).collect(Collectors.joining(", "));
			throw new ValidationException("No licence ids for user: " + t);
		}
		repo.deleteAll(findByUserId.stream().filter(dl -> ids.contains(dl.getId())).collect(Collectors.toList()));
	}

	@Override
	public List<DriverLicence> getAll() {
		User one = RepoHolder.userRepo().getOne(CredsServiceImpl.getUserId());
		if (one.getRole() != UserRole.admin)
			throw new Unauthorized();
		return super.getAll();
	}

}
