package com.example.service.impl.logic_service;

import com.example.entity.Address;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.AddressService;
import com.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userLogicService")
public class UserLogicService implements UserService<User> {

    private final UserRepository userRepository;
    private final AddressService<Address> addressService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserLogicService(UserRepository userRepository, AddressService<Address> addressService,
                            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void insert(User entity) {
        if(checkIfLoginExists(entity)){
            throw new RuntimeException("Login is not unique");
        }
        setEncodedPasswordToUser(entity);
        if (entity.getAddress() != null){
            addAddressToUser(entity,entity.getAddress());
        }
        userRepository.save(entity);
    }

    @Override
    public User getById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("Cannot find this user!");
        }
        return user.get();
    }


    @Override
    public Page<User> getAll(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber,pageSize));
    }

    @Override
    @Transactional
    public void update(Long id, User entity) {

        User user = getById(id);

        if (entity.getFirstName() != null){
            user.setFirstName(entity.getFirstName());
        }
        if (entity.getLastName() != null){
            user.setLastName(entity.getLastName());
        }
        if (entity.getLogin() != null){
            checkIfLoginExists(entity);
            user.setLogin(entity.getLogin());
        }
        if (entity.getPassword() != null){
            user.setPassword(entity.getPassword());
            setEncodedPasswordToUser(user);
        }
        if (entity.getBirthday() != null){
            user.setBirthday(entity.getBirthday());
        }
        if (entity.getAddress() != null){
            addAddressToUser(user,entity.getAddress());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(getById(id));
    }

    private boolean checkIfLoginExists(User entity) {
        Optional<User> user = userRepository.findUserByLogin(entity.getLogin());
        return user.isPresent();
    }

    private void setEncodedPasswordToUser(User entity){
        entity.setPassword(passwordEncoder
                .encode(new StringBuilder(String.valueOf(entity.getPassword())))
                .toCharArray());
    }

    private void addAddressToUser(User user, Address address){
        Address addressToSave = addressService.getAddressIfItExistsOrSave(address);
        user.addUserToAddress(addressToSave);
    }
}
