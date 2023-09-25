package br.com.softnutri.config.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.User;
import br.com.softnutri.repository.PersonPaperRepository;
import br.com.softnutri.repository.UserRepository;
import jakarta.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository usuarioRepository;
	private final PersonPaperRepository personPaperRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository usuarioRepository, PersonPaperRepository personPaperRepository) {
		this.usuarioRepository = usuarioRepository;
		this.personPaperRepository = personPaperRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
		return UserDetailsImpl.build(user, personPaperRepository.findByUserIdPerson(user.getIdPerson()));
	}

}
