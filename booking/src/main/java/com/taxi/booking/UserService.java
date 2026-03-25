package com.taxi.booking;

import com.taxi.booking.dto.RegisterUserRequest;
import com.taxi.booking.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO registerNewUser(RegisterUserRequest request) {
        if (userRepository.findByEmail(request.email).isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại: " + request.email);
        }

        User user = new User();
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setPassword(request.password);
        user.setRole(request.role);

        User saved = userRepository.save(user);
        return mapToDTO(saved);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user với id: " + id));
        return mapToDTO(user);
    }

    private UserResponseDTO mapToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.id = user.getId();
        dto.fullName = user.getFullName();
        dto.email = user.getEmail();
        dto.role = user.getRole();
        return dto;
    }
}