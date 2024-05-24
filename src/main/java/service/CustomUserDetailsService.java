package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import repository.UserRepository;

@Service
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		domain.User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User with email '" + email + "' not found.");
		}

		return User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole().toString())
				.build();
	}

}
