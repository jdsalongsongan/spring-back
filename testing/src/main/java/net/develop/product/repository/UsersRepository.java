package net.develop.product.repository;

import org.springframework.data.repository.CrudRepository;

import net.develop.product.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {

}
