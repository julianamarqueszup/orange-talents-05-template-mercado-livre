package br.com.zupacademy.juliana.mercadolivre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;
    @Value("${security.username-query}")
    private String query;
    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        List<?> objects = manager.createQuery(query)
                .setParameter("username", username).getResultList();
        Assert.isTrue(objects.size() <= 1,
                " More than one authenticateable has the same username. "
                        + username);

        if (objects.isEmpty()) {
            throw new UsernameNotFoundException(
                    "Could not find user with this email: "
                            + username);
        }

        return userDetailsMapper.map(objects.get(0));
    }

}