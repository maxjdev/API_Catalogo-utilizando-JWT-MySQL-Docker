package br.com.catalog.repositories;

import br.com.catalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
    boolean existsByLogin(String login);
}
