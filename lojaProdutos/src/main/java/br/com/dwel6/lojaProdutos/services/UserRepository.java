package br.com.dwel6.lojaProdutos.services;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dwel6.lojaProdutos.models.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer>{

    public AppUser findByEmail (String email);
}
