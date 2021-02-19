package fr.abes.lnevent.security.services.impl;


import fr.abes.lnevent.entities.ContactRow;
import fr.abes.lnevent.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ContactRepository contactRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String siren) throws UsernameNotFoundException {
		ContactRow user = contactRepository.findBySiren(siren);
		if(user == null) {
			throw new UsernameNotFoundException(siren);
		}
		return fr.abes.lnevent.security.services.impl.UserDetailsImpl.build(user);
	}

}
