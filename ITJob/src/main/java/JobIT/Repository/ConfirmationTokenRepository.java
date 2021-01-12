package JobIT.Repository;

import org.springframework.data.repository.CrudRepository;

import JobIT.Model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
